package com.lin.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcTransaction {
    /*
        ACID：
        Atomicity：原子性，将所有SQL作为原子工作单元执行，要么全部执行，要么全部不执行；
        Consistency：一致性，一致性，事务完成后，所有数据的状态都是一致的，即A账户只要减去了100，B账户则必定加上了100；
        Isolation：隔离性，隔离性，如果有多个事务并发执行，每个事务作出的修改必须与其他事务隔离；
        Durability：持久性，持久性，即事务完成后，对数据库数据的修改被持久化存储。
    */

    public static void main(String[] args) throws SQLException {
        String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/learnjdbc?useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "1234";

        String SQL = "UPDATE learnjdbc.students SET score = score + ? WHERE id = ?;";

        /*开启事务*/
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);  //MySQL的默认隔离级别是REPEATABLE_READ
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setObject(1, 2); // 注意：索引从1开始
                ps.setObject(2, 3);
                int n = ps.executeUpdate(); // 1
                System.out.println(n);
            }
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setObject(1, -2); // 注意：索引从1开始
                ps.setObject(2, 4);
                int n = ps.executeUpdate(); // 1
                System.out.println(n);
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
    }

}
