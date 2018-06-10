package www.zhaoxinkeji.com.inventotyproject.app;

import org.greenrobot.eventbus.EventBus;

/**
 * 集中EventBus，方便查找，并统一使用Message来调用{@link EventBus#post(Object)} }
 */
public class EventBusTags {

    private static final int WAN_LIN_RUO = 1000;

    //关闭其他页面，只保留主页面
    public static final int CLOSE_ALL_KEEP_THE_HOME = WAN_LIN_RUO + 1;
}
