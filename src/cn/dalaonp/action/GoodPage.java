package cn.dalaonp.action;

import cn.dalaonp.dao.GoodDao;
import cn.dalaonp.tools.ScannerInfo;

/**
 * 商品管理界面
 */
public class GoodPage {
    /**
     * 商品管理主界面菜单
     */
    public static void goodMainPage(){
        System.out.println("================商品管理主菜单==============");
        System.out.println("\t\t\t\t1.商品列表");
        System.out.println("\t\t\t\t2.添加商品");
        System.out.println("\t\t\t\t3.修改商品");
        System.out.println("\t\t\t\t4.下架商品");
        System.out.println("==========================================");
        System.out.println("输入指定数字进入,输入0返回上一级");
        String input = "";
        String rule="[0-4]";
        do {
            input = ScannerInfo.inputChose();
            if (input.matches(rule)){
                switch (Integer.parseInt(input)){
                    case 0:
                        MainPage.mainPage();
                        break;
                    case 1:
                        goodListPage();
                        System.out.println("1.返回上一级\t其他.退出");
                        input=ScannerInfo.inputChose();
                        if (input.equals("1")) {
                            goodMainPage();
                        }else System.exit(3);
                    case 2:
                        addGoodPage();
                        break;
                    case 3:
                        alterGoodPage();
                        break;
                    case 4:
                        deleteGoodPage();
                        break;
                    default:
                        break;
                }
                System.out.println("!输入错误!");
            }
        }while (true);

    }

    /**
     * 商品列表
     */
    private static void goodListPage(){
        System.out.println("================商品列表==============");
        //调用商品表的操作类
        boolean result = new GoodDao().showGoods();
        if (result){
            System.out.println("!查询完毕!");
        }else
            System.out.println("!查询失败!");
    }

    /**
     * 添加商品
     */
    private static void addGoodPage(){

    }

    /**
     * 修改商品
     */
    private static void alterGoodPage(){

    }

    /**
     * 下架商品
     */
    private static void deleteGoodPage(){

    }
}
