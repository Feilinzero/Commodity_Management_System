package cn.dalaonp.tools;

import java.util.Scanner;

/**
 *输入类
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

    public static String inputString() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入:");
        String string=input.nextLine();
        return string;
    }

    public static double inputDouble() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入:");
        double nextDouble = input.nextDouble();
        return nextDouble;
    }
}
