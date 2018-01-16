/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifa.stats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PlayerRepository implements PlayerRepo {

    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/fifaStats";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

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

            System.out.println("Record is inserted into players table!");

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

        return player;
    }

    @Override
    public void removeById(int playerId) {
        throw new NotImplementedException();
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
