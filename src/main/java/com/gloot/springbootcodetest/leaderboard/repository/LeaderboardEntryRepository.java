package com.gloot.springbootcodetest.leaderboard.repository;

import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderboardEntryRepository extends JpaRepository<LeaderboardEntryEntity, Integer> {
}
