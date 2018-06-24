package www.zhaoxinkeji.com.inventotyproject.module.main.contract;

import java.util.List;

import io.reactivex.Observable;
import www.zhaoxinkeji.com.dbdatabase.entity.BaseDataEntity;
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

public interface MainContract {

    interface View extends IView {

    }

    interface Model extends IModel {
        Observable<JavaResponse<List<BaseDataEntity>>> baseDataDownload(long userID, long storeID, int baseType);
    }
}
