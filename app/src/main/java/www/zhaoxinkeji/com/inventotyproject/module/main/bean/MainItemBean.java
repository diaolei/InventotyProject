package www.zhaoxinkeji.com.inventotyproject.module.main.bean;

import java.io.Serializable;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MainItemBean implements Serializable {
    //图标资源id
    private int resID;
    //图标名称
    private String iconName;

    public MainItemBean(int resID, String iconName) {
        this.resID = resID;
        this.iconName = iconName;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    @Override
    public String toString() {
        return "MainItemBean{" +
                "resID=" + resID +
                ", iconName='" + iconName + '\'' +
                '}';
    }
}
