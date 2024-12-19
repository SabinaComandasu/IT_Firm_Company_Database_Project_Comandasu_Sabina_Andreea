package DBOperations;
import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class TaskDBOperations {
    public void insertTask(String taskID, String name, String description, String projectID, String assignedTeamID, String status, String startline, String deadline) {
        String checkSql = "SELECT COUNT(*) FROM Task WHERE taskID = ?";
        String sql = "INSERT INTO Task (taskID, name, description, projectID, assignedTeamID, status, startline, deadline) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, taskID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("Error: Duplicate key violation - The task with the specified ID already exists.");
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                    insertStatement.setString(1, taskID);
                    insertStatement.setString(2, name);
                    insertStatement.setString(3, description);
                    insertStatement.setString(4, projectID);
                    insertStatement.setString(5, assignedTeamID);
                    insertStatement.setString(6, status);
                    insertStatement.setString(7, startline);
                    insertStatement.setString(8, deadline);

                    insertStatement.executeUpdate();
                    System.out.println("Task inserted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTask(String taskID, String newName, String newDescription, String newStatus, String newStartline, String newDeadline) {
        String sql = "UPDATE Task SET name = ?, description = ?, status = ?, startline = ?, deadline = ? WHERE taskID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newName);
            statement.setString(2, newDescription);
            statement.setString(3, newStatus);
            statement.setString(4, newStartline);
            statement.setString(5, newDeadline);
            statement.setString(6, taskID);

            statement.executeUpdate();
            System.out.println("Task updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(String taskID) {
        String checkSql = "SELECT COUNT(*) FROM Task WHERE taskID = ?";
        String sql = "DELETE FROM Task WHERE taskID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, taskID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Task with taskID " + taskID + " does not exist.");
            } else {
                try (PreparedStatement deleteStatement = connection.prepareStatement(sql)) {
                    deleteStatement.setString(1, taskID);

                    deleteStatement.executeUpdate();
                    System.out.println("Task deleted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}