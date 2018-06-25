package www.zhaoxinkeji.com.inventotyproject.module.main.contract;

import java.util.List;

import io.reactivex.Observable;
import www.zhaoxinkeji.com.dbdatabase.entity.AffairEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.BillRuleEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.DistributorEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.ProductEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.StorageRoomEntity;
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
        /**
         * 产品表基础数据下载
         *
         * @param userID
         * @param storeID
         * @param baseType
         * @return
         */
        Observable<JavaResponse<List<ProductEntity>>> downProduct(long userID, long storeID, int baseType);


        /**
         * 库房表基础数据下载
         *
         * @param userID
         * @param storeID
         * @param baseType
         * @return
         */
        Observable<JavaResponse<List<StorageRoomEntity>>> downStorageRoom(long userID, long storeID, int baseType);


        /**
         * 经销商表基础数据下载
         *
         * @param userID
         * @param storeID
         * @param baseType
         * @return
         */
        Observable<JavaResponse<List<DistributorEntity>>> downDistributor(long userID, long storeID, int baseType);


        /**
         * 单据转换表基础数据下载
         *
         * @param userID
         * @param storeID
         * @param baseType
         * @return
         */
        Observable<JavaResponse<List<BillRuleEntity>>> downBillRule(long userID, long storeID, int baseType);



        /**
         * 单据转换表基础数据下载
         *
         * @param userID
         * @param storeID
         * @param baseType
         * @return
         */
        Observable<JavaResponse<List<AffairEntity>>> downAffair(long userID, long storeID, int baseType);

    }
}
