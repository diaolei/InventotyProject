package www.zhaoxinkeji.com.inventotyproject.module.purchaseReceive;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.base.BaseActivity;
import www.zhaoxinkeji.com.inventotyproject.utils.RxToast;

/**
 * <pre>
 *     author : lei
 *     time   : 2018/6/14
 *     desc   : 采购出货页面
 *     version: 1.0
 * </pre>
 */
public class PurchaseReceiveActivity extends BaseActivity<PurchaseReceivePresenter> implements PurchaseReceiveContract.View {

    @BindView(R.id.search_ll)
    LinearLayout mSearchLl;
    @BindView(R.id.sell_out_order_lv)
    ListView mSellOutOrderLv;
    @BindView(R.id.upload_btn)
    Button mUploadBtn;
    @BindView(R.id.back_btn)
    Button mBackBtn;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_selling_out;
    }

    @Override
    public PurchaseReceivePresenter initPresenter() {
        return new PurchaseReceivePresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {
        RxToast.info(message);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.upload_btn, R.id.back_btn,R.id.search_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_ll:
                break;
            case R.id.upload_btn:
                break;
            case R.id.back_btn:
                break;
        }
    }
}
