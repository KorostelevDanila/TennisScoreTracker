package edu.korostelev.TennisScoreTracker.dto;

import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.util.MATCH_INFORMATION_KEYS;

import java.util.HashMap;

public class MatchInformation {
    private PlayerMatchInformation firstPlayer;
    private PlayerMatchInformation secondPlayer;

    public MatchInformation(PlayerMatchInformation firstPlayer, PlayerMatchInformation secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public PlayerMatchInformation getFirstPlayer() {
        return firstPlayer;
    }

    public PlayerMatchInformation getSecondPlayer() {
        return secondPlayer;
    }
}
