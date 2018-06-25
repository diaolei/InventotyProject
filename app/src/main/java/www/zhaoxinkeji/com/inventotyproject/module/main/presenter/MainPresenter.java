package www.zhaoxinkeji.com.inventotyproject.module.main.presenter;

import android.widget.GridView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import www.zhaoxinkeji.com.dbdatabase.entity.AffairEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.BillRuleEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.DistributorEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.ProductEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.StorageRoomEntity;
import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.app.AppConstant;
import www.zhaoxinkeji.com.inventotyproject.base.BasePresenter;
import www.zhaoxinkeji.com.inventotyproject.http.ErrorHandlerObserver;
import www.zhaoxinkeji.com.inventotyproject.manager.AffairEntityDaoManager;
import www.zhaoxinkeji.com.inventotyproject.manager.BillRuleEntityDaoManager;
import www.zhaoxinkeji.com.inventotyproject.manager.DistributorEntityDaoManager;
import www.zhaoxinkeji.com.inventotyproject.manager.ProductEntityDaoManager;
import www.zhaoxinkeji.com.inventotyproject.manager.StorageRoomEntityDaoManager;
import www.zhaoxinkeji.com.inventotyproject.model.entity.JavaResponse;
import www.zhaoxinkeji.com.inventotyproject.module.main.adapter.GridViewAdapter;
import www.zhaoxinkeji.com.inventotyproject.module.main.bean.MainItemBean;
import www.zhaoxinkeji.com.inventotyproject.module.main.contract.MainContract;
import www.zhaoxinkeji.com.inventotyproject.module.main.model.MainModel;
import www.zhaoxinkeji.com.inventotyproject.utils.LogUtil;
import www.zhaoxinkeji.com.inventotyproject.utils.RxLifecycleUtils;
import www.zhaoxinkeji.com.inventotyproject.utils.SharePreferanceUtil;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {

    public MainPresenter(MainContract.View rootView) {
        super(new MainModel(), rootView);
    }

    /**
     * 初始化数据集合
     *
     * @param list
     */
    public void initDataSetting(List<MainItemBean> list) {
        //根据登录的值显示不同的数据集合
        int userType = (int) SharePreferanceUtil.getSpUtil().get_sp(AppConstant.USER_TYPE, 0);
        switch (userType) {
            case 1://供应商
                list.add(new MainItemBean(R.drawable.collect, "采购出库"));
                break;
            case 2://企业仓库
                list.add(new MainItemBean(R.drawable.collect, "采购收货"));
                list.add(new MainItemBean(R.drawable.collect, "调拨收货"));
                list.add(new MainItemBean(R.drawable.collect, "生产入库"));
                list.add(new MainItemBean(R.drawable.collect, "销售出库"));
                list.add(new MainItemBean(R.drawable.collect, "调拨出库"));
                list.add(new MainItemBean(R.drawable.collect, "退货入库"));
                list.add(new MainItemBean(R.drawable.collect, "拆托"));
                list.add(new MainItemBean(R.drawable.collect, "拼托"));
                list.add(new MainItemBean(R.drawable.collect, "替换"));
                break;
            case 3://经销商
                list.add(new MainItemBean(R.drawable.collect, "收货入库"));
                list.add(new MainItemBean(R.drawable.collect, "销售出库"));
                list.add(new MainItemBean(R.drawable.collect, "退货入库"));
                list.add(new MainItemBean(R.drawable.collect, "退货出库"));
                list.add(new MainItemBean(R.drawable.collect, "盘点"));
                break;
        }
    }

    /**
     * 初始化gridview
     *
     * @param menuGv
     * @param list
     */
    public void initGridView(GridView menuGv, List<MainItemBean> list) {
        menuGv.setAdapter(new GridViewAdapter(mContext, list));
    }

    /**
     * 基础数据下载
     */
    public void baseDataDownload() {
        //获取存储在本地的值
        long userID = (long) SharePreferanceUtil.getSpUtil().get_sp(AppConstant.USER_ID, 0l);
        long storeID = (long) SharePreferanceUtil.getSpUtil().get_sp(AppConstant.STORE_ID, 0l);
        int userType = (int) SharePreferanceUtil.getSpUtil().get_sp(AppConstant.USER_TYPE, 0);


        //每个身份都要下载的产品表
        downProduct(userID, storeID);
        //        downStorageRoom(userID, storeID);
        //        downDistributor(userID, storeID);
        //        downBillRule(userID, storeID);
        //        downAffair(userID, storeID);

        //根据身份去下载不同的数据列表
        switch (userType) {
            case 1://供应商

                break;
            case 2://企业仓库
                downStorageRoom(userID, storeID);
                downBillRule(userID, storeID);
                downAffair(userID, storeID);
                break;
            case 3://经销商
                downDistributor(userID, storeID);
                downAffair(userID, storeID);
                break;
        }
    }

    /**
     * 产品表基础下载的网络请求
     *
     * @param userID
     * @param storeID
     */
    private void downProduct(long userID, long storeID) {
        //下载之前先清空产品表基础数据表,防止数据重复
        if (ProductEntityDaoManager.deleteAll()) {
            mModel.downProduct(userID, storeID, 1)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable -> {
                        mRootView.showLoading();
                    }).subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> {
                        mRootView.hideLoading();

                        List<ProductEntity> baseDataEntities = ProductEntityDaoManager.queryAllFromAsc();
                        LogUtil.v("数据库获取到的产品表基础下载的数据是" + baseDataEntities);
                    })
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                    .subscribe(new ErrorHandlerObserver<JavaResponse<List<ProductEntity>>>() {
                        @Override
                        public void onNext(JavaResponse<List<ProductEntity>> listJavaResponse) {
                            if (listJavaResponse != null
                                    && listJavaResponse.getData() != null
                                    && !listJavaResponse.getData().isEmpty()) {
                                LogUtil.v("产品表基础下载的数据是" + listJavaResponse.getData());
                                ProductEntityDaoManager.insertData(listJavaResponse.getData());
                            }
                        }
                    });
        }
    }


    /**
     * 库房表基础下载的网络请求
     *
     * @param userID
     * @param storeID
     */
    private void downStorageRoom(long userID, long storeID) {
        //下载之前先清空库房表基础数据表,防止数据重复
        if (StorageRoomEntityDaoManager.deleteAll()) {
            mModel.downStorageRoom(userID, storeID, 2)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable -> {
                        mRootView.showLoading();
                    }).subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> {
                        mRootView.hideLoading();

                        List<StorageRoomEntity> baseDataEntities = StorageRoomEntityDaoManager.queryAllFromAsc();
                        LogUtil.v("数据库获取到的库房表基础下载的数据是" + baseDataEntities);
                    })
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                    .subscribe(new ErrorHandlerObserver<JavaResponse<List<StorageRoomEntity>>>() {
                        @Override
                        public void onNext(JavaResponse<List<StorageRoomEntity>> listJavaResponse) {
                            if (listJavaResponse != null
                                    && listJavaResponse.getData() != null
                                    && !listJavaResponse.getData().isEmpty()) {
                                LogUtil.v("库房表基础下载的数据是" + listJavaResponse.getData());
                                StorageRoomEntityDaoManager.insertData(listJavaResponse.getData());
                            }
                        }
                    });
        }
    }


    /**
     * 经销商表基础下载的网络请求
     *
     * @param userID
     * @param storeID
     */
    private void downDistributor(long userID, long storeID) {
        //下载之前先清空经销商表基础数据表,防止数据重复
        if (DistributorEntityDaoManager.deleteAll()) {
            mModel.downDistributor(userID, storeID, 3)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable -> {
                        mRootView.showLoading();
                    }).subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> {
                        mRootView.hideLoading();

                        List<DistributorEntity> baseDataEntities = DistributorEntityDaoManager.queryAllFromAsc();
                        LogUtil.v("数据库获取到的经销商表基础下载的数据是" + baseDataEntities);
                    })
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                    .subscribe(new ErrorHandlerObserver<JavaResponse<List<DistributorEntity>>>() {
                        @Override
                        public void onNext(JavaResponse<List<DistributorEntity>> listJavaResponse) {
                            if (listJavaResponse != null
                                    && listJavaResponse.getData() != null
                                    && !listJavaResponse.getData().isEmpty()) {
                                LogUtil.v("经销商表基础下载的数据是" + listJavaResponse.getData());
                                DistributorEntityDaoManager.insertData(listJavaResponse.getData());
                            }
                        }
                    });
        }
    }

    /**
     * 单据转换表基础下载的网络请求
     *
     * @param userID
     * @param storeID
     */
    private void downBillRule(long userID, long storeID) {
        //下载之前先清空单据转换表基础数据表,防止数据重复
        if (BillRuleEntityDaoManager.deleteAll()) {
            mModel.downBillRule(userID, storeID, 4)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable -> {
                        mRootView.showLoading();
                    }).subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> {
                        mRootView.hideLoading();

                        List<BillRuleEntity> baseDataEntities = BillRuleEntityDaoManager.queryAllFromAsc();
                        LogUtil.v("数据库获取到的经销商表基础下载的数据是" + baseDataEntities);
                    })
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                    .subscribe(new ErrorHandlerObserver<JavaResponse<List<BillRuleEntity>>>() {
                        @Override
                        public void onNext(JavaResponse<List<BillRuleEntity>> listJavaResponse) {
                            if (listJavaResponse != null
                                    && listJavaResponse.getData() != null
                                    && !listJavaResponse.getData().isEmpty()) {
                                LogUtil.v("经销商表基础下载的数据是" + listJavaResponse.getData());
                                BillRuleEntityDaoManager.insertData(listJavaResponse.getData());
                            }
                        }
                    });
        }
    }

    /**
     * 事务类型表基础下载的网络请求
     *
     * @param userID
     * @param storeID
     */
    private void downAffair(long userID, long storeID) {
        //下载之前先清空事务类型表基础数据表,防止数据重复
        if (AffairEntityDaoManager.deleteAll()) {
            mModel.downAffair(userID, storeID, 5)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable -> {
                        mRootView.showLoading();
                    }).subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> {
                        mRootView.hideLoading();

                        List<AffairEntity> baseDataEntities = AffairEntityDaoManager.queryAllFromAsc();
                        LogUtil.v("数据库获取到的经销商表基础下载的数据是" + baseDataEntities);
                    })
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                    .subscribe(new ErrorHandlerObserver<JavaResponse<List<AffairEntity>>>() {
                        @Override
                        public void onNext(JavaResponse<List<AffairEntity>> listJavaResponse) {
                            if (listJavaResponse != null
                                    && listJavaResponse.getData() != null
                                    && !listJavaResponse.getData().isEmpty()) {
                                LogUtil.v("经销商表基础下载的数据是" + listJavaResponse.getData());
                                AffairEntityDaoManager.insertData(listJavaResponse.getData());
                            }
                        }
                    });
        }
    }
}
