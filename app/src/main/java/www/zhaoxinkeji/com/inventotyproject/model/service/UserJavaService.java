package www.zhaoxinkeji.com.inventotyproject.model.service;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import www.zhaoxinkeji.com.inventotyproject.app.Api;
import www.zhaoxinkeji.com.inventotyproject.model.entity.JavaResponse;

import static www.zhaoxinkeji.com.inventotyproject.app.Api.JAVA_DOMAIN_NAME;
import static www.zhaoxinkeji.com.inventotyproject.http.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;


/**
 * Author: ChenJia
 * Date : 2018/3/26
 * Description :
 */
public interface UserJavaService {

    //登陆
    @Headers({DOMAIN_NAME_HEADER + JAVA_DOMAIN_NAME})
    @FormUrlEncoded//用于修饰Field注解和FieldMap注解
    @POST(Api.LOGIN_LIST_URL)
    Observable<JavaResponse> login(@HeaderMap Map<String, Object> signMap, @FieldMap Map<String, Object> params);
}
