package com.gloot.springbootcodetest.leaderboard.controller;


import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardDto;
import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardEntryDto;
import com.gloot.springbootcodetest.leaderboard.service.LeaderboardEntryService;
import com.gloot.springbootcodetest.leaderboard.service.LeaderboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.gloot.springbootcodetest.Application.API_VERSION_1;

@RestController
@RequestMapping(API_VERSION_1)
@AllArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;
    private final LeaderboardEntryService leaderboardEntryService;

    @GetMapping("/leaderboard")
    public List<LeaderboardEntryDto> getLeaderboard() {
        return leaderboardEntryService.getListOfAllLeaderboardEntriesAsDTO();
    }

    @GetMapping("/allLeaderboards")
    public List<LeaderboardDto> getAllLeaderboards() {
        return leaderboardService.getAllLeaderboards();
    }

    @GetMapping("/getPosition/{username}/{leaderboardId}")
    public Integer getPositionOfUser(@PathVariable String username,
                                     @PathVariable int leaderboardId) {
        return leaderboardEntryService.getPositionOfUser(username, leaderboardId)
                .orElse(null);
    }
}
