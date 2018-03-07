package fifa.stats.repositories;

import fifa.stats.model.Match;
import fifa.stats.PlayerResult;
import fifa.stats.repositories.PlayerRepositoryImpl;
import fifa.stats.repositories.TeamRepositoryImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fifa.stats.MatchRepository;

public class MatchRepositoryImpl implements MatchRepository {

    @Override
    public List<Match> findAll() {


        String findAllSQL = "SELECT * FROM GAMES";
        List<Match> matchList = new ArrayList<Match>();
        try (Connection dbConnection = DatabaseConnector.getDBConnection();
           PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int hostPlayerId = rs.getInt("host_player_id");
                int hostTeamId = rs.getInt("host_team_id");
                int hostScore = rs.getInt("host_score");
                int guestPlayerId = rs.getInt("guest_player_id");
                int guestTeamId = rs.getInt("guest_team_id");
                int guestScore = rs.getInt("guest_score");
                PlayerRepositoryImpl playerRepository = new PlayerRepositoryImpl();
                TeamRepositoryImpl teamRepository = new TeamRepositoryImpl();
                LocalDate.now();
                PlayerResult hostPlayer = new PlayerResult(playerRepository.findById(hostPlayerId), teamRepository.findById(hostTeamId), hostScore);
                PlayerResult guestPlayer = new PlayerResult(playerRepository.findById(guestPlayerId), teamRepository.findById(guestTeamId), guestScore);

                Match match = new Match(id, hostPlayer, guestPlayer, LocalDate.now());
                matchList.add(match);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matchList;

    }

    @Override
    public Match findById(int gameId) {
        return null;
    }

    @Override
    public void insert(Match match) {
        String insertTableSQL = "INSERT INTO GAMES"
                + "(HOST_PLAYER_ID, HOST_TEAM_ID,HOST_SCORE,GUEST_PLAYER_ID,GUEST_TEAM_ID,GUEST_SCORE,DATE) VALUES"
                + "(?,?,?,?,?,?,?)";

        try (Connection dbConnection = DatabaseConnector.getDBConnection();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)){
                preparedStatement.setInt(1, match.getHostResult().getPlayer().getId());
                preparedStatement.setInt(2, match.getHostResult().getTeam().getId());
                preparedStatement.setInt(3, match.getHostResult().getNumberOfGoals());
                preparedStatement.setInt(4, match.getGuestResult().getPlayer().getId());
                preparedStatement.setInt(5, match.getGuestResult().getTeam().getId());
                preparedStatement.setInt(6, match.getGuestResult().getNumberOfGoals());
                preparedStatement.setDate(7, Date.valueOf(match.getDateOfTheMatch()));
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeById(int gameId) {
        String deleteGameByIdStatement = "DELETE FROM GAMES WHERE ID = ?";
        try (Connection dbConnection = DatabaseConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(deleteGameByIdStatement)) {
            preparedStatement.setInt(1, gameId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
