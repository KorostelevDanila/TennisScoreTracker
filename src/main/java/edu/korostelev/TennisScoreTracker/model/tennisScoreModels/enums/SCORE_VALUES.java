package edu.korostelev.TennisScoreTracker.model.tennisScoreModels.enums;

import java.util.ResourceBundle;

public enum SCORE_VALUES {
    LOVE,
    FIFTEEN,
    THIRTY,
    FORTY,
    DEUCE,
    ADVANTAGE,
    WIN;

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("score_values");

    public SCORE_VALUES next() {
        SCORE_VALUES[] values = SCORE_VALUES.values();
        int nextOrdinal;
        if ((this.ordinal() % values.length) == 3) {
            nextOrdinal = WIN.ordinal();
        } else {
            nextOrdinal = (this.ordinal() + 1) % values.length;
        }
        return values[nextOrdinal];
    }

    public String toString() {
        return resourceBundle.getString(this.name());
    }
}
