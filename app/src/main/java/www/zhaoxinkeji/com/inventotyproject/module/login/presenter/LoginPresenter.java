package www.zhaoxinkeji.com.inventotyproject.module.login.presenter;

import android.content.Intent;
import android.text.TextUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import www.zhaoxinkeji.com.inventotyproject.app.AppConstant;
import www.zhaoxinkeji.com.inventotyproject.base.BasePresenter;
import www.zhaoxinkeji.com.inventotyproject.http.ErrorHandlerObserver;
import www.zhaoxinkeji.com.inventotyproject.model.entity.JavaResponse;
import www.zhaoxinkeji.com.inventotyproject.module.login.bean.UserBean;
import www.zhaoxinkeji.com.inventotyproject.module.login.conract.LoginContract;
import www.zhaoxinkeji.com.inventotyproject.module.login.model.LoginModel;
import www.zhaoxinkeji.com.inventotyproject.module.main.activity.MainActivity;
import www.zhaoxinkeji.com.inventotyproject.utils.RxLifecycleUtils;
import www.zhaoxinkeji.com.inventotyproject.utils.SharePreferanceUtil;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {

    public LoginPresenter(LoginContract.View rootView) {
        super(new LoginModel(), rootView);
    }

    /**
     * 登陆
     *
     * @param userName
     * @param password
     */
    public void login(String userName, String password) {
        if (TextUtils.isEmpty(userName) ||
                TextUtils.isEmpty(password)) {
            mRootView.showMessage("请输入账号密码");
            return;
        }
        mModel.login(userName, password)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandlerObserver<JavaResponse<UserBean>>() {
                               @Override
                               public void onNext(JavaResponse<UserBean> javaResponse) {
                                   if (javaResponse != null && javaResponse.getData() != null) {
                                       UserBean data = javaResponse.getData();
                                       SharePreferanceUtil.getSpUtil().put_sp(AppConstant.USER_ID, data.getUserID());
                                       SharePreferanceUtil.getSpUtil().put_sp(AppConstant.STORE_ID, data.getStoreID());
                                       SharePreferanceUtil.getSpUtil().put_sp(AppConstant.USER_TYPE, data.getUserType());

                                       Intent intent = new Intent(mContext, MainActivity.class);
                                       mContext.startActivity(intent);
                                   } else {
                                       mRootView.showMessage("没有获取到登录身份");
                                   }
                               }

                               @Override
                               protected void _onError(String message) {
                                   super._onError(message);
                               }
                           }
                );
    }
}
