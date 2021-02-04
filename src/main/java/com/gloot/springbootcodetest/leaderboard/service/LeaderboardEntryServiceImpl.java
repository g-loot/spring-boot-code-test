package com.gloot.springbootcodetest.leaderboard.service;

import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardEntryDto;
import com.gloot.springbootcodetest.leaderboard.mapper.LeaderboardEntryMapper;
import com.gloot.springbootcodetest.leaderboard.repository.LeaderboardEntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeaderboardEntryServiceImpl implements LeaderboardEntryService {

    private final LeaderboardEntryRepository leaderboardEntryRepository;

    @Override
    public List<LeaderboardEntryDto> getListOfAllLeaderboardEntriesAsDTO() {

        return leaderboardEntryRepository.findAll()
                .stream()
                .map(LeaderboardEntryMapper::mapToDto)
                .sorted(Comparator.comparing(LeaderboardEntryDto::getPosition))
                .collect(Collectors.toList());
    }
}
