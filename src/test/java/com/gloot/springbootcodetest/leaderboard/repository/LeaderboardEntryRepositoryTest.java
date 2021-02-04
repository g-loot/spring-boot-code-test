package com.gloot.springbootcodetest.leaderboard.repository;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import com.gloot.springbootcodetest.leaderboard.criteria.LeaderboardEntryEntitySearchCriteria;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntity;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntryEntity;
import com.gloot.springbootcodetest.leaderboard.repository.specification.LeaderboardEntrySpecifications;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
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

    @Test
    void findAll_posAsCriteria() {
        LeaderboardEntity leaderboard = saveLeaderboard(1, "leaderboard-1");
        LeaderboardEntryEntity leaderboardFirstEntry = new LeaderboardEntryEntity(1, "g-looter", 100, leaderboard);
        LeaderboardEntryEntity leaderboardSecondEntry = new LeaderboardEntryEntity(2, "h-looter", 80, leaderboard);
        leaderboardEntryRepository.saveAll(List.of(leaderboardFirstEntry, leaderboardSecondEntry));

        LeaderboardEntryEntitySearchCriteria searchCriteria = new LeaderboardEntryEntitySearchCriteria();
        searchCriteria.setPos(2);
        Specification<LeaderboardEntryEntity> specification = LeaderboardEntrySpecifications.create(searchCriteria);

        List<LeaderboardEntryEntity> leaderboardEntryEntities = leaderboardEntryRepository.findAll(specification);
        assertThat("size of filtered LeaderboardEntryEntities ", leaderboardEntryEntities, hasSize(1));
        assertThat("filtered LeaderboardEntryEntity ", leaderboardEntryEntities, hasItem(leaderboardSecondEntry));
    }

    @Test
    void findAll_nickAsCriteria() {
        LeaderboardEntity leaderboard = saveLeaderboard(1, "leaderboard-1");
        LeaderboardEntryEntity leaderboardFirstEntry = new LeaderboardEntryEntity(1, "g-looter", 100, leaderboard);
        LeaderboardEntryEntity leaderboardSecondEntry = new LeaderboardEntryEntity(2, "h-looter", 80, leaderboard);
        leaderboardEntryRepository.saveAll(List.of(leaderboardFirstEntry, leaderboardSecondEntry));

        LeaderboardEntryEntitySearchCriteria searchCriteria = new LeaderboardEntryEntitySearchCriteria();
        searchCriteria.setNick("h-looter");
        Specification<LeaderboardEntryEntity> specification = LeaderboardEntrySpecifications.create(searchCriteria);

        List<LeaderboardEntryEntity> leaderboardEntryEntities = leaderboardEntryRepository.findAll(specification);
        assertThat("size of filtered LeaderboardEntryEntities ", leaderboardEntryEntities, hasSize(1));
        assertThat("filtered LeaderboardEntryEntity ", leaderboardEntryEntities, hasItem(leaderboardSecondEntry));
    }

    @Test
    void findAll_scoreAsCriteria() {
        LeaderboardEntity leaderboard = saveLeaderboard(1, "leaderboard-1");
        LeaderboardEntryEntity leaderboardFirstEntry = new LeaderboardEntryEntity(1, "g-looter", 100, leaderboard);
        LeaderboardEntryEntity leaderboardSecondEntry = new LeaderboardEntryEntity(2, "h-looter", 80, leaderboard);
        leaderboardEntryRepository.saveAll(List.of(leaderboardFirstEntry, leaderboardSecondEntry));

        LeaderboardEntryEntitySearchCriteria searchCriteria = new LeaderboardEntryEntitySearchCriteria();
        searchCriteria.setScore(80);
        Specification<LeaderboardEntryEntity> specification = LeaderboardEntrySpecifications.create(searchCriteria);

        List<LeaderboardEntryEntity> leaderboardEntryEntities = leaderboardEntryRepository.findAll(specification);
        assertThat("size of filtered LeaderboardEntryEntities ", leaderboardEntryEntities, hasSize(1));
        assertThat("filtered LeaderboardEntryEntity ", leaderboardEntryEntities, hasItem(leaderboardSecondEntry));
    }

    @Test
    void findAll_leaderboardEntityIdAsCriteria() {
        LeaderboardEntity firstLeaderboard = saveLeaderboard(1, "leaderboard-1");
        LeaderboardEntity secondLeaderboard = saveLeaderboard(2, "leaderboard-2");
        LeaderboardEntryEntity leaderboardFirstEntry = new LeaderboardEntryEntity(1, "g-looter", 100, firstLeaderboard);
        LeaderboardEntryEntity leaderboardSecondEntry = new LeaderboardEntryEntity(2, "h-looter", 80, secondLeaderboard);
        LeaderboardEntryEntity leaderboardThirdEntry = new LeaderboardEntryEntity(3, "i-looter", 80, firstLeaderboard);
        leaderboardEntryRepository.saveAll(List.of(leaderboardFirstEntry, leaderboardSecondEntry, leaderboardThirdEntry));

        LeaderboardEntryEntitySearchCriteria searchCriteria = new LeaderboardEntryEntitySearchCriteria();
        searchCriteria.setLeaderboardEntityId(1);
        Specification<LeaderboardEntryEntity> specification = LeaderboardEntrySpecifications.create(searchCriteria);

        List<LeaderboardEntryEntity> leaderboardEntryEntities = leaderboardEntryRepository.findAll(specification);
        assertThat("size of filtered LeaderboardEntryEntities ", leaderboardEntryEntities, hasSize(2));
        assertThat("filtered LeaderboardEntryEntity ", leaderboardEntryEntities, hasItems(leaderboardFirstEntry, leaderboardThirdEntry));
    }

    private LeaderboardEntity saveLeaderboard(int id, String name) {
        LeaderboardEntity leaderboardEntity = new LeaderboardEntity(id, name);
        leaderboardRepository.save(leaderboardEntity);

        return leaderboardEntity;
    }
}