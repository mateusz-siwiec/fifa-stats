package fifa.stats;

import fifa.stats.model.Team;
import fifa.stats.model.Player;

public class PlayerResult {

    private Player player;
    private Team team;
    private Integer numberOfGoals;

    public PlayerResult(Player player, Team team, Integer numberOfGoals) {
        this.player = player;
        this.team = team;
        this.numberOfGoals = numberOfGoals;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getNumberOfGoals() {
        return numberOfGoals;
    }

    public void setNumberOfGoals(Integer numberOfGoals) {
        this.numberOfGoals = numberOfGoals;
    }

    @Override
    public String toString() {
        return "PlayerResult{" + "player=" + player + ", team=" + team + ", numberOfGoals=" + numberOfGoals + '}';
    }
}
