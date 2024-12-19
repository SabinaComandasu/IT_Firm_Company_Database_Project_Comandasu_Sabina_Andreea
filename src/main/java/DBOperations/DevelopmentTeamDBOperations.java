package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DevelopmentTeamDBOperations {

    public void insertDevelopmentTeam(String teamID, String name, String teamLeaderID) {
        String sql = "INSERT INTO DevelopmentTeam (teamID, name, teamLeaderID) VALUES (?, ?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, teamID);
            statement.setString(2, name);
            statement.setString(3, teamLeaderID);

            statement.executeUpdate();
            System.out.println("Development team inserted successfully.");
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void updateDevelopmentTeam(String teamID, String newName, String newTeamLeaderID) {
        String sql = "UPDATE DevelopmentTeam SET name = ?, teamLeaderID = ? WHERE teamID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newName);
            statement.setString(2, newTeamLeaderID);
            statement.setString(3, teamID);

            statement.executeUpdate();
            System.out.println("Development team updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDevelopmentTeam(String teamID) {
        String sql = "DELETE FROM DevelopmentTeam WHERE teamID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, teamID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Error: Development team with teamID " + teamID + " does not exist.");
            } else {
                System.out.println("Development team deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
