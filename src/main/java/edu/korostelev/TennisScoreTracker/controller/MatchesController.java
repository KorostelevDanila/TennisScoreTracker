package edu.korostelev.TennisScoreTracker.controller;

import edu.korostelev.TennisScoreTracker.model.Match;
import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.repository.MatchesRepository;
import edu.korostelev.TennisScoreTracker.repository.PlayersRepository;
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
    private final MatchesRepository matchesRepository;
    private final PlayersRepository playersRepository;

    public MatchesController(MatchesRepository matchesRepository, PlayersRepository playersRepository) {
        this.matchesRepository = matchesRepository;
        this.playersRepository = playersRepository;
    }

    @GetMapping
    public String getMatches(
            @RequestParam(required = false, defaultValue = "1") String page,
            @RequestParam(required = false) String playerName,
            ModelMap modelMap) {
        Page<Match> allMatches = null;
        if (playerName == null) {
            allMatches = matchesRepository.findAll(PageRequest.of((Integer.parseInt(page) - 1), 5));
        } else {
            Optional<Player> player = playersRepository.findByName(playerName);
            if (player.isPresent()) {
                allMatches = matchesRepository.findMatchesByFirstPlayerOrSecondPlayer(player.get(), player.get(), PageRequest.of((Integer.parseInt(page) - 1), 5));
            } else {
                if (playerName.isEmpty()) {
                    allMatches = matchesRepository.findAll(PageRequest.of((Integer.parseInt(page) - 1), 5));
                    playerName = null;
                } else {
                    allMatches = Page.empty();
                }
            }
        }

        modelMap.addAttribute("matches", allMatches.getContent());
        modelMap.addAttribute("currentPage", allMatches.getNumber() + 1);
        modelMap.addAttribute("hasPrevious", allMatches.hasPrevious());
        modelMap.addAttribute("hasNext", allMatches.hasNext());
        modelMap.addAttribute("totalPages", allMatches.getTotalPages());
        modelMap.addAttribute("playerNameFilter", playerName);

        return "matches";
    }
}
