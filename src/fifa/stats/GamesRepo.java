
package fifa.stats;

import java.util.List;

public interface GamesRepo {

        List<Match> findAll();

        Match findById(int gameId);

        void insert(Match game);

        void removeById(int gameId);
    }

