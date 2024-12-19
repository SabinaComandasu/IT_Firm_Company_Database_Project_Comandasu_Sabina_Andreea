package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentDBOperations {

    public void insertDepartment(String departmentID, String name) {
        String sql = "INSERT INTO Department (departmentID, name) VALUES (?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, departmentID);
            statement.setString(2, name);

            statement.executeUpdate();
            System.out.println("Department inserted successfully.");
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void updateDepartment(String departmentID, String newName) {
        String sql = "UPDATE Department SET name = ? WHERE departmentID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newName);
            statement.setString(2, departmentID);

            statement.executeUpdate();
            System.out.println("Department updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartment(String departmentID) {
        String sql = "DELETE FROM Department WHERE departmentID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, departmentID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Error: Department with departmentID " + departmentID + " does not exist.");
            } else {
                System.out.println("Department deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
