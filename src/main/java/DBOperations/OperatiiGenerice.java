
package DBOperations;
import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperatiiGenerice {

    public static void insert(String table, String[] columns, Object[] values) {
        if (columns.length != values.length) {
            throw new IllegalArgumentException("Columns and values must have the same length.");
        }

        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(table).append(" (");

        for (int i = 0; i < columns.length; i++) {
            sql.append(columns[i]);
            if (i < columns.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(") VALUES (");

        for (int i = 0; i < columns.length; i++) {
            sql.append("?");
            if (i < columns.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Record inserted successfully into " + table);
            }

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                System.err.println("Error: Duplicate key violation for table '" + table + "'. " +
                        "The record with the specified key already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }


    public static void update(String table, String[] columns, Object[] values, String condition) {
        if (columns.length != values.length) {
            throw new IllegalArgumentException("Columns and values must have the same length.");
        }

        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(table).append(" SET ");

        // Build the SET clause
        for (int i = 0; i < columns.length; i++) {
            sql.append(columns[i]).append(" = ?");
            if (i < columns.length - 1) {
                sql.append(", ");
            }
        }

        sql.append(" WHERE ").append(condition);

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            // Set the values for the placeholders
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }

            // Execute the update statement
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Record updated successfully in " + table);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String table, String condition) {
        String sql = "DELETE FROM " + table + " WHERE " + condition;

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Execute the delete statement
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Record deleted successfully from " + table);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
