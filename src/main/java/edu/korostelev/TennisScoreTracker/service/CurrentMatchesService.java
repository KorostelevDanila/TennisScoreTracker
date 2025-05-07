package edu.korostelev.TennisScoreTracker.service;

import edu.korostelev.TennisScoreTracker.dto.MatchInformation;
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
    private final MatchesService matchesService;
    private final MatchInformationService matchInformationService;

    public CurrentMatchesService(PlayersService playersService, MatchesService matchesService, MatchInformationService matchInformationService) {
        this.playersService = playersService;
        this.matchesService = matchesService;
        this.matchInformationService = matchInformationService;
    }

    public Optional<MatchInformation> addScore(String currentMatchId, int winnerId) {
        CurrentMatch currentMatch = matches.get(UUID.fromString(currentMatchId));

        Optional<Player> winner = playersService.findById(winnerId);
        Optional<Player> matchWinner;

        Optional<MatchInformation> matchInformation = Optional.empty();

        if (winner.isPresent()) {
            matchWinner = currentMatch.winnedBy(winner.get());
            if (matchWinner.isPresent()) {
                matchesService.save(currentMatch.getFirstPlayer().getId(), currentMatch.getSecondPlayer().getId(), matchWinner.get().getId());
                matchInformation = Optional.of(matchInformationService.getMatchInformation(currentMatch, matchWinner.get()));
                matches.remove(UUID.fromString(currentMatchId));
            } else {
                matchInformation = Optional.of(matchInformationService.getMatchInformation(currentMatch));
            }
        }

        return matchInformation;
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
