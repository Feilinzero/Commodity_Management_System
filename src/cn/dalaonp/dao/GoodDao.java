package cn.dalaonp.dao;

import cn.dalaonp.db.DBConnection;
import cn.dalaonp.instance.Good;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Good数据表操作
 */
public final class GoodDao {
    private  Connection con = null;
    private  PreparedStatement pre = null;
    private  ResultSet resultSet = null;
 {
        try {
            con = DBConnection.getC3P0Con();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示商品列表
     * @return
     */
    public boolean showGoods(){
        //创建商品实例集合存储记录
        List<Good> goodList = new ArrayList<>();
        //定义查询语句
        String sql="SELECT * FROM Good";
        try {
            pre= con.prepareStatement(sql);
            //执行查询语句获得结果集
            resultSet = pre.executeQuery();
            //调用方法封装所有记录为实例装入集合中
            getGoodList(goodList,resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnection.close(con,pre,resultSet);
        }
        if (goodList!=null){
            System.out.println("\t商品id\t商品名称\t商品单价\t商品库存\t商品备注");
            goodList.forEach((Good good)-> System.out.println(good.toString()));
            return true;
        }
        return false;
    }

    /**
     * 封装查询记录为集合
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public void getGoodList(List<Good> goodList,ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            int goodId = resultSet.getInt("GoodId");
            String goodName = resultSet.getString("GoodName");
            double goodPrice = resultSet.getDouble("GoodPrice");
            int goodNumber = resultSet.getInt("GoodNumber");
            String goodNote = resultSet.getString("GoodNote");
            Good good=new Good(goodId,goodName,goodPrice,goodNumber,goodNote);
            goodList.add(good);
        }
    }

    /**
     * 添加商品信息
     * insert语句
     * @param name
     * @param price
     * @param number
     * @param note
     * @return
     */
    public boolean addGood(String name,double price,int number,String note){
        //定义sql插入语句
        String sql="INSERT INTO Good(GoodName,GoodPrice,GoodNumber,GoodNote) VALUES(?,?,?,?)";
        boolean result = false;
        try {
            pre = con.prepareStatement(sql);
            pre.setString(1,name);
            pre.setDouble(2,price);
            pre.setInt(3,number);
            pre.setString(4,note);
            //执行语句
            result = pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnection.close(con,pre);
        }
            return result;
    }
}
