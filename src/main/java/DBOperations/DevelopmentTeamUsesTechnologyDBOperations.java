package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DevelopmentTeamUsesTechnologyDBOperations {

    public void insertDevelopmentTeamUsesTechnology(String teamID, String technologyID) {
        String sql = "INSERT INTO DevelopmentTeamUsesTechnology (teamID, technologyID) VALUES (?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, teamID);
            statement.setString(2, technologyID);

            statement.executeUpdate();
            System.out.println("Development team-technology relationship inserted successfully.");
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void deleteDevelopmentTeamUsesTechnology(String teamID, String technologyID) {
        String sql = "DELETE FROM DevelopmentTeamUsesTechnology WHERE teamID = ? AND technologyID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, teamID);
            statement.setString(2, technologyID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Error: Development team " + teamID + " does not use technology " + technologyID + ".");
            } else {
                System.out.println("Development team-technology relationship deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDevelopmentTeamUsesTechnology(String oldTeamID, String oldTechnologyID, String newTeamID, String newTechnologyID) {
        String sql = "UPDATE DevelopmentTeamUsesTechnology SET teamID = ?, technologyID = ? WHERE teamID = ? AND technologyID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newTeamID);
            statement.setString(2, newTechnologyID);
            statement.setString(3, oldTeamID);
            statement.setString(4, oldTechnologyID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Error: Relationship between team " + oldTeamID + " and technology " + oldTechnologyID + " does not exist.");
            } else {
                System.out.println("Development team-technology relationship updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
