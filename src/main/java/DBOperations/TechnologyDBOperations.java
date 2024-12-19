package DBOperations;
import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class TechnologyDBOperations {
    public void insertTechnology(String technologyID, String name, String version, String licenseType) {
        String checkSql = "SELECT COUNT(*) FROM Technology WHERE technologyID = ?";
        String sql = "INSERT INTO Technology (technologyID, name, version, licenseType) VALUES (?, ?, ?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, technologyID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("Error: Duplicate key violation - The technology with the specified ID already exists.");
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                    insertStatement.setString(1, technologyID);
                    insertStatement.setString(2, name);
                    insertStatement.setString(3, version);
                    insertStatement.setString(4, licenseType);

                    insertStatement.executeUpdate();
                    System.out.println("Technology inserted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTechnology(String technologyID, String newName, String newVersion, String newLicenseType) {
        String sql = "UPDATE Technology SET name = ?, version = ?, licenseType = ? WHERE technologyID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newName);
            statement.setString(2, newVersion);
            statement.setString(3, newLicenseType);
            statement.setString(4, technologyID);

            statement.executeUpdate();
            System.out.println("Technology updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTechnology(String technologyID) {
        String checkSql = "SELECT COUNT(*) FROM Technology WHERE technologyID = ?";
        String sql = "DELETE FROM Technology WHERE technologyID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, technologyID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Technology with technologyID " + technologyID + " does not exist.");
            } else {
                try (PreparedStatement deleteStatement = connection.prepareStatement(sql)) {
                    deleteStatement.setString(1, technologyID);

                    deleteStatement.executeUpdate();
                    System.out.println("Technology deleted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
