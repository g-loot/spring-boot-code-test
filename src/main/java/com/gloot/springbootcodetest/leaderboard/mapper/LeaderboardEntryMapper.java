package com.gloot.springbootcodetest.leaderboard.mapper;

import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardEntryDto;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntryEntity;

public class LeaderboardEntryMapper {

    public static LeaderboardEntryDto mapToDto(LeaderboardEntryEntity entity) {
        return LeaderboardEntryDto.builder()
                .position(entity.getPos())
                .nick(entity.getNick())
                .score(entity.getScore())
                .leaderboardDto(LeaderboardMapper.mapToDto(entity.getLeaderboardEntity()))
                .build();
    }
}
