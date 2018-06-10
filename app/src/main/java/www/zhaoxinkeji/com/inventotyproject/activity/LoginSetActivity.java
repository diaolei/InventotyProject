package www.zhaoxinkeji.com.inventotyproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;

import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.constant.MessageConst;
import www.zhaoxinkeji.com.inventotyproject.constant.NetworkConst;
import www.zhaoxinkeji.com.inventotyproject.utils.JsonUtil;
import www.zhaoxinkeji.com.inventotyproject.utils.MyDialog;
import www.zhaoxinkeji.com.inventotyproject.utils.NetWorkUtils;
import www.zhaoxinkeji.com.inventotyproject.utils.NetworkUtil;

import static android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS;

/**
 * Created by 27631 on 2018/3/5.
 */

public class LoginSetActivity extends BaseActivity {

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessageConst.ACTION_CONNECT_SECCECE :
                    MyDialog.getInstance().dismiss();
                     showToast("连接服务器正常");
                    break;
                case MessageConst.ACTION_CONNECT_FAIL :
                    MyDialog.getInstance().dismiss();
                    showToast("连接服务器失败");
                    break;
            }
        }
    };
    @Override
    public void initUI() {
        LinearLayout wifiLl = (LinearLayout) findViewById(R.id.wifi_ll);
        LinearLayout bluetoothLl = (LinearLayout) findViewById(R.id.bluetooth_ll);
        LinearLayout soundLl = (LinearLayout) findViewById(R.id.sound_ll);
        LinearLayout displayLl = (LinearLayout) findViewById(R.id.display_ll);
        LinearLayout typewritingLl = (LinearLayout) findViewById(R.id.typewriting_ll);
        LinearLayout timeLl = (LinearLayout) findViewById(R.id.time_ll);
        LinearLayout internetLl = (LinearLayout) findViewById(R.id.internet_ll);
        LinearLayout serverSetLl = (LinearLayout) findViewById(R.id.server_set_ll);
        LinearLayout severLl = (LinearLayout) findViewById(R.id.sever_ll);
        wifiLl.setOnClickListener(this);
        bluetoothLl.setOnClickListener(this);
        soundLl.setOnClickListener(this);
        displayLl.setOnClickListener(this);
        typewritingLl.setOnClickListener(this);
        timeLl.setOnClickListener(this);
        internetLl.setOnClickListener(this);
        serverSetLl.setOnClickListener(this);
        severLl.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login_set;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.wifi_ll:
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivity(intent);
                break;
            case R.id.bluetooth_ll:
                intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(intent);
                break;
            case R.id.sound_ll:
                intent = new Intent(Settings.ACTION_SOUND_SETTINGS);
                startActivity(intent);
                break;
            case R.id.display_ll:
                intent = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
                startActivity(intent);
                break;
            case R.id.typewriting_ll:
                intent = new Intent(ACTION_INPUT_METHOD_SETTINGS);
                startActivity(intent);
                break;
            case R.id.time_ll:
                intent = new Intent(Settings.ACTION_DATE_SETTINGS);
                startActivity(intent);
                break;
            case R.id.internet_ll:
                if(NetWorkUtils.isNetworkConnected(this)) {
                    showToast("测试网络连接成功");
                }else{
                    showToast("当前网络不可用，请连接网络！");
                }
                break;
            case R.id.server_set_ll:
                MyDialog.getInstance().setContext(LoginSetActivity.this);
                MyDialog.getInstance().show();
                MyDialog.getInstance().setDisplay("测试连接服务器中...");
                sendAsyncTaskMessage();
                break;
            case R.id.sever_ll:
                intent = new Intent(this,SetServerActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void sendAsyncTask() {
        String result=null;
        SharedPreferences preferences = getSharedPreferences("server", 1);
        String serverName = preferences.getString("serverName", "");
        String port = preferences.getString("port", "");
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", "账号");
        map.put("passWord", "密码");
        if(TextUtils.isEmpty(serverName)||TextUtils.isEmpty(port)) {
            result = NetworkUtil.doPost(NetworkConst.ACTION_LOGIN, JsonUtil.map2Json(map));
        }else{
            result = NetworkUtil.doPost(NetworkConst.TITLE+serverName+NetworkConst
                    .POINT+port+NetworkConst.END+NetworkConst.LOGIN,JsonUtil.map2Json(map));
        }

        if(TextUtils.isEmpty(result)) {
            mHandler.obtainMessage(MessageConst.ACTION_CONNECT_FAIL).sendToTarget();
        }else{
            mHandler.obtainMessage(MessageConst.ACTION_CONNECT_SECCECE).sendToTarget();
        }
    }
}
