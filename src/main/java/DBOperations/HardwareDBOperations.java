package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HardwareDBOperations {

    public void insertHardware(String hardwareID, String hardwareType, String model, String manufacturerID) {
        String checkSql = "SELECT COUNT(*) FROM Hardware WHERE hardwareID = ?";
        String sql = "INSERT INTO Hardware (hardwareID, hardwareType, model, manufacturerID) VALUES (?, ?, ?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, hardwareID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                    insertStatement.setString(1, hardwareID);
                    insertStatement.setString(2, hardwareType);
                    insertStatement.setString(3, model);
                    insertStatement.setString(4, manufacturerID);

                    insertStatement.executeUpdate();
                    System.out.println("Hardware inserted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateHardware(String hardwareID, String hardwareType, String model, String manufacturerID) {
        String sql = "UPDATE Hardware SET hardwareType = ?, model = ?, manufacturerID = ? WHERE hardwareID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, hardwareType);
            statement.setString(2, model);
            statement.setString(3, manufacturerID);
            statement.setString(4, hardwareID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Error: Hardware with ID " + hardwareID + " does not exist.");
            } else {
                System.out.println("Hardware updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHardware(String hardwareID) {
        String checkSql = "SELECT COUNT(*) FROM Hardware WHERE hardwareID = ?";
        String sql = "DELETE FROM Hardware WHERE hardwareID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, hardwareID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Hardware with ID " + hardwareID + " does not exist.");
            } else {
                try (PreparedStatement deleteStatement = connection.prepareStatement(sql)) {
                    deleteStatement.setString(1, hardwareID);
                    deleteStatement.executeUpdate();
                    System.out.println("Hardware deleted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
