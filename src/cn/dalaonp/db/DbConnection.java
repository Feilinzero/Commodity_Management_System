package cn.dalaonp.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * 数据库连接
 * @author ouyangzhifei
 */

public final class DbConnection {
    /**
     * 获取数据库连接对象
     * @return 返回数据库连接对象
     * @throws SQLException 数据库连接异常
     */
    public static Connection getC3P0Con() throws SQLException {
        DataSource source=new ComboPooledDataSource("default");
        Connection connection;
        connection = source.getConnection();
        return connection;
    }

    /**
     * 释放资源
     * @param connection 数据库连接对象
     * @param preparedStatement 数据库执行对象
     * @param resultSet 结果集对象
     */
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(connection,preparedStatement);
    }

    /**
     * 释放资源
     * @param connection 数据库连接对象
     * @param preparedStatement 数据库执行对象
     */
    public static void close(Connection connection, PreparedStatement preparedStatement){
        if (preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
