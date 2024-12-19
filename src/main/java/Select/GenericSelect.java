package Select;

import ConexiunePentruPostgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericSelect {

    public void selectByID(String table, String idColumn, String idValue, String[] columns) {
        StringBuilder sql = new StringBuilder("SELECT ");

        for (int i = 0; i < columns.length; i++) {
            sql.append(columns[i]);
            if (i < columns.length - 1) {
                sql.append(", ");
            }
        }

        sql.append(" FROM ").append(table).append(" WHERE ").append(idColumn).append(" = ?");

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            statement.setString(1, idValue);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                for (String column : columns) {
                    String value = resultSet.getString(column);
                    System.out.println(column + ": " + value);
                }
            } else {
                System.out.println("Record with " + idColumn + " = " + idValue + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
