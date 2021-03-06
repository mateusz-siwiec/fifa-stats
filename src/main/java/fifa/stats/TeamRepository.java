package fifa.stats;

import fifa.stats.model.Team;
import java.util.List;

public interface TeamRepository {

    List<Team> findAll();

    Team findById(int teamId);

    Team insert(Team team);

    void removeById(int teamId);

    void update(Team team);

}
