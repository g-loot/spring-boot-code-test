package com.gloot.springbootcodetest.leaderboard;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LeaderboardEntryRepositoryIntegrationTest extends SpringBootComponentTest {
  @Autowired LeaderboardEntryRepository repository;

  @AfterEach
  void tearDown() {
    repository.deleteAll();
  }

  @Test
  void saveAndRetrieve() {
    LeaderboardEntry entity = new LeaderboardEntry("g-looter", 100);
    repository.saveAll(List.of(entity));
    LeaderboardEntry fromRepository = repository.findById(entity.getId()).get();
    assertThat(fromRepository, is(entity));
  }
}
