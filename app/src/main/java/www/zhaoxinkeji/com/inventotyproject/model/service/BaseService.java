package www.zhaoxinkeji.com.inventotyproject.model.service;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;
import www.zhaoxinkeji.com.dbdatabase.entity.AffairEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.BillRuleEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.DistributorEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.ProductEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.StorageRoomEntity;
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

    //登录
    @Headers({DOMAIN_NAME_HEADER + JAVA_DOMAIN_TEST})
    @GET(Api.BASE_URL)
    Observable<JavaResponse<UserBean>> login(@QueryMap Map<String, Object> params);

    //产品表基础数据下载
    @Headers({DOMAIN_NAME_HEADER + JAVA_DOMAIN_TEST})
    @GET(Api.BASE_URL)
    Observable<JavaResponse<List<ProductEntity>>> downProduct(@QueryMap Map<String, Object> params);

    //库房表基础数据下载
    @Headers({DOMAIN_NAME_HEADER + JAVA_DOMAIN_TEST})
    @GET(Api.BASE_URL)
    Observable<JavaResponse<List<StorageRoomEntity>>> downStorageRoom(@QueryMap Map<String, Object> params);

    //经销商表基础数据下载
    @Headers({DOMAIN_NAME_HEADER + JAVA_DOMAIN_TEST})
    @GET(Api.BASE_URL)
    Observable<JavaResponse<List<DistributorEntity>>> downDistributor(@QueryMap Map<String, Object> params);

    //单据转换表基础数据下载
    @Headers({DOMAIN_NAME_HEADER + JAVA_DOMAIN_TEST})
    @GET(Api.BASE_URL)
    Observable<JavaResponse<List<BillRuleEntity>>> downBillRule(@QueryMap Map<String, Object> params);

    //单据转换表基础数据下载
    @Headers({DOMAIN_NAME_HEADER + JAVA_DOMAIN_TEST})
    @GET(Api.BASE_URL)
    Observable<JavaResponse<List<AffairEntity>>> downAffair(@QueryMap Map<String, Object> params);
}
