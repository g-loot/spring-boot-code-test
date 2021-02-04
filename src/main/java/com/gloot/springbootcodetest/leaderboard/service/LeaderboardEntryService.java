package com.gloot.springbootcodetest.leaderboard.service;

import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardEntryDto;

import java.util.List;
import java.util.Optional;

public interface LeaderboardEntryService {

    List<LeaderboardEntryDto> getListOfAllLeaderboardEntriesAsDTO();

    Optional<Integer> getPositionOfUser(String username, int leaderboardId);
}
