package www.zhaoxinkeji.com.inventotyproject.module.login.presenter;

import android.text.TextUtils;
import android.widget.TextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import www.zhaoxinkeji.com.inventotyproject.app.Api;
import www.zhaoxinkeji.com.inventotyproject.app.AppConstant;
import www.zhaoxinkeji.com.inventotyproject.base.BasePresenter;
import www.zhaoxinkeji.com.inventotyproject.http.ErrorHandlerObserver;
import www.zhaoxinkeji.com.inventotyproject.model.entity.JavaResponse;
import www.zhaoxinkeji.com.inventotyproject.module.login.conract.NetSettingContract;
import www.zhaoxinkeji.com.inventotyproject.module.login.model.NetSettingModel;
import www.zhaoxinkeji.com.inventotyproject.utils.RxLifecycleUtils;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class NetSettingPresenter extends BasePresenter<NetSettingContract.Model, NetSettingContract.View> {

    public NetSettingPresenter(NetSettingContract.View rootView) {
        super(new NetSettingModel(), rootView);
    }

    /**
     * 测试网络连接
     *
     * @param testResultTv
     * @param cid
     * @param serverUrl
     */
    public void testNet(TextView testResultTv, String cid, String serverUrl) {
        if (TextUtils.isEmpty(cid) || TextUtils.isEmpty(serverUrl)) {
            mRootView.showMessage("请输入完整信息");
            testResultTv.setText("连接失败");
            return;
        } else if (!serverUrl.equals(Api.TEST_SERVER_IP)) {
            mRootView.showMessage("请输入正确服务器地址");
            testResultTv.setText("连接失败");
            return;
        }

        mModel.testNetSetting(AppConstant.NetSettingAction, Long.valueOf(cid))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandlerObserver<JavaResponse>() {
                               @Override
                               public void onNext(JavaResponse javaResponse) {
                                   testResultTv.setText("OK!连接成功");
                               }

                               @Override
                               protected void _onError(String message) {
                                   super._onError(message);
                                   testResultTv.setText("连接失败");
                               }
                           }
                );
    }
}
