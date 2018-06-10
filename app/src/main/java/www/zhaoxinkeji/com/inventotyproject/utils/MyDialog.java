package www.zhaoxinkeji.com.inventotyproject.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class MyDialog {
    public static MyDialog mmyDialog;
    private Context mContext;
    ProgressDialog dialog;
    private MyDialog(){
    }

    public void setContext(Context mContext){
        this.mContext = mContext;
    }

    public static MyDialog getInstance(){
        if(mmyDialog==null){
            mmyDialog = new MyDialog();
        }
        return mmyDialog;
    }

    private void  initDialog(){
        dialog = new ProgressDialog(mContext);
        dialog.setCancelable(false);

    }

    public void show(){
        initDialog();
        dialog.show();
    }

    public boolean isShow(){
        if(dialog!=null){
            return dialog.isShowing();
        }
        return false;
    }


    public void setDisplay(String display){
        dialog.setMessage(display);
    }

    public void dismiss(){
        dialog.dismiss();
        mContext = null;
    }
}
