package com.lin.test;

import java.sql.*;

public class jdbcTest01 {
    public static void main(String[] args) throws SQLException {
        String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/learnjdbc?useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "88718656";

        String SQL = "SELECT id, grade, name, gender FROM learnjdbc.students WHERE gender=? AND grade=?";

//        System.out.println(System.getProperty("file.encoding"));

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setObject(1, "1"); // 注意：索引从1开始
                ps.setObject(2, 3);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        long id = rs.getLong("id");
                        long grade = rs.getLong("grade");
                        String name = rs.getString("name");
                        String gender = rs.getString("gender");
                        System.out.println("id:" + id + "  grade:" + grade + " name:" + name + " gender:" + gender);
                        System.out.println("---------------------------------");
                    }
                }
            }
        }

    }
}
