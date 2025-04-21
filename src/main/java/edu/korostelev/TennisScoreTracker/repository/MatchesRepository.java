package edu.korostelev.TennisScoreTracker.repository;

import edu.korostelev.TennisScoreTracker.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchesRepository extends JpaRepository<Match, Integer> {
}
