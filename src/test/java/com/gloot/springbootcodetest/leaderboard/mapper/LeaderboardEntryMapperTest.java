package com.gloot.springbootcodetest.leaderboard.mapper;

import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardEntryDto;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntity;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntryEntity;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LeaderboardEntryMapperTest {

    @Test
    void mapToDto() {
        LeaderboardEntity leaderboardEntity = new LeaderboardEntity(1, "first-leaderboard");
        LeaderboardEntryEntity leaderboardEntryEntity = new LeaderboardEntryEntity(1, "nick", 50, leaderboardEntity);

        LeaderboardEntryDto leaderboardEntryDto = LeaderboardEntryMapper.mapToDto(leaderboardEntryEntity);

        assertAll("leaderboardEntryDto has ",
                () -> assertThat("position ", leaderboardEntryDto.getPosition(), is(1)),
                () -> assertThat("nick ", leaderboardEntryDto.getNick(), is("nick")),
                () -> assertThat("score ", leaderboardEntryDto.getScore(), is(50)),
                () -> assertThat("leaderboardDto ", leaderboardEntryDto.getLeaderboardDto(), is(notNullValue())),
                () -> assertThat("leaderboardDto id ", leaderboardEntryDto.getLeaderboardDto().getId(), is(1)),
                () -> assertThat("leaderboardDto name ", leaderboardEntryDto.getLeaderboardDto().getName(), is("first-leaderboard")));
    }

}