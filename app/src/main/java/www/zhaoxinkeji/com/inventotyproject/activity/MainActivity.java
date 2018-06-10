package www.zhaoxinkeji.com.inventotyproject.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import www.zhaoxinkeji.com.inventotyproject.R;


public class MainActivity extends BaseActivity {

    private LinearLayout mInventory,mCheckReceive,mReturnGoods,mSettings,mBack;
    private TextView mInventoryTv,mCheckReceiveTv,mReturnGoodsTv,
            mSettingsTv,mBackTv,mUserTv,mCodeTv;
    private ImageView mInventoryIv,mCheckReceiveIv,mReturnGoodsIv,mSettingsIv,mBackIv;

    @Override
    public void initUI() {
        mInventory = (LinearLayout) findViewById(R.id.inventory);
        mCheckReceive = (LinearLayout) findViewById(R.id.check_receive);
        mReturnGoods = (LinearLayout) findViewById(R.id.return_goods);
        mSettings = (LinearLayout) findViewById(R.id.settings);
        mBack = (LinearLayout) findViewById(R.id.back);
        mUserTv = (TextView) findViewById(R.id.user_tv);
        mCodeTv = (TextView) findViewById(R.id.code_tv);
        mInventory.setOnClickListener(this);
        mCheckReceive.setOnClickListener(this);
        mReturnGoods.setOnClickListener(this);
        mSettings.setOnClickListener(this);
        mBack.setOnClickListener(this);

        mInventoryIv = (ImageView) mInventory.findViewById(R.id.img_info);
        mInventoryTv = (TextView) mInventory.findViewById(R.id.name_tv);
        mCheckReceiveIv = (ImageView) mCheckReceive.findViewById(R.id.img_info);
        mCheckReceiveTv = (TextView) mCheckReceive.findViewById(R.id.name_tv);
        mReturnGoodsIv = (ImageView) mReturnGoods.findViewById(R.id.img_info);
        mReturnGoodsTv = (TextView) mReturnGoods.findViewById(R.id.name_tv);
        mSettingsIv = (ImageView) mSettings.findViewById(R.id.img_info);
        mSettingsTv = (TextView) mSettings.findViewById(R.id.name_tv);
        mBackIv = (ImageView) mBack.findViewById(R.id.img_info);
        mBackTv = (TextView) mBack.findViewById(R.id.name_tv);
    }

    @Override
    public void initData() {
        mUserTv.setText("当前用户:"+getIntent().getStringExtra("user"));
        mCodeTv.setText("油站编码:"+getIntent().getStringExtra("msgId"));
        mInventoryIv.setImageResource(R.mipmap.inventory);
        mInventoryTv.setText("盘点");
        mCheckReceiveIv.setImageResource(R.mipmap.check_receive);
        mCheckReceiveTv.setText("验收");
        mReturnGoodsIv.setImageResource(R.mipmap.return_goods);
        mReturnGoodsTv.setText("退货");
        mSettingsIv.setImageResource(R.mipmap.settings);
        mSettingsTv.setText("设置");
        mBackIv.setImageResource(R.mipmap.back);
        mBackTv.setText("返回");
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.inventory:
                Intent intent = new Intent(this, InventoryActivity.class);
                intent.putExtra("storeId",getIntent().getStringExtra("msgId"));
                startActivity(intent);
                break;
            case R.id.check_receive:
                intent = new Intent(this, CheckReceiveActivity.class);
                intent.putExtra("storeId",getIntent().getStringExtra("msgId"));
                startActivity(intent);
                break;
            case R.id.return_goods:
                intent = new Intent(this, ReturnGoodsActivity.class);
                intent.putExtra("storeId",getIntent().getStringExtra("msgId"));
                startActivity(intent);
                break;
            case R.id.settings:
                toActivity(SettingActivity.class);
                break;
            case R.id.back:
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        // 设置对话框标题
                        .setMessage("是否退出系统？")
                        // 设置显示的内容
                        //右边按钮
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {// 添加确定按钮
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {// 确定按钮的响应事件
                                        finish();
                                    }
                                })
                        //左边按钮
                        .setNegativeButton("返回", null).show();// 在按键响应事件中显示此对话框
                break;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction()==KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK :
                    /**复写返回键，禁止使用返回键退出**/
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
