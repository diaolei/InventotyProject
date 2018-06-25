package www.zhaoxinkeji.com.inventotyproject.manager;

import java.util.List;

import www.zhaoxinkeji.com.dbdatabase.entity.AffairEntity;
import www.zhaoxinkeji.com.dbdatabase.greendao.AffairEntityDao;
import www.zhaoxinkeji.com.inventotyproject.utils.LogUtil;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/25
 *     desc   : 事务类型表操作类
 *     version: 1.0
 * </pre>
 */

public class AffairEntityDaoManager {

    private static final String TAG = AffairEntityDaoManager.class.getSimpleName();

    /**
     * 单个添加
     *
     * @param entity 需要添加的实体
     * @return 是否成功
     */
    public static boolean insertData(AffairEntity entity) {
        boolean flag = false;
        try {
            flag = GreenDaoManager.getInstance().getNewSession().getAffairEntityDao().insertOrReplace(entity) != -1;
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
    public static boolean insertData(List<AffairEntity> list) {
        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getAffairEntityDao().insertOrReplaceInTx(list);
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
    public static boolean deleteData(AffairEntity entity) {
        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getAffairEntityDao().delete(entity);
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
    public static boolean deleteData(List<AffairEntity> list) {
        if (null == list || list.size() <= 0) {
            return false;
        }

        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getAffairEntityDao().deleteInTx(list);
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
    public static boolean updateData(AffairEntity entity) {
        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getAffairEntityDao().update(entity);
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
    public static boolean updateData(List<AffairEntity> list) {
        if (null == list || list.size() <= 0) {
            return false;
        }

        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getAffairEntityDao().updateInTx(list);
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
    public static AffairEntity queryDataFormId(long id) {
        return GreenDaoManager.getInstance()
                .getNewSession()
                .getAffairEntityDao()
                .queryBuilder()
                .where(AffairEntityDao.Properties.Id.eq(id))
                .unique();
    }


    /**
     * 查询全部数据(顺序)
     *
     * @return List集合
     */
    public static List<AffairEntity> queryAllFromAsc() {
        return GreenDaoManager.getInstance()
                .getNewSession()
                .getAffairEntityDao()
                .queryBuilder()
                .orderAsc(AffairEntityDao.Properties.Id)
                .list();
    }
}
