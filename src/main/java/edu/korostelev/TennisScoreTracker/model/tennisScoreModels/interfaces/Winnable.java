package edu.korostelev.TennisScoreTracker.model.tennisScoreModels.interfaces;

import edu.korostelev.TennisScoreTracker.model.Player;

import java.util.Optional;

public interface Winnable {
    Optional<Player> winnedBy(Player winner);
}
