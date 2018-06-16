package www.zhaoxinkeji.com.inventotyproject.module.login.presenter;

import www.zhaoxinkeji.com.inventotyproject.base.BasePresenter;
import www.zhaoxinkeji.com.inventotyproject.module.login.conract.LoginContract;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class LoginPresenter extends BasePresenter<LoginContract.Model,LoginContract.View>{

    public LoginPresenter(LoginContract.View rootView) {
        super(rootView);
    }
}
