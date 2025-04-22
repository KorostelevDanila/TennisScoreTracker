package edu.korostelev.TennisScoreTracker.dto;

public class MatchInformation {
    private PlayerMatchInformation firstPlayer;
    private PlayerMatchInformation secondPlayer;
    private String winnerName;

    public MatchInformation(PlayerMatchInformation firstPlayer, PlayerMatchInformation secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public MatchInformation(PlayerMatchInformation firstPlayer, PlayerMatchInformation secondPlayer, String winnerName) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.winnerName = winnerName;
    }

    public PlayerMatchInformation getFirstPlayer() {
        return firstPlayer;
    }

    public PlayerMatchInformation getSecondPlayer() {
        return secondPlayer;
    }

    public void setWinner(String name) {
        this.winnerName = name;
    }

    public String getWinnerName() {
        return winnerName;
    }
}
