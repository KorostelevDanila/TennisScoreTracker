package edu.korostelev.TennisScoreTracker.service;

import edu.korostelev.TennisScoreTracker.dto.MatchInformation;
import edu.korostelev.TennisScoreTracker.dto.PlayerMatchInformation;
import edu.korostelev.TennisScoreTracker.model.CurrentMatch;
import edu.korostelev.TennisScoreTracker.model.Player;
import org.springframework.stereotype.Service;

@Service
public class MatchInformationService {
    public MatchInformation getMatchInformation(CurrentMatch match) {
        PlayerMatchInformation firstPlayer = match.getPlayerInformation(match.getFirstPlayer());
        PlayerMatchInformation secondPlayer = match.getPlayerInformation(match.getSecondPlayer());
        return new MatchInformation(firstPlayer, secondPlayer);
    }

    public MatchInformation getMatchInformation(CurrentMatch match, Player winner) {
        MatchInformation matchInformation = getMatchInformation(match);
        matchInformation.setWinner(winner.getName());
        return matchInformation;
    }
}
