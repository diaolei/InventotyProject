package www.zhaoxinkeji.com.inventotyproject.model.service;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;
import www.zhaoxinkeji.com.inventotyproject.app.Api;
import www.zhaoxinkeji.com.inventotyproject.model.entity.JavaResponse;
import www.zhaoxinkeji.com.inventotyproject.module.login.bean.UserBean;

import static www.zhaoxinkeji.com.inventotyproject.app.Api.JAVA_DOMAIN_TEST;
import static www.zhaoxinkeji.com.inventotyproject.http.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;


/**
 * Author: ChenJia
 * Date : 2018/3/26
 * Description :
 */
public interface BaseService {

    //网络测试
    @Headers({DOMAIN_NAME_HEADER + JAVA_DOMAIN_TEST})
    @GET(Api.BASE_URL)
    Observable<JavaResponse> testNetSetting(@QueryMap Map<String, Object> params);

    @Headers({DOMAIN_NAME_HEADER + JAVA_DOMAIN_TEST})
    @GET(Api.BASE_URL)
    Observable<JavaResponse<UserBean>> login(@QueryMap Map<String, Object> params);

}
