package cn.dalaonp.instance;

/**
 * Good表实例
 * @author ouyangzhifei
 */
public final class Good {
    private int goodId;
    private String goodName;
    private double goodPrice;
    private int goodNumber;
    private String goodNote;

    /**
     * 录入商品
     * 录入商品的数量
     * @param goodId 商品id
     * @param goodNumber 商品数量
     */
    public Good(int goodId, int goodNumber) {
        this.goodId = goodId;
        this.goodNumber = goodNumber;
    }

    /**
     *
     * @param goodId 商品id
     * @param goodName 商品名称
     * @param goodPrice 商品单价
     * @param goodNumber 商品数量
     * @param goodNote 商品备注
     */
    public Good(int goodId, String goodName, double goodPrice, int goodNumber, String goodNote) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodNumber = goodNumber;
        this.goodNote = goodNote;
    }
    @Override
    public String toString() {
        return "\tid=" + goodId + "\t名称=" + goodName + "\t单价=" + goodPrice +"\t库存数量="+ goodNumber +
                "\t备注=" + goodNote + "";
    }
    public int getGoodId() {
        return goodId;
    }
    public int getGoodNumber() {
        return goodNumber;
    }
}
