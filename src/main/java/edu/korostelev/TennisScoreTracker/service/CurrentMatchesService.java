package edu.korostelev.TennisScoreTracker.service;

import edu.korostelev.TennisScoreTracker.model.CurrentMatch;
import edu.korostelev.TennisScoreTracker.model.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public class CurrentMatchesService {
    private final HashMap<UUID, CurrentMatch> matches = new HashMap<>();

    private final PlayersService playersService;

    public CurrentMatchesService(PlayersService playersService) {
        this.playersService = playersService;
    }

    //TODO implement generation of unique id for matches
    public UUID createMatch(String firstPlayerName, String secondPlayerName) {
        Optional<Player> firstPlayer = playersService.findByName(firstPlayerName);
        Optional<Player> secondPlayer = playersService.findByName(secondPlayerName);

        if (firstPlayer.isEmpty()) {
            firstPlayer = playersService.savePlayer(firstPlayerName);
        }
        if (secondPlayer.isEmpty()) {
            secondPlayer = playersService.savePlayer(secondPlayerName);
        }

        UUID matchId;

        do {
            matchId = UUID.randomUUID();
        } while (matches.containsKey(matchId));

        matches.put(matchId,
                new CurrentMatch(firstPlayer.get(), secondPlayer.get())
        );

        return matchId;
    }

    public CurrentMatch getCurrentMatch(UUID currentMatchId) {
        return matches.get(currentMatchId);
    }
}
