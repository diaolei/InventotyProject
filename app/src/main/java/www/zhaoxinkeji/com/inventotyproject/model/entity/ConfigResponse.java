package www.zhaoxinkeji.com.inventotyproject.model.entity;

import java.io.Serializable;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/04/28
 *     desc   : （启动图，个人中心banner，纸条安利，纸条福利，登陆限制）
 *     version: 1.0
 * </pre>
 */

public class ConfigResponse implements Serializable {

    /**
     * anli : {"id":5,"pic":"","title":"安利","url":"http://47.97.123.158:8880/zt/anli/share.html?browser=1&limitlogin=1","isShow":1,"type":"anli","startTime":1515541945000,"endTime":1542066745000}
     * action : {"id":3,"pic":"http://oss.zuowenzhitiao.com/redirect/2018-03-28/b58523d369054963ae6b76a1ff4272da.jpg","title":"万能胶活动第三期","url":"http://app.zuowenzhitiao.com/zt/anli/share-3rd.html?browser=1&limitlogin=1","isShow":1,"type":"action","startTime":1522080000000,"endTime":1540915200000}
     * limitLogin : false
     */

    private ActionBean action;//个人中心banner
    private AnliBean anli;//纸条安利
    private FuliBean fuli;//纸条福利
    private StartBean start;//启动图
    private boolean limitLogin;//登陆限制

    public ActionBean getAction() {
        return action;
    }

    public void setAction(ActionBean action) {
        this.action = action;
    }

    public AnliBean getAnli() {
        return anli;
    }

    public void setAnli(AnliBean anli) {
        this.anli = anli;
    }

    public FuliBean getFuli() {
        return fuli;
    }

    public void setFuli(FuliBean fuli) {
        this.fuli = fuli;
    }

    public StartBean getStart() {
        return start;
    }

    public void setStart(StartBean start) {
        this.start = start;
    }

    public boolean isLimitLogin() {
        return limitLogin;
    }

    public void setLimitLogin(boolean limitLogin) {
        this.limitLogin = limitLogin;
    }

    public static class ActionBean implements Serializable {
        /**
         * id : 3
         * pic : http://oss.zuowenzhitiao.com/redirect/2018-03-28/b58523d369054963ae6b76a1ff4272da.jpg
         * title : 万能胶活动第三期
         * url : http://app.zuowenzhitiao.com/zt/anli/share-3rd.html?browser=1&limitlogin=1
         * isShow : 1
         * type : action
         * startTime : 1522080000000
         * endTime : 1540915200000
         */

        private int id;
        private String pic;
        private String title;
        private String url;
        private int isShow;
        private String type;
        private long startTime;
        private long endTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getIsShow() {
            return isShow;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "ActionBean{" +
                    "id=" + id +
                    ", pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", isShow=" + isShow +
                    ", type='" + type + '\'' +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    '}';
        }
    }


    public static class AnliBean implements Serializable {
        /**
         * id : 5
         * pic :
         * title : 安利
         * url : http://47.97.123.158:8880/zt/anli/share.html?browser=1&limitlogin=1
         * isShow : 1
         * type : anli
         * startTime : 1515541945000
         * endTime : 1542066745000
         */

        private int id;
        private String pic;
        private String title;
        private String url;
        private int isShow;
        private String type;
        private long startTime;
        private long endTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getIsShow() {
            return isShow;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "AnliBean{" +
                    "id=" + id +
                    ", pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", isShow=" + isShow +
                    ", type='" + type + '\'' +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    '}';
        }
    }

    public static class FuliBean implements Serializable {
        /**
         * id : 5
         * pic :
         * title : 安利
         * url : http://47.97.123.158:8880/zt/anli/share.html?browser=1&limitlogin=1
         * isShow : 1
         * type : anli
         * startTime : 1515541945000
         * endTime : 1542066745000
         */

        private int id;
        private String pic;
        private String title;
        private String url;
        private int isShow;
        private String type;
        private long startTime;
        private long endTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getIsShow() {
            return isShow;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "FuliBean{" +
                    "id=" + id +
                    ", pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", isShow=" + isShow +
                    ", type='" + type + '\'' +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    '}';
        }
    }

    public static class StartBean implements Serializable {
        /**
         * id : 5
         * pic :
         * title : 安利
         * url : http://47.97.123.158:8880/zt/anli/share.html?browser=1&limitlogin=1
         * isShow : 1
         * type : anli
         * startTime : 1515541945000
         * endTime : 1542066745000
         */

        private int id;
        private String pic;
        private String title;
        private String url;
        private int isShow;
        private String type;
        private long startTime;
        private long endTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getIsShow() {
            return isShow;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "StartBean{" +
                    "id=" + id +
                    ", pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", isShow=" + isShow +
                    ", type='" + type + '\'' +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ConfigResponse{" +
                "action=" + action +
                ", anli=" + anli +
                ", fuli=" + fuli +
                ", start=" + start +
                ", limitLogin=" + limitLogin +
                '}';
    }
}
