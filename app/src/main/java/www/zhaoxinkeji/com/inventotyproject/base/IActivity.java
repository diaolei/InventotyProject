package www.zhaoxinkeji.com.inventotyproject.base;


import android.app.Activity;
import android.os.Bundle;


/**
 * @param <P> Presenter
 */
public interface IActivity<P extends IPresenter> {

    /**
     * 初始化布局，不为0的时候会调用{@link Activity#setContentView(int)}以及 {@link butterknife.ButterKnife#bind(Activity)} (int)}
     *
     * @param savedInstanceState
     * @return
     */
    int initContentView(Bundle savedInstanceState);

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    void initData(Bundle savedInstanceState);

    /**
     * 初始化Presenter
     *
     * @return
     */
    P initPresenter();

}
