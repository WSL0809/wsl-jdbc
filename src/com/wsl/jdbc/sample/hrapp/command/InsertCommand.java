package com.wsl.jdbc.sample.hrapp.command;

import com.wsl.jdbc.sample.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InsertCommand implements Command {
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please input eno");
        int eno = in.nextInt();
        System.out.println("Please input ename");
        String ename = in.next();
        System.out.println("Please input salary");
        float salary = in.nextFloat();
        System.out.println("Please input dname");
        String dname = in.next();
        System.out.println("Please input hiredate");
        String strHiredate = in.next();
        java.util.Date udHiredate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            udHiredate = sdf.parse(strHiredate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long time = udHiredate.getTime();
        java.sql.Date sdHiredate = new java.sql.Date(time);

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "insert into employee(eno,ename,salary,dname,hiredate) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, eno);
            pstmt.setString(2, ename);
            pstmt.setFloat(3, salary);
            pstmt.setString(4, dname);
            pstmt.setDate(5, sdHiredate);
            int cnt = pstmt.executeUpdate();
            System.out.println("cnt" + cnt);
            System.out.println("in_company success");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(null, pstmt, conn);
        }

    }
}
