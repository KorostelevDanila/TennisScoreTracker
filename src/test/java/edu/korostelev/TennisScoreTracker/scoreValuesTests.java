package edu.korostelev.TennisScoreTracker;

import edu.korostelev.TennisScoreTracker.model.tennisScoreModels.enums.SCORE_VALUES;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class scoreValuesTests {

    @Test
    void normalScoring() {
        SCORE_VALUES scoreValue = SCORE_VALUES.LOVE;
        Assertions.assertEquals(SCORE_VALUES.FIFTEEN, scoreValue.next());
    }

    @Test
    void winnableScoring() {
        SCORE_VALUES scoreValue = SCORE_VALUES.FORTY;
        Assertions.assertEquals(SCORE_VALUES.WIN, scoreValue.next());
    }
}
