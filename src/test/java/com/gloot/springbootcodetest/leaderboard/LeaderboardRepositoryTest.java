package com.gloot.springbootcodetest.leaderboard;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntryEntity;
import com.gloot.springbootcodetest.leaderboard.repository.LeaderboardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootComponentTest
public class LeaderboardRepositoryTest {

    @Autowired LeaderboardRepository repository;

    @Test
    void saveAndRetrieve() {
        LeaderboardEntryEntity entity = new LeaderboardEntryEntity(1, "g-looter", 100);
        repository.saveAll(List.of(entity));
        LeaderboardEntryEntity fromRepository = repository.findById(entity.getPos()).get();
        assertThat(fromRepository, is(entity));
    }
}
