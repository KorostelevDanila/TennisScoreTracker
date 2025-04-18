package edu.korostelev.TennisScoreTracker;

import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.model.tennisScoreModels.Game;
import edu.korostelev.TennisScoreTracker.model.tennisScoreModels.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

public class SetTests {
    private Set set;
    private Player firstPlayer;
    private Player secondPlayer;

    private final int firstPlayerId = 1;
    private final int secondPlayerId = 2;

    private void init() {
        firstPlayer = new Player("John");
        secondPlayer = new Player("Winkie");
        firstPlayer.setId(firstPlayerId);
        secondPlayer.setId(secondPlayerId);
        set = new Set(firstPlayer, secondPlayer);
    }

    @Test
    public void normalSet() {
        init();
        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);
        Assertions.assertEquals(firstPlayer, playOneGame(set, firstPlayer).get());
    }

    @Test
    public void tieSet() {
        init();

        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);

        playOneGame(set, secondPlayer);
        playOneGame(set, secondPlayer);
        playOneGame(set, secondPlayer);
        playOneGame(set, secondPlayer);
        playOneGame(set, secondPlayer);

        playOneGame(set, firstPlayer);
        Assertions.assertEquals(firstPlayer, playOneGame(set, firstPlayer).get());
    }

    @Test
    public void prolongedTieWinTest() {
        init();

        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);

        playOneGame(set, secondPlayer);
        playOneGame(set, secondPlayer);
        playOneGame(set, secondPlayer);
        playOneGame(set, secondPlayer);
        playOneGame(set, secondPlayer);

        playOneGame(set, firstPlayer);
        playOneGame(set, secondPlayer);

        playOneGame(set, firstPlayer);
        Assertions.assertEquals(firstPlayer, playOneGame(set, firstPlayer).get());
    }

    @Test
    public void prolongedTieStillTest() {
        init();

        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);
        playOneGame(set, firstPlayer);

        playOneGame(set, secondPlayer);
        playOneGame(set, secondPlayer);
        playOneGame(set, secondPlayer);
        playOneGame(set, secondPlayer);
        playOneGame(set, secondPlayer);

        playOneGame(set, firstPlayer);
        playOneGame(set, secondPlayer);

        Assertions.assertThrows(NoSuchElementException.class, () -> playOneGame(set, firstPlayer).get());
    }

    private Optional<Player> playOneGame(Set set, Player winner) {
        set.winnedBy(winner);
        set.winnedBy(winner);
        set.winnedBy(winner);
        return set.winnedBy(winner);
    }
}
