package www.zhaoxinkeji.com.inventotyproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.constant.MessageConst;


/**
 * 启动界面
 */
public class WelcomeActivity extends Activity {

	private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MessageConst.ACTION_START_LOGIN:
				Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		alphaAnim();
		startApp();
	}

	private void startApp() {
		new Thread() {
			public void run() {
				SystemClock.sleep(2000);
				mHandler.sendEmptyMessage(MessageConst.ACTION_START_LOGIN);
			}
		}.start();
	}

	private void alphaAnim() {
		Animation alphaAnim = new AlphaAnimation(0.2f, 1.0f);
		alphaAnim.setDuration(2000);
		alphaAnim.setFillAfter(true);
		ImageView logoIv = (ImageView) findViewById(R.id.logo_iv);
		logoIv.startAnimation(alphaAnim);
	}
}
