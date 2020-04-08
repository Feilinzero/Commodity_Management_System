package cn.dalaonp.instance;

/**
 * 售货员表
 * @author ouyangzhifei
 */
public final class Salesman {
    private int saleId;
    private String saleName;
    private String salePassword;

    /**
     * 添加售货员信息
     * 根据姓名,密码
     * @param saleId 售货员id
     * @param saleName 售货员名称或用户名
     * @param salePassword 售货员密码
     */
    public Salesman(int saleId, String saleName, String salePassword) {
        this.saleId=saleId;
        this.saleName = saleName;
        this.salePassword = salePassword;
    }

    @Override
    public String toString() {
        return "售货员Id=" + saleId +
                ", 用户名='" + saleName + '\'' +
                ", 密码='" + salePassword + '\'';
    }
}
