package com.wsl.jdbc.sample.sample;

import com.wsl.jdbc.sample.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionSample {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            conn = DbUtils.getConnection();
            conn.setAutoCommit(false);//关闭自动提交
            String sql = "insert into employee(eno,ename,salary,dname) values(?,?,?,?)";
            for (int i = 1000; i <= 2000; i++) {
                if (i == 10005) {
                    throw new RuntimeException("error");
                }
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, i);
                pstmt.setString(2, "emp" + i);
                pstmt.setInt(3, 16000);
                pstmt.setString(4, "C");
                pstmt.executeUpdate();
            }//for
            conn.commit();//提交数据
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback();//回滚数据

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DbUtils.closeConnection(null, pstmt, conn);
        }
    }
}
