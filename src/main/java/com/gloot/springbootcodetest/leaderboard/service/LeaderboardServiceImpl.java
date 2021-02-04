package com.gloot.springbootcodetest.leaderboard.service;

import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardDto;
import com.gloot.springbootcodetest.leaderboard.mapper.LeaderboardMapper;
import com.gloot.springbootcodetest.leaderboard.repository.LeaderboardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;

    @Override
    public List<LeaderboardDto> getAllLeaderboards() {
        return leaderboardRepository.findAll()
                .stream()
                .map(LeaderboardMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
