package www.zhaoxinkeji.com.inventotyproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import www.zhaoxinkeji.com.inventotyproject.bean.Goods;

/**
 * Created by Administrator on 2017/7/5.
 */

public class PdSqliteDao {

    private SQLiteDatabase mDb;
    private List<Goods.ContentBean.GoodsBean> mList;
    private String formName;

    public PdSqliteDao(Context c,String name) {
        PdSqlite dbOpenHelper = new PdSqlite(c);
        mDb = dbOpenHelper.getWritableDatabase();
        mList=new ArrayList<>();
        formName=name;
    }


    public boolean insert(Goods.ContentBean.GoodsBean bean,String billNum){
        ContentValues values = new ContentValues();
            values.put(DbConst.BILL_NUM, billNum);
            values.put(DbConst.BARCODE, bean.getBarcodeid());
            values.put(DbConst.GOODS_NAME, bean.getGoodsName());
            values.put(DbConst.GOODS_NUM, bean.getGoodsNum());
            values.put(DbConst.SCAN_NUM, bean.getScanNum());
            values.put(DbConst.GOODS_ID, bean.getGoodsId());

            long insert = mDb.insert(formName, null, values);
        return insert>0;
    }

    //	查询整张表 并且将数据在该方法中打印出来
    public List<Goods.ContentBean.GoodsBean> queryContact(String billNum) {
        //		columns 查询的列 String[]
        //		selection selectionArgs  查询的条件 还有查询条件的绑定值
        //		_id barcode
        Cursor cursor = mDb.query(formName,
                new String[]{DbConst.BARCODE,DbConst.GOODS_NAME,
                DbConst.GOODS_NUM,DbConst.SCAN_NUM,DbConst.GOODS_ID},
                DbConst.BILL_NUM+"=?", new String[]{billNum},
                null, null, null);
        //		cursor.moveToNext() 移动游标  返回一个boolean
        while (cursor.moveToNext()) {
            //读取每一行数据
            String barcode = cursor.getString(cursor.getColumnIndex(DbConst.BARCODE));
            String goodsName = cursor.getString(cursor.getColumnIndex(DbConst.GOODS_NAME));
            String goodsNum = cursor.getString(cursor.getColumnIndex(DbConst.GOODS_NUM));
            String scanNum = cursor.getString(cursor.getColumnIndex(DbConst.SCAN_NUM));
            String goodsId = cursor.getString(cursor.getColumnIndex(DbConst.GOODS_ID));
            Goods.ContentBean.GoodsBean goodsBean=new Goods.ContentBean.GoodsBean();
            goodsBean.setBarcodeid(barcode);
            goodsBean.setGoodsName(goodsName);
            goodsBean.setGoodsNum(goodsNum);
            goodsBean.setScanNum(scanNum);
            goodsBean.setGoodsId(goodsId);

            mList.add(goodsBean);
        }

        return mList;
    }



    //查询一行数据
    public String queryOne(String data){

        Cursor cursor = mDb.query(formName, null,
                DbConst.BARCODE +"=?", new String[]{data}, null,
                null, null);
        while (cursor.moveToNext()) {
            //读取每一行数据
            int columnIndex = cursor.getColumnIndex(data);
            Log.i("abc","行数："+columnIndex);
            if(columnIndex>0) {
                return cursor.getString(columnIndex);
            }
        }
        return "";

        /*Cursor cursor = mDb.query(DbConst.CHECK_RECEIVE, new String[] {
                        DbConst.BARCODE}, null, null,
                null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String data = cursor.getString(cursor
                    .getColumnIndex(DbConst.BARCODE));
            return data;
        }
        return "";*/
    }

    public boolean deleteContact(String billNum) {
        //		whereClause 查询条件
        //		whereArgs 查询条件绑定的值
        int deleteRows = mDb.delete(formName, "billNo=?", new String[]{billNum});
        //		deleteRows 删除的行数
        Log.i("abc","删除行数："+deleteRows);
        return deleteRows>0;
    }
    public void deleteAll(Context context) {
        /**DROP TABLE COMPANY**/
        //mDb.execSQL("DROP TABLE "+DbConst.INVENTORY);
        //mDb.delete(DbConst.DB_NAME,"1",new String[]{});
        context.deleteDatabase(DbConst.DB_NAME);
    }

    public boolean updateContact(String newData,String data) {
        ContentValues values=new ContentValues();
        values.put(DbConst.SCAN_NUM, newData);
        //		update contactinfo set data='新数据' where data='原数据';
        //		whereClause 查询条件
        //		whereArgs 查询条件绑定的值
        int updateRows = mDb.update(formName, values, "barcode=?", new String[]{data});
        //		updateRows更新了3行  返回3
        return updateRows>0;
    }

    /*public void update(String newData,String data){
        mDb.execSQL("update "+DbConst.RETURN_GOODS+" set "+DbConst
                .SCAN_NUM+"=? where "+DbConst.BARCODE+"=?",
                new String[] {newData,data});
    }*/

}
