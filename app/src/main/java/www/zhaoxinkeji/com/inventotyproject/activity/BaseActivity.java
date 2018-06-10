package www.zhaoxinkeji.com.inventotyproject.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.utils.SoundVibratorManager;

/**
 * Created by 27631 on 2018/3/3.
 */

public abstract class BaseActivity extends Activity implements View.OnClickListener{

    private ExecutorService mExecutorService;
    protected AlertDialog.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentViewId());
        initUI();
        initData();
        mBuilder = new AlertDialog.Builder(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SoundVibratorManager.initSounds(this);
        SoundVibratorManager.addSound(1, R.raw.scan_success);
        SoundVibratorManager.addSound(2, R.raw.scan_failed);
    }

    /** 注册事件**/
    public abstract void initUI();

    /**初始化数据*/
    public abstract void initData();
    /**当前窗的viewId
     * @return*/
    public abstract int getContentViewId();

    protected void toActivity(Class mClass) {
        Intent intent = new Intent(this, mClass);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        //        if(FastClick.isFastClick()){
        //            return;
        //        }
    }

    protected void sendAsyncTaskMessage(){
        mExecutorService = Executors.newFixedThreadPool(5);
        Runnable syncRunnable = new Runnable() {
            @Override
            public void run() {
                sendAsyncTask();
            }
        };
        mExecutorService.execute(syncRunnable);
    }

    protected void sendAsyncTask(){
        //default empty
    }

    protected void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
