
package fifa.stats.repositories;

import fifa.stats.model.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fifa.stats.TeamRepository;


public class TeamRepositoryImpl implements TeamRepository {

    @Override
    public List<Team> findAll() {


        String findAllSQL = "SELECT * FROM TEAMS";
        List<Team> teamList = new ArrayList<Team>();
        try (Connection dbConnection = DatabaseConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Team team = new Team(id, name);
                teamList.add(team);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teamList;
    }

    @Override
    public Team findById(int teamId) {
        String findPlayerById = "SELECT * FROM TEAMS WHERE ID =" + teamId;
        try (Connection dbConnection = DatabaseConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findPlayerById)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Team team = new Team(name);

                return team;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Team insert(Team team) {
        String insertTableSQL = "INSERT INTO TEAMS"
                + "(NAME) VALUES"
                + "(?)";

        try (Connection dbConnection = DatabaseConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setString(1, team.getTeamName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return team;
    }

    @Override
    public void removeById(int teamId) {


        String deleteTeamById = "DELETE FROM TEAMS WHERE ID= " + teamId;
        try (Connection dbConnection = DatabaseConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(deleteTeamById)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Team team) {
        String updateTeamSql = "UPDATE teams SET name = ? WHERE id = ?";
        try (Connection dbConnection = DatabaseConnector.getDBConnection();
           PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTeamSql)){
            preparedStatement.setString(1, team.getTeamName());
            preparedStatement.setInt(2, team.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
