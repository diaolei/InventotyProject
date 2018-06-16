package www.zhaoxinkeji.com.inventotyproject.module.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.base.BaseActivity;
import www.zhaoxinkeji.com.inventotyproject.module.login.conract.LoginContract;
import www.zhaoxinkeji.com.inventotyproject.module.login.presenter.LoginPresenter;
import www.zhaoxinkeji.com.inventotyproject.utils.RxToast;

/**
 * <pre>
 *     author : LinRuo
 *     time   : 2018/6/11
 *     desc   : 登录页面
 *     version: 1.0
 * </pre>
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_user_password)
    EditText mEtUserPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_logout)
    Button mBtnLogout;
    @BindView(R.id.btn_net)
    Button mBtnNet;
    @BindView(R.id.btn_wifi)
    Button mBtnWifi;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {
        RxToast.showToast(message);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_login, R.id.btn_logout, R.id.btn_net, R.id.btn_wifi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login://登录
                showMessage("登录");
                break;
            case R.id.btn_logout://退出
                showMessage("退出");
                finish();
                break;
            case R.id.btn_net://网络配置
                Intent intent = new Intent(this, NetSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_wifi://wifi配置
                showMessage("wifi配置");
                break;
        }
    }
}
