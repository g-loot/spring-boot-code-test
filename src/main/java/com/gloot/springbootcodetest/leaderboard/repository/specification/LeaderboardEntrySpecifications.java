package com.gloot.springbootcodetest.leaderboard.repository.specification;

import com.gloot.springbootcodetest.leaderboard.criteria.LeaderboardEntryEntitySearchCriteria;
import com.gloot.springbootcodetest.leaderboard.entity.LeaderboardEntryEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardEntrySpecifications {

    public static Specification<LeaderboardEntryEntity> create(LeaderboardEntryEntitySearchCriteria searchCriteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchCriteria.getPos() != null) {
                predicates.add(criteriaBuilder.equal(root.get("pos"), searchCriteria.getPos()));
            }
            if (searchCriteria.getNick() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nick"), searchCriteria.getNick()));
            }
            if (searchCriteria.getScore() != null) {
                predicates.add(criteriaBuilder.equal(root.get("score"), searchCriteria.getScore()));
            }
            if (searchCriteria.getLeaderboardEntityId() != null) {
                predicates.add(criteriaBuilder.equal(root.join("leaderboardEntity").get("id"),
                        searchCriteria.getLeaderboardEntityId()));
            }

            Predicate[] predicatesArray = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(predicatesArray));
        };
    }
}
