CREATE TABLE IF NOT EXISTS `leaderboard`
(
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(255)
);

CREATE TABLE IF NOT EXISTS `leaderboardentry`
(
    `pos`            int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nick`           varchar(20),
    `score`          int,
    `leaderboard_id` int
);

ALTER TABLE `leaderboardentry`
    ADD CONSTRAINT fk_leaderboard_id FOREIGN KEY (leaderboard_id) REFERENCES leaderboard (id);