package www.zhaoxinkeji.com.inventotyproject.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.trello.rxlifecycle2.android.ActivityEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import www.zhaoxinkeji.com.inventotyproject.app.EventBusTags;
import www.zhaoxinkeji.com.inventotyproject.lifecycle.ActivityLifecycleable;
import www.zhaoxinkeji.com.inventotyproject.module.main.activity.MainActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IActivity<P>, ActivityLifecycleable {

    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();

    protected P mPresenter;
    private Unbinder unbinder;

    @NonNull
    @Override
    public final Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //设置为只允许竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);//注册到事件主线
        int layoutResID = initContentView(savedInstanceState);
        if (layoutResID != 0) {
            //如果initView返回0,不会调用setContentView(),也不会 Bind ButterKnife
            setContentView(layoutResID);
            //绑定到ButterKnife
            bindView();
        }
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        initBarView();
        initData(savedInstanceState);
    }

    protected void bindView() {
        unbinder = ButterKnife.bind(this);
    }

    protected void initBarView() {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //由子类自身决定是否实现
    protected void initSkin(boolean isNightMode) {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //反注册EventBus
        EventBus.getDefault().unregister(this);
        if (unbinder != null && unbinder != Unbinder.EMPTY)
            unbinder.unbind();
        this.unbinder = null;
        if (mPresenter != null)
            mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BaseEvent event) {
        if (event.getTag() == EventBusTags.CLOSE_ALL_KEEP_THE_HOME) {
            //关闭所有页面，保留主页面
            if (!(this instanceof MainActivity)) {
                finish();
            }
        }
    }
}
