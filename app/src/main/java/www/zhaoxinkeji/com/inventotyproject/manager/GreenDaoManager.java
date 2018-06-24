package www.zhaoxinkeji.com.inventotyproject.manager;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

import www.zhaoxinkeji.com.dbdatabase.greendao.DaoMaster;
import www.zhaoxinkeji.com.dbdatabase.greendao.DaoSession;
import www.zhaoxinkeji.com.dbdatabase.openhelper.MySQLiteOpenHelper;
import www.zhaoxinkeji.com.inventotyproject.app.AppConstant;
import www.zhaoxinkeji.com.inventotyproject.app.BaseApplication;


/**
 * <pre>
 *     author : LinRuo
 *     time   : 2018/04/08
 *     desc   : GreenDao数据库初始化管理器
 *     version: 1.0
 * </pre>
 */
public class GreenDaoManager {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static volatile GreenDaoManager mInstance = null;

    private GreenDaoManager() {
        if (mInstance == null) {
            //重写MySQLiteOpenHelper数据库升级，数据不丢失
            //DEBUG表示是否打印debug
            MigrationHelper.DEBUG = false;
            MySQLiteOpenHelper helper = new
                    MySQLiteOpenHelper(BaseApplication.getInstance(), AppConstant.DB_NAME, null);
            mDaoMaster = new DaoMaster(helper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}