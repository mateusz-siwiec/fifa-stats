/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifa.stats.repositories;

import fifa.stats.model.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fifa.stats.PlayerRepository;


public class PlayerRepositoryImpl implements PlayerRepository {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/fifaStats";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    public List<Player> findAll() {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String findAllSQL = "SELECT * FROM PLAYERS";
        List<Player> playerList = new ArrayList<Player>();
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(findAllSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Player player = new Player(id, name, surname);
                playerList.add(player);
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
        return playerList;
    }

    @Override
    public Player findById(int playerId) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String findPlayerById = "SELECT * FROM PLAYERS WHERE ID =" +playerId;
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(findPlayerById);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Player player = new Player(id, name, surname);

                return player;
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
    public Player insert(Player player) {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO PLAYERS"
                + "(NAME, SURNAME) VALUES"
                + "(?,?)";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getSurname());

            // execute insert SQL stetement
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

        return player;
    }

    @Override
    public void removeById(int playerId) {
        
         Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String deletePlayerById = "DELETE FROM PLAYERS WHERE ID= "+playerId;
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(deletePlayerById);
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
    public void update(Player player) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updatePlayerSql = "UPDATE players SET name = ?, surname = ? WHERE id = ?";
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(updatePlayerSql);
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getSurname());
            preparedStatement.setInt(3, player.getId());
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
            throw new RuntimeException(e.getMessage());
        }

        try {

            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }
}
