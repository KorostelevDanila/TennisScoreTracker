package edu.korostelev.TennisScoreTracker.controller;

import edu.korostelev.TennisScoreTracker.dto.MatchInformation;
import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.service.CurrentMatchesService;
import edu.korostelev.TennisScoreTracker.service.MatchInformationService;
import edu.korostelev.TennisScoreTracker.service.PlayersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/match-score")
public class MatchScoreController {
    private final CurrentMatchesService currentMatchesService;
    private final MatchInformationService matchInformationService;
    private final PlayersService playersService;

    public MatchScoreController(CurrentMatchesService currentMatchesService, MatchInformationService matchInformationService, PlayersService playersService) {
        this.currentMatchesService = currentMatchesService;
        this.matchInformationService = matchInformationService;
        this.playersService = playersService;
    }

    @GetMapping
    public String getMatchScore(
            @RequestParam("uuid") String uuid,
            ModelMap modelMap
    ) {
        MatchInformation matchInformation = matchInformationService.getMatchInformation(uuid);

        modelMap.addAttribute("uuid", uuid);
        modelMap.addAttribute("matchInformation", matchInformation);

        return "match-score";
    }

    @PostMapping
    public String addScore(
            @RequestParam("uuid") String uuid,
            @RequestParam("winner") Integer winnerId,
            ModelMap modelMap
    ) {
        Optional<Player> matchWinner = currentMatchesService.addScore(uuid, winnerId);

        if (matchWinner.isPresent()) {
            modelMap.addAttribute("winnerName", matchWinner.get().getName());
            // TODO Win logic
        }

        modelMap.addAttribute("matchInformation", matchInformationService.getMatchInformation(uuid));

        modelMap.addAttribute("uuid", uuid);

        return "match-score";
    }
}
