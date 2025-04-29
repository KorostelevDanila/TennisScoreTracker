package edu.korostelev.TennisScoreTracker.service;

import edu.korostelev.TennisScoreTracker.model.Match;
import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.repository.MatchesRepository;
import edu.korostelev.TennisScoreTracker.repository.PlayersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Page<Match> getAllMatches(String page, String playerName) {
        Page<Match> allMatches = null;
        if (playerName.isEmpty()) {
            allMatches = matchesRepository.findAll(PageRequest.of((Integer.parseInt(page) - 1), 5));
        } else {
            Optional<Player> player = playersRepository.findByName(playerName);
            if (player.isPresent()) {
                allMatches = matchesRepository.findMatchesByFirstPlayerOrSecondPlayer(player.get(), player.get(), PageRequest.of((Integer.parseInt(page) - 1), 5));
            } else {
                allMatches = Page.empty();
            }
        }

        return allMatches;
    }
}
