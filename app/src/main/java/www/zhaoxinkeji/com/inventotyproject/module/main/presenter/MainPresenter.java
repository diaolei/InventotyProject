package www.zhaoxinkeji.com.inventotyproject.module.main.presenter;

import android.widget.GridView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import www.zhaoxinkeji.com.dbdatabase.entity.BaseDataEntity;
import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.app.AppConstant;
import www.zhaoxinkeji.com.inventotyproject.base.BasePresenter;
import www.zhaoxinkeji.com.inventotyproject.http.ErrorHandlerObserver;
import www.zhaoxinkeji.com.inventotyproject.manager.BaseDataDaoManager;
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
        int baseType = (int) SharePreferanceUtil.getSpUtil().get_sp(AppConstant.USER_TYPE, 0);

        //下载之前先清空基础数据下载表,防止数据重复
        if (BaseDataDaoManager.deleteAll()) {
            mModel.baseDataDownload(userID, storeID, baseType)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable -> {
                        mRootView.showLoading();
                    }).subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> {
                        mRootView.hideLoading();

                        List<BaseDataEntity> baseDataEntities = BaseDataDaoManager.queryAllFromAsc();
                        LogUtil.v("数据库获取到的基础下载的数据是" + baseDataEntities);
                    })
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                    .subscribe(new ErrorHandlerObserver<JavaResponse<List<BaseDataEntity>>>() {
                        @Override
                        public void onNext(JavaResponse<List<BaseDataEntity>> listJavaResponse) {
                            if (listJavaResponse != null
                                    && listJavaResponse.getData() != null
                                    && !listJavaResponse.getData().isEmpty()) {
                                LogUtil.v("基础下载的数据是" + listJavaResponse.getData());
                                // TODO: 2018/6/24 数据库存储
                                BaseDataDaoManager.insertData(listJavaResponse.getData());
                            }
                        }
                    });
        }
    }
}
