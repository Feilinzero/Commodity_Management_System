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
     * @param saleId
     * @param saleName
     * @param salePassword
     */
    public Salesman(int saleId, String saleName, String salePassword) {
        this.saleId=saleId;
        this.saleName = saleName;
        this.salePassword = salePassword;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSalePassword() {
        return salePassword;
    }

    public void setSalePassword(String salePassword) {
        this.salePassword = salePassword;
    }

    @Override
    public String toString() {
        return "售货员Id=" + saleId +
                ", 用户名='" + saleName + '\'' +
                ", 密码='" + salePassword + '\'';
    }
}
