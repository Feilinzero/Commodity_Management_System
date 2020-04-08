package cn.dalaonp.action;

import cn.dalaonp.dao.GoodDao;
import cn.dalaonp.dao.SalesmanDao;
import cn.dalaonp.db.DbConnection;
import cn.dalaonp.instance.Good;
import cn.dalaonp.tools.ScannerInfo;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * 前台收银界面
 * @author ouyangzhifei
 */

public class CashierDeskPage {
    private static boolean initial;
    private static int salesmanId;
    static {
        initial= new SalesmanDao().isInitial();
    }
    /**
     * 前台收银主界面
     */

    private static void cashierDeskMainPage(){
        //界面显示
        out.println("==================前台收银菜单===================");
        out.println("\t\t\t\t1.录入商品");
        out.println("\t\t\t\t2.收银找零");
        out.println("==========================================");
        out.println("输入指定数字进入,输入0返回上一级");
        //判断选择
        String input;
        String rule="[0-2]";
        do {
            input = ScannerInfo.inputChose();
            if (input.matches(rule)){
                switch (Integer.parseInt(input)){
                    case 0:
                        //返回一级主菜单
                        MainPage.mainPage();
                        break;
                    case 1:
                        //录入商品
                        readGoodPage();
                        break;
                    case 2:
                        //收银找零
                        calculateMoney();
                        break;
                    default:
                        break;
                }
            }else {
                out.println("!输入错误,请重新选择!");
            }
        }while (true);
    }

    /**
     * 收银找零
     */
    private static void calculateMoney() {
        //收银找零
        out.println("收到金额:");
        double money=ScannerInfo.inputDouble();

        //计算找零
        out.println("应收金额");
        double price=ScannerInfo.inputDouble();
        if (money < price){
            out.println("收到金额小于应收金额");
        }else {
            out.println("找零:"+(money-price));
        }
        cashierDeskMainPage();
    }

    /**
     * 商品录入
     */
    private static void readGoodPage() {
        //录入商品
        out.println("========录入商品========");
        GoodDao goodDao=new GoodDao();
        //商品结算列表
        List<Good> goodList=new ArrayList<>();
        //初始化商品id和商品数量
        int goodId,goodNumber;
        while (true){
            out.print("商品id");
            goodId=ScannerInfo.inputInt();
            //检查商品id是否存在
            if (goodDao.checkGoodId(goodId)){
                out.print("商品数量");
                //输入商品数量
                goodNumber=ScannerInfo.inputInt();
                //获取商品剩余库存
                int number = goodDao.getGoodNumber(goodId);
                //检查库存是否是否充足
                if (number >= goodNumber){
                    //将商品录入结算列表
                    Good good=new Good(goodId,goodNumber);
                    goodList.add(good);
                }else {
                    out.printf("\n商品剩余%d,库存不足请重新确认或增加库存\n",number);
                }
            }else {
                out.println("商品不存在,请重新确认");
            }
            //
            out.println("继续录入么? 1. 继续录入商品    2.结算收银  0.返回上一级");
            String chose=ScannerInfo.inputChose();
            String regex="[012]";
            if (chose.matches(regex)){
                switch (Integer.parseInt(chose)){
                    case 0:
                        //释放资源
                        DbConnection.close(goodDao.getCon(),goodDao.getPre(),goodDao.getResultSet());
                        cashierDeskMainPage();
                        break;
                    case 1:
                        continue;
                    case 2:
                        //释放资源
                        DbConnection.close(goodDao.getCon(),goodDao.getPre(),goodDao.getResultSet());
                        //计算总价收银找零
                        calculateMoney(goodList,salesmanId);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 计算商品结算列表
     *
     * @param goodList 录入的商品列表
     * @param salesmanId 售货员id
     */
    private static void calculateMoney(List<Good> goodList, int salesmanId) {
        GoodDao goodDao=new GoodDao();
        //总价
        double sum = 0;
        for (Good good : goodList) {
            //根据商品单价和数量计算总额
            double price = goodDao.getGoodPrice(good.getGoodId())*good.getGoodNumber();
            //加入总价
            sum += price;
        }
        //收银找零
        out.println("收钱");
        double money=ScannerInfo.inputDouble();
        if (money >= sum) {
            out.printf("\n找回零钱:%8.2f",money-sum);
            out.println("确认结算么? 1.确认  0.返回上一级");
            String chose=ScannerInfo.inputChose();
            String regex="[01]";
            if (chose.matches(regex)) {
                switch (chose){
                    case "0":
                        //释放资源
                        DbConnection.close(goodDao.getCon(),goodDao.getPre(),goodDao.getResultSet());
                        readGoodPage();
                        break;
                    case "1":
                        //更新商品库存
                        boolean result1 = goodDao.updateGood(goodList);
                        //更新销售记录
                        boolean result2 = goodDao.insertSoldNote(goodList, salesmanId);
                        //释放资源
                        DbConnection.close(goodDao.getCon(),goodDao.getPre(),goodDao.getResultSet());
                        if (result1&&result2){
                            out.println("结算完成");
                        }else {
                            out.println("结算失败");
                        }
                        cashierDeskMainPage();
                        break;
                    default:
                        break;
                }
            }

        }else {
            out.println("额度不足");
        }

    }

    /**
     * 账号登录
     */
    public static void loginDeskPage() {
        SalesmanDao smDao=new SalesmanDao();
        //用户表已经初始化
        if (initial){
            //输入账号密码,循环验证,错误3次退出系统
            int count=0;
            boolean flag;
            int number = 3;
            while (count< number){
                out.println("======前台登录界面=======");
                out.print("账号");
                String account=ScannerInfo.inputString();
                out.print("密码");
                String password=ScannerInfo.inputString();
                out.println("=======================");
                //验证账号密码
                flag=smDao.checkAccount(account,password);
                if (flag) {
                    salesmanId=smDao.getSalesmanId(account);
                    //密码正确则进入收银台主菜单
                    cashierDeskMainPage();
                }else {
                    out.println("!用户名或密码错误!");
                }
                count++;
            }
            out.println("错误登录3次,退出系统");
            System.exit(1);
        //用户表没有初始化则需要添加用户
        }else {
            //添加用户
            out.println("后台初始化.....");
                boolean flag = smDao.addUser();
                if (flag){
                    DbConnection.close(smDao.getCon(),smDao.getPre(),smDao.getResultSet());
                    out.println("用户添加成功,进入登录界面....");
                    //变更初始化状态
                    initial = true;
                    loginDeskPage();
                }else {
                    out.println("用户添加失败,返回主菜单....");
                    DbConnection.close(smDao.getCon(),smDao.getPre(),smDao.getResultSet());
                    cashierDeskMainPage();
                }
        }
    }
}
