package fifa.stats;

import javafx.scene.control.TextField;

public class Team {

    private Integer id;
    private String teamName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Team(Integer id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "Team{" + "id=" + id + ", teamName=" + teamName + '}';
    }

    public Team(String teamName) {

        this.teamName = teamName;
    }

}
