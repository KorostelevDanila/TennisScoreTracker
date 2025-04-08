package edu.korostelev.TennisScoreTracker.controller;

import edu.korostelev.TennisScoreTracker.model.CurrentMatch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/match-score")
public class MatchScoreController {
    @GetMapping
    public String getMatchScore(
            @RequestParam("uuid") Integer uuid,
            ModelMap modelMap
    ) {
        CurrentMatch currentMatch = new CurrentMatch(1, 2);

        modelMap.addAttribute("uuid", uuid);
        modelMap.addAttribute("match", currentMatch);

        return "match-score";
    }
}
