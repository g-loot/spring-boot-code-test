package com.gloot.springbootcodetest.leaderboard.service;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardEntryDto;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntity;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntryEntity;
import com.gloot.springbootcodetest.leaderboard.repository.LeaderboardEntryRepository;
import com.gloot.springbootcodetest.leaderboard.repository.LeaderboardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootComponentTest
public class LeaderboardEntryServiceTest {

    @Autowired
    LeaderboardEntryRepository repository;
    @Autowired
    LeaderboardRepository leaderboardRepository;
    @Autowired
    LeaderboardEntryService service;

    @Test
    void getLeaderboardEntryForUser() {
        LeaderboardEntity leaderboard = saveLeaderboard(1, "leaderboard-1");
        List<LeaderboardEntryEntity> entities = List.of(
                createLeaderboardEntry(1, "g-looter-1", 100, leaderboard),
                createLeaderboardEntry(2, "g-looter-2", 90, leaderboard)
        );
        repository.saveAll(entities);

        Optional<LeaderboardEntryEntity> leaderboardEntryForUser = service.getLeaderboardEntryForUser("g-looter-1");

        assertThat("leaderboardEntry user ", leaderboardEntryForUser, not(Optional.empty()));
        assertThat("leaderboardEntry ", leaderboardEntryForUser.get(), is(entities.get(0)));
    }

    @Test
    void getLeaderboardEntryForUser_notFound() {
        LeaderboardEntity leaderboard = saveLeaderboard(1, "leaderboard-1");
        List<LeaderboardEntryEntity> entities = List.of(
                createLeaderboardEntry(1, "g-looter-1", 100, leaderboard),
                createLeaderboardEntry(2, "g-looter-2", 90, leaderboard)
        );
        repository.saveAll(entities);

        Optional<LeaderboardEntryEntity> leaderboardEntryForUser = service.getLeaderboardEntryForUser("g-looter-12");

        assertThat("leaderboardEntry user ", leaderboardEntryForUser, is(Optional.empty()));
    }

    @Test
    void getListOfAllLeaderboardEntriesAsDTO() {
        LeaderboardEntity leaderboard = saveLeaderboard(1, "leaderboard-1");
        List<LeaderboardEntryEntity> entities = List.of(
                createLeaderboardEntry(1, "g-looter-1", 100, leaderboard),
                createLeaderboardEntry(2, "g-looter-2", 90, leaderboard)
        );
        repository.saveAll(entities);

        List<LeaderboardEntryDto> listOfAllLeaderboardEntriesAsDTO = service.getListOfAllLeaderboardEntriesAsDTO();

        assertEquals(entities.size(), listOfAllLeaderboardEntriesAsDTO.size());
        IntStream.range(0, entities.size())
                .forEach(count -> assertEqual(entities.get(count), listOfAllLeaderboardEntriesAsDTO.get(count)));
    }

    @Test
    void getPositionOfUser() {
        LeaderboardEntity leaderboard = saveLeaderboard(1, "leaderboard-1");
        List<LeaderboardEntryEntity> entities = List.of(
                createLeaderboardEntry(1, "g-looter-1", 100, leaderboard),
                createLeaderboardEntry(2, "g-looter-2", 90, leaderboard)
        );
        repository.saveAll(entities);

        Optional<Integer> positionOfUser = service.getPositionOfUser("g-looter-2", 1);

        assertThat("position of the user ", positionOfUser, not(Optional.empty()));
        assertThat("position ", positionOfUser.get(), is(2));
    }

    @Test
    void getPositionOfUser_notFound() {
        LeaderboardEntity leaderboard = saveLeaderboard(1, "leaderboard-1");
        List<LeaderboardEntryEntity> entities = List.of(
                createLeaderboardEntry(1, "g-looter-1", 100, leaderboard),
                createLeaderboardEntry(2, "g-looter-2", 90, leaderboard)
        );
        repository.saveAll(entities);

        Optional<Integer> positionOfUser = service.getPositionOfUser("g-looter-3", 1);

        assertThat("position of the user ", positionOfUser, is(Optional.empty()));
    }

    @Test
    void setScoreForUser() {
        LeaderboardEntity leaderboard = saveLeaderboard(1, "leaderboard-1");
        List<LeaderboardEntryEntity> entities = List.of(
                createLeaderboardEntry(1, "g-looter-1", 100, leaderboard),
                createLeaderboardEntry(2, "g-looter-2", 90, leaderboard)
        );
        repository.saveAll(entities);

        LeaderboardEntryDto updatedLeaderboardEntryDto = service.setScore(entities.get(0), 50);

        assertThat("updated score ", updatedLeaderboardEntryDto.getScore(), is(50));
    }

    private LeaderboardEntity saveLeaderboard(int id, String name) {
        LeaderboardEntity leaderboardEntity = new LeaderboardEntity(id, name);
        leaderboardRepository.save(leaderboardEntity);

        return leaderboardEntity;
    }

    private LeaderboardEntryEntity createLeaderboardEntry(int pos, String nick, int score, LeaderboardEntity
            leaderboard) {
        return new LeaderboardEntryEntity(pos, nick, score, leaderboard);
    }

    private void assertEqual(LeaderboardEntryEntity entity, LeaderboardEntryDto dto) {
        assertEquals(entity.getPos(), dto.getPosition());
        assertEquals(entity.getNick(), dto.getNick());
        assertEquals(entity.getScore(), dto.getScore());
        assertEquals(entity.getLeaderboardEntity().getId(), dto.getLeaderboardDto().getId());
        assertEquals(entity.getLeaderboardEntity().getName(), dto.getLeaderboardDto().getName());
    }
}

