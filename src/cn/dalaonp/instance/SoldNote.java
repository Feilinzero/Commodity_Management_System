package cn.dalaonp.instance;

/**
 * 销售记录表
 */
public final class SoldNote {
    //主键,自动生成
    private int SoldId;
    //商品id,参照商品表
    private int GoodId;
    //售货员id,参照售货员表
    private int SaleId;
    //销售数量
    private int SoldNumber;
    //销售时间
    private String SoldDate;

    /**
     * 查询销售记录
     * @param soldId
     * @param goodId
     * @param saleId
     * @param soldNumber
     * @param soldDate
     */
    public SoldNote(int soldId, int goodId, int saleId, int soldNumber, String soldDate) {
        SoldId = soldId;
        GoodId = goodId;
        SaleId = saleId;
        SoldNumber = soldNumber;
        SoldDate = soldDate;
    }
}
