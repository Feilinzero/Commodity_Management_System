package cn.dalaonp.test;

import cn.dalaonp.db.DBConnection;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectionTest {

    @Test
    public void getC3P0Con() {
        try {
            Connection connection= DBConnection.getC3P0Con();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}