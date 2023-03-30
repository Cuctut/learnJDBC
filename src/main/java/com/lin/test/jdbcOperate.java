package com.lin.test;

import java.sql.*;

    /*
        ��ز���
        https://www.liaoxuefeng.com/wiki/1252599548343744/1321748475674658
    */

public class jdbcOperate {
    public static void main(String[] args) throws SQLException {
        String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/learnjdbc?useSSL=false&characterEncoding=utf8&useServerPrepStmts=true";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "1234";

        String SQL = "INSERT INTO learnjdbc.students (id, grade, name, gender, score) VALUES (?,?,?,?,?)";

        /*��ͨ�������*/
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setObject(1, 13); // ע�⣺������1��ʼ
                ps.setObject(2, 1); // grade
                ps.setObject(3, "Bob"); // name
                ps.setObject(4, "1"); // gender
                ps.setObject(5, 100);
                int n = ps.executeUpdate(); // 1

                System.out.println(n);
            }
        }

        /*���벢��ȡ����*/
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setObject(1, 14); // ע�⣺������1��ʼ
                ps.setObject(2, 1); // grade
                ps.setObject(3, "Tom"); // name
                ps.setObject(4, "1"); // gender
                ps.setObject(5, 100);
                int n = ps.executeUpdate(); // 1

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    System.out.println("GeneratedKeys : ");
                    if (rs.next()) {
                        long id = rs.getLong(1); // ע�⣺������1��ʼ
                        System.out.println(id);
                    }
                }

                System.out.println("Update : " + n);
            }
        }
    }
}
