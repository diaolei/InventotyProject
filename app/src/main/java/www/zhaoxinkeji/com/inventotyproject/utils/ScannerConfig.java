package www.zhaoxinkeji.com.inventotyproject.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/7/6.
 */

public class ScannerConfig {

    //广播名称
    public static final String ACTION_NAME= "com.android.scanner.service_settings";
    //广播名称的KEY
    public static final String BROADCAST_KEY = "action_barcode_broadcast";
    //广播名称的value
    public static final String BROADCAST_VALUE = "my_broadcast_service";

    public static void initReceiver(Context context){
        Intent intent = new Intent(ACTION_NAME);
        intent.putExtra(BROADCAST_KEY,BROADCAST_VALUE);
        context.sendBroadcast(intent);
    }
}
