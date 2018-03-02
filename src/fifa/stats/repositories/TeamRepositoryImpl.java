
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

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String findAllSQL = "SELECT * FROM TEAMS";
        List<Team> teamList = new ArrayList<Team>();
        try {
            dbConnection = DatabaseConnector.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(findAllSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                Team team = new Team(id, name);
                teamList.add(team);
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
        return teamList;
    }

    @Override
    public Team findById(int teamId) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String findPlayerById = "SELECT * FROM TEAMS WHERE ID =" +teamId;
        try {
            dbConnection = DatabaseConnector.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(findPlayerById);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                Team team = new Team( name);

                return team;
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
        return null;

    }

    @Override
    public Team insert(Team team) {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO TEAMS"
                + "(NAME) VALUES"
                + "(?)";

        try {
            dbConnection = DatabaseConnector.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);         
            preparedStatement.setString(1, team.getTeamName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
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
        return team;
    }

    @Override
    public void removeById(int teamId) {
        
         Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String deleteTeamById = "DELETE FROM TEAMS WHERE ID= "+teamId;
        try {
            dbConnection = DatabaseConnector.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(deleteTeamById);
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
    
    @Override
    public void update(Team team) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateTeamSql = "UPDATE teams SET name = ? WHERE id = ?";
        try {
            dbConnection = DatabaseConnector.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(updateTeamSql);
            preparedStatement.setString(1, team.getTeamName());       
            preparedStatement.setInt(2, team.getId());
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
}
