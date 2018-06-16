package www.zhaoxinkeji.com.inventotyproject.module.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.base.BaseActivity;
import www.zhaoxinkeji.com.inventotyproject.module.main.bean.MainItemBean;
import www.zhaoxinkeji.com.inventotyproject.module.main.contract.MainContract;
import www.zhaoxinkeji.com.inventotyproject.module.main.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.menu_gv)
    GridView mMenuGv;
    @BindView(R.id.download_btn)
    Button mDownloadBtn;
    @BindView(R.id.back_btn)
    Button mBackBtn;

    //装载主页面item的数据集合
    private List<MainItemBean> mList = new ArrayList();

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.initDataSetting(mList);
        mPresenter.initGridView(mMenuGv, mList);
    }

    @OnClick({R.id.download_btn, R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.download_btn://基础数据下载
                break;
            case R.id.back_btn://返回按钮
                break;
        }
    }
}
