package fifa.stats;

import fifa.stats.model.Match;
import java.util.List;

public interface MatchRepository {

    List<Match> findAll();

    Match findById(int gameId);

    void insert(Match game);

    void removeById(int gameId);
}
