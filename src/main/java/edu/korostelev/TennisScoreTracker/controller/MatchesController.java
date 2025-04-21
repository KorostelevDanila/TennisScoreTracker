package edu.korostelev.TennisScoreTracker.controller;

import edu.korostelev.TennisScoreTracker.model.Match;
import edu.korostelev.TennisScoreTracker.repository.MatchesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/matches")
public class MatchesController {
    private final MatchesRepository matchesRepository;

    public MatchesController(MatchesRepository matchesRepository) {
        this.matchesRepository = matchesRepository;
    }

    @GetMapping
    public String getMatches(ModelMap modelMap) {
        List<Match> allMatches = matchesRepository.findAll();

        modelMap.addAttribute("matches", allMatches);
        return "matches";
    }
}
