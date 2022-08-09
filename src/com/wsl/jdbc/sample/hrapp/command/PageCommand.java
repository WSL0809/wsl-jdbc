package com.wsl.jdbc.sample.hrapp.command;

import com.wsl.jdbc.sample.common.DbUtils;
import com.wsl.jdbc.sample.hrapp.entity.Empolyee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PageCommand implements Command {
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("input page number");
        int page = in.nextInt();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Empolyee> list = new ArrayList();

        try {
            conn = DbUtils.getConnection();
            String sql = "select * from employee limit ?, 10";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, (page - 1) * 10);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer eno = rs.getInt("eno");
                String ename = rs.getString("ename");
                Float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                Date hiredate = rs.getDate("hiredate");
                Empolyee emp = new Empolyee();
                emp.setEno(eno);
                emp.setEname(ename);
                emp.setSalary(salary);
                emp.setDname(dname);
                emp.setHiredate(hiredate);
                
                list.add(emp);
            }
            System.out.println(list.size());
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(rs, pstmt, conn);
        }
    }
}
