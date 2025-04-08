package edu.korostelev.TennisScoreTracker.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Data
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

}
