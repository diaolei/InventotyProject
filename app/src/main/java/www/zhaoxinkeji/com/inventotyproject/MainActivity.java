package www.zhaoxinkeji.com.inventotyproject;

import android.os.Bundle;

import www.zhaoxinkeji.com.inventotyproject.base.BaseActivity;
import www.zhaoxinkeji.com.inventotyproject.base.IPresenter;

public class MainActivity extends BaseActivity {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public IPresenter initPresenter() {
        return null;
    }
}
