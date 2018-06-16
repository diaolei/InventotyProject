package www.zhaoxinkeji.com.inventotyproject.module.purchaseReceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import butterknife.ButterKnife;
import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.base.BaseActivity;
import www.zhaoxinkeji.com.inventotyproject.utils.RxToast;
import www.zhaoxinkeji.com.inventotyproject.utils.ScannerConfig;

/**
 * <pre>
 *     author : lei
 *     time   : 2018/6/14
 *     desc   : 采购出货删除订单
 *     version: 1.0
 * </pre>
 */
public class DeleteBarcodeActivity extends BaseActivity<PurchaseReceivePresenter> implements PurchaseReceiveContract.View {

    private MyReceiver mReceiver;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_delete_barcode;
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

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String barcode = intent.getStringExtra("scannerdata");

        }
    }
}
