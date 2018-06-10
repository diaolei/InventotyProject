package www.zhaoxinkeji.com.inventotyproject.activity;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.constant.MessageConst;
import www.zhaoxinkeji.com.inventotyproject.constant.NetworkConst;
import www.zhaoxinkeji.com.inventotyproject.utils.JsonUtil;
import www.zhaoxinkeji.com.inventotyproject.utils.MyDialog;
import www.zhaoxinkeji.com.inventotyproject.utils.NetworkUtil;

/**
 * Created by 27631 on 2018/3/13.
 */

public class SetServerActivity extends BaseActivity {

    private EditText mServerEt,mPortEt,mPathEt;
    private TextView mServerTv;
    private Button mSetKeepBtn;
    private Button mDefaultBtn;
    private SharedPreferences.Editor mEdit;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessageConst.ACTION_CONNECT_SECCECE :
                    MyDialog.getInstance().dismiss();
                    showToast("设置成功");
                    break;
                case MessageConst.ACTION_CONNECT_FAIL :
                    MyDialog.getInstance().dismiss();
                    showToast("保存失败,设置错误！");
                    break;
            }
        }
    };

    @Override
    public void initUI() {
        mServerEt = (EditText) findViewById(R.id.server_et);
        mPortEt = (EditText) findViewById(R.id.port_et);
        mPathEt = (EditText) findViewById(R.id.path_et);
        mServerTv = (TextView) findViewById(R.id.server_tv);
        mSetKeepBtn = (Button) findViewById(R.id.set_keep_btn);
        mDefaultBtn = (Button) findViewById(R.id.default_btn);
        mSetKeepBtn.setOnClickListener(this);
        mDefaultBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {
        SharedPreferences preferences =  getSharedPreferences("server", 1);
        mEdit = preferences.edit();
        getShare();
    }

    private void getShare() {
        SharedPreferences sp = getSharedPreferences("server", 1);
        String serverName=sp.getString("serverName","");
        String port=sp.getString("port","");
        String path=sp.getString("path","");
        mServerEt.setText(serverName);
        mPortEt.setText(port);
        mPathEt.setText(path);
        if(serverName.equals("")||
                port.equals("")||
                path.equals("")) {
            return;
        }

        mServerTv.setText(NetworkConst.TITLE+serverName+NetworkConst.POINT+
                port);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_server;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_keep_btn :
                if(TextUtils.isEmpty(mServerEt.getText().toString().trim())||
                   TextUtils.isEmpty(mPortEt.getText().toString().trim())||
                   TextUtils.isEmpty(mPathEt.getText().toString().trim())) {
                    showToast("请补全输入");
                    return;
                }

                MyDialog.getInstance().setContext(SetServerActivity.this);
                MyDialog.getInstance().show();
                MyDialog.getInstance().setDisplay("设置服务器地址...");
                sendAsyncTaskMessage();
                break;
            case R.id.default_btn :
                finish();
                break;
        }
    }

    @Override
    protected void sendAsyncTask() {
        String result=null;
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", "账号");
        map.put("passWord", "密码");

            result = NetworkUtil.doPost(NetworkConst.TITLE+mServerEt.getText().toString().trim()+NetworkConst
                    .POINT+mPortEt.getText().toString().trim()+NetworkConst.LINE+mPathEt.getText().toString().trim()
                    +NetworkConst.END+NetworkConst.LOGIN, JsonUtil.map2Json(map));

        if(TextUtils.isEmpty(result)) {
            mEdit.putString("serverName","");
            mEdit.putString("port","");
            mEdit.putString("path","");
            mEdit.commit();
            mHandler.obtainMessage(MessageConst.ACTION_CONNECT_FAIL).sendToTarget();
        }else{
            mEdit.putString("serverName",mServerEt.getText().toString().trim());
            mEdit.putString("port",mPortEt.getText().toString().trim());
            mEdit.putString("path",mPathEt.getText().toString().trim());
            mEdit.commit();
            mHandler.obtainMessage(MessageConst.ACTION_CONNECT_SECCECE).sendToTarget();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_ENTER :
                    /**复写enter键**/
                    if(mServerEt.hasFocus()&&mServerEt.getText().toString().trim().length()>0) {
                        mPortEt.requestFocus();
                    }

                    if(mPortEt.hasFocus()&&mPortEt.getText().toString().trim().length()>0) {
                        mPathEt.requestFocus();
                    }
                    return true;
        }

        return super.dispatchKeyEvent(event);
    }
}
