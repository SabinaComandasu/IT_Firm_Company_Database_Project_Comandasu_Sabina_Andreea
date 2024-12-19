package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManufacturerDBOperations {

    public void insertManufacturer(String manufacturerID, String name, String email, String phoneNumber, String address) {
        String checkSql = "SELECT COUNT(*) FROM Manufacturer WHERE manufacturerID = ?";
        String sql = "INSERT INTO Manufacturer (manufacturerID, name, email, phoneNumber, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, manufacturerID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                    insertStatement.setString(1, manufacturerID);
                    insertStatement.setString(2, name);
                    insertStatement.setString(3, email);
                    insertStatement.setString(4, phoneNumber);
                    insertStatement.setString(5, address);

                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateManufacturer(String manufacturerID, String name, String email, String phoneNumber, String address) {
        String sql = "UPDATE Manufacturer SET name = ?, email = ?, phoneNumber = ?, address = ? WHERE manufacturerID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phoneNumber);
            statement.setString(4, address);
            statement.setString(5, manufacturerID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Error: Manufacturer with manufacturerID " + manufacturerID + " does not exist.");
            } else {
                System.out.println("Manufacturer updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteManufacturer(String manufacturerID) {
        String checkSql = "SELECT COUNT(*) FROM Manufacturer WHERE manufacturerID = ?";
        String sql = "DELETE FROM Manufacturer WHERE manufacturerID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, manufacturerID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Manufacturer with manufacturerID " + manufacturerID + " does not exist.");
            } else {
                try (PreparedStatement deleteStatement = connection.prepareStatement(sql)) {
                    deleteStatement.setString(1, manufacturerID);
                    deleteStatement.executeUpdate();
                    System.out.println("Manufacturer deleted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
