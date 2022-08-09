package com.wsl.jdbc.sample.sample;

import java.sql.*;

public class StandardJDBC {
    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        try {
            //加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");//
            //创建数据库连接
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/wsl?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                    "root", "root"
            );
            //创建Statement对象，用于执行sql语句
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee where ename='wsl'");
            //遍历查询结果
            while (rs.next()) {
                Integer eno = rs.getInt(1);
                String ename = rs.getString("ename");
                Float salary = rs.getFloat("salary");
                System.out.println(ename + "-" + eno + "-" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && conn.isClosed() == false) {
                    //5-关闭连接，释放资源
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }

    }
}
