package www.zhaoxinkeji.com.inventotyproject.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by 27631 on 2017/8/11.
 */

public class OkHttpUtil {
    public static String TAG = "debug-okhttp";

    private OkHttpClient client;
    private static OkHttpUtil instance;
    // 超时时间
    public static final int TIMEOUT = 1000 * 60;

    //json请求
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private Handler handler = new Handler(Looper.getMainLooper());
    //private final BaseApplication mInstance;
    private int counter = 0;

    public static OkHttpUtil getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtil.class) {
                if (instance == null) {
                    instance = new OkHttpUtil();
                }
            }
        }
        return instance;
    }

    public OkHttpUtil() {
        client = new OkHttpClient();
        //mInstance = BaseApplication.getInstance();
        // 设置超时时间
        client.newBuilder().connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS).build();
    }

    /**
     * post请求，json数据为body
     *
     * @param url
     * @param map<String, Object>
     * @param callback
     */
    public void postJson(String url, HashMap<String, String> map,String json, final HttpCallback callback) {
        //map.put("app_version", gotoSplitStr(AppUtils.getVersionName(mInstance)));
        //map.put("device_type", "1");
       String content = JsonUtil.map2Json1(map)+"\""+"goods"+"\""+":"+json+"}";
        //Log.i("abc",content);
        //当所传参数为空时，会传默认空body,防止报错
        RequestBody body = okhttp3.internal.Util.EMPTY_REQUEST;
        //加上try-catch处理
        try {
            body = RequestBody.create(JSON, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Request request = new Request.Builder().url(url).post(body).build();

        onStart(callback);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO Auto-generated method stub

                if (response.isSuccessful()) {
                    onSuccess(callback, response.body().string());
                } else {
                    onError(callback, response.message());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // TODO Auto-generated method stub
                onError(callback, e.getMessage());
                e.printStackTrace();
            }

        });

    }

    /**
     * 截取版本号,用于后台方便进行版本管理
     */
    private String gotoSplitStr(String versionName) {
        counter = 0;
        if (countStr(versionName, ".") > 2) {
            return versionName.substring(0, versionName.lastIndexOf("."));
        } else {
            return versionName;
        }
    }

    /**
     * 判断str1中包含str2的个数
     *
     * @param str1
     * @param str2
     * @return counter
     */
    public int countStr(String str1, String str2) {
        if (str1.indexOf(str2) == -1) {
            return 0;
        } else if (str1.indexOf(str2) != -1) {
            counter++;
            countStr(str1.substring(str1.indexOf(str2) + str2.length()), str2);
            return counter;
        }
        return 0;
    }

    /**
     * post请求 map为body
     *
     * @param url
     * @param map
     * @param callback
     */
    public void post(String url, Map<String, Object> map, final HttpCallback callback) {

        /**
         * 创建请求的参数body
         */
        FormBody.Builder builder = new FormBody.Builder();

        /**
         * 遍历key
         */
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {

                System.out.println("Key = " + entry.getKey() + ", Value = "
                        + entry.getValue());
                builder.add(entry.getKey(), entry.getValue().toString());

            }
        }

        RequestBody body = builder.build();

        Request request = new Request.Builder().url(url).post(body).build();

        onStart(callback);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO Auto-generated method stub

                if (response.isSuccessful()) {

                    onSuccess(callback, response.body().string());

                } else {
                    onError(callback, response.message());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // TODO Auto-generated method stub
                e.printStackTrace();
                onError(callback, e.getMessage());
            }
        });
    }

    /**
     * get请求
     *
     * @param url
     * @param callback
     */
    public void get(String url, final HttpCallback callback) {

        Request request = new Request.Builder().url(url).build();

        onStart(callback);

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO Auto-generated method stub
                if (response.isSuccessful()) {
                    onSuccess(callback, response.body().string());
                } else {
                    onError(callback, response.message());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // TODO Auto-generated method stub
                onError(callback, e.getMessage());
                e.printStackTrace();
            }
        });

    }


    private void onStart(HttpCallback callback) {
        if (null != callback) {
            callback.onStart();
        }
    }

    private void onSuccess(final HttpCallback callback, final String data) {

        if (null != callback) {
            handler.post(new Runnable() {
                public void run() {
                    // 需要在主线程的操作。
                    callback.onSuccess(data);
                }
            });
        }
    }

    private void onError(final HttpCallback callback, final String msg) {
        if (null != callback) {
            handler.post(new Runnable() {
                public void run() {
                    // 需要在主线程的操作。
                    callback.onError(msg);
                }
            });
        }
    }


    /**
     * http请求回调
     *
     * @author Flyjun
     */
    public static abstract class HttpCallback {
        // 开始
        public void onStart() {
        }

        // 成功回调
        public abstract void onSuccess(String data);

        // 失败回调
        public void onError(String msg) {

        }
    }

    public Call postForm(String url, String content, final HttpCallback callback) {
        Log.d(TAG, "url = " + url + "\ncontent = " + content);

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), content);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        onStart(callback);

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "request fail: " + (e != null ? e.getMessage() : null));
                onError(callback, e != null ? e.getMessage() : null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "request onResponse: " + (response != null ? response.toString() : null));
                Log.d(TAG, "request onResponse: " + (response != null && response.body() != null ? response.body().toString() : null));
                if (response != null && response.isSuccessful()) {
                    onSuccess(callback, response.body() != null ? response.body().string() : null);
                } else {
                    onError(callback, response != null ? response.message() : null);
                }
            }
        });
        return call;
    }
}
