package com.gloot.springbootcodetest.leaderboard.mapper;

import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardDto;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntity;

public class LeaderboardMapper {

    public static LeaderboardDto mapToDto(LeaderboardEntity entity) {
        return LeaderboardDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
