package www.zhaoxinkeji.com.inventotyproject.module.login.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.base.BaseActivity;
import www.zhaoxinkeji.com.inventotyproject.module.login.conract.NetSettingContract;
import www.zhaoxinkeji.com.inventotyproject.module.login.presenter.NetSettingPresenter;
import www.zhaoxinkeji.com.inventotyproject.utils.RxToast;

public class NetSettingActivity extends BaseActivity<NetSettingPresenter> implements NetSettingContract.View {

    @BindView(R.id.server_et)
    EditText mServerEt;
    @BindView(R.id.url_et)
    EditText mUrlEt;
    @BindView(R.id.port_et)
    EditText mPortEt;
    @BindView(R.id.test_result_tv)
    TextView mTestResultTv;
    @BindView(R.id.test_btn)
    Button mTestBtn;
    @BindView(R.id.back_btn)
    Button mBackBtn;
    private ProgressDialog mProgressDialog;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_net_setting;
    }

    @Override
    public NetSettingPresenter initPresenter() {
        return new NetSettingPresenter(this);
    }

    @Override
    public void showLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String message) {
        RxToast.showToast(message);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //初始化进度条
        mProgressDialog = new ProgressDialog(this);
    }

    @OnClick({R.id.test_btn, R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test_btn://测试按钮
                mPresenter.testNet(mTestResultTv, mServerEt.getText().toString().trim(), mUrlEt.getText().toString().trim());
                break;
            case R.id.back_btn://返回按钮
                finish();
                break;
        }
    }
}
