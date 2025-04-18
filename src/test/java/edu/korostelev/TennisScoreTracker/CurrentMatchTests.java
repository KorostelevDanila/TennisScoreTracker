package edu.korostelev.TennisScoreTracker;

import edu.korostelev.TennisScoreTracker.model.CurrentMatch;
import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.model.tennisScoreModels.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class CurrentMatchTests {
    private CurrentMatch currentMatch;
    Player firstPlayer;
    Player secondPlayer;

    final int firstPlayerId = 1;
    final int secondPlayerId = 2;

    public void init() {
        firstPlayer = new Player("John");
        secondPlayer = new Player("Winkie");
        firstPlayer.setId(firstPlayerId);
        secondPlayer.setId(secondPlayerId);
        currentMatch = new CurrentMatch(firstPlayer, secondPlayer);
    }

    @Test
    public void firstPlayerWins() {
        init();

        playOneSet(firstPlayer);
        Assertions.assertEquals(firstPlayer, playOneSet(firstPlayer).get());
    }

    @Test
    public void secondPlayerWins() {
        init();

        playOneSet(secondPlayer);
        Assertions.assertEquals(secondPlayer, playOneSet(secondPlayer).get());
    }

    @Test
    public void firstPlayerWinsAfterTie() {
        init();

        //playOneSet(firstPlayer);
        playOneSet(secondPlayer);
        Assertions.assertEquals(secondPlayer, playOneSet(secondPlayer).get());
    }

    @Test
    public void secondPlayerWinsAfterTie() {
        init();

        //playOneSet(firstPlayer);
        playOneSet(secondPlayer);
        Assertions.assertEquals(secondPlayer, playOneSet(secondPlayer).get());
    }

    private Optional<Player> playOneGame(Player winner) {
        currentMatch.winnedBy(winner);
        currentMatch.winnedBy(winner);
        currentMatch.winnedBy(winner);
        return currentMatch.winnedBy(winner);
    }

    private Optional<Player> playOneSet(Player winner) {
        playOneGame(winner);
        playOneGame(winner);
        playOneGame(winner);
        playOneGame(winner);
        playOneGame(winner);
        return playOneGame(winner);
    }
}
