package www.zhaoxinkeji.com.inventotyproject.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import www.zhaoxinkeji.com.inventotyproject.R;


/**
 * Created by 27631 on 2018/3/8.
 */

public class DialogActivity extends BaseActivity {

    private TextView mBarcodeTv;
    private EditText mNumEt;
    private Button mConfirmBtn;
    private Button mCancelBtn;
    private int resultCode=1;
    private EditText mGiftNumEt;

    @Override
    public void initUI() {
        mBarcodeTv = (TextView) findViewById(R.id.modify_barcode_tv);
        mGiftNumEt = (EditText) findViewById(R.id.gift_num_et);
        mNumEt = (EditText) findViewById(R.id.modify_num_et);
        mConfirmBtn = (Button) findViewById(R.id.confirm_btn);
        mConfirmBtn.setOnClickListener(this);
        mCancelBtn = (Button) findViewById(R.id.cancel_btn);
        mCancelBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mBarcodeTv.setText("条码："+getIntent().getStringExtra("barcode"));
        mGiftNumEt.setText(getIntent().getStringExtra("giftNum"));
        mGiftNumEt.setSelection(mGiftNumEt.getText().toString().length());
        mNumEt.setText(getIntent().getStringExtra("number"));
        mNumEt.setSelection(mNumEt.getText().toString().length());
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_btn :
                if(TextUtils.isEmpty(mNumEt.getText().toString().trim())) {
                    showToast("请输入数量");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("giftNum",mGiftNumEt.getText().toString().trim());
                intent.putExtra("rNumber",mNumEt.getText().toString().trim());
                this.setResult(resultCode, intent);
                finish();
                break;
            case R.id.cancel_btn :
                intent = new Intent();
                intent.putExtra("rNumber",getIntent().getStringExtra("number"));
                intent.putExtra("giftNum",getIntent().getStringExtra("giftNum"));
                this.setResult(resultCode, intent);
                finish();
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
