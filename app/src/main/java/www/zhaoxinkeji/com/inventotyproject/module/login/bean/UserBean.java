package www.zhaoxinkeji.com.inventotyproject.module.login.bean;

import java.io.Serializable;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class UserBean implements Serializable {

    private long userID;// 用户ID
    private long storeID;// 库房ID（供应商、库存组织ID、经销商ID）
    private int userType;// 身份类型（1 供应商  2 企业仓库 3 经销商）

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userID=" + userID +
                ", storeID=" + storeID +
                ", userType=" + userType +
                '}';
    }
}
