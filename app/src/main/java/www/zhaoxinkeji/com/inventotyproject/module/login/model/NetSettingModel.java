package www.zhaoxinkeji.com.inventotyproject.module.login.model;

import java.util.Map;

import io.reactivex.Observable;
import www.zhaoxinkeji.com.inventotyproject.base.BaseModel;
import www.zhaoxinkeji.com.inventotyproject.model.entity.JavaResponse;
import www.zhaoxinkeji.com.inventotyproject.model.helper.JavaRequestHelper;
import www.zhaoxinkeji.com.inventotyproject.model.service.BaseService;
import www.zhaoxinkeji.com.inventotyproject.module.login.conract.NetSettingContract;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class NetSettingModel extends BaseModel implements NetSettingContract.Model {
    @Override
    public Observable<JavaResponse> testNetSetting(String action, long cid) {
        Map<String, Object> stringObjectMap = JavaRequestHelper.testNetSetting(action, cid);
        return mRetrofitFactory.obtainRetrofitService(BaseService.class).testNetSetting(stringObjectMap);
    }
}
