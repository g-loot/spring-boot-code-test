package com.gloot.springbootcodetest.leaderboard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "leaderboard")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardEntity {
    @Id
    private Integer id;

    private String name;
}
