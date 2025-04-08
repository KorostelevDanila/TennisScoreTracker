package edu.korostelev.TennisScoreTracker.controller;

import edu.korostelev.TennisScoreTracker.service.MatchesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/new-match")
public class NewMatchController {
    private final MatchesService matchesService;

    public NewMatchController(MatchesService matchesService) {
        this.matchesService = matchesService;
    }

    @GetMapping
    public String createMatch() {
        return "new-match";
    }

    @PostMapping
    public String startMatch(
            @RequestParam String firstPlayerName,
            @RequestParam String secondPlayerName,
            RedirectAttributes redirectAttributes
    ) {
        Integer createdMatchId = matchesService.createMatch(firstPlayerName, secondPlayerName);
        redirectAttributes.addAttribute("uuid", createdMatchId);
        return "redirect:/match-score";
    }
}
