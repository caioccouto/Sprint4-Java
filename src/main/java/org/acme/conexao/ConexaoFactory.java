package org.acme.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");

        return DriverManager.getConnection(url, user, pwd);
    }
}