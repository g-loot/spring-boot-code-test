package com.gloot.springbootcodetest.leaderboard.service;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardDto;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntity;
import com.gloot.springbootcodetest.leaderboard.repository.LeaderboardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootComponentTest
public class LeaderboardServiceTest {

    @Autowired
    LeaderboardRepository leaderboardRepository;
    @Autowired
    LeaderboardService leaderboardService;

    @Test
    void getAllLeaderboards() {
        LeaderboardEntity firstLeaderboard = new LeaderboardEntity(1, "leaderboard-1");
        LeaderboardEntity secondLeaderboard = new LeaderboardEntity(2, "leaderboard-2");
        List<LeaderboardEntity> leaderboardEntities = List.of(firstLeaderboard, secondLeaderboard);
        leaderboardRepository.saveAll(leaderboardEntities);

        List<LeaderboardDto> leaderboardDtoList = leaderboardService.getAllLeaderboards();

        assertThat("fetched leaderboards ", leaderboardDtoList, hasSize(2));
        IntStream.range(0, leaderboardEntities.size())
                .forEach(index -> assertLeaderboard(leaderboardEntities.get(index), leaderboardDtoList.get(index)));
    }

    private void assertLeaderboard(LeaderboardEntity leaderboardEntity, LeaderboardDto leaderboardDto) {
        assertThat("id ", leaderboardDto.getId(), is(leaderboardEntity.getId()));
        assertThat("name ", leaderboardDto.getName(), is(leaderboardEntity.getName()));
    }
}
