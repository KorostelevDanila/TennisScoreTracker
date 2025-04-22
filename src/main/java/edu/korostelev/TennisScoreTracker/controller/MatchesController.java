package edu.korostelev.TennisScoreTracker.controller;

import edu.korostelev.TennisScoreTracker.model.Match;
import edu.korostelev.TennisScoreTracker.repository.MatchesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/matches")
public class MatchesController {
    private final MatchesRepository matchesRepository;

    public MatchesController(MatchesRepository matchesRepository) {
        this.matchesRepository = matchesRepository;
    }

    @GetMapping
    public String getMatches(
            @RequestParam(required = false, defaultValue = "1") String page,
            ModelMap modelMap) {
        Page<Match> allMatches = matchesRepository.findAll(PageRequest.of((Integer.parseInt(page)-1), 5));

        modelMap.addAttribute("matches", allMatches.getContent());
        modelMap.addAttribute("currentPage", allMatches.getNumber() + 1);
        modelMap.addAttribute("hasPrevious", allMatches.hasPrevious());
        modelMap.addAttribute("hasNext", allMatches.hasNext());
        modelMap.addAttribute("totalPages", allMatches.getTotalPages());

        return "matches";
    }
}
