package com.wsl.jdbc.sample.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.wsl.jdbc.sample.common.DbUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DruidSample {
    public static void main(String[] args) throws IOException {
        //1.加载属性文件
        Properties properties = new Properties();
        String propertyFile = DruidSample.class.getResource("/druid-config.properties").getPath();
        try {
            propertyFile = URLDecoder.decode(propertyFile, StandardCharsets.UTF_8);
            properties.load(new FileInputStream(propertyFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //2.获取datasource数据源对象
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("select * from employee ");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer eno = rs.getInt(1);
                String ename = rs.getString("ename");
                Float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                System.out.println(ename + "-" + eno + "-" + salary + dname);
            }//while
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(rs, pstmt, conn);
        }
        //3.创建数据库链接

    }
}
