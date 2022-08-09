package com.wsl.jdbc.sample.hrapp.command;

import java.sql.*;
import java.util.Scanner;

public class QueryCommand implements Command{

    @Override
    public void execute() {
        System.out.println("Please input ename");
        Scanner in = new Scanner(System.in);
        String pdname = in.next();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //加载并注册JDBC驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //创建数据库连接
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/wsl?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                    "root", "root"
            );
            String sql = "select * from employee where ename=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,pdname);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Integer eno = rs.getInt(1);
                String ename = rs.getString("ename");
                Float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                System.out.println(ename + "-" + eno + "-" + salary + dname);
            }//while
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭连接，释放资源
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null){
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null && !conn.isClosed()){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
