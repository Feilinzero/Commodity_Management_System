package cn.dalaonp.action;

import cn.dalaonp.dao.GoodDao;
import cn.dalaonp.db.DbConnection;
import cn.dalaonp.tools.ScannerInfo;

import static java.lang.System.*;

/**
 * 商品管理模块界面
 * @author ouyangzhifei
 */
public class GoodPage {
    /**
     * 商品管理主界面菜单
     * 由此进入商品管理模块下的子功能
     */
    public static void goodMainPage(){
        out.println("================商品管理主菜单==============");
        out.println("\t\t\t\t1.商品列表");
        out.println("\t\t\t\t2.添加商品");
        out.println("\t\t\t\t3.修改商品");
        out.println("\t\t\t\t4.下架商品");
        out.println("==========================================");
        out.println("输入指定数字进入,输入0返回上一级");
        //校验规则
        String input;
        String rule="[0-4]";
        do {
            input = ScannerInfo.inputChose();
            //判断合法性
            if (input.matches(rule)){
                //执行选择的功能
                switch (Integer.parseInt(input)){
                    case 0:
                        //返回主菜单界面
                        MainPage.mainPage();
                        break;
                    case 1:
                        //进入商品列表页面
                        goodListPage();
                        break;
                    case 2:
                        //添加商品页面
                        addGoodPage();
                        break;
                    case 3:
                        //修改商品页面
                        alterGoodPage();
                        break;
                    case 4:
                        //删除商品页面
                        deleteGoodPage();
                        break;
                    default:
                        break;
                }
                out.println("!输入错误!");
            }
        }while (true);

    }

    /**
     * 商品列表
     * 显示所有的商品信息
     */
    private static void goodListPage(){
        out.println("================商品列表==============");
        //创建商品表操作对象
        GoodDao goodDao = new GoodDao();
        //调用showGoods方法显示所有商品,返回查询是否成功
        boolean result = goodDao.showGoods();
        if (result){
            out.println("!查询完毕!");
        }else {
            out.println("!暂无商品数据,查询失败!");
        }
        //释放资源
        DbConnection.close(goodDao.getCon(),goodDao.getPre(),goodDao.getResultSet());
        out.println("1.返回上一级\t其他.退出");
        String input=ScannerInfo.inputChose();
        String regex="[1]";
        if (input.matches(regex)) {
            goodMainPage();
        }else {
            exit(1);
        }
    }

    /**
     * 添加商品
     *
     */
    private static void addGoodPage(){
        //创建商品表操作对象
        GoodDao goodDao=new GoodDao();
        while (true) {
            //输入商品信息
            out.println("================添加商品==============");
            out.print("商品名称");
            String name = ScannerInfo.inputString();
            out.print("商品单价");
            double price = ScannerInfo.inputDouble();
            out.print("商品库存");
            int number = (int) ScannerInfo.inputDouble();
            out.print("商品备注");
            String note = ScannerInfo.inputString();
            //检查输入是否合法
            boolean check = ScannerInfo.checkAddGood(name, price, number);
            if (check) {
                //将商品信息加入商品表
                boolean result = goodDao.addGood(name, price, number, note);
                if (result) {
                    out.println("添加成功!!!\n继续添加么? 1.继续\t 0.返回上一级");
                    while (true) {
                        String chose = ScannerInfo.inputChose();
                        if (chose.matches("[01]")) {
                            //返回上级菜单
                            if (Integer.parseInt(chose) == 0) {
                                //释放资源
                                DbConnection.close(goodDao.getCon(), goodDao.getPre(), goodDao.getResultSet());
                                goodMainPage();
                            } else {
                                continue;
                            }
                        }
                        out.println("!输入错误!");
                    }
                } else {
                    out.println("添加失败,请重试");
                }
            } else {
                out.println("添加失败,请重试");
            }
        }
    }

    /**
     * 修改商品
     */
    private static void alterGoodPage(){
        GoodDao goodDao=new GoodDao();
        //显示商品列表
        goodDao.showGoods();
        while (true) {
            out.println("请输入你要修改的商品id");
            int id = (int) ScannerInfo.inputDouble();
            //判断id是否存在
            if (goodDao.checkGoodId(id)) {
                //修改商品信息
                out.println("正在修改商品id=" + id + "的商品信息:");
                out.print("要修改的商品名称");
                String name = ScannerInfo.inputString();
                out.print("要修改的商品单价");
                double price = ScannerInfo.inputDouble();
                out.print("要修改的商品库存");
                int number = (int) ScannerInfo.inputDouble();
                out.print("要修改的商品备注");
                String note = ScannerInfo.inputString();
                //验证输入合法
                boolean check = ScannerInfo.checkGood(name, price, number);
                if (check) {
                    out.printf("\nid=%d修改后的信息为名称=%s,单价=%5.2f,库存=%d,备注=%s\n 1.确认修改 0.放弃修改", id, name, price, number, note);
                    String chose = ScannerInfo.inputChose();
                    if (chose.matches("[01]")) {
                        switch (Integer.parseInt(chose)) {
                            case 0:
                                //释放资源
                                DbConnection.close(goodDao.getCon(), goodDao.getPre(), goodDao.getResultSet());
                                goodMainPage();
                                break;
                            case 1:
                                //操作数据表,执行update语句
                                goodDao.updateGood(id, name, price, number, note);
                                //释放资源
                                DbConnection.close(goodDao.getCon(), goodDao.getPre(), goodDao.getResultSet());
                                //返回上级界面
                                goodMainPage();
                                break;
                            default:
                                break;
                        }
                    } else {
                        //释放资源
                        DbConnection.close(goodDao.getCon(), goodDao.getPre(), goodDao.getResultSet());
                        exit(2);
                    }

                }
            } else {
                out.println("!商品不存在!");
            }
        }
    }

    /**
     * 下架商品
     */
    private static void deleteGoodPage(){
        //显示商品列表
        GoodDao good=new GoodDao();
        good.showGoods();
        out.println("请输入你要下架的商品id:");
        while (true){
                int id = ScannerInfo.inputInt();
                //验证id是否存在
                if (good.checkGoodId(id)) {
                    String goodInfo = good.getGood(id);
                    out.println("删除的商品信息:\n"+goodInfo);
                    out.println("确认删除么? 1.确认修改 0.放弃修改");
                    String input=ScannerInfo.inputChose();
                    if (input.matches("[01]")) {
                        switch (Integer.parseInt(input)){
                            case 0:
                                goodMainPage();
                                break ;
                            case 1:
                                //操作数据表,执行delete语句
                                boolean result = good.deleteGood(id);
                                //释放资源
                                DbConnection.close(good.getCon(),good.getPre(),good.getResultSet());
                                if (!result) {
                                    //删除失败退出系统
                                    exit(403);
                                }
                                goodMainPage();
                                break ;
                            default:
                                break ;
                        }
                    }else {
                        exit(2);
                    }
                    break;
                }else {
                    out.println("该商品不存在");
                }

        }
        //释放资源
        DbConnection.close(good.getCon(),good.getPre(),good.getResultSet());
    }
}
