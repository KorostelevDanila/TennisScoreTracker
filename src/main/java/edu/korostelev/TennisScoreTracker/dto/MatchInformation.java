package edu.korostelev.TennisScoreTracker.dto;

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
