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

    public Optional<Player> addScore(String currentMatchId, int winnerId) {
        CurrentMatch currentMatch = matches.get(UUID.fromString(currentMatchId));

        Optional<Player> winner = playersService.findById(winnerId);
        Optional<Player> matchWinner = Optional.empty();

        if (winner.isPresent()) {
            matchWinner = currentMatch.winnedBy(winner.get());
        }

        return matchWinner;
    }

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

    public CurrentMatch getCurrentMatch(String currentMatchId) {
        return matches.get(UUID.fromString(currentMatchId));
    }
}
