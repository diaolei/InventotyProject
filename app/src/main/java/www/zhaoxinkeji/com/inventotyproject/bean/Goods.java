package www.zhaoxinkeji.com.inventotyproject.bean;

import java.util.List;

/**
 * Created by 27631 on 2018/3/7.
 */

public class Goods {

    /**
     * code : 100
     * content :
     * isShow :
     * msgInfo : 调用成功
     */

    private String code;
    private ContentBean content;
    private String isShow;
    private String msgInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    public static class ContentBean {
        private List<GoodsBean> goods;

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * barcodeid : 7777777
             * gifNum :
             * goodsId : 20210
             * goodsName : 尤溪红糖果
             * goodsNum : -213
             * scanNum : 250.00
             */

            private String barcodeid;
            private String gifNum;
            private String goodsId;
            private String goodsName;
            private String goodsNum;
            private String scanNum;
            private String billNo;

            public String getBillNo() {
                return billNo;
            }

            public void setBillNo(String billNo) {
                this.billNo = billNo;
            }

            public String getBarcodeid() {
                return barcodeid;
            }

            public void setBarcodeid(String barcodeid) {
                this.barcodeid = barcodeid;
            }

            public String getGifNum() {
                return gifNum;
            }

            public void setGifNum(String gifNum) {
                this.gifNum = gifNum;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(String goodsNum) {
                this.goodsNum = goodsNum;
            }

            public String getScanNum() {
                return scanNum;
            }

            public void setScanNum(String scanNum) {
                this.scanNum = scanNum;
            }
        }
    }
}
