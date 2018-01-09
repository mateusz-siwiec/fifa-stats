
package fifa.stats;


public class PlayerResult {
    private Player player;
    private Team team; 
    private int numberOfGoals;

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

    public int getNumberOfGoals() {
        return numberOfGoals;
    }

    public void setNumberOfGoals(int numberOfGoals) {
        this.numberOfGoals = numberOfGoals;
    }

    @Override
    public String toString() {
        return "PlayerResult{" + "player=" + player + ", team=" + team + ", numberOfGoals=" + numberOfGoals + '}';
    }

    public PlayerResult(Player player, Team team, int numberOfGoals) {
        this.player = player;
        this.team = team;
        this.numberOfGoals = numberOfGoals;
    }

    

}