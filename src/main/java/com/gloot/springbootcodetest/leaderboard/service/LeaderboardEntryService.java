package com.gloot.springbootcodetest.leaderboard.service;

import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardEntryDto;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntryEntity;

import java.util.List;
import java.util.Optional;

public interface LeaderboardEntryService {

    Optional<LeaderboardEntryEntity> getLeaderboardEntryForUser(String username);

    List<LeaderboardEntryDto> getListOfAllLeaderboardEntriesAsDTO();

    Optional<Integer> getPositionOfUser(String username, int leaderboardId);

    LeaderboardEntryDto setScore(LeaderboardEntryEntity leaderboardEntryEntity, int score);
}
