package edu.korostelev.TennisScoreTracker.service;

import edu.korostelev.TennisScoreTracker.model.CurrentMatch;
import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.repository.PlayersRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class MatchesService {
    private final HashMap<Integer, CurrentMatch> matches = new HashMap<>();

    private final PlayersService playersService;

    public MatchesService(PlayersService playersService) {
        this.playersService = playersService;
    }

    //TODO implement generation of unique id for matches
    public Integer createMatch(String firstPlayerName, String secondPlayerName) {
        Optional<Player> firstPlayer = playersService.findByName(firstPlayerName);
        Optional<Player> secondPlayer = playersService.findByName(secondPlayerName);

        if (firstPlayer.isEmpty()) {
            firstPlayer = playersService.savePlayer(firstPlayerName);
        }
        if (secondPlayer.isEmpty()) {
            secondPlayer = playersService.savePlayer(secondPlayerName);
        }

        int matchId = 0;

        matches.put(matchId,
                new CurrentMatch(firstPlayer.get().getId(), secondPlayer.get().getId())
        );

        return matchId;
    }

    public CurrentMatch getCurrentMatch(Integer currentMatchId) {
        return matches.get(currentMatchId);
    }
}
