package cn.tedu.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @功能 MySQL数据库连接工具类, 使用连接池与数据库建立连接
 * @作者 Mr_hui
 * @日期 2023.6.13
 */
public class JDBCUtils {

    private static DataSource dataSource = new ComboPooledDataSource();

    /**
     * 与MySQL数据库建立连接
     */
    public static Connection getConnection() throws SQLException {
        try {
            // 相当于注册驱动
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 关闭所有资源
     */
    public static void closeAll(Connection conn, Statement stat, ResultSet result) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                conn = null;
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                stat = null;
            }
        }
        if (result != null) {
            try {
                result.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                result = null;
            }
        }
    }
}
