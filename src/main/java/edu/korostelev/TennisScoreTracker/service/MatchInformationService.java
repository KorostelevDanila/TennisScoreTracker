package edu.korostelev.TennisScoreTracker.service;

import edu.korostelev.TennisScoreTracker.dto.MatchInformation;
import edu.korostelev.TennisScoreTracker.dto.PlayerMatchInformation;
import edu.korostelev.TennisScoreTracker.model.CurrentMatch;
import edu.korostelev.TennisScoreTracker.util.MATCH_INFORMATION_KEYS;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class MatchInformationService {
    private final CurrentMatchesService currentMatchesService;

    public MatchInformationService(CurrentMatchesService currentMatchesService) {
        this.currentMatchesService = currentMatchesService;
    }

    public MatchInformation getMatchInformation(CurrentMatch match) {
        PlayerMatchInformation firstPlayer = match.getPlayerInformation(match.getFirstPlayer());
        PlayerMatchInformation secondPlayer = match.getPlayerInformation(match.getSecondPlayer());
        return new MatchInformation(firstPlayer, secondPlayer);
    }

    public MatchInformation getMatchInformation(String uuid) {
        CurrentMatch currentMatch = currentMatchesService.getCurrentMatch(uuid);
        return getMatchInformation(currentMatch);
    }
}
