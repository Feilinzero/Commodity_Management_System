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
     * @param goodId
     * @param goodNumber
     */

    public Good(int goodId, int goodNumber) {
        this.goodId = goodId;
        this.goodNumber = goodNumber;
    }

    /**
     * 添加商品
     * 根据商品名称,单价,库存数量,备注
     * @param goodName 商品名称
     * @param goodPrice 单价
     * @param goodNumber 库存数量
     */
    public Good(String goodName, double goodPrice, int goodNumber, String goodNote) {
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodNumber = goodNumber;
        this.goodNote = goodNote;
    }

    /**
     * 修改商品信息
     * 根据商品id,修改名称
     * @param goodId
     * @param goodName
     */
    public Good(int goodId, String goodName) {
        this.goodId = goodId;
        this.goodName = goodName;
    }

    /**
     * 修改商品信息
     * 根据商品id,修改价格
     * @param goodId
     * @param goodPrice
     */

    public Good(int goodId, double goodPrice) {
        this.goodId = goodId;
        this.goodPrice = goodPrice;
    }

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

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public int getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(int goodNumber) {
        this.goodNumber = goodNumber;
    }

    public String getGoodNote() {
        return goodNote;
    }

    public void setGoodNote(String goodNote) {
        this.goodNote = goodNote;
    }
}
