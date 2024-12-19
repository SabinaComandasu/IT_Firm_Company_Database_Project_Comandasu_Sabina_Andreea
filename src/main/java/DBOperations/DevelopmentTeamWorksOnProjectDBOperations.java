package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DevelopmentTeamWorksOnProjectDBOperations {

    public void insertDevelopmentTeamWorksOnProject(String teamID, String projectID) {
        String sql = "INSERT INTO DevelopmentTeamWorksOnProject (teamID, projectID) VALUES (?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, teamID);
            statement.setString(2, projectID);

            statement.executeUpdate();
            System.out.println("Development team-project relationship inserted successfully.");
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void deleteDevelopmentTeamWorksOnProject(String teamID, String projectID) {
        String sql = "DELETE FROM DevelopmentTeamWorksOnProject WHERE teamID = ? AND projectID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, teamID);
            statement.setString(2, projectID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Error: Development team " + teamID + " is not working on project " + projectID + ".");
            } else {
                System.out.println("Development team-project relationship deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDevelopmentTeamWorksOnProject(String oldTeamID, String oldProjectID, String newTeamID, String newProjectID) {
        String sql = "UPDATE DevelopmentTeamWorksOnProject SET teamID = ?, projectID = ? WHERE teamID = ? AND projectID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newTeamID);
            statement.setString(2, newProjectID);
            statement.setString(3, oldTeamID);
            statement.setString(4, oldProjectID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Error: Relationship between team " + oldTeamID + " and project " + oldProjectID + " does not exist.");
            } else {
                System.out.println("Development team-project relationship updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
