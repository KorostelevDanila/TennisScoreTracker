package edu.korostelev.TennisScoreTracker.model;

import edu.korostelev.TennisScoreTracker.model.tennisScoreModels.Set;
import edu.korostelev.TennisScoreTracker.model.tennisScoreModels.interfaces.Winnable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Optional;

public class CurrentMatch implements Winnable {
    private final Player firstPlayer;
    private final Player secondPlayer;
    private Set currentSet;
    private int firstPlayerScore;
    private int secondPlayerScore;
    private final int SETS_TO_WIN = 2;

    public CurrentMatch(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        firstPlayerScore = 0;
        secondPlayerScore = 0;
        startSet();
    }

    private void startSet() {
        currentSet = new Set(firstPlayer, secondPlayer);
    }

    @Override
    public Optional<Player> winnedBy(Player winner) {
        Optional<Player> matchWinner = Optional.empty();
        Optional<Player> setWinner = currentSet.winnedBy(winner);

        if (setWinner.isPresent()) {
            if (setWinner.get().equals(firstPlayer)) {
                firstPlayerScore++;
                if (firstPlayerScore == SETS_TO_WIN) {
                    return Optional.of(firstPlayer);
                }
            } else if (setWinner.get().equals(secondPlayer)) {
                secondPlayerScore++;
                if (secondPlayerScore == SETS_TO_WIN) {
                    return Optional.of(secondPlayer);
                }
            }

            startSet();
        }

        return Optional.empty();
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }
}
