package cn.dalaonp.tools;

import cn.dalaonp.dao.GoodDao;

import java.util.Scanner;

/**
 *输入类
 * @author ouyangzhifei
 */
public class ScannerInfo {
//    private static Scanner input = new Scanner(System.in);

    /**
     * 菜单选择
     * @return
     */
    public static String inputChose() {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入:");
        return input.next();
    }

    /**
     * 输入字符串
     * @return
     */
    public static String inputString() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入:");
        String string=input.nextLine();
        return string;
    }

    /**
     * 输入指定范围内的字数
     * @param words
     * @return
     */
    public static String inputString(int words) {
        Scanner input = new Scanner(System.in);
        String string = "";
        while (true){
            System.out.println("请输入:");
            string = input.nextLine();
            if (string.length()<=words){
                break;
            }else {
                System.out.println("请输入合法的");
            }
        }
        return string;
    }
    /**
     * 输入浮点数值
     * @return
     */
    public static double inputDouble() {
        Scanner input = new Scanner(System.in);
        double number = 0;
        //校验规则:最高8位整数
        String regex="^[0-9]{0,8}[.]?[0-9]{0,2}$";
        String string = "";
        while (true){
            System.out.println("请输入:");
            string = input.nextLine();
            if (string.matches(regex)){
                number = Double.parseDouble(string);
                break;
            }else {
                System.out.println("输入错误,输入合法的数字");
            }
        }
        return number;
    }

    /**
     * 添加商品的校验规则
     * @param name
     * @param price
     * @param number
     * @param note
     * @return
     */
    public static boolean checkAddGood(String name, double price, int number, String note) {
        if (name.isEmpty()){
            System.out.println("!商品名称不能为空!");
            return false;
        }
        //商品名称不能重复
        if (!new GoodDao().checkGoodName(name)){
            System.out.println("!商品已存在!");
            return false;
        }
        if (price <= 0){
            System.out.println("!商品单价必须大于0!");
            return false;
        }
        if (number <= 0){
            System.out.println("!添加商品数量必须大于0!");
            return false;
        }
        if (note.isEmpty()){
            note=" ";
        }
        return true;
    }

    /**
     * Good表字段值的合法校验
     * @param name
     * @param price
     * @param number
     * @param note
     * @return
     */
    public static boolean checkGood(String name, double price, int number, String note) {
        if (name.isEmpty()){
            System.out.println("!商品名称不能为空!");
            return false;
        }
        if (price <= 0){
            System.out.println("!商品单价必须大于0!");
            return false;
        }
        if (number <= 0){
            System.out.println("!添加商品数量必须大于0!");
            return false;
        }
        if (note.isEmpty()){
            note=" ";
        }
        return true;
    }

    public static int inputInt() {
        Scanner input = new Scanner(System.in);
        int number = 0;
        //校验规则:最高8位整数
        String regex="^[0-9]{1,8}$";
        String string = "";
        while (true){
            System.out.println("请输入:");
            string = input.nextLine();
            if (string.matches(regex)){
                number = Integer.parseInt(string);
                break;
            }else {
                System.out.println("输入错误,输入合法的数字");
            }
        }
        return number;
    }
}
