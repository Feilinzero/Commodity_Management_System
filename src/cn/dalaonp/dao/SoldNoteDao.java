package cn.dalaonp.dao;

import cn.dalaonp.db.DbConnection;
import cn.dalaonp.instance.SoldNote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * SoldNote表操作
 * @author ouyangzhifei
 */
public class SoldNoteDao {
    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet resultSet = null;
    {
        try {
            con = DbConnection.getC3P0Con();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }

    public PreparedStatement getPre() {
        return pre;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    /**
     * 读取销售记录列表
     * @return 销售记录列表
     */
    public List<SoldNote> getNoteList() {
        String sql="SELECT * FROM SoldNote";
        return getSoldNotes(sql);

    }

    /**
     * 根据查询语句获取查询结果集
     * @param sql 查询语句
     * @return 结果集合
     */
    private List<SoldNote> getSoldNotes(String sql) {
        List<SoldNote> soldNoteList=new ArrayList<>();
        try {
            pre = con.prepareStatement(sql);
            resultSet = pre.executeQuery();
            while (resultSet.next()){
                String soldDate = resultSet.getString("SoldDate");
                int soldNumber = resultSet.getInt("SoldNumber");
                int saleId = resultSet.getInt("SaleId");
                int goodId = resultSet.getInt("GoodId");
                int soldId = resultSet.getInt("SoldId");
                SoldNote soldNote=new SoldNote(soldId,goodId,saleId,soldNumber,soldDate);
                soldNoteList.add(soldNote);
            }
            return soldNoteList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当日销售记录
     * @return 结果集合
     */
    public List<SoldNote> getNoteListToDay() {
        String sql="SELECT * FROM SoldNote WHERE " +
                "DATEFROMPARTS(YEAR(SoldDate),MONTH(SoldDate),DAY(SoldDate)) = " +
                "DATEFROMPARTS(YEAR(GETDATE()),MONTH(GETDATE()),DAY(GETDATE())) ";
        return getSoldNotes(sql);
    }
}
