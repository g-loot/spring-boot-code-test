package com.gloot.springbootcodetest.leaderboard.repository;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntity;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootComponentTest
public class LeaderboardEntryRepositoryTest {

    @Autowired
    LeaderboardRepository leaderboardRepository;
    @Autowired
    LeaderboardEntryRepository leaderboardEntryRepository;

    @Test
    void save() {
        LeaderboardEntity leaderboard = saveLeaderboard(1, "leaderboard-1");
        LeaderboardEntryEntity leaderboardEntry = new LeaderboardEntryEntity(1, "g-looter", 100, leaderboard);

        leaderboardEntryRepository.save(leaderboardEntry);

        LeaderboardEntryEntity fromRepository = leaderboardEntryRepository.findById(leaderboardEntry.getPos()).get();
        assertThat(fromRepository, is(leaderboardEntry));
    }

    private LeaderboardEntity saveLeaderboard(int id, String name) {
        LeaderboardEntity leaderboardEntity = new LeaderboardEntity(id, name);
        leaderboardRepository.save(leaderboardEntity);

        return leaderboardEntity;
    }
}