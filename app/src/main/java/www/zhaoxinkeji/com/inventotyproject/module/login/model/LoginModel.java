package www.zhaoxinkeji.com.inventotyproject.module.login.model;

import java.util.Map;

import io.reactivex.Observable;
import www.zhaoxinkeji.com.inventotyproject.base.BaseModel;
import www.zhaoxinkeji.com.inventotyproject.model.entity.JavaResponse;
import www.zhaoxinkeji.com.inventotyproject.model.helper.JavaRequestHelper;
import www.zhaoxinkeji.com.inventotyproject.model.service.BaseService;
import www.zhaoxinkeji.com.inventotyproject.module.login.bean.UserBean;
import www.zhaoxinkeji.com.inventotyproject.module.login.conract.LoginContract;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class LoginModel extends BaseModel implements LoginContract.Model {
    @Override
    public Observable<JavaResponse<UserBean>> login(String userName, String password) {
        Map<String, Object> stringObjectMap = JavaRequestHelper.login(userName, password);
        return mRetrofitFactory.obtainRetrofitService(BaseService.class).login(stringObjectMap);
    }
}
