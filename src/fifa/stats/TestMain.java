/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifa.stats;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Mateusz
 */
public class TestMain {
    public static void main(String[] args) {
        // Zakladamy ze istnieja gracze o ID 1 i 2 oraz druzyny o ID 1 i 2


        PlayerRepository playerRepository = new PlayerRepository();
        Player player1 = playerRepository.findById(1);
        Player player2 = playerRepository.findById(2);

        TeamRepository teamRepository = new TeamRepository();
        Team team1 = teamRepository.findById(1);
        Team team2 = teamRepository.findById(2);

        PlayerResult hostResult = new PlayerResult(player1, team1, 7);
        PlayerResult guestResult = new PlayerResult(player2, team2, 2);

        LocalDate.now();

        Match meczStulecia = new Match(hostResult, guestResult, LocalDate.now());

        GamesRepository gameRepository = new GamesRepository();
        gameRepository.insert(meczStulecia);
    }
}
