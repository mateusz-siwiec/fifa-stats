/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifa.stats;

import java.util.List;

public interface PlayerRepository {

    List<Player> findAll();

    Player findById(int playerId);

    Player insert(Player player);

    void removeById(int playerId);

    void update(Player player);
}
