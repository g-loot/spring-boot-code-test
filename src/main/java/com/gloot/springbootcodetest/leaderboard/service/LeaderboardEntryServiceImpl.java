package com.gloot.springbootcodetest.leaderboard.service;

import com.gloot.springbootcodetest.leaderboard.criteria.LeaderboardEntryEntitySearchCriteria;
import com.gloot.springbootcodetest.leaderboard.dto.LeaderboardEntryDto;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntryEntity;
import com.gloot.springbootcodetest.leaderboard.mapper.LeaderboardEntryMapper;
import com.gloot.springbootcodetest.leaderboard.repository.LeaderboardEntryRepository;
import com.gloot.springbootcodetest.leaderboard.repository.specification.LeaderboardEntrySpecifications;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeaderboardEntryServiceImpl implements LeaderboardEntryService {

    private final LeaderboardEntryRepository leaderboardEntryRepository;

    @Override
    public Optional<LeaderboardEntryEntity> getLeaderboardEntryForUser(String username) {
        LeaderboardEntryEntitySearchCriteria searchCriteria = new LeaderboardEntryEntitySearchCriteria();
        searchCriteria.setNick(username);

        return leaderboardEntryRepository.findOne(LeaderboardEntrySpecifications.create(searchCriteria));
    }

    @Override
    public List<LeaderboardEntryDto> getListOfAllLeaderboardEntriesAsDTO() {

        return leaderboardEntryRepository.findAll()
                .stream()
                .map(LeaderboardEntryMapper::mapToDto)
                .sorted(Comparator.comparing(LeaderboardEntryDto::getPosition))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Integer> getPositionOfUser(String username, int leaderboardId) {
        LeaderboardEntryEntitySearchCriteria searchCriteria = new LeaderboardEntryEntitySearchCriteria();
        searchCriteria.setNick(username);
        searchCriteria.setLeaderboardEntityId(leaderboardId);

        return leaderboardEntryRepository.findOne(LeaderboardEntrySpecifications.create(searchCriteria))
                .map(LeaderboardEntryEntity::getPos);
    }

    @Override
    public LeaderboardEntryDto setScore(LeaderboardEntryEntity leaderboardEntryEntity, int score) {
        leaderboardEntryEntity.setScore(score);

        LeaderboardEntryEntity updatedLeaderboardEntry = leaderboardEntryRepository.save(leaderboardEntryEntity);

        return LeaderboardEntryMapper.mapToDto(updatedLeaderboardEntry);
    }
}
