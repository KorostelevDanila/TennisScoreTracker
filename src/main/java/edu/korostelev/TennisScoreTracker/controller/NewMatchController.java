package edu.korostelev.TennisScoreTracker.controller;

import edu.korostelev.TennisScoreTracker.service.CurrentMatchesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/new-match")
public class NewMatchController {
    private final CurrentMatchesService currentMatchesService;

    public NewMatchController(CurrentMatchesService currentMatchesService) {
        this.currentMatchesService = currentMatchesService;
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
        UUID createdMatchId = currentMatchesService.createMatch(firstPlayerName, secondPlayerName);
        redirectAttributes.addAttribute("uuid", createdMatchId.toString());
        return "redirect:/match-score";
    }
}
