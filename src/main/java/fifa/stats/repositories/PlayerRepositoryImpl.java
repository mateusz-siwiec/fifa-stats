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

    @Override
    public List<Player> findAll() {

        String findAllSQL = "SELECT * FROM PLAYERS";
        List<Player> playerList = new ArrayList<Player>();
        try (Connection dbConnection = DatabaseConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
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
        }
        return playerList;
    }

    @Override
    public Player findById(int playerId) {

        String findPlayerById = "SELECT * FROM PLAYERS WHERE ID =" + playerId;
        try (Connection dbConnection = DatabaseConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findPlayerById)) {
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
        }
        return null;
    }

    @Override
    public Player insert(Player player) {
        String insertTableSQL = "INSERT INTO PLAYERS"
                + "(NAME, SURNAME) VALUES"
                + "(?,?)";
        try (Connection dbConnection = DatabaseConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getSurname());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return player;
    }

    @Override
    public void removeById(int playerId) {

        String deletePlayerById = "DELETE FROM PLAYERS WHERE ID= " + playerId;
        try (Connection dbConnection = DatabaseConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(deletePlayerById)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Player player) {

        String updatePlayerSql = "UPDATE players SET name = ?, surname = ? WHERE id = ?";
        try (Connection dbConnection = DatabaseConnector.getDBConnection();
           PreparedStatement preparedStatement = dbConnection.prepareStatement(updatePlayerSql)){
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getSurname());
            preparedStatement.setInt(3, player.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
