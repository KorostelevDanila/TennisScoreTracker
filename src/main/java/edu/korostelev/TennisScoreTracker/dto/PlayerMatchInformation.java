package edu.korostelev.TennisScoreTracker.dto;

public class PlayerMatchInformation {
    private Integer id;
    private String name;
    private String matchScore;
    private String setScore;
    private String gameScore;

    public PlayerMatchInformation(Integer id, String name, String matchScore, String setScore, String gameScore) {
        this.id = id;
        this.name = name;
        this.matchScore = matchScore;
        this.setScore = setScore;
        this.gameScore = gameScore;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public String getSetScore() {
        return setScore;
    }

    public String getGameScore() {
        return gameScore;
    }
}
