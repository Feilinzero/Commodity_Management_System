package cn.dalaonp.action;

import cn.dalaonp.tools.ScannerInfo;

/**
 * 商品管理系统主界面
 * author: oyzf
 * @author ouyangzhifei
 */
public class MainPage {
    /**
     * 主函数--程序执行入口
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("=============欢迎进入商品管理系统=============");
        //启动主菜单
        mainPage();
    }

    /**
     * 主界面菜单
     * 从这里可以跳转到三个子模块
     */
    public static void mainPage(){
        //界面显示
        System.out.println("==================主菜单===================");
        System.out.println("\t\t\t\t1.商品管理");
        System.out.println("\t\t\t\t2.前台收银");
        System.out.println("\t\t\t\t3.后台管理");
        System.out.println("==========================================");
        System.out.println("输入指定数字进入,输入0退出");
        //选择校验规则
        String rule;
        rule = "[0-3]";
        do {
            String input = ScannerInfo.inputChose();
            //判断输入合法性
            if (input.matches(rule)){
                //执行选择的模块
                switch (Integer.parseInt(input)){
                    case 0:
                        System.out.println(">>>>>>>>>>>>已退出系统");
                        //退出程序
                        System.exit(1);
                    case 1:
                        //进入商品管理模块
                        GoodPage.goodMainPage();
                        break;
                    case 2:
                        //进入前台收银模块
                        CashierDeskPage.loginDeskPage();
                        break;
                    case 3:
                        //进入后台管理模块
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
