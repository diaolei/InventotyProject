package www.zhaoxinkeji.com.inventotyproject.module.main.model;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import www.zhaoxinkeji.com.dbdatabase.entity.BaseDataEntity;
import www.zhaoxinkeji.com.inventotyproject.base.BaseModel;
import www.zhaoxinkeji.com.inventotyproject.model.entity.JavaResponse;
import www.zhaoxinkeji.com.inventotyproject.model.helper.JavaRequestHelper;
import www.zhaoxinkeji.com.inventotyproject.model.service.BaseService;
import www.zhaoxinkeji.com.inventotyproject.module.main.contract.MainContract;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MainModel extends BaseModel implements MainContract.Model {

    @Override
    public Observable<JavaResponse<List<BaseDataEntity>>> baseDataDownload(long userID, long storeID, int baseType) {
        Map<String, Object> stringObjectMap = JavaRequestHelper.baseDataDownload(userID, storeID, baseType);
        return mRetrofitFactory.obtainRetrofitService(BaseService.class).baseDataDownload(stringObjectMap);
    }
}
