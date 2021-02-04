package com.gloot.springbootcodetest.leaderboard.repository;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootComponentTest
public class LeaderboardRepositoryTest {

    @Autowired
    LeaderboardRepository leaderboardRepository;

    @Test
    void save() {
        LeaderboardEntity leaderboardEntity = new LeaderboardEntity(1, "leaderboard-1");

        leaderboardRepository.save(leaderboardEntity);

        Optional<LeaderboardEntity> leaderboardEntityOptional = leaderboardRepository.findById(leaderboardEntity.getId());

        assertThat("fetched leaderboardEntity is ", leaderboardEntityOptional, not(Optional.empty()));
        assertThat("leaderboardEntity is ", leaderboardEntityOptional.get(), is(leaderboardEntity));
    }
}
