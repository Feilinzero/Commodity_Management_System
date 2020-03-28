package cn.dalaonp.action;

import cn.dalaonp.tools.ScannerInfo;

/**
 * 主界面
 * author: oyzf
 */
public class MainPage {
    public static void main(String[] args) {
        System.out.println("=============欢迎进入商品管理系统=============");
        //启动主菜单
        mainPage();
    }

    /**
     * 主界面菜单
     */
    public static void mainPage(){
        //界面显示
        System.out.println("==================主菜单===================");
        System.out.println("\t\t\t\t1.商品管理");
        System.out.println("\t\t\t\t2.前台收银");
        System.out.println("\t\t\t\t3.后台管理");
        System.out.println("==========================================");
        System.out.println("输入指定数字进入,输入0退出");
        //判断选择
        String input = "";
        String rule="[0-3]";
        do {
            input = ScannerInfo.inputChose();
            if (input.matches(rule)){
                switch (Integer.parseInt(input)){
                    case 0:
                        System.out.println(">>>>>>>>>>>>已退出系统");
                        System.exit(0);
                    case 1:
                        GoodPage.goodMainPage();
                        break;
                    case 2:
                        CashierDeskPage.loginDeskPage();
                        break;
                    case 3:
                        BackstagePage.loginBackstagePage();
                        break;
                    default:
                        break;
                }
                System.out.println("!输入错误,请重新选择!");
            }
        }while (true);
    }
}
