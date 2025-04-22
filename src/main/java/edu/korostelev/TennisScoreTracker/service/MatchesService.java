package edu.korostelev.TennisScoreTracker.service;

import edu.korostelev.TennisScoreTracker.model.Match;
import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.repository.MatchesRepository;
import edu.korostelev.TennisScoreTracker.repository.PlayersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchesService {
    private final MatchesRepository matchesRepository;
    private final PlayersRepository playersRepository;

    public MatchesService(MatchesRepository matchesRepository, PlayersRepository playersRepository) {
        this.matchesRepository = matchesRepository;
        this.playersRepository = playersRepository;
    }

    public Optional<Match> save(Integer firstPlayerId, Integer secondPlayerId, Integer winnerId) {
        Optional<Player> firstPlayer = playersRepository.findById(firstPlayerId);
        Optional<Player> secondPlayer = playersRepository.findById(secondPlayerId);
        Optional<Player> winner = playersRepository.findById(winnerId);

        Optional<Match> savedMatch = Optional.empty();
        if (firstPlayer.isPresent() && secondPlayer.isPresent() && winner.isPresent()) {
            savedMatch = Optional.of(matchesRepository.save(new Match(firstPlayer.get(), secondPlayer.get(), winner.get())));
        }

        return savedMatch;
    }
}
