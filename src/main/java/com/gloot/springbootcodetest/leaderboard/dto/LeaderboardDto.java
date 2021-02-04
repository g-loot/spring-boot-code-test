package com.gloot.springbootcodetest.leaderboard.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class LeaderboardDto {
    int id;
    String name;
}
