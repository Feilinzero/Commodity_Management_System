package cn.dalaonp.instance;

/**
 * 售货员表
 */
public final class Salesman {
    //主键,自动增长
    private int SaleId;
    //售货员姓名
    private String SaleName;
    //售货员登录密码
    private String SalePassword;

    /**
     * 添加售货员信息
     * 根据姓名,密码
     * @param saleName
     * @param salePassword
     */
    public Salesman(String saleName, String salePassword) {
        SaleName = saleName;
        SalePassword = salePassword;
    }

    public int getSaleId() {
        return SaleId;
    }

    public void setSaleId(int saleId) {
        SaleId = saleId;
    }

    public String getSaleName() {
        return SaleName;
    }

    public void setSaleName(String saleName) {
        SaleName = saleName;
    }

    public String getSalePassword() {
        return SalePassword;
    }

    public void setSalePassword(String salePassword) {
        SalePassword = salePassword;
    }
}
