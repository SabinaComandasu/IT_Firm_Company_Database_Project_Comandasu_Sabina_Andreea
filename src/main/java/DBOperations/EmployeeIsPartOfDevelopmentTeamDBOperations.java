package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeIsPartOfDevelopmentTeamDBOperations {

    public void insertEmployeeIsPartOfDevelopmentTeam(String employeeID, String developmentTeamID) {
        String checkSql = "SELECT COUNT(*) FROM EmployeeIsPartOfDevelopmentTeam WHERE employeeID = ? AND developmentTeamID = ?";
        String sql = "INSERT INTO EmployeeIsPartOfDevelopmentTeam (employeeID, developmentTeamID) VALUES (?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, employeeID);
            checkStatement.setString(2, developmentTeamID);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                    insertStatement.setString(1, employeeID);
                    insertStatement.setString(2, developmentTeamID);

                    insertStatement.executeUpdate();
                    System.out.println("Employee added to development team.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployeeIsPartOfDevelopmentTeam(String employeeID, String developmentTeamID) {
        String checkSql = "SELECT COUNT(*) FROM EmployeeIsPartOfDevelopmentTeam WHERE employeeID = ? AND developmentTeamID = ?";
        String sql = "DELETE FROM EmployeeIsPartOfDevelopmentTeam WHERE employeeID = ? AND developmentTeamID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, employeeID);
            checkStatement.setString(2, developmentTeamID);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Employee with employeeID " + employeeID + " is not part of development team " + developmentTeamID + ".");
            } else {
                try (PreparedStatement deleteStatement = connection.prepareStatement(sql)) {
                    deleteStatement.setString(1, employeeID);
                    deleteStatement.setString(2, developmentTeamID);

                    deleteStatement.executeUpdate();
                    System.out.println("Employee removed from development team.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
