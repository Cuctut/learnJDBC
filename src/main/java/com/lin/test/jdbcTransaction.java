package com.lin.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcTransaction {
    /*
        ACID��
        Atomicity��ԭ���ԣ�������SQL��Ϊԭ�ӹ�����Ԫִ�У�Ҫôȫ��ִ�У�Ҫôȫ����ִ�У�
        Consistency��һ���ԣ�һ���ԣ�������ɺ��������ݵ�״̬����һ�µģ���A�˻�ֻҪ��ȥ��100��B�˻���ض�������100��
        Isolation�������ԣ������ԣ�����ж�����񲢷�ִ�У�ÿ�������������޸ı���������������룻
        Durability���־��ԣ��־��ԣ���������ɺ󣬶����ݿ����ݵ��޸ı��־û��洢��
    */

    public static void main(String[] args) throws SQLException {
        String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/learnjdbc?useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "1234";

        String SQL = "UPDATE learnjdbc.students SET score = score + ? WHERE id = ?;";

        /*��������*/
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);  //MySQL��Ĭ�ϸ��뼶����REPEATABLE_READ
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setObject(1, 2); // ע�⣺������1��ʼ
                ps.setObject(2, 3);
                int n = ps.executeUpdate(); // 1
                System.out.println(n);
            }
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setObject(1, -2); // ע�⣺������1��ʼ
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
