package com.gloot.springbootcodetest.leaderboard;

import java.util.List;

public interface LeaderboardService {

    List<LeaderboardEntryDto> getListOfAllLeaderboardEntriesAsDTO();
}
