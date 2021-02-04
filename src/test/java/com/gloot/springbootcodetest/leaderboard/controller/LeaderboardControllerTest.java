package com.gloot.springbootcodetest.leaderboard.controller;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntity;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntryEntity;
import com.gloot.springbootcodetest.leaderboard.repository.LeaderboardEntryRepository;
import com.gloot.springbootcodetest.leaderboard.repository.LeaderboardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootComponentTest
public class LeaderboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    LeaderboardRepository leaderboardRepository;
    @Autowired
    LeaderboardEntryRepository leaderboardEntryRepository;

    @Test
    void getLeaderboard() throws Exception {
        LeaderboardEntity leaderboardEntity = new LeaderboardEntity(1, "leaderboard-1");
        leaderboardRepository.save(leaderboardEntity);
        LeaderboardEntryEntity entity = new LeaderboardEntryEntity(1, "g-looter", 100, leaderboardEntity);
        leaderboardEntryRepository.saveAll(List.of(entity));

        mockMvc.perform(get("/api/v1/leaderboard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].position", is(entity.getPos())))
                .andExpect(jsonPath("$.[0].nick", is(entity.getNick())))
                .andExpect(jsonPath("$.[0].score", is(entity.getScore())))
                .andExpect(jsonPath("$.[0].leaderboardDto.id", is(entity.getLeaderboardEntity().getId())))
                .andExpect(jsonPath("$.[0].leaderboardDto.name", is(entity.getLeaderboardEntity().getName())));
    }

    @Test
    void getAllLeaderboards() throws Exception {
        LeaderboardEntity firstLeaderboard = new LeaderboardEntity(1, "leaderboard-1");
        LeaderboardEntity secondLeaderboard = new LeaderboardEntity(2, "leaderboard-2");
        List<LeaderboardEntity> leaderboardEntities = List.of(firstLeaderboard, secondLeaderboard);
        leaderboardRepository.saveAll(leaderboardEntities);

        mockMvc.perform(get("/api/v1/allLeaderboards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id", is(firstLeaderboard.getId())))
                .andExpect(jsonPath("$.[0].name", is(firstLeaderboard.getName())))
                .andExpect(jsonPath("$.[1].id", is(secondLeaderboard.getId())))
                .andExpect(jsonPath("$.[1].name", is(secondLeaderboard.getName())));
    }

    @Test
    void getPositionOfUser() throws Exception {
        LeaderboardEntity leaderboardEntity = new LeaderboardEntity(1, "leaderboard-1");
        leaderboardRepository.save(leaderboardEntity);
        LeaderboardEntryEntity firstEntry = new LeaderboardEntryEntity(1, "g-looter", 100, leaderboardEntity);
        LeaderboardEntryEntity secondEntry = new LeaderboardEntryEntity(2, "h-looter", 90, leaderboardEntity);
        leaderboardEntryRepository.saveAll(List.of(firstEntry, secondEntry));

        mockMvc.perform(get("/api/v1/getPosition/h-looter/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(2)));
    }

    @Test
    void getPositionOfUser_notFound() throws Exception {
        LeaderboardEntity leaderboardEntity = new LeaderboardEntity(1, "leaderboard-1");
        leaderboardRepository.save(leaderboardEntity);
        LeaderboardEntryEntity firstEntry = new LeaderboardEntryEntity(1, "g-looter", 100, leaderboardEntity);
        LeaderboardEntryEntity secondEntry = new LeaderboardEntryEntity(2, "h-looter", 90, leaderboardEntity);
        leaderboardEntryRepository.saveAll(List.of(firstEntry, secondEntry));

        mockMvc.perform(get("/api/v1/getPosition/j-looter/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void setScoreForUser() throws Exception {
        LeaderboardEntity leaderboardEntity = new LeaderboardEntity(1, "leaderboard-1");
        leaderboardRepository.save(leaderboardEntity);
        LeaderboardEntryEntity firstEntry = new LeaderboardEntryEntity(1, "g-looter", 100, leaderboardEntity);
        LeaderboardEntryEntity secondEntry = new LeaderboardEntryEntity(2, "h-looter", 90, leaderboardEntity);
        leaderboardEntryRepository.saveAll(List.of(firstEntry, secondEntry));

        mockMvc.perform(put("/api/v1/setScoreForUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nick\": \"g-looter\", \"score\": 50}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nick", is("g-looter")))
                .andExpect(jsonPath("$.score", is(50)));
    }

    @Test
    void setScoreForUser_leaderboardEntryNotFoundForUser() throws Exception {
        LeaderboardEntity leaderboardEntity = new LeaderboardEntity(1, "leaderboard-1");
        leaderboardRepository.save(leaderboardEntity);
        LeaderboardEntryEntity firstEntry = new LeaderboardEntryEntity(1, "g-looter", 100, leaderboardEntity);
        LeaderboardEntryEntity secondEntry = new LeaderboardEntryEntity(2, "h-looter", 90, leaderboardEntity);
        leaderboardEntryRepository.saveAll(List.of(firstEntry, secondEntry));

        mockMvc.perform(put("/api/v1/setScoreForUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nick\": \"g-looter123\", \"score\": 50}"))
                .andExpect(status().isNotFound());
    }
}
