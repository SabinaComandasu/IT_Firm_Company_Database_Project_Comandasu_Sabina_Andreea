package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectDBOperations {

    public void insertProject(String projectID, String name, String startDate, String endDate, int budget, String departmentID) {
        String checkSql = "SELECT COUNT(*) FROM Project WHERE projectID = ?";
        String sql = "INSERT INTO Project (projectID, name, startDate, endDate, budget, departmentID) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, projectID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                    insertStatement.setString(1, projectID);
                    insertStatement.setString(2, name);
                    insertStatement.setString(3, startDate);
                    insertStatement.setString(4, endDate);
                    insertStatement.setInt(5, budget);
                    insertStatement.setString(6, departmentID);

                    insertStatement.executeUpdate();
                    System.out.println("Project inserted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProject(String projectID, String name, String startDate, String endDate, int budget, String departmentID) {
        String sql = "UPDATE Project SET name = ?, startDate = ?, endDate = ?, budget = ?, departmentID = ? WHERE projectID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, startDate);
            statement.setString(3, endDate);
            statement.setInt(4, budget);
            statement.setString(5, departmentID);
            statement.setString(6, projectID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Error: Project with projectID " + projectID + " does not exist.");
            } else {
                System.out.println("Project updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProject(String projectID) {
        String checkSql = "SELECT COUNT(*) FROM Project WHERE projectID = ?";
        String sql = "DELETE FROM Project WHERE projectID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, projectID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Project with projectID " + projectID + " does not exist.");
            } else {
                try (PreparedStatement deleteStatement = connection.prepareStatement(sql)) {
                    deleteStatement.setString(1, projectID);
                    deleteStatement.executeUpdate();
                    System.out.println("Project deleted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
