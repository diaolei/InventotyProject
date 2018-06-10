package www.zhaoxinkeji.com.inventotyproject.app;


import android.os.Environment;

public class AppConstant {

    //============================================全局配置常量=====================================
    /**
     * DEBUG模式
     */
    public static final Boolean IS_DEBUG = true;

    /**
     * Android请求合法值
     */
    public static final String ANDROID_VALUE_SALT = "22db4860f15e9a5a";

    /**
     * Android平台值
     */
    public static final String ANDROID_VALUE_PLATFORM = "android";

    /**
     * Android固定值
     */
    public static final String ANDROID_VALUE_T = "b1522de9a4860f5a";
    /**
     * 数据库名
     */
    public static final String DB_NAME = "zwzt_db";

    /**
     * 阿里热修复AppKey
     */
    public static final String SOPHIX_APPKEY = "24777924-1";

    /**
     * 阿里热修复AppSecret
     */
    public static final String SOPHIX_APPSECRET = "2c4666047dea59982173f75288629bcc";

    /**
     * 阿里热修复RSA
     */
    public static final String SOPHIX_RSA = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCykTBcmoRJ7s0T1XSSNmUT28NxQ5nxN5wvN+DH/+9A7wXcbkA8ejpE3V88qZgrccBiGnO5vouMtWQZIBmQMvk5mjIReoOUmJB5aGKJRK8x+v7uedPNPIv9zOUx2IOwz4LDab7QW74KmqvBgV+ewC3VFx7OaDZw8crhgdCUpq0tPDVQTMXVwtjiBbZsn5YSIkJ4Xwgnc4HmCfuzqsphq05fd57yJEYNbh8h2FZNn+ilvg35AFCRvFy2oO/Juj3wsYJgRwtw38V64zXkDVS4UUAx/g0uTFM8N5NT9NxBzlFdnXJYskGPNqqhVQbFvBqgAVRY+QO0uzajtNUsnhMskn6HAgMBAAECggEBAIMZCwuBucvZ0aBVH29VFiJdPwEB45xv+m/EM3hCU3I1yfTK0MhV6D+00v3jeT+0FM0xpHLjDOsyJRo5MetnMQh0R3wDmWTYGYG4/FbRIVqtbsrAkJpmcFTWQBK3Zf/Cabt0AE5SfJSmd6DqjbwsVbnTbp+VbRrAufWT7AGm2Q+Sng1payo5CYTxKxlIfBExxJDLglK0w5epEQ0N2A47JA/+fhQkbUqjOTu8rOQVzxb//IpZLevvRPSrrfbe7dXUQc2PrQUpM1finIk30lvMmJAJbQ9hejTd7BYGnMkLq7OnLcKbWDiF/xlPEAIe3rU/GsVih3SBGj8cqlc/05i/aNECgYEA+Ffu5vPqfCDJMFMg5grANhh6WvmwCCsXql0hzAdQTlZiTpipDrLt9Do9TXffRjYo8RFe1yrRNRVY+mQTmNpjtq23VSzam83n2aT8hJvh6FX2s9NCsF6X29f/PxhTcsjd2gTekDW5gPWc0LwoN1LbLxRkiAM1giZtsPnddjqgE0kCgYEAuBKKnRsPycoqgLZ3C55+bJ75RvQEz4syNBsob1tCSNIEfr86wUi3bYo5d7OINXHWhtHnKW19DRoU5L778jHAltwXYOXUIuAjubKyMaJVQn/RVCBizlwT3CzdfcsuR+1LU89hil8kv04pWgZeubsjHN00al/wU1msSeUigeGtM08CgYEAu5S/0RbgQM4K6UOBEAGC2dl3YgxDWIgkIfyisJe9os5BBxjsaTvh3J24/DDVJxV1q1DxkBi5WR08zjSVUNl0g+GHHwD4RsMqowyp6AhOhM2ZwI6MSufIULhABgp7zbHHUQxoiAjsi2n5/viAb/lXsaH3pXy3CA26wPiavNjfXHECgYAzQ5avInZHMLKdnjORXNbA0ZWDGTqtwop80IN2X+gZ98OguQlYAzRyoWhX2fw5RjcE3TiP5RpKp9Thz/7C+PA2E52/MsLd46aPdSEDib5NAuP9lfdumV4+l5AoFIoTJhcwhDUaIF1PmikSFWwUNfYVd9W9OnLoA7Vj7xipK3zdKQKBgQCyztEbka7D30JzUyTX7HaNYamNi5wKpmCiX7QEn7PwhXBiG1rf++DtT6t73dwniBqpI6mIQfAbyv9Fh89q/6D8PjscChB2k4kP9hXsglyHVm8K4WMPDJFbJSXyYWoOHlkxQYbKg07fyKBH4MQNSqOUk95n796mrtgnS6YsXLZyfw==";

    /**
     * 阿里云反馈AppKey
     */
    public static final String FEEDBACK_APPKEY = "24777924";

    /**
     * 阿里云反馈AppSecret
     */
    public static final String FEEDBACK_APPSECRET = "2c4666047dea59982173f75288629bcc";

    /**
     * 鲸鱼高校
     */
    public static final String WHALE_COLLEGE_WEB_SERVER_URL = "http://m.whalecollege.com/";

    /**
     * 广告图的路径
     */
    public static final String FILEPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/zwzt_/advert/";

    //=========================================全局SharePreferance常量=====================================
    /**
     * 是否第一次启动
     */
    public static final String SP_IS_FIRST_START = "sp_is_first_start";
    /**
     * 护眼模式
     */
    public static final String SP_EYE_SHIELD_MODE = "sp_eye_shield_mode";
    /**
     * 夜间模式
     */
    public static final String SP_NIGHT_MODE = "sp_night_mode";

    /**
     * 用户信息
     */
    public static final String SP_ISLOGIN = "islogin";
    public static final String SP_USERID = "userId";
    public static final String SP_USER_TOKEN = "userToken";
    public static final String SP_SHOWNAME = "showName";
    public static final String SP_HEAD_PIC = "picUrl";
    public static final String SP_MOBILE = "mobile";

    public static final String SP_WEIXIN_STATUS = "SP_WEIXIN_STATUS";
    public static final String SP_WEIBO_STATUS = "SP_WEIBO_STATUS";
    public static final String SP_QQ_STATUS = "SP_QQ_STATUS";

    /**
     * 广告图片地址
     */
    public static final String SP_ADVERT_IMAGE_NAME = "sp_advert_image_name";

    /**
     * 锁屏模式
     */
    public static final String SP_LOCK_SCREEN_STATUS = "sp_lock_screen_status";

    /**
     * 锁屏模式是哪种(所有纸条随机,喜欢的纸条随机)
     */
    public static final String SP_LOCK_SCREEN_FROM_CONCERN = "sp_lock_screen_from_concern";

    /**
     * 是否开启自定义字体
     */
    public static final String SP_CUSTOM_FONT = "sp_custom_font";

    /**
     * 自定义字体的大小,1代表普通,2代表大字,3代表超大字
     */
    public static final String SP_CUSTOM_SIZE = "sp_custom_size";

    /**
     * 绑定手机弹出框样式
     */
    public static final String SP_BIND_PHOEN_TYPE = "sp_bind_phone_type";

    /**
     * 绑定手机弹框是否显示
     */
    public static final String SP_BIND_PHOEN_FLAG = "sp_bind_phone_flag";

    /*==================================== 其他的一些常量 ===================================*/
    /**
     * 跳转我喜欢的页面标示
     */
    public static final int I_LIKE_TO_CALENDER = 0x99;

    /**
     * 跳转日历的页面标示
     */
    public static final int IMG_TO_CALENDER = 0x39;
    /**
     * 跳转文件夹的页面标示
     */
    public static final int IMG_TO_PAPER_CLIP = 0x40;

    /**
     * 跳转素材投稿的页面标示
     */
    public static final int SOURCE_POST_CLIP = 0x41;

    /**
     * 跳转作文投稿的页面标示
     */
    public static final int PAPER_POST_CLIP = 0x42;

    /**
     * 夜间模式
     */
    public static final String NIGHT_MODE = "NIGHT_MODE";

    public static int hasNewSiteMsg = 0;//是否有新的系统消息
    public static int hasNewDiscuss = 0;//是否有新的评论与回复
    public static int hasNewPraise = 0;//是否有新的赞同

    /**
     * 传递CommentBean 使用的key
     */
    public static final String KEY_TEXT_COMMENT_BEAN = "KEY_ARTICLE_BEAN_BEAN";

    /**
     * 传递评论回复使用的key
     */
    public static final String KEY_TEXT_COMMENT_TARGET_ID = "KEY_TEXT_COMMENT_TARGET_ID";

    /**
     * 传递评论回复使用的type
     */
    public static final String KEY_TEXT_COMMENT_TYPE = "KEY_TEXT_COMMENT_DISCUSS_TYPE";

    /**
     * 传递PraiseBean 使用的key
     */
    public static final String KEY_PRAISE_BEAN = "KEY_PRAISE_BEAN";


    /**
     * 记录纸条阅读时间,用户历史最近浏览记录
     */
    public static final String ARTICLE_READ_TIME = "article_read_time";


    /**
     * 跳转到 收到的 评论与回复 的请求码
     */
    public static final int COMMENT_AND_REPLY_DETAIL_ACTIVITY_REQUEST_CODE = 0x2000;

    /**
     * 跳转到 收到的 赞同我详情界面 的请求码
     */
    public static final int PRAISE_ME_DETAIL_ACTIVITY_REQUEST_CODE = 0x3000;


    /**
     * 用于存储 搜索类型列表的Json字符串
     */
    public static final String KEY_SEARCH_CATEGORY_JSON = "KEY_SEARCH_CATEGORY_JSON";

    /**
     * 搜索界面的存储key
     */
    public static final String SEARCH_DATA_HISTORY = "SEARCH_DATA_HISTORY";

    /**
     * 版本更新  时间间隔
     */
    public static final String SP_LAST_CHECK_VERSION_TIME = "SP_LAST_CHECK_VERSION_TIME";

    /**
     * 皮肤 管理key
     */
    public static final String SKIN_ID = "SKIN_ID";

    /**
     * 记录第一次左右滑动动画引导图展示,时机为第一次进来
     */
    public static final String IS_FIRST_SHOW_LEFT_AND_RIGHT = "is_first_show_left_and_right";

    /**
     * 记录第一次上下滑动动画引导图展示,时机为第一次翻转纸条进来
     */
    public static final String IS_FIRST_SHOW_TOP_AND_BOTTOM = "is_first_show_top_and_bottom";

    /**
     * 带特定标记的页面需要验证登录的字符
     */
    public static final String isLogin = "limitlogin=1";

    /**
     * 是否有浏览器标识显示
     */
    public static final String browser = "browser=1";
    /*==================================== 其他的一些常量 ===================================*/
}
