package www.zhaoxinkeji.com.inventotyproject.manager;

import java.util.List;

import www.zhaoxinkeji.com.dbdatabase.entity.BillRuleEntity;
import www.zhaoxinkeji.com.dbdatabase.greendao.BillRuleEntityDao;
import www.zhaoxinkeji.com.inventotyproject.utils.LogUtil;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/25
 *     desc   : 单据转换表操作类
 *     version: 1.0
 * </pre>
 */

public class BillRuleEntityDaoManager {

    private static final String TAG = BillRuleEntityDaoManager.class.getSimpleName();

    /**
     * 单个添加
     *
     * @param entity 需要添加的实体
     * @return 是否成功
     */
    public static boolean insertData(BillRuleEntity entity) {
        boolean flag = false;
        try {
            flag = GreenDaoManager.getInstance().getNewSession().getBillRuleEntityDao().insertOrReplace(entity) != -1;
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
    public static boolean insertData(List<BillRuleEntity> list) {
        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getBillRuleEntityDao().insertOrReplaceInTx(list);
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
    public static boolean deleteData(BillRuleEntity entity) {
        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getBillRuleEntityDao().delete(entity);
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
    public static boolean deleteData(List<BillRuleEntity> list) {
        if (null == list || list.size() <= 0) {
            return false;
        }

        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getBillRuleEntityDao().deleteInTx(list);
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
    public static boolean updateData(BillRuleEntity entity) {
        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getBillRuleEntityDao().update(entity);
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
    public static boolean updateData(List<BillRuleEntity> list) {
        if (null == list || list.size() <= 0) {
            return false;
        }

        boolean flag = false;
        try {
            GreenDaoManager.getInstance().getNewSession().getBillRuleEntityDao().updateInTx(list);
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
    public static BillRuleEntity queryDataFormId(long id) {
        return GreenDaoManager.getInstance()
                .getNewSession()
                .getBillRuleEntityDao()
                .queryBuilder()
                .where(BillRuleEntityDao.Properties.Id.eq(id))
                .unique();
    }


    /**
     * 查询全部数据(顺序)
     *
     * @return List集合
     */
    public static List<BillRuleEntity> queryAllFromAsc() {
        return GreenDaoManager.getInstance()
                .getNewSession()
                .getBillRuleEntityDao()
                .queryBuilder()
                .orderAsc(BillRuleEntityDao.Properties.Id)
                .list();
    }
}
