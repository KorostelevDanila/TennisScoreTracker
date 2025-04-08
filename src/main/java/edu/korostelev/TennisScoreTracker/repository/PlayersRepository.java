package edu.korostelev.TennisScoreTracker.repository;

import edu.korostelev.TennisScoreTracker.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayersRepository extends JpaRepository<Player, Integer> {
    Optional<Player> findByName(String name);
}
