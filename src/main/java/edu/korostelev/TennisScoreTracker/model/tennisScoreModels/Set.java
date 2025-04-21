package edu.korostelev.TennisScoreTracker.model.tennisScoreModels;

import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.model.tennisScoreModels.interfaces.Winnable;

import java.util.Optional;

public class Set implements Winnable {
    private final Player firstPlayer;
    private final Player secondPlayer;
    private int firstPlayerScore;
    private int secondPlayerScore;
    private Game currentGame;

    public Set(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        firstPlayerScore = 0;
        secondPlayerScore = 0;
        startNewGame();
    }

    private void startNewGame() {
        currentGame = new Game(firstPlayer, secondPlayer);
    }

    @Override
    public Optional<Player> winnedBy(Player winner) {
        Optional<Player> gameWinner = currentGame.winnedBy(winner);
        Optional<Player> setWinner = Optional.empty();

        if (gameWinner.isPresent()) {
            score(gameWinner.get());

            setWinner = checkWinningConditions();

            startNewGame();
        }

        return setWinner;
    }

    private void score(Player winner) {
        if (winner.equals(firstPlayer)) {
            firstPlayerScore++;
        } else if (winner.equals(secondPlayer)) {
            secondPlayerScore++;
        }
    }

    private Optional<Player> checkWinningConditions() {
        Optional<Player> winner = Optional.empty();
        int difference = Math.abs(firstPlayerScore - secondPlayerScore);

        // Match is won only when there's a difference between players scores => check for that condition first
        if (firstPlayerScore != secondPlayerScore) {
            // Then we check if player's scores are higher than 6, as it is also one of the win conditions
            if (firstPlayerScore >= 6 || secondPlayerScore >= 6) {
                // Player wins set only if difference between score is greater than 2
                if (difference >= 2){
                    if (firstPlayerScore > secondPlayerScore) {
                        winner = Optional.of(firstPlayer);
                    } else if (secondPlayerScore > firstPlayerScore) {
                        winner = Optional.of(secondPlayer);
                    }
                }
            }
        }

        return winner;
    }

    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }

    public String getFirstPlayerGameScore() {
        return currentGame.getFirstPlayerScore().toString();
    }

    public String getSecondPlayerGameScore() {
        return currentGame.getSecondPlayerScore().toString();
    }

    public Game getCurrentGame() {
        return currentGame;
    }
}
