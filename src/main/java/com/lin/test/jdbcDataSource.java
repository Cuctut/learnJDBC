package com.lin.test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class jdbcDataSource {
    public static void main(String[] args) {
        String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/learnjdbc?useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "1234";

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(JDBC_USER);
        config.setPassword(JDBC_PASSWORD);
        config.setConnectionTimeout(1000);   // 连接超时：1秒
        config.setIdleTimeout(60000);        // 空闲超时：60秒
        config.setMaximumPoolSize(10);       // 最大连接数：10

        HikariDataSource dataSource = new HikariDataSource();

        try (Connection conn = dataSource.getConnection()) { // 在此获取连接
            //do something ...
            conn.close(); //不是真正“关闭”连接，而是释放到连接池中，以便下次获取连接时能直接返回
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
