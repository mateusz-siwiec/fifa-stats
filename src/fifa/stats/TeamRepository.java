package fifa.stats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamRepository implements TeamRepo{

     

    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/fifaStats";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    public List<Team> findAll() {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String findAllSQL = "SELECT * FROM TEAMS";
        List<Team> teamList = new ArrayList<Team>();
        try {
            dbConnection = getDBConnection();
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
        String findTeamById = "SELECT * FROM TEAMS WHERE ID =" +teamId;
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(findTeamById);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");          
                Team team = new Team(id, name);

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
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, team.getTeamName());
            

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into teams table!");

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

        return team;
    }

    @Override
    public void removeById(int teamId) {
        
         Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String deleteTeamById = "DELETE FROM TEAMS WHERE ID= "+teamId;
        try {
            dbConnection = getDBConnection();
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



