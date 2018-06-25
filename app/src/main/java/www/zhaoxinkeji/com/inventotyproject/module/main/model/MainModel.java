package www.zhaoxinkeji.com.inventotyproject.module.main.model;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import www.zhaoxinkeji.com.dbdatabase.entity.AffairEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.BillRuleEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.DistributorEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.ProductEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.StorageRoomEntity;
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
    public Observable<JavaResponse<List<ProductEntity>>> downProduct(long userID, long storeID, int baseType) {
        Map<String, Object> stringObjectMap = JavaRequestHelper.baseDataDownload(userID, storeID, baseType);
        return mRetrofitFactory.obtainRetrofitService(BaseService.class).downProduct(stringObjectMap);
    }


    @Override
    public Observable<JavaResponse<List<StorageRoomEntity>>> downStorageRoom(long userID, long storeID, int baseType) {
        Map<String, Object> stringObjectMap = JavaRequestHelper.baseDataDownload(userID, storeID, baseType);
        return mRetrofitFactory.obtainRetrofitService(BaseService.class).downStorageRoom(stringObjectMap);
    }

    @Override
    public Observable<JavaResponse<List<DistributorEntity>>> downDistributor(long userID, long storeID, int baseType) {
        Map<String, Object> stringObjectMap = JavaRequestHelper.baseDataDownload(userID, storeID, baseType);
        return mRetrofitFactory.obtainRetrofitService(BaseService.class).downDistributor(stringObjectMap);
    }

    @Override
    public Observable<JavaResponse<List<BillRuleEntity>>> downBillRule(long userID, long storeID, int baseType) {
        Map<String, Object> stringObjectMap = JavaRequestHelper.baseDataDownload(userID, storeID, baseType);
        return mRetrofitFactory.obtainRetrofitService(BaseService.class).downBillRule(stringObjectMap);
    }

    @Override
    public Observable<JavaResponse<List<AffairEntity>>> downAffair(long userID, long storeID, int baseType) {
        Map<String, Object> stringObjectMap = JavaRequestHelper.baseDataDownload(userID, storeID, baseType);
        return mRetrofitFactory.obtainRetrofitService(BaseService.class).downAffair(stringObjectMap);
    }
}
