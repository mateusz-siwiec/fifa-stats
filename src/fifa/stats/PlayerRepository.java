/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifa.stats;

import java.util.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PlayerRepository implements PlayerRepo {

    @Override
    public List<Player> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public Player findById(int playerId) {
        throw new NotImplementedException();
    }

    @Override
    public Player insert(Player player) {
        return player;
    }

    @Override
    public void removeById(int playerId) {
        throw new NotImplementedException();
    }

}
