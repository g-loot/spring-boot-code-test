package com.gloot.springbootcodetest.leaderboard.criteria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaderboardEntryEntitySearchCriteria {
    private Integer pos;
    private String nick;
    private Integer score;
    private Integer leaderboardEntityId;
}
