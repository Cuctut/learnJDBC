package com.lin.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    /*
        ��ز���
        https://www.liaoxuefeng.com/wiki/1252599548343744/1322290857902113
    */

public class jdbcBatch {
    public static void main(String[] args) throws SQLException {
        String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/learnjdbc?useSSL=false&characterEncoding=utf8&useServerPrepStmts=true";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "1234";

        String SQL = "INSERT INTO learnjdbc.students (name, gender, grade, score) VALUES (?, ?, ?, ?)";

        Student[] students = new Student[10];

        try(Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)){
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                // ��ͬһ��PreparedStatement�������ò���������addBatch():
                for (Student s : students) {
                    ps.setString(1, s.name);
                    ps.setBoolean(2, s.gender);
                    ps.setInt(3, s.grade);
                    ps.setInt(4, s.score);
                    ps.addBatch(); // ��ӵ�batch
                }
                // ִ��batch:
                int[] ns = ps.executeBatch();
                for (int n : ns) {
                    System.out.println(n + " inserted."); // batch��ÿ��SQLִ�еĽ������
                }
            }
        }

    }

    private static class Student {
        String name;
        Boolean gender;
        int grade;
        int score;
    }
}
