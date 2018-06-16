package www.zhaoxinkeji.com.inventotyproject.http;

import com.google.gson.stream.MalformedJsonException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import www.zhaoxinkeji.com.inventotyproject.utils.LogUtil;
import www.zhaoxinkeji.com.inventotyproject.utils.RxToast;

/**
 * 公共的异常处理
 */
public abstract class ErrorHandlerObserver<T> implements Observer<T> {

    private static final String TAG = "ErrorHandlerObserver";

    @Override
    public void onError(Throwable e) {
        LogUtil.d(TAG, "onError " + e.getMessage());
        if (e instanceof ConnectException) {
            RxToast.showToast("连接超时！请检查您的网络设置");
            _onError("连接超时！请检查您的网络设置");
            return;
        }
        if (e instanceof SocketTimeoutException) {
            RxToast.showToast("连接超时！请检查您的网络设置");
            _onError("连接超时！请检查您的网络设置");
            return;
        }
        if (e instanceof MalformedJsonException) {
            RxToast.showToast("服务器返回JSON格式错误");
            _onError("连接超时！请检查您的网络设置");
            return;
        }
        if (e instanceof UnknownHostException) {
            RxToast.showToast("数据获取失败，请检查您的网络");
            _onError("连接超时！请检查您的网络设置");
            return;
        }
        //添加了自定义错误收集
        if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            RxToast.showToast(exception.getMessage());
            _onError(exception.getMessage());
            return;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        LogUtil.d(TAG, "onSubscribe");
    }

    @Override
    public void onComplete() {
        LogUtil.d(TAG, "onComplete");
    }

    /**
     * 这里处理自定义的错误逻辑
     *
     * @param message
     */
    protected void _onError(String message) {
    }
}
