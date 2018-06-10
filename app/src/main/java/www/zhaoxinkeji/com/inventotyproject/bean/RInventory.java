package www.zhaoxinkeji.com.inventotyproject.bean;

import java.util.List;

/**
 * Created by 27631 on 2018/3/6.
 */

public class RInventory {
    /**
     * code : 100
     * content :
     * msgInfo : 调用成功
     */

    private String code;
    private ContentBean content;
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

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    public static class ContentBean {
        private List<BillsBean> bills;

        public List<BillsBean> getBills() {
            return bills;
        }

        public void setBills(List<BillsBean> bills) {
            this.bills = bills;
        }

        public static class BillsBean {
            /**
             * billNo : PD20170000000025
             * createTime : 2017-09-01 00:00:00
             * venderName :
             */

            private String billNo;
            private String createTime;
            private String venderName;

            public String getBillNo() {
                return billNo;
            }

            public void setBillNo(String billNo) {
                this.billNo = billNo;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getVenderName() {
                return venderName;
            }

            public void setVenderName(String venderName) {
                this.venderName = venderName;
            }
        }
    }
}
