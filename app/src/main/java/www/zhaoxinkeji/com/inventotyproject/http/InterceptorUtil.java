package www.zhaoxinkeji.com.inventotyproject.http;


import okhttp3.logging.HttpLoggingInterceptor;
import www.zhaoxinkeji.com.inventotyproject.utils.LogUtil;

public class InterceptorUtil {

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {


        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.v("—————" + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }
}
