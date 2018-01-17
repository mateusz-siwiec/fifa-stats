/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifa.stats;

import java.util.List;

/**
 *
 * @author Mateusz
 */
public class testMain {
    public static void main(String[] args) {
        List<Player> allPlayers = new PlayerRepository().findAll();
        for (Player player : allPlayers) {  
  System.out.println(player);
}
    }
}
