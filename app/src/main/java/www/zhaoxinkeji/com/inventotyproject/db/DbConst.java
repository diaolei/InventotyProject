package www.zhaoxinkeji.com.inventotyproject.db;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 2017/7/5.
 */

public class DbConst implements BaseColumns {

   public static final String DB_NAME="data.db";
   public static final int  DB_VERSION= 1;
   public static final String INVENTORY = "inventory";
   public static final String CHECK_RECEIVE = "checkReceive";
   public static final String RETURN_GOODS = "returnGoods";
   public static final String BILL_NUM = "billNo";
   public static final String BARCODE = "barcode";
   public static final String GOODS_NAME = "goodsName";
   public static final String GOODS_NUM = "goodsNum";
   public static final String SCAN_NUM = "scanNum";
   public static final String GIFT_NUM = "giftNum";
   public static final String GOODS_ID = "goodsId";
}

