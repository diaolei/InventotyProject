package www.zhaoxinkeji.com.inventotyproject.model.entity;

import java.io.Serializable;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/05/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class AppVersionResponse implements Serializable {

    /**
     * id : 11
     * versionCode : 25
     * versionName : 4.0.0
     * versionInfo : 作文纸条4.0新版本更新。
     * downloadUrl : http://oss.zuowenzhitiao.com/zhitiao/zwzt_v4.0.0_20180206_0238_400_jiagu_sign.apk
     * type : 1
     * updateTime : 1517884945000
     * createTime : 1517884945000
     * "isForce": 1,
     * "size": 18
     */

    private int id;
    private String versionCode;//版本号
    private String versionName;//版本名称
    private String versionInfo;//版本信息提示语
    private String downloadUrl;//版本下载地址
    private int type;
    private long updateTime;//更新时间
    private long createTime;//创建时间
    private int isForce;//默认为 0，是否强制更新，0为不强制更新，1为强制更新
    private double size;//apk大小，M为单位

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getIsForce() {
        return isForce;
    }

    public void setIsForce(int isForce) {
        this.isForce = isForce;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "AppVersionResponse{" +
                "id=" + id +
                ", versionCode='" + versionCode + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionInfo='" + versionInfo + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", type=" + type +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", isForce=" + isForce +
                ", size=" + size +
                '}';
    }
}
