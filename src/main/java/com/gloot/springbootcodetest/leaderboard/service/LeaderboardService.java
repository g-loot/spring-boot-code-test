package com.gloot.springbootcodetest.leaderboard.service;

import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardEntryDto;

import java.util.List;

public interface LeaderboardService {

    List<LeaderboardEntryDto> getListOfAllLeaderboardEntriesAsDTO();
}
