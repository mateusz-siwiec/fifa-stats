
package fifa.stats;

import fifa.stats.model.Player;
import java.util.List;

public interface PlayerRepository {

    List<Player> findAll();

    Player findById(int playerId);

    Player insert(Player player);

    void removeById(int playerId);

    void update(Player player);
}
