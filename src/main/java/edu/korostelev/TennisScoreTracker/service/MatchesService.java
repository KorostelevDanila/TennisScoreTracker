package edu.korostelev.TennisScoreTracker.service;

import edu.korostelev.TennisScoreTracker.model.CurrentMatch;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MatchesService {
    private final HashMap<Integer, CurrentMatch> matches = new HashMap<>();

    //TODO implement searching and creating players in database
    //TODO implement generation of unique id for matches
    public Integer createMatch(String firstPlayerName, String secondPlayerName) {
        int firstPlayerId = 1;
        int secondPlayerId = 2;

        int matchId = 0;

        matches.put(matchId,
                new CurrentMatch(firstPlayerId, secondPlayerId)
        );

        return matchId;
    }

    public CurrentMatch getCurrentMatch(Integer currentMatchId) {
        return matches.get(currentMatchId);
    }
}
