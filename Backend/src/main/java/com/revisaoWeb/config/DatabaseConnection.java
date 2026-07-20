package com.revisaoWeb.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Properties;

public class DatabaseConnection {

    private static HikariDataSource dataSource;

    static {
        try {
            Properties props = loadProperties();
            HikariConfig config = new HikariConfig();

            //Banco e usuario
            config.setJdbcUrl(props.getProperty("db.url"));
            config.setUsername(props.getProperty("db.user"));
            config.setPassword(props.getProperty("db.pass"));

            //Pool
            config.setMaximumPoolSize(Integer.parseInt(props.getProperty("db.pool.max-size", "10")));
            config.setMinimumIdle(2);
            config.setIdleTimeout(30000);
            config.setConnectionTimeout(30000);

            //Otimizações mysql
            config.addDataSourceProperty("cachePrepStmts" , "true");
            config.addDataSourceProperty("prepStmtCacheSize" , "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048");

            dataSource = new HikariDataSource(config);

        } catch (Exception e) {
            throw new RuntimeException("Falha ao inicialiar o pool de conexões do banco de dados. ", e);
        }
    }

    public static Properties loadProperties() {
        Properties props = new Properties();

        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException ("Desculpe, não conseguimos encontrar o arquivo db.properties");
            }

            props.load(input);
            return props;

        } catch (IOException ex) {
            throw new RuntimeException ("Erro ao carregar configurações do banco de dados.", ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void shutdown() {
        if (dataSource != null){
            dataSource.close();
        }
    }
}
