package cn.dalaonp.action;

import cn.dalaonp.tools.ScannerInfo;

/**
 * 前台收银界面
 */
public class CashierDeskPage {
    /**
     * 前台收银主界面
     */
    private static void cashierDeskMainPage(){
        //界面显示
        System.out.println("==================前台收银菜单===================");
        System.out.println("\t\t\t\t1.录入商品");
        System.out.println("\t\t\t\t2.收银找零");
        System.out.println("==========================================");
        System.out.println("输入指定数字进入,输入0返回上一级");
        //判断选择
        String input = "";
        String rule="[0-2]";
        do {
            input = ScannerInfo.inputChose();
            if (input.matches(rule)){
                switch (Integer.parseInt(input)){
                    case 0:
                        MainPage.mainPage();    //返回一级主菜单
                        break;
                    case 1:
                        readGoodPage();         //录入商品
                        break;
                    case 2:
                        calculateMoney();       //收银找零
                        break;
                    default:
                        break;
                }
                System.out.println("!输入错误,请重新选择!");
            }
        }while (true);

    }

    /**
     * 收银找零
     */
    private static void calculateMoney() {
        //收银找零
        System.out.println("收到金额:");
        double money=ScannerInfo.inputDouble();

        //计算找零

    }

    /**
     * 商品录入
     */
    private static void readGoodPage() {
        //录入商品

        //计算总价

        //收银找零
    }

    /**
     * 账号登录
     */
    public static void loginDeskPage() {
        //输入账号密码,循环验证,错误3次退出系统
        int count=0;
        boolean flag = false;
        while (count<3){
            System.out.println("======前台登录界面=======");
            System.out.println("账号:");
            String account=ScannerInfo.inputString();
            System.out.println("密码:");
            String password=ScannerInfo.inputString();
            System.out.println("=======================");
            //验证账号密码

            if (flag) cashierDeskMainPage();
            count++;
        }
        System.out.println("错误登录3次,退出系统");
        System.exit(1);
    }

}
