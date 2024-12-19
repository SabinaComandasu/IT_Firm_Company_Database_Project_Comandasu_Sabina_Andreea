package DBOperations;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class ClientDBOperations {

    public void insertClient(String clientID, String name, String industry, String email, String phoneNumber) {
        String sql = "INSERT INTO Client (clientID, name, industry, email, phoneNumber) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, clientID);
            statement.setString(2, name);
            statement.setString(3, industry);
            statement.setString(4, email);
            statement.setString(5, phoneNumber);

            statement.executeUpdate();
            System.out.println("Client inserted successfully.");
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                System.err.println("Error: Duplicate key violation - The record with the specified key already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void updateClient(String oldClientID, String newName, String newIndustry, String newEmail, String newPhoneNumber) {
        String checkSql = "SELECT COUNT(*) FROM Client WHERE clientID = ?";
        String sql = "UPDATE Client SET name = ?, industry = ?, email = ?, phoneNumber = ? WHERE clientID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setString(1, oldClientID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.err.println("Error: Client with clientID " + oldClientID + " does not exist.");
            } else {
                try (PreparedStatement updateStatement = connection.prepareStatement(sql)) {
                    updateStatement.setString(1, newName);
                    updateStatement.setString(2, newIndustry);
                    updateStatement.setString(3, newEmail);
                    updateStatement.setString(4, newPhoneNumber);
                    updateStatement.setString(5, oldClientID);

                    updateStatement.executeUpdate();
                    System.out.println("Client updated successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(String clientID) {
        String sql = "DELETE FROM Client WHERE clientID = ?";

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, clientID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Error: Client with clientID " + clientID + " does not exist.");
            } else {
                System.out.println("Client deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
