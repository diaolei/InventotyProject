package www.zhaoxinkeji.com.inventotyproject.module.purchaseReceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.base.BaseActivity;
import www.zhaoxinkeji.com.inventotyproject.utils.RxToast;
import www.zhaoxinkeji.com.inventotyproject.utils.ScannerConfig;

/**
 * <pre>
 *     author : lei
 *     time   : 2018/6/14
 *     desc   : 采购出货订单详情页面
 *     version: 1.0
 * </pre>
 */
public class OrderDetailActivity extends BaseActivity<PurchaseReceivePresenter> implements PurchaseReceiveContract.View {

    @BindView(R.id.receive_goods_sp)
    Spinner mReceiveGoodsSp;
    @BindView(R.id.company_rule_sp)
    Spinner mCompanyRuleSp;
    @BindView(R.id.affair_type_sp)
    Spinner mAffairTypeSp;
    @BindView(R.id.purchase_receive_lv)
    ListView mPurchaseReceiveLv;
    @BindView(R.id.delete_success_tv)
    TextView mDeleteSuccessTv;
    @BindView(R.id.delete_fail_tv)
    TextView mDeleteFailTv;
    @BindView(R.id.complete_btn)
    Button mCompleteBtn;
    @BindView(R.id.back_btn)
    Button mBackBtn;
    private MyReceiver mReceiver;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_purchase_receive;
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
        ScannerConfig.initReceiver(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mReceiver = new MyReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ScannerConfig.BROADCAST_VALUE);
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    @OnClick({R.id.complete_btn, R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.complete_btn:
                break;
            case R.id.back_btn:
                break;
        }
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String barcode = intent.getStringExtra("scannerdata");

        }
    }
}
