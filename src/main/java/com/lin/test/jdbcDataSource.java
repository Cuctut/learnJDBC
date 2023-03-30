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
        config.setConnectionTimeout(1000);   // ���ӳ�ʱ��1��
        config.setIdleTimeout(60000);        // ���г�ʱ��60��
        config.setMaximumPoolSize(10);       // �����������10

        HikariDataSource dataSource = new HikariDataSource();

        try (Connection conn = dataSource.getConnection()) { // �ڴ˻�ȡ����
            //do something ...
            conn.close(); //�����������رա����ӣ������ͷŵ����ӳ��У��Ա��´λ�ȡ����ʱ��ֱ�ӷ���
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
