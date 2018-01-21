
package fifa.stats;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GamesRepository implements GamesRepo {

    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/fifaStats";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    public List<Match> findAll() {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String findAllSQL = "SELECT * FROM GAMES";
        List<Match> matchList = new ArrayList<Match>();
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(findAllSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int hostPlayerId = rs.getInt("host_player_id");
                int hostTeamId = rs.getInt("host_team_id");
                int hostScore = rs.getInt("host_score");
                int guestPlayerId = rs.getInt("guest_player_id");
                int guestTeamId = rs.getInt("guest_team_id");
                int guestScore = rs.getInt("guest_score");
                PlayerRepository playerRepository = new PlayerRepository();
                TeamRepository teamRepository = new TeamRepository();
                LocalDate.now();
                PlayerResult hostPlayer = new PlayerResult(playerRepository.findById(hostPlayerId), teamRepository.findById(hostTeamId), hostScore);
                PlayerResult guestPlayer = new PlayerResult(playerRepository.findById(guestPlayerId), teamRepository.findById(guestTeamId), guestScore);

                Match match = new Match( id, hostPlayer, guestPlayer, LocalDate.now());
                matchList.add(match);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return matchList;

    }

    @Override
    public Match findById(int gameId) {
        return null;
    }

    @Override
    public void insert(Match match) {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO GAMES"
                + "(HOST_PLAYER_ID, HOST_TEAM_ID,HOST_SCORE,GUEST_PLAYER_ID,GUEST_TEAM_ID,GUEST_SCORE,DATE) VALUES"
                + "(?,?,?,?,?,?,?)";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setInt(1, match.getHostResult().getPlayer().getId());
            preparedStatement.setInt(2, match.getHostResult().getTeam().getId());
            preparedStatement.setInt(3, match.getHostResult().getNumberOfGoals());
            preparedStatement.setInt(4, match.getGuestResult().getPlayer().getId());
            preparedStatement.setInt(5, match.getGuestResult().getTeam().getId());
            preparedStatement.setInt(6, match.getGuestResult().getNumberOfGoals());
            preparedStatement.setDate(7, Date.valueOf(match.getDateOfTheMatch()));

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into games table!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

    }

    @Override
    public void removeById(int gameId) {
        
         Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String deleteGameById = "DELETE FROM GAMES WHERE ID= "+gameId;
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(deleteGameById);
            preparedStatement.executeUpdate();
        
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        } 
    }

    private static Connection getDBConnection() {

        Connection dbConnection = null;

        try {

            Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {

            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }

    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

}
