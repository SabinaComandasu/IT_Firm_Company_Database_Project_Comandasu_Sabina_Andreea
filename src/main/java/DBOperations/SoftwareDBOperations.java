package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SoftwareDBOperations {

    public void insertSoftware(String softwareID, String name, String version, String licenseKey, String supplierID) {
        String checkSql = "SELECT COUNT(*) FROM Software WHERE softwareID = ?";
        String sql = "INSERT INTO Software (softwareID, name, version, licenseKey, supplierID) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, softwareID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("Error: Duplicate key violation - The software with the specified ID already exists.");
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                    insertStatement.setString(1, softwareID);
                    insertStatement.setString(2, name);
                    insertStatement.setString(3, version);
                    insertStatement.setString(4, licenseKey);
                    insertStatement.setString(5, supplierID);

                    insertStatement.executeUpdate();
                    System.out.println("Software inserted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSoftware(String softwareID, String name, String version, String licenseKey, String supplierID) {
        String sql = "UPDATE Software SET name = ?, version = ?, licenseKey = ?, supplierID = ? WHERE softwareID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, version);
            statement.setString(3, licenseKey);
            statement.setString(4, supplierID);
            statement.setString(5, softwareID);

            statement.executeUpdate();
            System.out.println("Software updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSoftware(String softwareID) {
        String checkSql = "SELECT COUNT(*) FROM Software WHERE softwareID = ?";
        String sql = "DELETE FROM Software WHERE softwareID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, softwareID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Software with softwareID " + softwareID + " does not exist.");
            } else {
                try (PreparedStatement deleteStatement = connection.prepareStatement(sql)) {
                    deleteStatement.setString(1, softwareID);

                    deleteStatement.executeUpdate();
                    System.out.println("Software deleted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
