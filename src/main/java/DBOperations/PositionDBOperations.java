package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionDBOperations {

    public void insertPosition(String positionID, String name, String description) {
        String checkSql = "SELECT COUNT(*) FROM Position WHERE positionID = ?";
        String sql = "INSERT INTO Position (positionID, name, description) VALUES (?, ?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, positionID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                    insertStatement.setString(1, positionID);
                    insertStatement.setString(2, name);
                    insertStatement.setString(3, description);

                    insertStatement.executeUpdate();
                    System.out.println("Position inserted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePosition(String positionID, String newName, String newDescription) {
        String sql = "UPDATE Position SET name = ?, description = ? WHERE positionID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newName);
            statement.setString(2, newDescription);
            statement.setString(3, positionID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Error: Position with positionID " + positionID + " does not exist.");
            } else {
                System.out.println("Position updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePosition(String positionID) {
        String checkSql = "SELECT COUNT(*) FROM Position WHERE positionID = ?";
        String sql = "DELETE FROM Position WHERE positionID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, positionID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Position with positionID " + positionID + " does not exist.");
            } else {
                try (PreparedStatement deleteStatement = connection.prepareStatement(sql)) {
                    deleteStatement.setString(1, positionID);
                    deleteStatement.executeUpdate();
                    System.out.println("Position deleted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
