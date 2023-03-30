package com.lin.test;

import java.sql.*;

    /*
        相关博客
        https://www.liaoxuefeng.com/wiki/1252599548343744/1321748475674658
    */

public class jdbcOperate {
    public static void main(String[] args) throws SQLException {
        String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/learnjdbc?useSSL=false&characterEncoding=utf8&useServerPrepStmts=true";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "1234";

        String SQL = "INSERT INTO learnjdbc.students (id, grade, name, gender, score) VALUES (?,?,?,?,?)";

        /*普通插入操作*/
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setObject(1, 13); // 注意：索引从1开始
                ps.setObject(2, 1); // grade
                ps.setObject(3, "Bob"); // name
                ps.setObject(4, "1"); // gender
                ps.setObject(5, 100);
                int n = ps.executeUpdate(); // 1

                System.out.println(n);
            }
        }

        /*插入并获取主键*/
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setObject(1, 14); // 注意：索引从1开始
                ps.setObject(2, 1); // grade
                ps.setObject(3, "Tom"); // name
                ps.setObject(4, "1"); // gender
                ps.setObject(5, 100);
                int n = ps.executeUpdate(); // 1

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    System.out.println("GeneratedKeys : ");
                    if (rs.next()) {
                        long id = rs.getLong(1); // 注意：索引从1开始
                        System.out.println(id);
                    }
                }

                System.out.println("Update : " + n);
            }
        }
    }
}
