package edu.korostelev.TennisScoreTracker.controller;

import edu.korostelev.TennisScoreTracker.model.Match;
import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.repository.MatchesRepository;
import edu.korostelev.TennisScoreTracker.repository.PlayersRepository;
import edu.korostelev.TennisScoreTracker.service.MatchesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/matches")
public class MatchesController {
    private final MatchesService matchesService;

    public MatchesController(MatchesRepository matchesRepository, PlayersRepository playersRepository, MatchesService matchesService) {
        this.matchesService = matchesService;
    }

    @GetMapping
    public String getMatches(
            @RequestParam(required = false, defaultValue = "1") String page,
            @RequestParam(required = false) String playerName,
            ModelMap modelMap) {
        Page<Match> allMatches = matchesService.getAllMatches(page, playerName);

        modelMap.addAttribute("matches", allMatches.getContent());
        modelMap.addAttribute("currentPage", allMatches.getNumber() + 1);
        modelMap.addAttribute("hasPrevious", allMatches.hasPrevious());
        modelMap.addAttribute("hasNext", allMatches.hasNext());
        modelMap.addAttribute("totalPages", allMatches.getTotalPages());
        modelMap.addAttribute("playerNameFilter", playerName);

        return "matches";
    }
}
