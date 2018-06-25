package www.zhaoxinkeji.com.inventotyproject.manager;

import java.util.List;

import www.zhaoxinkeji.com.dbdatabase.entity.DistributorEntity;
import www.zhaoxinkeji.com.dbdatabase.greendao.DistributorEntityDao;
import www.zhaoxinkeji.com.inventotyproject.utils.LogUtil;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/25
 *     desc   : 下级经销商表操作类
 *     version: 1.0
 * </pre>
 */

public class DistributorEntityDaoManager {

    private static final String TAG = DistributorEntityDaoManager.class.getSimpleName();

    /**
     * 单个添加
     *
     * @param entity 需要添加的实体
     * @return 是否成功
     */
    public static boolean insertData(DistributorEntity entity) {
        boolean flag = false;
        try {
            flag = GreenDaoManager.getInstance().getNewSession().getDistributorEntityDao().insertOrReplace(entity) != -1;
        } catch (Exception e) {
            LogUtil.v(TAG, e.toString());
        }
        return flag;
    }

    /**
     * 列表添加
     *
     * @param list 需要添加的列表
     * @return 是否成功
     */
    public static boolean insertData(List<DistributorEntity> list) {
        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getDistributorEntityDao().insertOrReplaceInTx(list);
            flag = true;
        } catch (Exception e) {
            LogUtil.v(TAG, e.toString());
        }
        return flag;
    }

    /**
     * 删除指定的单个数据
     *
     * @param entity 需要删除的数据
     * @return 是否成功
     */
    public static boolean deleteData(DistributorEntity entity) {
        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getDistributorEntityDao().delete(entity);
            flag = true;
        } catch (Exception e) {
            LogUtil.v(TAG, e.toString());
        }
        return flag;
    }

    /**
     * 删除指定的列表数据
     *
     * @param list 需要删除的列表
     * @return 是否成功
     */
    public static boolean deleteData(List<DistributorEntity> list) {
        if (null == list || list.size() <= 0) {
            return false;
        }

        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getDistributorEntityDao().deleteInTx(list);
            flag = true;
        } catch (Exception e) {
            LogUtil.v(TAG, e.toString());
        }
        return flag;
    }

    /**
     * 删除表中所有数据
     *
     * @return 是否成功
     */
    public static boolean deleteAll() {
        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getProductEntityDao().deleteAll();
            flag = true;
        } catch (Exception e) {
            LogUtil.v(TAG, e.toString());
        }
        return flag;
    }

    /**
     * 更新单个数据
     *
     * @param entity 需要更新的数据实体
     * @return 是否成功
     */
    public static boolean updateData(DistributorEntity entity) {
        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getDistributorEntityDao().update(entity);
            flag = true;
        } catch (Exception e) {
            LogUtil.v(TAG, e.toString());
        }
        return flag;
    }

    /**
     * 更新列表数据
     *
     * @param list 需要更新的列表数据
     * @return 是否成功
     */
    public static boolean updateData(List<DistributorEntity> list) {
        if (null == list || list.size() <= 0) {
            return false;
        }

        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getDistributorEntityDao().updateInTx(list);
            flag = true;
        } catch (Exception e) {
            LogUtil.v(TAG, e.toString());
        }
        return flag;
    }

    /**
     * 查询单个数据
     *
     * @return List集合
     */
    public static DistributorEntity queryDataFormId(long id) {
        return GreenDaoManager.getInstance()
                .getNewSession()
                .getDistributorEntityDao()
                .queryBuilder()
                .where(DistributorEntityDao.Properties.Id.eq(id))
                .unique();
    }


    /**
     * 查询全部数据(顺序)
     *
     * @return List集合
     */
    public static List<DistributorEntity> queryAllFromAsc() {
        return GreenDaoManager.getInstance()
                .getNewSession()
                .getDistributorEntityDao()
                .queryBuilder()
                .orderAsc(DistributorEntityDao.Properties.Id)
                .list();
    }
}
