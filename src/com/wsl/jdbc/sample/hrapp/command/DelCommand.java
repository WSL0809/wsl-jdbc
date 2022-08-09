package com.wsl.jdbc.sample.hrapp.command;

import com.wsl.jdbc.sample.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DelCommand implements Command{
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("please input eno");
        int eno = in.nextInt();
//        System.out.println("please input salary");
//        float salary = in.nextFloat();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "delete from employee where eno=?";
            pstmt = conn.prepareStatement(sql);
//            pstmt.setFloat(1, salary);
            pstmt.setInt(1, eno);
            int cnt = pstmt.executeUpdate();
            if (cnt == 1){
                System.out.println("delete success");
            }else{
                System.out.println("nothing to delete");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,pstmt,conn);
        }

    }
}
