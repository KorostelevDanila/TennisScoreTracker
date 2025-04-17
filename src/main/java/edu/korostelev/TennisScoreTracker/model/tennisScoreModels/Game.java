package edu.korostelev.TennisScoreTracker.model.tennisScoreModels;

import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.model.tennisScoreModels.enums.SCORE_VALUES;
import edu.korostelev.TennisScoreTracker.model.tennisScoreModels.interfaces.Winnable;

import java.util.Optional;

public class Game implements Winnable {
    private final Player firstPlayer;
    private final Player secondPlayer;

    private SCORE_VALUES firstPlayerScore;
    private SCORE_VALUES secondPlayerScore;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        firstPlayerScore = SCORE_VALUES.LOVE;
        secondPlayerScore = SCORE_VALUES.LOVE;
    }

    @Override
    public Optional<Player> winnedBy(Player winner) {
        checkTieSituations();

        addScoreToWinner(winner);

        checkDeuceSituation();

        return getWinnerIfPresent();
    }

    private void checkTieSituations() {
        if (firstPlayerScore == secondPlayerScore) {
            SCORE_VALUES sameScoreValue = firstPlayerScore;
            if (sameScoreValue == SCORE_VALUES.ADVANTAGE) {
                firstPlayerScore = SCORE_VALUES.DEUCE;
                secondPlayerScore = SCORE_VALUES.DEUCE;
            }
        }
    }

    private void addScoreToWinner(Player winner) {
        if (winner.getId().equals(firstPlayer.getId())) {
            firstPlayerScore = firstPlayerScore.next();
        } else if (winner.getId().equals(secondPlayer.getId())) {
            secondPlayerScore = secondPlayerScore.next();
        }
    }

    private void checkDeuceSituation() {
        if (firstPlayerScore == SCORE_VALUES.FORTY && secondPlayerScore == SCORE_VALUES.FORTY) {
            firstPlayerScore = SCORE_VALUES.DEUCE;
            secondPlayerScore = SCORE_VALUES.DEUCE;
        }
    }

    private Optional<Player> getWinnerIfPresent() {
        if (firstPlayerScore == SCORE_VALUES.WIN) {
            return Optional.of(firstPlayer);
        } else if (secondPlayerScore == SCORE_VALUES.WIN) {
            return Optional.of(secondPlayer);
        } else {
            return Optional.empty();
        }
    }
}

