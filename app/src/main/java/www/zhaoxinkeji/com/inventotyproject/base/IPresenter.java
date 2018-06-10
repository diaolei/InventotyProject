package www.zhaoxinkeji.com.inventotyproject.base;

public interface IPresenter {

    /**
     * 做一些初始化操作
     */
    void onStart();

    /**
     * 避免内存泄露
     */
    void onDestroy();
}
