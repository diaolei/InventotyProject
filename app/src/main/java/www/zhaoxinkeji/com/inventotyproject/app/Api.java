package www.zhaoxinkeji.com.inventotyproject.app;

public class Api {

    //网络协议
    private final static String HTTP = "http://";
    private final static String HTTPS = "https://";

    /**
     * 正式环境
     */
    public static final String SERVER_IP = "47.97.123.158:8880";
    public static final String JAVA_DOMAIN_NAME = "formal";//正式服务器标示，用于retrofit的BaseUrl切换
    public static final String APP_JAVA_DOMAIN = HTTP + SERVER_IP;

    /**
     * 测试环境
     */
    public static final String TEST_SERVER_IP = "interface.zaosin.cn";
    public static final String JAVA_DOMAIN_TEST = "test";//测试服务器标示，用于retrofit的BaseUrl切换
    public static final String APP_JAVA_DOMAIN_TEST = HTTP + TEST_SERVER_IP;

    //----------------------------------- 3.2 新增接口 -----------------------------------------------

    //基础接口
    public static final String BASE_URL = "/pda/StorePDAWebService.ashx";
}
