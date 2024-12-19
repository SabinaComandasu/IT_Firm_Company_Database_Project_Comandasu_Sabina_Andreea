package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectUsesTechnologyDBOperations {

    public void insertProjectUsesTechnology(String projectID, String technologyID) {
        String checkSql = "SELECT COUNT(*) FROM ProjectUsesTechnology WHERE projectID = ? AND technologyID = ?";
        String sql = "INSERT INTO ProjectUsesTechnology (projectID, technologyID) VALUES (?, ?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, projectID);
            checkStatement.setString(2, technologyID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                    insertStatement.setString(1, projectID);
                    insertStatement.setString(2, technologyID);

                    insertStatement.executeUpdate();
                    System.out.println("Project-Technology relationship inserted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProjectUsesTechnology(String projectID, String technologyID) {
        String checkSql = "SELECT COUNT(*) FROM ProjectUsesTechnology WHERE projectID = ? AND technologyID = ?";
        String sql = "DELETE FROM ProjectUsesTechnology WHERE projectID = ? AND technologyID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, projectID);
            checkStatement.setString(2, technologyID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Project with projectID " + projectID + " and technology with technologyID " + technologyID + " does not exist.");
            } else {
                try (PreparedStatement deleteStatement = connection.prepareStatement(sql)) {
                    deleteStatement.setString(1, projectID);
                    deleteStatement.setString(2, technologyID);

                    deleteStatement.executeUpdate();
                    System.out.println("Project-Technology relationship deleted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProjectUsesTechnology(String oldProjectID, String oldTechnologyID, String newProjectID, String newTechnologyID) {
        String checkSql = "SELECT COUNT(*) FROM ProjectUsesTechnology WHERE projectID = ? AND technologyID = ?";
        String sql = "UPDATE ProjectUsesTechnology SET projectID = ?, technologyID = ? WHERE projectID = ? AND technologyID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, oldProjectID);
            checkStatement.setString(2, oldTechnologyID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Project with projectID " + oldProjectID + " and technology with technologyID " + oldTechnologyID + " does not exist.");
            } else {
                try (PreparedStatement updateStatement = connection.prepareStatement(sql)) {
                    updateStatement.setString(1, newProjectID);
                    updateStatement.setString(2, newTechnologyID);
                    updateStatement.setString(3, oldProjectID);
                    updateStatement.setString(4, oldTechnologyID);

                    updateStatement.executeUpdate();
                    System.out.println("Project-Technology relationship updated successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
