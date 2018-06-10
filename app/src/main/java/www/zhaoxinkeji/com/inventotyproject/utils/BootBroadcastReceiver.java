package www.zhaoxinkeji.com.inventotyproject.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import www.zhaoxinkeji.com.inventotyproject.activity.WelcomeActivity;


public class BootBroadcastReceiver extends BroadcastReceiver {
    //重写onReceive方法
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent(context, WelcomeActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent2);
    }

}