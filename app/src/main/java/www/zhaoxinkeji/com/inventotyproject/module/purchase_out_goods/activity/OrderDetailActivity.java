package www.zhaoxinkeji.com.inventotyproject.module.purchase_out_goods.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.base.BaseActivity;
import www.zhaoxinkeji.com.inventotyproject.module.purchase_out_goods.contract.OrderDetailContract;
import www.zhaoxinkeji.com.inventotyproject.module.purchase_out_goods.presenter.OrderDetailPresenter;
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
public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter> implements OrderDetailContract.View {

    @BindView(R.id.order_details_lv)
    ListView mOrderDetailsLv;
    @BindView(R.id.case_rb)
    RadioButton mCaseRb;
    @BindView(R.id.tray_rb)
    RadioButton mTrayRb;
    @BindView(R.id.case_tray_rg)
    RadioGroup mCaseTrayRg;
    @BindView(R.id.barcode_et)
    EditText mBarcodeEt;
    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;
    @BindView(R.id.scan_tv)
    TextView mScanTv;
    @BindView(R.id.network_state_tv)
    TextView mNetworkStateTv;
    @BindView(R.id.upload_tv)
    TextView mUploadTv;
    @BindView(R.id.delete_btn)
    Button mDeleteBtn;
    @BindView(R.id.complete_btn)
    Button mCompleteBtn;
    @BindView(R.id.back_btn)
    Button mBackBtn;
    private MyReceiver mReceiver;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_order_details;
    }

    @Override
    public OrderDetailPresenter initPresenter() {
        return new OrderDetailPresenter(this);
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

    @OnClick({R.id.confirm_btn, R.id.delete_btn, R.id.complete_btn, R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm_btn:
                break;
            case R.id.delete_btn:
                break;
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
