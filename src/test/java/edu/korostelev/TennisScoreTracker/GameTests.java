package edu.korostelev.TennisScoreTracker;

import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.model.tennisScoreModels.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTests {
    Game testGame;
    Player firstPlayer;
    Player secondPlayer;

    final int firstPlayerId = 1;
    final int secondPlayerId = 2;

    public void init() {
        firstPlayer = new Player("John");
        secondPlayer = new Player("Winkie");
        firstPlayer.setId(firstPlayerId);
        secondPlayer.setId(secondPlayerId);
        testGame = new Game(firstPlayer, secondPlayer);
    }

    @Test
    public void normalGameFirstPlayerWins() {
        init();
        testGame.winnedBy(firstPlayer);
        testGame.winnedBy(firstPlayer);
        testGame.winnedBy(firstPlayer);
        Assertions.assertEquals(firstPlayer, testGame.winnedBy(firstPlayer).get());
    }

    @Test
    public void tieGameFirstPlayerWins() {
        init();
        testGame.winnedBy(firstPlayer);
        testGame.winnedBy(firstPlayer);
        testGame.winnedBy(firstPlayer);

        testGame.winnedBy(secondPlayer);
        testGame.winnedBy(secondPlayer);
        testGame.winnedBy(secondPlayer);

        testGame.winnedBy(firstPlayer);
        Assertions.assertEquals(firstPlayer, testGame.winnedBy(firstPlayer).get());
    }

    @Test
    public void prolongedTieGameFirstPlayerWins() {
        init();

        // first player get up to 40
        testGame.winnedBy(firstPlayer);
        testGame.winnedBy(firstPlayer);
        testGame.winnedBy(firstPlayer);

        // second player get up to 40
        testGame.winnedBy(secondPlayer); // 15
        testGame.winnedBy(secondPlayer);
        testGame.winnedBy(secondPlayer);

        // first and second player get tie after taking advantage
        testGame.winnedBy(firstPlayer);
        testGame.winnedBy(secondPlayer);

        // first player gets final advantage
        testGame.winnedBy(firstPlayer);
        Assertions.assertEquals(firstPlayer, testGame.winnedBy(firstPlayer).get());
    }

    @Test
    public void normalGameSecondPlayerWins() {
        init();
        testGame.winnedBy(secondPlayer);
        testGame.winnedBy(secondPlayer);
        testGame.winnedBy(secondPlayer);
        Assertions.assertEquals(secondPlayer, testGame.winnedBy(secondPlayer).get());
    }
}
