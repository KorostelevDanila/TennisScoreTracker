package edu.korostelev.TennisScoreTracker.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class CurrentMatch {
    private Integer firstPlayerId;
    private Integer secondPlayerId;
    private HashMap<Integer, Integer> sets;
    private HashMap<Integer, Integer> games;
    private HashMap<Integer, Integer> points;

    public CurrentMatch(int firstPlayerId, int secondPlayerId) {
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
        sets = new HashMap<>();
        sets.put(firstPlayerId, 0);
        sets.put(secondPlayerId, 0);
        games = new HashMap<>();
        games.put(firstPlayerId, 0);
        games.put(secondPlayerId, 0);
        points = new HashMap<>();
        points.put(firstPlayerId, 0);
        points.put(secondPlayerId, 0);
    }

    public Integer getFirstPlayerId() {
        return firstPlayerId;
    }

    public void setFirstPlayerId(Integer firstPlayerId) {
        this.firstPlayerId = firstPlayerId;
    }

    public Integer getSecondPlayerId() {
        return secondPlayerId;
    }

    public void setSecondPlayerId(Integer secondPlayerId) {
        this.secondPlayerId = secondPlayerId;
    }

    public HashMap<Integer, Integer> getSets() {
        return sets;
    }

    public void setSets(HashMap<Integer, Integer> sets) {
        this.sets = sets;
    }

    public HashMap<Integer, Integer> getGames() {
        return games;
    }

    public void setGames(HashMap<Integer, Integer> games) {
        this.games = games;
    }

    public HashMap<Integer, Integer> getPoints() {
        return points;
    }

    public void setPoints(HashMap<Integer, Integer> points) {
        this.points = points;
    }
}
