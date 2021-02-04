package com.gloot.springbootcodetest.leaderboard.service;

import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardDto;

import java.util.List;

public interface LeaderboardService {

    List<LeaderboardDto> getAllLeaderboards();
}
