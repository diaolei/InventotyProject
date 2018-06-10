package www.zhaoxinkeji.com.inventotyproject.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.adapter.ThGoodsAdapter;
import www.zhaoxinkeji.com.inventotyproject.bean.Goods;
import www.zhaoxinkeji.com.inventotyproject.bean.RUpload;
import www.zhaoxinkeji.com.inventotyproject.constant.MessageConst;
import www.zhaoxinkeji.com.inventotyproject.constant.NetworkConst;
import www.zhaoxinkeji.com.inventotyproject.db.DbConst;
import www.zhaoxinkeji.com.inventotyproject.db.PdSqliteDao;
import www.zhaoxinkeji.com.inventotyproject.utils.JsonUtil;
import www.zhaoxinkeji.com.inventotyproject.utils.MyDialog;
import www.zhaoxinkeji.com.inventotyproject.utils.NetworkUtil;
import www.zhaoxinkeji.com.inventotyproject.utils.OkHttpUtil;
import www.zhaoxinkeji.com.inventotyproject.utils.SoundVibratorManager;

/**
 * Created by 27631 on 2018/3/8.
 */

public class ThGoodsActivity extends BaseActivity implements AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener  {

    private TextView mThGoodsTv;
    private EditText mThGoodsEt;
    private Button mThGoodsBtn;
    private ListView mThGoodsLv;
    private ThGoodsAdapter mAdapter;
    private Button mUpLoadBtn;
    private Button mUpdateBtn;
    private OkHttpUtil mOkHttpUtil;
    private MyReceiver receiver;
    private List<Goods.ContentBean.GoodsBean> list=new ArrayList<>();
    private List<Goods.ContentBean.GoodsBean> uploadList=new ArrayList<>();
    private boolean isHave;
    private int requestcode=1;
    private int isSelector;
    private int isCache;/**是否缓存**/
    private String billNum;/**单号**/
    private String barCodeNum;/**条码号**/
    private PdSqliteDao mDao;
    private String scanNumber;
    public String barcode;
    public String goodsName;
    public String scanNum;
    public String goodsId;
    private AlertDialog.Builder builder;
    private View mView;
    private AlertDialog mDialog;
    private LinearLayout mBarcodeLl;
    private EditText mBarcodeEt;
    private LinearLayout mCodeLl;
    private EditText mCodeEt;
    private EditText mNumberEt;
    private boolean isBarcode = true;
    private String action="com.android.server.scannerservice.broadcast";

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessageConst.ACTION_INVENTORY :
                    mThGoodsLv.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    break;
                case MessageConst.ACTION_INVENTORY_FAIL :
                    showToast((String)msg.obj);
                    break;
                case MessageConst.ACTION_INIT_DIALOG :
                    MyDialog.getInstance().setContext(ThGoodsActivity.this);
                    MyDialog.getInstance().show();
                    MyDialog.getInstance().setDisplay("载入数据中...");
                    break;
                case MessageConst.ACTION_DIALOG_DISMISS :
                    MyDialog.getInstance().dismiss();
                    break;
                case MessageConst.ACTION_INTERNET :
                    showToast("请查看网络环境是否正常！");
                    break;
                case MessageConst.ACTION_INSET_FAILE :
                    showToast("更新数据失败,请查看网络后再次操作！");
                    break;
            }
        }
    };

    @Override
    public void initUI() {
        mThGoodsTv = (TextView) findViewById(R.id.thgoods_tv);
        mThGoodsEt = (EditText) findViewById(R.id.thgoods_et);
        mThGoodsBtn = (Button) findViewById(R.id.thgoods_btn);
        mThGoodsBtn.setOnClickListener(this);
        mThGoodsLv = (ListView) findViewById(R.id.thgoods_lv);
        Button inputBtn = (Button) findViewById(R.id.input_btn);
        inputBtn.setOnClickListener(this);
        mThGoodsLv.setOnItemClickListener(this);
        mUpLoadBtn = (Button) findViewById(R.id.upload_btn);
        mUpLoadBtn.setOnClickListener(this);
        mUpdateBtn = (Button) findViewById(R.id.update_btn);
        mUpdateBtn.setOnClickListener(this);
        Button signOutBtn = (Button) findViewById(R.id.sign_out_btn);
        signOutBtn.setOnClickListener(this);
        builder = new AlertDialog.Builder(this);
        mView = View.inflate(this, R.layout.input_dialog, null);
        builder.setView(mView);
        builder.setCancelable(true);
        //输入内容

        RadioGroup barcodeRg = (RadioGroup) mView.findViewById(R.id.barcode_rg);
        barcodeRg.setOnCheckedChangeListener(this);
        mBarcodeLl = (LinearLayout) mView.findViewById(R.id.barcode_ll);
        mBarcodeEt = (EditText) mView.findViewById(R.id.barcode_et);
        mCodeLl = (LinearLayout) mView.findViewById(R.id.code_ll);
        mCodeEt = (EditText) mView.findViewById(R.id.code_et);
        mNumberEt = (EditText) mView.findViewById(R.id.number_et);
        Button cancelBtn=(Button) mView.findViewById(R.id.cancel_btn);//取消按钮
        cancelBtn.setOnClickListener(this);
        Button confirmBtn=(Button) mView.findViewById(R.id.confirm_btn);//确定按钮
        confirmBtn.setOnClickListener(this);
        //取消或确定按钮监听事件处理
        mDialog = builder.create();
    }

    @Override
    public void initData() {
        billNum = getIntent().getStringExtra("billNo");
        mThGoodsTv.setText("退货单号:"+billNum);
        mDao = new PdSqliteDao(this, DbConst.RETURN_GOODS);
        list=mDao.queryContact(billNum);
        if(list.size()==0) {
            isCache=0;
        }else{
            isCache=1;
            showToast("该退货单共有"+list.size()+"条商品信息");
        }
        sendAsyncTaskMessage();
        mOkHttpUtil = OkHttpUtil.getInstance();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_thgooods;
    }


    @Override
    protected void onResume() {
        super.onResume();
        receiver=new MyReceiver();
        IntentFilter filter=new IntentFilter(action);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.thgoods_btn :
                if(TextUtils.isEmpty(mThGoodsEt.getText().toString().trim())) {
                    showToast("请输入退货条码");
                    SoundVibratorManager.playSound(2);
                    return;
                }
                isHave=false;
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getBarcodeid().equals(mThGoodsEt.getText().toString().trim())) {
                        mAdapter.selectIndex=i;
                        mAdapter.backGround=0;
                        isSelector=i;
                        if(isSelector!=0) {
                            barcode=list.get(0).getBarcodeid();
                            goodsName=list.get(0).getGoodsName();
                            scanNum=list.get(0).getScanNum();
                            goodsId=list.get(0).getGoodsId();
                            list.get(0).setBarcodeid(list.get(i).getBarcodeid());
                            list.get(0).setGoodsName(list.get(i).getGoodsName());
                            list.get(0).setGoodsId(list.get(i).getGoodsId());
                            list.get(0).setScanNum((int)(Double.parseDouble(list.get(i).getScanNum())+1)+"");
                            list.get(isSelector).setBarcodeid(barcode);
                            list.get(isSelector).setGoodsName(goodsName);
                            list.get(isSelector).setScanNum(scanNum);
                            list.get(isSelector).setGoodsId(goodsId);

                        }else{
                            list.get(0).setScanNum((int)(Double.parseDouble(list.get(i).getScanNum())+1)+"");
                        }
                        mAdapter.notifyDataSetChanged();
                        isHave=true;
                        SoundVibratorManager.playSound(1);
                        mDao.updateContact(list.get(0).getScanNum(),mThGoodsEt.getText().toString().trim());
                    }
                }
                if(!isHave) {
                    mThGoodsEt.setText("");
                    showToast("没有该商品");
                    SoundVibratorManager.playSound(2);
                }
                break;
            case R.id.upload_btn:
                upLoadMessage();
                break;
            case R.id.update_btn:
                update();
                break;
            case R.id.input_btn:
                mDialog.show();
                break;
            case R.id.sign_out_btn:
                mBuilder.setTitle("提示");
                mBuilder.setMessage("是否退出？");
                mBuilder.setPositiveButton("是",
                        new DialogInterface.OnClickListener() {// 添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {// 确定按钮的响应事件
                                finish();
                            }
                        });
                mBuilder.setNegativeButton("否", null);
                mBuilder.show();
                break;
            case R.id.cancel_btn:
                mBarcodeEt.setText("");
                mCodeEt.setText("");
                mNumberEt.setText("");
                mDialog.dismiss();
                break;
            case R.id.confirm_btn:

                if (isBarcode) {
                    if (TextUtils.isEmpty(mBarcodeEt.getText().toString().trim())) {
                        showToast("条码不能为空！");
                        SoundVibratorManager.playSound(2);
                        return;
                    }

                    if(TextUtils.isEmpty(mNumberEt.getText().toString().trim())) {
                        showToast("修改数量不能为空！");
                        SoundVibratorManager.playSound(2);
                        return;
                    }
                } else {
                    if (TextUtils.isEmpty(mCodeEt.getText().toString().trim())) {
                        showToast("商品编码不能为空！");
                        SoundVibratorManager.playSound(2);
                        return;
                    }

                    if(TextUtils.isEmpty(mNumberEt.getText().toString().trim())) {
                        showToast("修改数量不能为空！");
                        SoundVibratorManager.playSound(2);
                        return;
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    if(isBarcode) {
                        if (list.get(i).getBarcodeid().equals(mBarcodeEt.getText().toString().trim())) {
                            mAdapter.selectIndex = i;
                            mAdapter.backGround = 0;
                            isSelector = i;
                            isHave=true;
                            if (isSelector != 0) {
                                barcode = list.get(0).getBarcodeid();
                                goodsName = list.get(0).getGoodsName();
                                scanNum = list.get(0).getScanNum();
                                goodsId = list.get(0).getGoodsId();
                                list.get(0).setBarcodeid(list.get(isSelector).getBarcodeid());
                                list.get(0).setGoodsName(list.get(isSelector).getGoodsName());
                                list.get(0).setGoodsNum(list.get(isSelector).getGoodsNum());
                                list.get(0).setGoodsId(list.get(isSelector).getGoodsId());
                                list.get(0).setScanNum(mNumberEt.getText().toString().trim());
                                list.get(isSelector).setBarcodeid(barcode);
                                list.get(isSelector).setGoodsName(goodsName);
                                list.get(isSelector).setScanNum(scanNum);
                                list.get(isSelector).setGoodsId(goodsId);

                            } else {
                                list.get(0).setScanNum(mNumberEt.getText().toString().trim());
                            }
                            mAdapter.notifyDataSetChanged();
                            showToast("修改完成");
                            SoundVibratorManager.playSound(1);
                            mDao.updateContact(list.get(0).getScanNum(),
                                    list.get(0).getBarcodeid());

                            mBarcodeEt.setText("");
                            mNumberEt.setText("");
                            mDialog.dismiss();
                        }
                    }else{
                        if (list.get(i).getGoodsId().equals(mCodeEt.getText().toString().trim())) {
                            mAdapter.selectIndex = i;
                            mAdapter.backGround = 0;
                            isSelector = i;
                            isHave = true;
                            if (isSelector != 0) {
                                barcode = list.get(0).getBarcodeid();
                                goodsName = list.get(0).getGoodsName();
                                scanNum = list.get(0).getScanNum();
                                goodsId = list.get(0).getGoodsId();
                                list.get(0).setBarcodeid(list.get(isSelector).getBarcodeid());
                                list.get(0).setGoodsName(list.get(isSelector).getGoodsName());
                                list.get(0).setGoodsNum(list.get(isSelector).getGoodsNum());
                                list.get(0).setGoodsId(list.get(isSelector).getGoodsId());
                                list.get(0).setScanNum(mNumberEt.getText().toString().trim());
                                list.get(isSelector).setBarcodeid(barcode);
                                list.get(isSelector).setGoodsName(goodsName);
                                list.get(isSelector).setScanNum(scanNum);
                                list.get(isSelector).setGoodsId(goodsId);

                            } else {
                                list.get(0).setScanNum(mNumberEt.getText().toString().trim());
                            }
                            mAdapter.notifyDataSetChanged();
                            showToast("修改完成");
                            SoundVibratorManager.playSound(1);
                            mDao.updateContact(list.get(0).getScanNum(),
                                    list.get(0).getBarcodeid());

                            mCodeEt.setText("");
                            mNumberEt.setText("");
                            mDialog.dismiss();
                        }

                    }
                }

                if(!isHave) {
                    showToast("该单无此条码");
                    isHave=false;
                    SoundVibratorManager.playSound(2);
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int position) {
        switch (position) {
            case R.id.barcode_rb:
                mCodeLl.setVisibility(View.GONE);
                mBarcodeLl.setVisibility(View.VISIBLE);
                isBarcode=true;
                break;

            case R.id.code_rb:
                mBarcodeLl.setVisibility(View.GONE);
                mCodeLl.setVisibility(View.VISIBLE);
                isBarcode=false;
                break;
        }
    }

    private void update() {

        mBuilder.setTitle("提示");
        mBuilder.setMessage("刷新获取最新商品列表会丢失当前扫描数据,确定要刷新吗？");
        mBuilder.setPositiveButton("是",
                new DialogInterface.OnClickListener() {// 添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {// 确定按钮的响应事件
                            mDao.deleteContact(billNum);
                                isCache=0;
                                sendAsyncTaskMessage();
                    }
                });
        mBuilder.setNegativeButton("否", null);
        mBuilder.show();
    }

    /**判断完整上传盘点单信息**/
    private void upLoadMessage() {
        for (int i = 0; i < list.size(); i++) {
            if((int)Double.parseDouble(list.get(i).getScanNum())!=0) {
                uploadList.add(list.get(i));
            }
        }

        if(uploadList.size()==0) {
            showToast("还未扫描数据,不可上传");
            return;
        }
            mBuilder.setTitle("提示");

            mBuilder.setMessage("是否上传？");

            mBuilder.setPositiveButton("是",
                    new DialogInterface.OnClickListener() {// 添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {// 确定按钮的响应事件
                            upLoad();
                            uploadList.clear();
                        }
                    });
            mBuilder.setNegativeButton("否", null);
            mBuilder.show();
    }

    /**上传盘点单信息**/
    private void upLoad() {
        mHandler.obtainMessage(MessageConst.ACTION_INIT_DIALOG).sendToTarget();

        String result=null;
        SharedPreferences preferences = getSharedPreferences("server", 1);
        String serverName = preferences.getString("serverName", "");
        String port = preferences.getString("port", "");
        String path = preferences.getString("path", "");

        if(TextUtils.isEmpty(serverName)||TextUtils.isEmpty(port)||
                TextUtils.isEmpty(path)) {
            result = NetworkConst.ACTION_UPLOAD;
        }else{
            result = NetworkConst.TITLE+serverName+NetworkConst
                    .POINT+port+NetworkConst.LINE+path+NetworkConst.END+NetworkConst.UPLOAD;
        }

        String json = null;
        HashMap<String, String> map = new HashMap<>();
        map.put("billNo", getIntent().getStringExtra("billNo"));
        HashMap<String, String> hashMap = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            hashMap.put("barcodeid",list.get(i).getBarcodeid());
            hashMap.put("goodsNum",list.get(i).getScanNum());
            hashMap.put("goodsId",list.get(i).getGoodsId());
            hashMap.put("goodsName",list.get(i).getGoodsName());
            json = JsonUtil.list2Json(hashMap);
            /**拼接出列表格式**/
            builder.append(json+",");
        }

        json="["+builder.toString().substring(0,builder.toString().length()-1)+"]";

        mOkHttpUtil.postJson(result,map,json,new OkHttpUtil.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                mHandler.obtainMessage(MessageConst.ACTION_DIALOG_DISMISS).sendToTarget();
                RUpload rUpload = JSON.parseObject(data, RUpload.class);
                showToast(rUpload.getMsgInfo());
                mDao.deleteContact(billNum);
                finish();
            }

            @Override
            public void onError(String msg) {
                mHandler.obtainMessage(MessageConst.ACTION_DIALOG_DISMISS).sendToTarget();
                showToast("上传失败："+msg);
            }
        });
    }

    @Override
    protected void sendAsyncTask() {
        switch (isCache) {
            case 0 :
                String result=null;
                SharedPreferences preferences = getSharedPreferences("server", 1);
                String serverName = preferences.getString("serverName", "");
                String port = preferences.getString("port", "");
                String path = preferences.getString("path", "");

                HashMap<String, String> map = new HashMap<>();
                map.put("billNo", getIntent().getStringExtra("billNo"));

                mHandler.obtainMessage(MessageConst.ACTION_INIT_DIALOG).sendToTarget();

                if(TextUtils.isEmpty(serverName)||TextUtils.isEmpty(port)||
                        TextUtils.isEmpty(path)) {
                    result = NetworkUtil.doPost(NetworkConst.ACTION_GETBILLGOODS,JsonUtil.map2Json(map));
                }else{
                    result = NetworkUtil.doPost(NetworkConst.TITLE+serverName+NetworkConst
                            .POINT+port+NetworkConst.LINE+path+NetworkConst.END+NetworkConst.GETBILLGOODS,JsonUtil.map2Json(map));
                }

                mHandler.obtainMessage(MessageConst.ACTION_DIALOG_DISMISS).sendToTarget();
                if(TextUtils.isEmpty(result)) {
                    mHandler.obtainMessage(MessageConst.ACTION_INTERNET).sendToTarget();
                    return;
                }
                Goods goods = JSON.parseObject(result, Goods.class);
                list = goods.getContent().getGoods();

                if (goods.getMsgInfo().equals("调用成功")) {
                    mAdapter = new ThGoodsAdapter(this, list);
                    mHandler.obtainMessage(MessageConst.ACTION_INVENTORY).sendToTarget();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast("该退货单共有"+list.size()+"条商品信息");
                        }
                    });
                } else {
                    mHandler.obtainMessage(MessageConst.ACTION_INVENTORY_FAIL, goods.getMsgInfo()).sendToTarget();
                }
                    for (int i = 0; i < list.size(); i++) {
                        boolean insert = mDao.insert(list.get(i), billNum);
                        if(!insert) {
                            mHandler.obtainMessage(MessageConst.ACTION_INSET_FAILE).sendToTarget();
                        }
                    }
                break;
            case 1 :
                mAdapter = new ThGoodsAdapter(this, list);
                mHandler.obtainMessage(MessageConst.ACTION_INVENTORY).sendToTarget();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        isSelector=position;
        /**点击获取修改前的退货数**/
        barCodeNum =list.get(position).getBarcodeid();

        Intent intent = new Intent(this, ModifyNumActivity.class);
        intent.putExtra("barcode",list.get(position).getBarcodeid());
        intent.putExtra("number",String.valueOf((int)Double.parseDouble(list.get(position).getScanNum())));
        scanNumber=String.valueOf((int)Double.parseDouble(list.get(position).getScanNum()));
        startActivityForResult(intent,requestcode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(!data.getStringExtra("rNumber").equals(scanNumber)) {
            mAdapter.selectIndex=isSelector;
            mAdapter.backGround=0;
            if(isSelector!=0) {
                barcode=list.get(0).getBarcodeid();
                goodsName=list.get(0).getGoodsName();
                scanNum=list.get(0).getScanNum();
                goodsId=list.get(0).getGoodsId();
                list.get(0).setBarcodeid(list.get(isSelector).getBarcodeid());
                list.get(0).setGoodsName(list.get(isSelector).getGoodsName());
                list.get(0).setGoodsId(list.get(isSelector).getGoodsId());
                list.get(0).setScanNum(data.getStringExtra("rNumber"));
                list.get(isSelector).setBarcodeid(barcode);
                list.get(isSelector).setGoodsName(goodsName);
                list.get(isSelector).setScanNum(scanNum);
                list.get(isSelector).setGoodsId(goodsId);
                mDao.updateContact(data.getStringExtra("rNumber"), barCodeNum);
            }else{
                list.get(0).setScanNum(data.getStringExtra("rNumber"));
                mDao.updateContact(data.getStringExtra("rNumber"), barCodeNum);
            }
            mAdapter.notifyDataSetChanged();
            showToast("修改完成");
        }
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String barcode = intent.getStringExtra("scannerdata");
            barcode=barcode.toString().trim();
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getBarcodeid().equals(barcode)) {
                    mThGoodsEt.setText(barcode);
                    mAdapter.selectIndex=i;
                    mAdapter.backGround=0;
                    isSelector=i;
                    if(isSelector!=0) {
                        barcode=list.get(0).getBarcodeid();
                        goodsName=list.get(0).getGoodsName();
                        scanNum=list.get(0).getScanNum();
                        goodsId=list.get(0).getGoodsId();
                        list.get(0).setBarcodeid(list.get(i).getBarcodeid());
                        list.get(0).setGoodsName(list.get(i).getGoodsName());
                        list.get(0).setGoodsId(list.get(i).getGoodsId());
                        list.get(0).setScanNum((int)(Double.parseDouble(list.get(i).getScanNum())+1)+"");
                        list.get(isSelector).setBarcodeid(barcode);
                        list.get(isSelector).setGoodsName(goodsName);
                        list.get(isSelector).setScanNum(scanNum);
                        list.get(isSelector).setGoodsId(goodsId);

                    }else{
                        list.get(0).setScanNum((int)(Double.parseDouble(list.get(i).getScanNum())+1)+"");
                    }
                    mAdapter.notifyDataSetChanged();
                    isHave=true;
                    SoundVibratorManager.playSound(1);
                    mDao.updateContact(list.get(0).getScanNum(),
                            list.get(0).getBarcodeid());
                }
            }
            if(!isHave) {
                mThGoodsEt.setText("");
                showToast("没有该商品");
                isHave=false;
                SoundVibratorManager.playSound(2);
            }
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction()==KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK :
                    /**复写返回键**/
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}