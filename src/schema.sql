DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS players;
DROP TABLE IF EXISTS teams;

CREATE TABLE players (
  id      INT PRIMARY KEY,
  name    VARCHAR(32),
  surname VARCHAR(64)
);

CREATE TABLE teams (
  id      INT PRIMARY KEY,
  name    VARCHAR(64),
  country VARCHAR(64),
  league  VARCHAR(64)
);

CREATE TABLE games (
  id              INT PRIMARY KEY,
  host_player_id  INT,
  host_team_id    INT,
  host_score      INT,
  guest_player_id INT,
  guest_team_id   INT,
  guest_score     INT,
  date            DATETIME DEFAULT CURRENT_TIMESTAMP,

  FOREIGN KEY (host_player_id) REFERENCES players (id),
  FOREIGN KEY (host_team_id) REFERENCES teams (id),
  FOREIGN KEY (guest_player_id) REFERENCES players (id),
  FOREIGN KEY (guest_team_id) REFERENCES teams (id)
);



