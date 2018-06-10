package www.zhaoxinkeji.com.inventotyproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/7/5.
 */

public class PdSqlite extends SQLiteOpenHelper {
    public PdSqlite(Context context) {
        super(context, DbConst.DB_NAME, null, DbConst.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DbConst.INVENTORY +" ("
                  +DbConst._ID+" integer primary key autoincrement,"
                  +DbConst.BILL_NUM +" text," +
                ""+DbConst.BARCODE +" text," +
                ""+DbConst.GOODS_NAME +" text," +
                ""+DbConst.GOODS_NUM +" text," +
                ""+DbConst.SCAN_NUM +" text," +
                ""+DbConst.GOODS_ID +" text)");

        db.execSQL("create table "+DbConst.CHECK_RECEIVE +" ("
                +DbConst._ID+" integer primary key autoincrement,"
                +DbConst.BILL_NUM +" text," +
                ""+DbConst.BARCODE +" text," +
                ""+DbConst.GOODS_NAME +" text," +
                ""+DbConst.GOODS_NUM +" text," +
                ""+DbConst.SCAN_NUM +" text," +
                ""+DbConst.GIFT_NUM +" text," +
                ""+DbConst.GOODS_ID +" text)");

        db.execSQL("create table "+DbConst.RETURN_GOODS +" ("
                +DbConst._ID+" integer primary key autoincrement,"
                +DbConst.BILL_NUM +" text," +
                ""+DbConst.BARCODE +" text," +
                ""+DbConst.GOODS_NAME +" text," +
                ""+DbConst.GOODS_NUM +" text," +
                ""+DbConst.SCAN_NUM +" text," +
                ""+DbConst.GOODS_ID +" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
