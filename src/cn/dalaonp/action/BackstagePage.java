package cn.dalaonp.action;

import cn.dalaonp.tools.ScannerInfo;

/**
 * 后台管理界面
 */
public class BackstagePage {
    /**
     * 后台管理登陆界面
     */
    public static void loginBackstagePage() {
        //输入账号密码,循环验证,错误3次退出系统
        int count=0;
        boolean flag = false;
        do {
            System.out.println("账号:");
            String account= ScannerInfo.inputString();
            System.out.println("密码:");
            String password=ScannerInfo.inputString();
            System.out.println("=======================");
            //验证账号密码

            if (flag) mainBackstagePage();
            count++;
        }while (count==3);
        System.out.println("错误登录3次,退出系统");
        System.exit(1);
    }



    /**
     * 后台管理主界面
     */
    private static void mainBackstagePage() {
        saleNotePage();
        saleNoteToday();
        saleNoteHistory();

    }

    /**
     * 商品销售历史记录
     */
    private static void saleNoteHistory() {

    }

    /**
     * 商品当日销售记录
     */
    private static void saleNoteToday() {

    }

    /**
     * 商品销售记录
     */
    private static void saleNotePage() {

    }
}
