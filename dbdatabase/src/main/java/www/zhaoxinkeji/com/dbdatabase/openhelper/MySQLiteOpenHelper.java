package www.zhaoxinkeji.com.dbdatabase.openhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;

import www.zhaoxinkeji.com.dbdatabase.greendao.BaseDataEntityDao;
import www.zhaoxinkeji.com.dbdatabase.greendao.DaoMaster;

/**
 * <pre>
 *     author : LinRuo
 *     time   : 2018/04/08
 *     desc   : GreenDao数据库升级帮助类
 *     version: 1.0
 * </pre>
 */
public class MySQLiteOpenHelper extends DaoMaster.DevOpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, BaseDataEntityDao.class);
    }
}
