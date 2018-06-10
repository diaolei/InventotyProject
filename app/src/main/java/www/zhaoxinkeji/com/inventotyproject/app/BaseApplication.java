package www.zhaoxinkeji.com.inventotyproject.app;

import android.app.Application;
import android.content.Context;

import www.zhaoxinkeji.com.inventotyproject.http.imageloader.ImageLoader;
import www.zhaoxinkeji.com.inventotyproject.http.imageloader.glide.ImageConfigImpl;
import www.zhaoxinkeji.com.inventotyproject.lifecycle.ActivityLifecycleForRxLifecycle;


public class BaseApplication extends Application {

    private ActivityLifecycleForRxLifecycle mActivityLifecycleForRxLifecycle = ActivityLifecycleForRxLifecycle.getInstance();

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //保存本地变量
        instance = this;

        //注册RxLifecycle
        registerActivityLifecycleCallbacks(mActivityLifecycleForRxLifecycle);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    /**
     * 在模拟环境中程序终止时会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mActivityLifecycleForRxLifecycle != null) {
            unregisterActivityLifecycleCallbacks(mActivityLifecycleForRxLifecycle);
        }
        mActivityLifecycleForRxLifecycle = null;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.getInstance().clear(
                getInstance(),
                ImageConfigImpl
                        .builder()
                        .isClearMemory(true)
                        .build());
    }
}
