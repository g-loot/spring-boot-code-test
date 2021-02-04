package com.gloot.springbootcodetest.leaderboard;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;

    @Override
    public List<LeaderboardEntryDto> getListOfAllLeaderboardEntriesAsDTO() {

        return leaderboardRepository.findAll()
                .stream()
                .map(LeaderboardEntryMapper::mapToDto)
                .sorted(Comparator.comparing(LeaderboardEntryDto::getPosition))
                .collect(Collectors.toList());
    }
}
