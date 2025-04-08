package edu.korostelev.TennisScoreTracker.controller;

import edu.korostelev.TennisScoreTracker.model.CurrentMatch;
import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.service.MatchesService;
import edu.korostelev.TennisScoreTracker.service.PlayersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/match-score")
public class MatchScoreController {
    private final MatchesService matchesService;
    private final PlayersService playersService;

    public MatchScoreController(MatchesService matchesService, PlayersService playersService) {
        this.matchesService = matchesService;
        this.playersService = playersService;
    }

    @GetMapping
    public String getMatchScore(
            @RequestParam("uuid") Integer uuid,
            ModelMap modelMap
    ) {
        CurrentMatch currentMatch = new CurrentMatch(1, 2);
        CurrentMatch currentMatch = matchesService.getCurrentMatch(uuid);

        modelMap.addAttribute("uuid", uuid);
        modelMap.addAttribute("match", currentMatch);

        return "match-score";
    }
}
