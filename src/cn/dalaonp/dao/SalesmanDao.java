package cn.dalaonp.dao;

import cn.dalaonp.db.DbConnection;
import cn.dalaonp.instance.Salesman;
import cn.dalaonp.tools.ScannerInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Salesman表操作
 * @author ouyangzhifei
 */
public class SalesmanDao {
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
     * 检查是否初始化用户表
     * @return 返回检查结果
     */
    public boolean isInitial() {
        String sql="select * from Salesman";
        try {
            pre=con.prepareStatement(sql);
            resultSet=pre.executeQuery();
            //判断查询是否有结果
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检查用户名密码是否存在
     * @param account 用户名
     * @param password 密码
     * @return 存在返回true
     */
    public boolean checkAccount(String account, String password) {
        String sql="select SaleId from Salesman where SaleName = ? and SalePassword = ?";
        boolean flag;
        try {
            pre=con.prepareStatement(sql);
            pre.setString(1,account);
            pre.setString(2,password);
            resultSet=pre.executeQuery();
            //判断是否有指定用户的返回结果
            flag=resultSet.next();
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加用户
     * @return 添加成功返回true
     */
    public boolean addUser() {
        boolean flag = false;
        //输入要添加的用户名及密码
        String account;
        String password;
        //验证输入合法
        while (true){
            out.print("用户名");
        account = ScannerInfo.inputString();
            out.print("密码");
        password = ScannerInfo.inputString();
            if (account.isEmpty()||password.isEmpty()){
                out.println("!用户名或密码不能为空!,请重试");
            }
            else if (account.length() > 10||password.length() > 10){
                out.println("用户名或密码长度应该在10位以内");
            }else {
                break;
            }

        }
        //在数据库表中插入用户记录
        String sql="INSERT INTO Salesman VALUES(?,?)";
        try {
            pre=con.prepareStatement(sql);
            pre.setString(1,account);
            pre.setString(2,password);
            int rows = pre.executeUpdate();
            if (rows > 0){
                flag = true;
            }
            return flag;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取所有售货员信息
     * @return 返回结果集合
     */
    public List<Salesman> getSalesmanList() {
        String sql="SELECT * FROM Salesman";
        List<Salesman> salesmanList=new ArrayList<>();
        try {
            pre=con.prepareStatement(sql);
            resultSet=pre.executeQuery();
            while (resultSet.next()){
                int saleId = resultSet.getInt("SaleId");
                String saleName = resultSet.getString("SaleName");
                String salePassword = resultSet.getString("SalePassword");
                Salesman salesman=new Salesman(saleId,saleName,salePassword);
                salesmanList.add(salesman);
            }
            return salesmanList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 打印所有售货员信息
     */
    public void showList() {
        List<Salesman> salesmanList = getSalesmanList();
        for (Salesman salesman : salesmanList) {
            out.println(salesman.toString());
        }
    }

    /**
     * 根据id
     * 删除售货员
     * @param saleId 售货员id
     * @return 删除成功返回true
     */
    public boolean deleteSale(int saleId) {
        String sql = "DELETE FROM Salesman WHERE SaleId = ?";
        boolean flag = false;
        try {
            pre = con.prepareStatement(sql);
            pre.setInt(1,saleId);
            int rows = pre.executeUpdate();
            if (rows > 0){
                flag=true;
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 检查id是否存在
     * @param saleId 售货员id
     * @return 存在返回true
     */
    public boolean checkSaleId(int saleId) {
        String sql = "SELECT * FROM Salesman WHERE SaleId = ?";
        boolean flag = false;
        try {
            pre = con.prepareStatement(sql);
            pre.setInt(1,saleId);
           resultSet = pre.executeQuery();
           if (resultSet.next()){
               flag = true;
           }
           return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据用户名获取售货员id
     * @param account 用户名
     * @return 返回售货员id
     */
    public int getSalesmanId(String account) {
        String sql="SELECT SaleId FROM Salesman WHERE SaleName = ? ";
        try {
            pre = con.prepareStatement(sql);
            pre.setString(1,account);
            resultSet = pre.executeQuery();
            resultSet.next();
            int saleId = resultSet.getInt("SaleId");
            return saleId;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }
}
