package www.zhaoxinkeji.com.inventotyproject.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.UUID;

import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.bean.RLogin;
import www.zhaoxinkeji.com.inventotyproject.constant.MessageConst;
import www.zhaoxinkeji.com.inventotyproject.constant.NetworkConst;
import www.zhaoxinkeji.com.inventotyproject.utils.JsonUtil;
import www.zhaoxinkeji.com.inventotyproject.utils.MyDialog;
import www.zhaoxinkeji.com.inventotyproject.utils.NetWorkUtils;
import www.zhaoxinkeji.com.inventotyproject.utils.NetworkUtil;
import www.zhaoxinkeji.com.inventotyproject.utils.SoundVibratorManager;

/**
 * Created by 27631 on 2018/3/3.
 */

public class LoginActivity extends BaseActivity implements View.OnLongClickListener {

    private EditText mUserEt,mPwdEt;
    private Button mLoginBtn;
    private ImageView mChoiceIv;
    private boolean isSaved;
    private SharedPreferences.Editor mEdit;
    private TextView mEquipmentCodeTv;
    private Button mSetTv;
    private TextView mVersionTv;
    private AlertDialog.Builder builder;
    private View mView;
    private AlertDialog mDialog;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessageConst.ACTION_LOGIN_FAIL:
                    showToast((String)msg.obj);
                    break;
                case MessageConst.ACTION_LOGIN_SUCCESS:
                    mPwdEt.setText("");
                    break;
                case MessageConst.ACTION_INIT_DIALOG:
                    MyDialog.getInstance().setContext(LoginActivity.this);
                    MyDialog.getInstance().show();
                    MyDialog.getInstance().setDisplay("用户登录中...");
                    break;
                case MessageConst.ACTION_DIALOG_DISMISS:
                    MyDialog.getInstance().dismiss();
                    break;
                case MessageConst.ACTION_SET_SERVER:
                    showToast("请检查地址或者端口是否正确");
                    break;
            }
        }
    };
    private EditText mPassword;

    @Override
    public void initUI() {
        mUserEt = (EditText) findViewById(R.id.user_et);
        mPwdEt = (EditText) findViewById(R.id.pwd_et);
        ImageView logoIv = (ImageView) findViewById(R.id.logo_iv);
        logoIv.setOnLongClickListener(this);
        mChoiceIv = (ImageView) findViewById(R.id.choice_iv);
        mLoginBtn = (Button) findViewById(R.id.login_btn);
        mEquipmentCodeTv = (TextView) findViewById(R.id.equipment_code_tv);
        mSetTv = (Button) findViewById(R.id.set_tv);
        mVersionTv = (TextView) findViewById(R.id.version_tv);
        mChoiceIv.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
        mSetTv.setOnClickListener(this);
        builder = new AlertDialog.Builder(LoginActivity.this);
        mView = View.inflate(this, R.layout.sign_out_dialog, null);
        builder.setView(mView);
        builder.setCancelable(true);
        //输入内容
        mPassword = (EditText) mView
                .findViewById(R.id.dialog_edit);
        Button cancelBtn=(Button)mView
                .findViewById(R.id.cancel_btn);//取消按钮
        cancelBtn.setOnClickListener(this);
        Button confirmBtn=(Button)mView
                .findViewById(R.id.confirm_btn);//确定按钮
        confirmBtn.setOnClickListener(this);
        //取消或确定按钮监听事件处理
        mDialog = builder.create();
    }

    @Override
    public void initData() {
        mEquipmentCodeTv.setText("设备编号："+getDeviceId().substring(13));
        mVersionTv.setText(getVersion());
        SharedPreferences sp = getSharedPreferences("bundle", 1);
        mEdit = sp.edit();
        getShared();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    private void getShared(){
        SharedPreferences shared = getSharedPreferences("bundle", 1);

        mUserEt.setText(shared.getString("user",""));

        if(shared.getString("isSaved","").equals("")) {
            isSaved=false;
        }else{
            isSaved = Boolean.parseBoolean(shared.getString("isSaved",""));
        }

        if(isSaved) {
            mChoiceIv.setImageResource(R.mipmap.choice);
        }else{
            mChoiceIv.setImageResource(R.mipmap.no_choice);
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.login_btn:
                Intent intent1 = new Intent(this,MainActivity.class);
                startActivity(intent1);
                //login();

                break;
            case R.id.choice_iv:
                if(isSaved) {
                    isSaved=false;
                    mChoiceIv.setImageResource(R.mipmap.no_choice);
                }else{
                    isSaved=true;
                    mChoiceIv.setImageResource(R.mipmap.choice);
                }

                mEdit.putString("isSaved",isSaved+"");
                mEdit.commit();
                break;
            case R.id.set_tv:
                Intent intent = new Intent(this, LoginSetActivity.class);
                startActivity(intent);
                break;
            case R.id.confirm_btn :
                if(TextUtils.isEmpty(mPassword.getText().toString().trim())) {
                    showToast("请输入退出密码！");
                    return;
                }
                if(mPassword.getText().toString().trim().equals("87651234")) {
                    finish();
                }else{
                    showToast("密码错误！");
                }
                break;
            case R.id.cancel_btn :
                mDialog.dismiss();
                break;
        }
    }

    private void login(){
        if (!NetWorkUtils.isNetworkConnected(this)) {
            showToast("当前网络不可用，请连接网络！");
            SoundVibratorManager.playSound(2);
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    // 设置对话框标题
                    .setMessage("请设置连接网络")
                    // 设置显示的内容
                    //右边按钮
                    .setPositiveButton("是",
                            new DialogInterface.OnClickListener() {// 添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {// 确定按钮的响应事件
                                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                    startActivity(intent);
                                }
                            })
                    //左边按钮
                    .setNegativeButton("否", null).show();// 在按键响应事件中显示此对话框
            return;
        }
        if (TextUtils.isEmpty(mUserEt.getText().toString().trim()) ||
                TextUtils.isEmpty(mPwdEt.getText().toString().trim())) {
            showToast("请输入账号或者密码");
            return;
        }

        sendAsyncTaskMessage();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction()==KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK :
                    /**复写返回键，禁止使用返回键退出**/
                    return true;
            }

            if(event.getKeyCode()==KeyEvent.KEYCODE_ENTER) {
                if(!mUserEt.hasFocus()) {
                    login();
                    return true;
                }
            }
        }

        if(event.getAction()==KeyEvent.ACTION_UP) {
            if(event.getKeyCode()==KeyEvent.KEYCODE_ENTER) {
                mPwdEt.requestFocus();
                return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void sendAsyncTask() {
        String result=null;
        SharedPreferences preferences = getSharedPreferences("server", 1);
        String serverName = preferences.getString("serverName", "");
        String port = preferences.getString("port", "");
        String path = preferences.getString("path", "");
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", mUserEt.getText().toString().trim());
        map.put("passWord", mPwdEt.getText().toString().trim());

        handler.obtainMessage(MessageConst.ACTION_INIT_DIALOG).sendToTarget();

        if(TextUtils.isEmpty(serverName)||TextUtils.isEmpty(port)||
                TextUtils.isEmpty(path)) {
            result = NetworkUtil.doPost(NetworkConst.ACTION_LOGIN, JsonUtil.map2Json(map));
        }else{
            result = NetworkUtil.doPost(NetworkConst.TITLE+serverName+NetworkConst
                    .POINT+port+NetworkConst.LINE+path+NetworkConst.END+NetworkConst.LOGIN,JsonUtil.map2Json(map));
        }
        handler.obtainMessage(MessageConst.ACTION_DIALOG_DISMISS).sendToTarget();

        if(TextUtils.isEmpty(result)) {
            handler.obtainMessage(MessageConst.ACTION_SET_SERVER).sendToTarget();
            return;
        }
        RLogin rLogin = JSON.parseObject(result, RLogin.class);
        if(rLogin.getMsgInfo().equals("调用成功")) {
            if(isSaved) {
                mEdit.putString("user",mUserEt.getText().toString().trim());
            }else{
                mEdit.putString("user","");
            }
            mEdit.commit();
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("user",mUserEt.getText().toString().trim());
            intent.putExtra("msgId",rLogin.getMsgId());
            startActivity(intent);
            handler.obtainMessage(MessageConst.ACTION_LOGIN_SUCCESS).sendToTarget();
        }else{
            handler.obtainMessage(MessageConst.ACTION_LOGIN_FAIL,rLogin.getMsgInfo()).sendToTarget();
        }
    }

    /**获取设备型号**/
    private String getDeviceId() {
        String deviceId = null;
        deviceId = ((TelephonyManager) this
                .getSystemService(this.TELEPHONY_SERVICE)).getDeviceId();
        if (deviceId == null && Build.VERSION.SDK_INT > 9) {
            deviceId = Settings.Secure.getString(this.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            if (deviceId == null) {
                ConnectivityManager cm = (ConnectivityManager) this
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                if (networkInfo != null
                        && networkInfo.isAvailable()
                        && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    WifiManager wm = (WifiManager) this
                            .getSystemService(Context.WIFI_SERVICE);
                    deviceId = wm.getConnectionInfo().getMacAddress();
                } else {
                    deviceId = UUID.randomUUID().toString();
                }
            }
        }

        if (deviceId != null && deviceId.length() < 28) {
            int len = 28 - deviceId.length();
            for (int i = 0; i < len; i++) {
                deviceId = "0" + deviceId;
            }
        }

        return deviceId;
    }

    /**获取应用版本号**/
    public String getVersion(){
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return this.getString(R.string.version_name) + version;
        } catch (Exception e) {
            e.printStackTrace();
            return this.getString(R.string.version_name);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.logo_iv :
                mDialog.show();
                break;
        }
        return false;
    }
}
