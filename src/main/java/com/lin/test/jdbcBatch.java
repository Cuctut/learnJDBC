package com.lin.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    /*
        相关博客
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
                // 对同一个PreparedStatement反复设置参数并调用addBatch():
                for (Student s : students) {
                    ps.setString(1, s.name);
                    ps.setBoolean(2, s.gender);
                    ps.setInt(3, s.grade);
                    ps.setInt(4, s.score);
                    ps.addBatch(); // 添加到batch
                }
                // 执行batch:
                int[] ns = ps.executeBatch();
                for (int n : ns) {
                    System.out.println(n + " inserted."); // batch中每个SQL执行的结果数量
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
