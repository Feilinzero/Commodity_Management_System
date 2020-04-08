package cn.dalaonp.action;

import cn.dalaonp.dao.SoldNoteDao;
import cn.dalaonp.dao.SalesmanDao;
import cn.dalaonp.db.DbConnection;
import cn.dalaonp.instance.Salesman;
import cn.dalaonp.instance.SoldNote;
import cn.dalaonp.tools.ScannerInfo;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * 后台管理界面
 * @author ouyangzhifei
 */
public class BackstagePage {

    private static boolean initial;
    static {
        initial= new SalesmanDao().isInitial();
    }

    /**
     * 后台管理登陆界面
     */
    public static void loginBackstagePage() {
        SalesmanDao smDao=new SalesmanDao();
        //输入账号密码,循环验证,错误3次退出系统
        if (initial){
            int count=0;
            boolean flag = false;
            do {
                System.out.println("账号:");
                String account= ScannerInfo.inputString();
                System.out.println("密码:");
                String password=ScannerInfo.inputString();
                System.out.println("=======================");
                //验证账号密码
                flag = smDao.checkAccount(account, password);
                if (flag) {
                    DbConnection.close(smDao.getCon(),smDao.getPre(),smDao.getResultSet());
                    mainBackstagePage();
                }else {
                    System.out.println("账号或密码错误,请重试");
                    count++;
                }
            }while (count==3);
            System.out.println("错误登录3次,退出系统");
            System.exit(1);
        }else {
            //添加用户
            out.println("后台初始化.....");
            boolean flag = smDao.addUser();
            if (flag){
                DbConnection.close(smDao.getCon(),smDao.getPre(),smDao.getResultSet());
                out.println("用户添加成功,进入登录界面....");
                initial = true;
                loginBackstagePage();
            }else {
                out.println("用户添加失败,返回主菜单....");
                DbConnection.close(smDao.getCon(),smDao.getPre(),smDao.getResultSet());
                MainPage.mainPage();
            }
        }

    }



    /**
     * 后台管理主界面
     */
    private static void mainBackstagePage() {
        out.println("==============后台管理菜单=================");
        out.println("\t\t\t\t1.今日销售记录");
        out.println("\t\t\t\t2.历史销售记录");
        out.println("\t\t\t\t3.售货员管理");
        out.println("==========================================");
        out.println("输入指定数字进入,输入0返回上一级");
        //判断选择
        String input = "";
        String rule="[0-3]";
        do {
            input = ScannerInfo.inputChose();
            if (input.matches(rule)){
                switch (Integer.parseInt(input)){
                    case 0:
                        //返回一级主菜单
                        MainPage.mainPage();
                        break;
                    case 1:
                        //今日销售记录
                        saleNoteToday();
                        break;
                    case 2:
                        //历史销售记录
                        saleNoteHistory();
                        break;
                    case 3:
                        //售货员管理界面
                        salesmanManagement();
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
     * 售货员管理
     */
    private static void salesmanManagement() {
        out.println("==============售货员管理菜单================");
        out.println("\t\t\t\t1.售货员列表");
        out.println("\t\t\t\t2.添加售货员");
        out.println("\t\t\t\t3.删除售货员");
        out.println("==========================================");
        out.println("输入指定数字进入,输入0返回上一级");
        //判断选择
        String input = "";
        String rule="[0-3]";
        do {
            input = ScannerInfo.inputChose();
            if (input.matches(rule)){
                switch (Integer.parseInt(input)){
                    case 0:
                        //返回一级主菜单
                        mainBackstagePage();
                        break;
                    case 1:
                        //售货员列表
                        salesmanList();
                        break;
                    case 2:
                        //添加售货员
                        addSalesman();
                        break;
                    case 3:
                        //删除售货员
                        deleteSalesman();
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
     * 删除售货员
     */
    private static void deleteSalesman() {
        SalesmanDao smDao=new SalesmanDao();
        //显示售货员列表
        smDao.showList();
        while (true){
            //根据id或用户名删除售货员
            out.println("请输入要删除的售货员id");
            int saleId=ScannerInfo.inputInt();
            //检查id是否存在
            boolean flag = smDao.checkSaleId(saleId);
            if (flag){
                out.print("确认删除么? 1.确认  0.返回上一级");
                String chose = ScannerInfo.inputChose();
                String regex="[01]";
                if (chose.matches(regex)){
                    switch (chose){
                        case "0":
                            //释放资源,返回上一级
                            DbConnection.close(smDao.getCon(),smDao.getPre(),smDao.getResultSet());
                            salesmanManagement();
                            break;
                        case "1":
                            //删除售货员
                            flag=smDao.deleteSale(saleId);
                            if (flag){
                                out.println("删除成功");
                            }else {
                                out.println("删除失败,返回上级菜单");
                            }
                            DbConnection.close(smDao.getCon(),smDao.getPre(),smDao.getResultSet());
                            salesmanManagement();
                            break;
                        default:
                            break;
                    }
                }

            }else {
                out.println("id不存在,请重试");
            }

        }

    }

    /**
     * 添加售货员
     */
    private static void addSalesman() {
        SalesmanDao smDao=new SalesmanDao();
       while (true){
           boolean flag = smDao.addUser();
           if (flag){
               out.println("用户添加成功");
           }else {
               out.println("用户添加失败");
           }
           out.println("还要继续添加用户么? 1.继续  0.返回上一级");
           String chose=ScannerInfo.inputChose();
           String regex="[01]";
           if (chose.matches(regex)){
               switch (chose){
                   case "0":
                       DbConnection.close(smDao.getCon(),smDao.getPre(),smDao.getResultSet());
                       salesmanManagement();
                       break;
                   case "1":
                        continue;
                   default:
                       break;
               }
           }
       }

    }

    /**
     * 显示售货员列表
     */
    private static void salesmanList() {
        SalesmanDao smDao=new SalesmanDao();
        List<Salesman> salesmanList=new ArrayList<>();
        salesmanList = smDao.getSalesmanList();
        if (salesmanList!=null){
            for (Salesman salesman : salesmanList) {
                out.println(salesman.toString());
            }
            out.println("读取完毕");
        }else {
            out.println("读取售货员列表失败");
        }
        out.println("输入0返回上一级");
        while (true){
            String input=ScannerInfo.inputChose();
            String regex="[0]";
            if(input.matches(regex)){
                DbConnection.close(smDao.getCon(),smDao.getPre(),smDao.getResultSet());
                salesmanManagement();
            }
        }

    }

    /**
     * 商品销售历史记录
     */
    private static void saleNoteHistory() {
        SoldNoteDao snDao=new SoldNoteDao();
        List<SoldNote> soldNoteList= snDao.getNoteList();
        out.println("=============历史销售记录=================");
        if(soldNoteList.size()!=0&&soldNoteList!=null){
            for (SoldNote soldNote : soldNoteList) {
                out.println(soldNote.toString());
            }
        }else {
            out.println("\t目前无销售记录");
        }
        //返回上一级
        out.println("输入0返回上一级");
        while (true){
            String input=ScannerInfo.inputChose();
            String regex="[0]";
            if(input.matches(regex)){
                DbConnection.close(snDao.getCon(),snDao.getPre(),snDao.getResultSet());
                mainBackstagePage();
            }
        }
    }

    /**
     * 商品当日销售记录
     */
    private static void saleNoteToday() {
        SoldNoteDao snDao=new SoldNoteDao();
        List<SoldNote> soldNoteList= snDao.getNoteListToDay();
        out.println("=============当日销售记录=================");
        if(soldNoteList.size() != 0){
            for (SoldNote soldNote : soldNoteList) {
                out.println(soldNote.toString());
            }
        }else {
            out.println("\t目前无销售记录");
        }
        //返回上一级
        out.println("输入0返回上一级");
        while (true){
            String input=ScannerInfo.inputChose();
            String regex="[0]";
            if(input.matches(regex)){
                DbConnection.close(snDao.getCon(),snDao.getPre(),snDao.getResultSet());
                mainBackstagePage();
            }
        }
    }

}
