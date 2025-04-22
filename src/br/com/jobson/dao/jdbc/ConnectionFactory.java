package br.com.jobson.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;

    private ConnectionFactory(Connection connection) {

    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = initConnection();
        } else if (connection.isClosed()) {
            connection = initConnection();
        }

        return connection;
    }

    // Efetua a conexão
    private static Connection initConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/vendas_online", "postgres", "12345678");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
