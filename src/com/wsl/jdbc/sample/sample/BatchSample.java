package com.wsl.jdbc.sample.sample;

import com.wsl.jdbc.sample.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class BatchSample {
    private static void tc1() {
        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            long startTime = new Date().getTime();
            conn = DbUtils.getConnection();
            conn.setAutoCommit(false);//关闭自动提交
            String sql = "insert into employee(eno,ename,salary,dname) values(?,?,?,?)";
            for (int i = 0; i <= 100000; i++) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, i);
                pstmt.setString(2, "emp" + i);
                pstmt.setInt(3, 16000);
                pstmt.setString(4, "C");
                pstmt.executeUpdate();
            }//for
            conn.commit();//提交数据
            long endTime = new Date().getTime();
            System.out.println("cost" + (endTime - startTime));
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

    private static void tc2() {//使用批处理
        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            long startTime = new Date().getTime();
            conn = DbUtils.getConnection();
            conn.setAutoCommit(false);//关闭自动提交
            String sql = "insert into employee(eno,ename,salary,dname) values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            for (int i = 200000; i <= 300000; i++) {


                pstmt.setInt(1, i);
                pstmt.setString(2, "emp" + i);
                pstmt.setInt(3, 16000);
                pstmt.setString(4, "C");
                pstmt.addBatch();//将参数加入批处理任务
//                pstmt.executeUpdate();
            }//for
            pstmt.executeBatch();//执行批处理任务
            conn.commit();//提交数据
            long endTime = new Date().getTime();
            System.out.println("Batch_cost" + (endTime - startTime));
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

    public static void main(String[] args) {
        tc1();
        tc2();//323
    }
}
