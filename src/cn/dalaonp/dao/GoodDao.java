package cn.dalaonp.dao;

import cn.dalaonp.db.DbConnection;
import cn.dalaonp.instance.Good;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Good数据表操作
 * @author ouyangzhifei
 */
public final class GoodDao {
    private  Connection con = null;
    private  PreparedStatement pre = null;
    private  ResultSet resultSet = null;
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

    public void setCon(Connection con) {
        this.con = con;
    }

    public PreparedStatement getPre() {
        return pre;
    }

    public void setPre(PreparedStatement pre) {
        this.pre = pre;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    /**
     * 检查GoodName字段是否已存在
     * @param name
     * @return
     */
    public boolean checkGoodName(String name) {
        List<String> goodNameList = new ArrayList<>();
        String sql="SELECT GoodName FROM Good";
        try {
            pre= con.prepareStatement(sql);
            //执行查询语句获得结果集
            resultSet = pre.executeQuery();
            //调用方法封装所有记录为实例装入集合中
            getGoodNameList(goodNameList,resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbConnection.close(con,pre,resultSet);
        }
        if (goodNameList!=null) {
            for (String goodName : goodNameList) {
                if (goodName.equals(name)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 封装GoodName字段的查询记录
     * @param goodNameList
     * @param resultSet
     */
    private void getGoodNameList(List<String> goodNameList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            String goodName = resultSet.getString("GoodName");
            goodNameList.add(goodName);
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
        int result = 0;
        try {
            pre = con.prepareStatement(sql);
            pre.setString(1,name);
            pre.setDouble(2,price);
            pre.setInt(3,number);
            pre.setString(4,note);
            //执行语句,返回影响行数
            result = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

            return result > 0?true:false;
    }

    /**
     * 检查GoodId是否已存在
     * @param id
     * @return
     */
    public boolean checkGoodId(int id) {
        List<Integer> goodIdList = new ArrayList<>();
        String sql="SELECT GoodId FROM Good";
        try {
            pre= con.prepareStatement(sql);
            //执行查询语句获得结果集
            resultSet = pre.executeQuery();
            //调用方法封装所有记录为实例装入集合中
            getGoodIdList(goodIdList,resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (goodIdList!=null) {
            for (Integer goodId : goodIdList) {
                //存在返回true
                if (goodId==id) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 封装GoodId字段的查询记录
     * @param goodIdList
     * @param resultSet
     */
    private void getGoodIdList(List<Integer> goodIdList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            int goodId=resultSet.getInt("GoodId");
            goodIdList.add(goodId);
        }

    }

    /**
     * 修改Good表记录
     * 根据GoodId字段
     * @param id
     * @param name
     * @param price
     * @param number
     * @param note
     */
    public boolean updateGood(int id, String name, double price, int number, String note){
        String sql="UPDATE Good SET GoodName = ?,GoodPrice = ?,GoodNumber = ?,GoodNote = ? WHERE GoodId = ?";
        int rows = 0;
        try {
            pre = con.prepareStatement(sql);
            pre.setString(1,name);
            pre.setDouble(2,price);
            pre.setInt(3,number);
            pre.setString(4,note);
            pre.setInt(5,id);
            rows = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbConnection.close(con,pre);
        }
        if (rows>0){
            System.out.println("修改成功");
            return true;
        }else {
            System.out.println("修改失败");
            return false;
        }


    }

    /**
     * 根据商品id获取商品信息
     * @param id
     * @return
     */
    public String getGood(int id) {
        String sql = "Select * from Good where GoodId = ?";
            String string = "";
        try {
            pre = con.prepareStatement(sql);
            pre.setInt(1,id);
            resultSet = pre.executeQuery();
            resultSet.next();
            int goodId = resultSet.getInt("GoodId");
            String goodName = resultSet.getString("GoodName");
            double goodPrice = resultSet.getDouble("GoodPrice");
            int goodNumber = resultSet.getInt("GoodNumber");
            String goodNote = resultSet.getString("GoodNote");
            Good good=new Good(goodId,goodName,goodPrice,goodNumber,goodNote);
            string = good.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 根据商品id删除商品记录
     * @param id
     * @return
     */
    public boolean deleteGood(int id) {
        String sql = "delete from Good where GoodId = ?";
            int rows = 0;
        try {
            pre=con.prepareStatement(sql);
            pre.setInt(1,id);
            rows = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rows > 0) {
            System.out.println("删除成功!");
            return true;
        }
        return false;
    }

    /**
     * 检查商品库存数量是否充足
     * @param goodId
     * @param goodNumber
     * @return
     */
    public boolean checkGoodNumber(int goodId, int goodNumber) {
        String sql="SELECT GoodNumber FROM Good WHERE GoodId = ?";
        int number = 0;
        boolean flag = false;
        try {
            pre = con.prepareStatement(sql);
            pre.setInt(1,goodId);
            resultSet = pre.executeQuery();
            resultSet.next();
            number = resultSet.getInt("GoodNumber");
            if (number >= goodNumber){
                flag = true;
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取商品库存数量
     * @param goodId
     * @return
     */
    public int getGoodNumber(int goodId) {
        String sql="SELECT GoodNumber FROM Good WHERE GoodId = ?";
        int number = 0;
        boolean flag = false;
        try {
            pre = con.prepareStatement(sql);
            pre.setInt(1,goodId);
            resultSet = pre.executeQuery();
            resultSet.next();
            number = resultSet.getInt("GoodNumber");
            return number;
        } catch (SQLException e) {
            e.printStackTrace();
            return number;
        }
    }

    /**
     * 获取商品单价
     * @param goodId
     * @return
     */
    public double getGoodPrice(int goodId) {
        String sql="SELECT GoodPrice FROM Good WHERE GoodId = ?";
        double number = 0;
        boolean flag = false;
        try {
            pre = con.prepareStatement(sql);
            pre.setInt(1,goodId);
            resultSet = pre.executeQuery();
            resultSet.next();
            number = resultSet.getDouble("GoodPrice");
            return number;
        } catch (SQLException e) {
            e.printStackTrace();
            return number;
        }
    }

    /**
     * 更新商品库存
     * @param goodList
     */
    public void updateGood(List<Good> goodList) {
        String sql;
        int originalNumber;
        int newGoodNumber;
        try {
            for (Good good : goodList) {
                //获取原来库存
                int goodId = good.getGoodId();
                originalNumber = getGoodNumber(goodId);
                //更新后库存
                newGoodNumber = originalNumber - good.getGoodNumber();

                sql="update Good set GoodNumber ="+newGoodNumber+"where GoodId ="+goodId;
                pre=con.prepareStatement(sql);
                pre.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新销售记录
     * @param goodList
     * @param salesmanId
     */
    public void insertSoldNote(List<Good> goodList, int salesmanId) {
        String sql = "INSERT INTO SoldNote VALUES(?,?,?,GETDATE())";
        try {
            pre=con.prepareStatement(sql);
            for (Good good : goodList) {
                pre.setInt(1,good.getGoodId());
                pre.setInt(2,salesmanId);
                pre.setInt(3,good.getGoodNumber());
                pre.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
