package com.revisaoWeb.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConnection {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPass;

    @Value("${db.pool.max-size}")
    private String poolHikariConn;

    private static HikariDataSource dataSource;

    @PostConstruct
    public void init() {
        try {
            HikariConfig config = new HikariConfig();

            //Banco e usuario
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(dbUser);
            config.setPassword(dbPass);

            //Pool
            config.setMaximumPoolSize(Integer.parseInt(poolHikariConn, 10));
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

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void shutdown() {
        if (dataSource != null){
            dataSource.close();
        }
    }
}
