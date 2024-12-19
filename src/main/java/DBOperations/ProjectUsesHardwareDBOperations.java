package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectUsesHardwareDBOperations {

    public void insertProjectUsesHardware(String projectID, String hardwareID) {
        String checkSql = "SELECT COUNT(*) FROM ProjectUsesHardware WHERE projectID = ? AND hardwareID = ?";
        String sql = "INSERT INTO ProjectUsesHardware (projectID, hardwareID) VALUES (?, ?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, projectID);
            checkStatement.setString(2, hardwareID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                    insertStatement.setString(1, projectID);
                    insertStatement.setString(2, hardwareID);

                    insertStatement.executeUpdate();
                    System.out.println("Project-Hardware relationship inserted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProjectUsesHardware(String projectID, String hardwareID) {
        String checkSql = "SELECT COUNT(*) FROM ProjectUsesHardware WHERE projectID = ? AND hardwareID = ?";
        String sql = "DELETE FROM ProjectUsesHardware WHERE projectID = ? AND hardwareID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, projectID);
            checkStatement.setString(2, hardwareID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Project with projectID " + projectID + " and hardware with hardwareID " + hardwareID + " does not exist.");
            } else {
                try (PreparedStatement deleteStatement = connection.prepareStatement(sql)) {
                    deleteStatement.setString(1, projectID);
                    deleteStatement.setString(2, hardwareID);

                    deleteStatement.executeUpdate();
                    System.out.println("Project-Hardware relationship deleted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProjectUsesHardware(String oldProjectID, String oldHardwareID, String newProjectID, String newHardwareID) {
        String checkSql = "SELECT COUNT(*) FROM ProjectUsesHardware WHERE projectID = ? AND hardwareID = ?";
        String sql = "UPDATE ProjectUsesHardware SET projectID = ?, hardwareID = ? WHERE projectID = ? AND hardwareID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, oldProjectID);
            checkStatement.setString(2, oldHardwareID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Project with projectID " + oldProjectID + " and hardware with hardwareID " + oldHardwareID + " does not exist.");
            } else {
                try (PreparedStatement updateStatement = connection.prepareStatement(sql)) {
                    updateStatement.setString(1, newProjectID);
                    updateStatement.setString(2, newHardwareID);
                    updateStatement.setString(3, oldProjectID);
                    updateStatement.setString(4, oldHardwareID);

                    updateStatement.executeUpdate();
                    System.out.println("Project-Hardware relationship updated successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
