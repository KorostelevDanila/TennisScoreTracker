package edu.korostelev.TennisScoreTracker.controller;

import edu.korostelev.TennisScoreTracker.model.CurrentMatch;
import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.service.CurrentMatchesService;
import edu.korostelev.TennisScoreTracker.service.PlayersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/match-score")
public class MatchScoreController {
    private final CurrentMatchesService currentMatchesService;
    private final PlayersService playersService;

    public MatchScoreController(CurrentMatchesService currentMatchesService, PlayersService playersService) {
        this.currentMatchesService = currentMatchesService;
        this.playersService = playersService;
    }

    @GetMapping
    public String getMatchScore(
            @RequestParam("uuid") String uuid,
            ModelMap modelMap
    ) {
        CurrentMatch currentMatch = currentMatchesService.getCurrentMatch(UUID.fromString(uuid));
        Optional<Player> firstPlayer = playersService.findById(currentMatch.getFirstPlayer().getId());
        Optional<Player> secondPlayer = playersService.findById(currentMatch.getSecondPlayer().getId());

        modelMap.addAttribute("uuid", uuid);
        modelMap.addAttribute("match", currentMatch);
        firstPlayer.ifPresent(player -> modelMap.addAttribute("firstPlayer", player));
        secondPlayer.ifPresent(player -> modelMap.addAttribute("secondPlayer", player));

        return "match-score";
    }
}
