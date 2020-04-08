package cn.dalaonp.instance;

/**
 * 销售记录表
 * @author ouyangzhifei
 */
public final class SoldNote {
    private int soldId;
    private int goodId;
    private int saleId;
    private int soldNumber;
    private String soldDate;

    /**
     * 查询销售记录
     * @param soldId
     * @param goodId
     * @param saleId
     * @param soldNumber
     * @param soldDate
     */
    public SoldNote(int soldId, int goodId, int saleId, int soldNumber, String soldDate) {
        this.soldId = soldId;
        this.goodId = goodId;
        this.saleId = saleId;
        this.soldNumber = soldNumber;
        this.soldDate = soldDate;
    }

    @Override
    public String toString() {
        return "销售记录id=" + soldId +
                ", 商品id=" + goodId +
                ", 售货员id=" + saleId +
                ", 销售数量=" + soldNumber +
                ", 销售时间='" + soldDate + '\'';
    }
}
