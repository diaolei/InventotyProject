package www.zhaoxinkeji.com.inventotyproject.module.login.conract;

import io.reactivex.Observable;
import www.zhaoxinkeji.com.inventotyproject.base.IModel;
import www.zhaoxinkeji.com.inventotyproject.base.IView;
import www.zhaoxinkeji.com.inventotyproject.model.entity.JavaResponse;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface NetSettingContract {

    interface View extends IView {

    }

    interface Model extends IModel {

        Observable<JavaResponse> testNetSetting(String action, long cid);
    }
}
