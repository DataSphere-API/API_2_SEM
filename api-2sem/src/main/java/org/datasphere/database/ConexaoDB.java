package org.datasphere.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoDB {

    private static String url;
    private static String username;
    private static String password;

    public static void loadProperties(){

        Dotenv dotenv = Dotenv.configure().directory("./").filename(".env").load();

        url = dotenv.get("DB_URL");
        username = dotenv.get("DB_USER");
        password = dotenv.get("DB_PASSWORD");

    }

    public static Connection getConexao(){

        loadProperties();

        try{
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
