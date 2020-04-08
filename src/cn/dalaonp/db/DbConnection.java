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
     * @return
     * @throws SQLException
     */
    public static Connection getC3P0Con() throws SQLException {
        DataSource source=new ComboPooledDataSource("default");
        Connection connection = source.getConnection();
        return connection;
    }

    /**
     * 释放资源
     * @param connection
     * @param preparedStatement
     * @param resultSet
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
     * @param connection
     * @param preparedStatement
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
