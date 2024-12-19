package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDBOperations {

    public void insertEmployee(String employeeID, String firstName, String lastName, String phoneNumber,
                               String address, String positionid, String departmentID, int salary) {
        String sql = "INSERT INTO Employee (employeeID, firstName, lastName, phoneNumber, address, positionid, departmentID, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, employeeID);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, phoneNumber);
            statement.setString(5, address);
            statement.setString(6, positionid);
            statement.setString(7, departmentID);
            statement.setInt(8, salary);

            statement.executeUpdate();
            System.out.println("Employee inserted successfully.");
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void updateEmployeeSalary(String employeeID, int newSalary) {
        String sql = "UPDATE Employee SET salary = ? WHERE employeeID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, newSalary);
            statement.setString(2, employeeID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Error: Employee with ID " + employeeID + " does not exist.");
            } else {
                System.out.println("Employee salary updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(String employeeID) {
        String sql = "DELETE FROM Employee WHERE employeeID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, employeeID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Error: Employee with ID " + employeeID + " does not exist.");
            } else {
                System.out.println("Employee deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
