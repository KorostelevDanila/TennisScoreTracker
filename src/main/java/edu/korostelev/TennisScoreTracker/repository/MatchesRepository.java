package edu.korostelev.TennisScoreTracker.repository;

import edu.korostelev.TennisScoreTracker.model.Match;
import edu.korostelev.TennisScoreTracker.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchesRepository extends JpaRepository<Match, Integer> {
    Page<Match> findMatchesByFirstPlayerOrSecondPlayer(Player firstPlayer, Player secondPlayer, Pageable pageable);
}
