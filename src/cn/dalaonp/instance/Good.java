package cn.dalaonp.instance;

/**
 * Good表实例
 */
public final class Good {
    //主键,自动增长
    private int GoodId;
    //商品名称
    private String GoodName;
    //商品单价
    private double GoodPrice;
    //商品库存
    private int GoodNumber;
    //备注,可为空
    private String GoodNote;

    /**
     * 添加商品
     * 根据商品名称,单价,库存数量,备注
     * @param goodName 商品名称
     * @param goodPrice 单价
     * @param goodNumber 库存数量
     */
    public Good(String goodName, double goodPrice, int goodNumber, String goodNote) {
        GoodName = goodName;
        GoodPrice = goodPrice;
        GoodNumber = goodNumber;
        GoodNote = goodNote;
    }

    /**
     * 修改商品信息
     * 根据商品id,修改名称
     * @param goodId
     * @param goodName
     */
    public Good(int goodId, String goodName) {
        GoodId = goodId;
        GoodName = goodName;
    }

    /**
     * 修改商品信息
     * 根据商品id,修改价格
     * @param goodId
     * @param goodPrice
     */

    public Good(int goodId, double goodPrice) {
        GoodId = goodId;
        GoodPrice = goodPrice;
    }

    public Good(int goodId, String goodName, double goodPrice, int goodNumber, String goodNote) {
        GoodId = goodId;
        GoodName = goodName;
        GoodPrice = goodPrice;
        GoodNumber = goodNumber;
        GoodNote = goodNote;
    }

    @Override
    public String toString() {
        return "\t" + GoodId + "\t" + GoodName + "\t" + GoodPrice +"\t"+ GoodNumber +
                "\t" + GoodNote+ "";
    }

    public int getGoodId() {
        return GoodId;
    }

    public void setGoodId(int goodId) {
        GoodId = goodId;
    }

    public String getGoodName() {
        return GoodName;
    }

    public void setGoodName(String goodName) {
        GoodName = goodName;
    }

    public double getGoodPrice() {
        return GoodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        GoodPrice = goodPrice;
    }

    public int getGoodNumber() {
        return GoodNumber;
    }

    public void setGoodNumber(int goodNumber) {
        GoodNumber = goodNumber;
    }

    public String getGoodNote() {
        return GoodNote;
    }

    public void setGoodNote(String goodNote) {
        GoodNote = goodNote;
    }
}
