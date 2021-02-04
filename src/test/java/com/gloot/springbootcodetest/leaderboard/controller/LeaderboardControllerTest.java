package com.gloot.springbootcodetest.leaderboard.controller;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntryEntity;
import com.gloot.springbootcodetest.leaderboard.repository.LeaderboardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootComponentTest
public class LeaderboardControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired LeaderboardRepository repository;

    @Test
    void getLeaderboardTest() throws Exception {
        LeaderboardEntryEntity entity = new LeaderboardEntryEntity(1, "g-looter", 100);
        repository.saveAll(List.of(entity));

        mockMvc.perform(get("/api/v1/leaderboard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].position", is(entity.getPos())))
                .andExpect(jsonPath("$.[0].nick", is(entity.getNick())))
                .andExpect(jsonPath("$.[0].score", is(entity.getScore())));
    }
}
