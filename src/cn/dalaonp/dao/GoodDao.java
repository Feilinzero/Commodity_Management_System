package cn.dalaonp.dao;

import cn.dalaonp.db.DbConnection;
import cn.dalaonp.instance.Good;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Good数据表操作
 * @author ouyangzhifei
 *
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

    /**
     * 获取数据库连接对象
     * @return con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * 获取数据库执行对象
     * @return pre
     */
    public PreparedStatement getPre() {
        return pre;
    }

    /**
     * 获取结果集对象
     * @return resultSet
     */
    public ResultSet getResultSet() {
        return resultSet;
    }

    /**
     * 检查GoodName字段是否已存在
     * @param name 商品名称
     * @return 存在返回true
     */
    public boolean checkGoodName(String name) {
        List<String> goodNameList = new ArrayList<>();
        String sql="SELECT GoodName FROM Good";
        boolean flag = false;
        try {
            pre= con.prepareStatement(sql);
            //执行查询语句获得结果集
            resultSet = pre.executeQuery();
            //封装所有记录为实例装入集合中
            while (resultSet.next()){
                String goodName = resultSet.getString("GoodName");
                goodNameList.add(goodName);
            }
            //遍历查询
            for (String goodName : goodNameList) {
                if (goodName.equals(name)) {
                    flag = true;
                    break;
                }
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            DbConnection.close(con,pre,resultSet);
        }
    }

    /**
     * 显示商品列表
     * @return 有查询结果返回true
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
            //封装所有记录为实例装入集合中
            while (resultSet.next()){
                int goodId = resultSet.getInt("GoodId");
                String goodName = resultSet.getString("GoodName");
                double goodPrice = resultSet.getDouble("GoodPrice");
                int goodNumber = resultSet.getInt("GoodNumber");
                String goodNote = resultSet.getString("GoodNote");
                Good good=new Good(goodId,goodName,goodPrice,goodNumber,goodNote);
                goodList.add(good);
            }
            if (goodList.size()>0){
                System.out.println("\t商品id\t商品名称\t商品单价\t商品库存\t商品备注");
                goodList.forEach((Good good)-> System.out.println(good.toString()));
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加商品信息
     * insert语句
     * @param name 商品名称
     * @param price 商品单价
     * @param number 商品数量
     * @param note 商品备注
     * @return 添加成功返回true
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
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检查GoodId是否已存在
     * @param id 商品id
     * @return 存在返回true
     */
    public boolean checkGoodId(int id) {
        List<Integer> goodIdList = new ArrayList<>();
        String sql="SELECT GoodId FROM Good";
        try {
            pre= con.prepareStatement(sql);
            //执行查询语句获得结果集
            resultSet = pre.executeQuery();
            //封装所有记录为实例装入集合中
            while (resultSet.next()){
                int goodId=resultSet.getInt("GoodId");
                goodIdList.add(goodId);
            }
            for (Integer goodId : goodIdList) {
                //存在返回true
                if (goodId==id) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改Good表记录
     * 根据GoodId字段
     * @param id 商品id
     * @param name 商品名称
     * @param price 商品单价
     * @param number 商品数量
     * @param note 商品备注
     * @return 修改成功返回true
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
            if (rows>0){
                System.out.println("修改成功");
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("修改失败");
            return false;
        }


    }

    /**
     * 根据商品id获取商品信息
     * @param id 商品id
     * @return 商品信息
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
            return string;
        } catch (SQLException e) {
            e.printStackTrace();
            return string;
        }
    }

    /**
     * 根据商品id删除商品记录
     * @param id 商品id
     * @return 删除成功返回true
     */
    public boolean deleteGood(int id) {
        String sql = "delete from Good where GoodId = ?";
            int rows = 0;
        try {
            pre=con.prepareStatement(sql);
            pre.setInt(1,id);
            //执行delete语句,返回影响行数
            rows = pre.executeUpdate();
            if (rows > 0) {
                System.out.println("删除成功!");
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取商品库存数量
     * @param goodId 商品id
     * @return 返回商品库存数量
     */
    public int getGoodNumber(int goodId) {
        String sql="SELECT GoodNumber FROM Good WHERE GoodId = ?";
        int number = 0;
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
     * @param goodId 商品id
     * @return 返回商品单价
     */
    public double getGoodPrice(int goodId) {
        String sql="SELECT GoodPrice FROM Good WHERE GoodId = ?";
        double price = 0;
        try {
            pre = con.prepareStatement(sql);
            pre.setInt(1,goodId);
            resultSet = pre.executeQuery();
            resultSet.next();
            price = resultSet.getDouble("GoodPrice");
            return price;
        } catch (SQLException e) {
            e.printStackTrace();
            return price;
        }
    }

    /**
     * 更新商品库存
     * @param goodList 变更的商品库存信息
     * @return 更新成功返回true
     */
    public boolean updateGood(List<Good> goodList) {
        String sql;
        int originalNumber;
        int newGoodNumber;
        boolean flag = false;
        int rows;
        try {
            for (Good good : goodList) {
                //获取原来库存
                int goodId = good.getGoodId();
                originalNumber = getGoodNumber(goodId);
                //更新后库存
                newGoodNumber = originalNumber - good.getGoodNumber();
                //拼接SQL语句
                sql="update Good set GoodNumber ="+newGoodNumber+"where GoodId ="+goodId;
                pre=con.prepareStatement(sql);
                //执行语句返回影响行数
                rows = pre.executeUpdate();
                if (rows > 0) {
                    flag = true;
                }
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新销售记录
     * @param goodList 销售信息
     * @param salesmanId 售货员id
     * @return 更新成功返回true
     */
    public boolean insertSoldNote(List<Good> goodList, int salesmanId) {
        String sql = "INSERT INTO SoldNote VALUES(?,?,?,GETDATE())";
        int rows = 0;
        boolean flag = true;
        try {
            pre=con.prepareStatement(sql);
            for (Good good : goodList) {
                pre.setInt(1,good.getGoodId());
                pre.setInt(2,salesmanId);
                pre.setInt(3,good.getGoodNumber());
                rows = pre.executeUpdate();
                if (rows <= 0) {
                    flag = false;
                }
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
