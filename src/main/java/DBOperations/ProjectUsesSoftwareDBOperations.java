package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectUsesSoftwareDBOperations {

    public void insertProjectUsesSoftware(String projectID, String softwareID) {
        String checkSql = "SELECT COUNT(*) FROM ProjectUsesSoftware WHERE projectID = ? AND softwareID = ?";
        String sql = "INSERT INTO ProjectUsesSoftware (projectID, softwareID) VALUES (?, ?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, projectID);
            checkStatement.setString(2, softwareID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                    insertStatement.setString(1, projectID);
                    insertStatement.setString(2, softwareID);

                    insertStatement.executeUpdate();
                    System.out.println("Project-Software relationship inserted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProjectUsesSoftware(String projectID, String softwareID) {
        String checkSql = "SELECT COUNT(*) FROM ProjectUsesSoftware WHERE projectID = ? AND softwareID = ?";
        String sql = "DELETE FROM ProjectUsesSoftware WHERE projectID = ? AND softwareID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, projectID);
            checkStatement.setString(2, softwareID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Project with projectID " + projectID + " and software with softwareID " + softwareID + " does not exist.");
            } else {
                try (PreparedStatement deleteStatement = connection.prepareStatement(sql)) {
                    deleteStatement.setString(1, projectID);
                    deleteStatement.setString(2, softwareID);

                    deleteStatement.executeUpdate();
                    System.out.println("Project-Software relationship deleted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProjectUsesSoftware(String oldProjectID, String oldSoftwareID, String newProjectID, String newSoftwareID) {
        String checkSql = "SELECT COUNT(*) FROM ProjectUsesSoftware WHERE projectID = ? AND softwareID = ?";
        String sql = "UPDATE ProjectUsesSoftware SET projectID = ?, softwareID = ? WHERE projectID = ? AND softwareID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, oldProjectID);
            checkStatement.setString(2, oldSoftwareID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Project with projectID " + oldProjectID + " and software with softwareID " + oldSoftwareID + " does not exist.");
            } else {
                try (PreparedStatement updateStatement = connection.prepareStatement(sql)) {
                    updateStatement.setString(1, newProjectID);
                    updateStatement.setString(2, newSoftwareID);
                    updateStatement.setString(3, oldProjectID);
                    updateStatement.setString(4, oldSoftwareID);

                    updateStatement.executeUpdate();
                    System.out.println("Project-Software relationship updated successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
