package com.gloot.springbootcodetest.leaderboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootComponentTest
public class LeaderboardServiceTest {

  @Autowired LeaderboardRepository repository;
  @Autowired LeaderboardService service;

  @Test
  void getListOfAllLeaderboardEntriesAsDTO() {
    List<LeaderboardEntryEntity> entities = List.of(
            createLeaderboardEntry(1, "g-looter-1", 100),
            createLeaderboardEntry(2, "g-looter-2", 90)
    );
    repository.saveAll(entities);

    List<LeaderboardEntryDto> leaderboard = service.getListOfAllLeaderboardEntriesAsDTO();

    assertEquals(entities.size(), leaderboard.size());
    IntStream.range(0, entities.size())
            .forEach(count -> assertEqual(entities.get(count), leaderboard.get(count)));
  }

  private LeaderboardEntryEntity createLeaderboardEntry(int pos, String nick, int score) {
    return new LeaderboardEntryEntity(pos, nick, score);
  }

  private void assertEqual(LeaderboardEntryEntity entity, LeaderboardEntryDto dto) {
    assertEquals(entity.getPos(), dto.getPosition());
    assertEquals(entity.getNick(), dto.getNick());
    assertEquals(entity.getScore(), dto.getScore());
  }
}
