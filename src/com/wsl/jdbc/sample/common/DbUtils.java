package com.wsl.jdbc.sample.common;

import java.sql.*;

public class DbUtils {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //创建数据库连接
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/wsl?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                "root", "root"
        );
        return conn;
    }
    public static void closeConnection(ResultSet rs, Statement stmt,Connection conn){
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
