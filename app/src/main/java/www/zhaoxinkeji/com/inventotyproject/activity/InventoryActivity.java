package www.zhaoxinkeji.com.inventotyproject.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.adapter.InventoryAdapter;
import www.zhaoxinkeji.com.inventotyproject.bean.RInventory;
import www.zhaoxinkeji.com.inventotyproject.constant.MessageConst;
import www.zhaoxinkeji.com.inventotyproject.constant.NetworkConst;
import www.zhaoxinkeji.com.inventotyproject.utils.JsonUtil;
import www.zhaoxinkeji.com.inventotyproject.utils.MyDialog;
import www.zhaoxinkeji.com.inventotyproject.utils.MyListView;
import www.zhaoxinkeji.com.inventotyproject.utils.NetworkUtil;

/**
 * Created by 27631 on 2018/3/6.
 */

public class InventoryActivity extends BaseActivity implements AdapterView.OnItemClickListener,MyListView.OnRefreshListener{

    private MyListView mInventoryLV;
    private InventoryAdapter mAdapter;
    private List<RInventory.ContentBean.BillsBean> mList=new ArrayList<>();
    List<RInventory.ContentBean.BillsBean> items = new ArrayList<>();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessageConst.ACTION_INVENTORY :
                    mInventoryLV.setAdapter(mAdapter);
                    if(mList.size()==mAdapter.mList.size()) {
                        mInventoryLV.initcomplete();
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
                case MessageConst.ACTION_INVENTORY_FAIL :
                    showToast((String)msg.obj);
                    break;
                case MessageConst.ACTION_INIT_DIALOG :
                    MyDialog.getInstance().setContext(InventoryActivity.this);
                    MyDialog.getInstance().show();
                    MyDialog.getInstance().setDisplay("载入数据中...");
                    break;
                case MessageConst.ACTION_DIALOG_DISMISS :
                    MyDialog.getInstance().dismiss();
                    break;
                case MessageConst.ACTION_INTERNET :
                    showToast("请查看网络！");
                    break;
            }
        }
    };

    @Override
    public void initUI() {
        mInventoryLV = (MyListView) findViewById(R.id.inventory_lv);
        mInventoryLV.setOnItemClickListener(this);
        mInventoryLV.setOnRefreshListener(this);

    }

    @Override
    public void initData() {
        sendAsyncTaskMessage();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_inventory;
    }

    @Override
    protected void sendAsyncTask() {
        String result=null;
        SharedPreferences preferences = getSharedPreferences("server", 1);
        String serverName = preferences.getString("serverName", "");
        String port = preferences.getString("port", "");
        String path = preferences.getString("path", "");
        HashMap<String, String> map = new HashMap<>();
        map.put("storeId", getIntent().getStringExtra("storeId"));
        map.put("businessType", "PD");

        mHandler.obtainMessage(MessageConst.ACTION_INIT_DIALOG).sendToTarget();
        if(TextUtils.isEmpty(serverName)||TextUtils.isEmpty(port)||
                TextUtils.isEmpty(path)){
            result = NetworkUtil.doPost(NetworkConst.ACTION_GETBILL, JsonUtil.map2Json(map));
        }else{
            result = NetworkUtil.doPost(NetworkConst.TITLE+serverName+NetworkConst
                    .POINT+port+NetworkConst.LINE+path+NetworkConst.END+NetworkConst.GETBILL, JsonUtil.map2Json(map));
        }

        mHandler.obtainMessage(MessageConst.ACTION_DIALOG_DISMISS).sendToTarget();
        if(TextUtils.isEmpty(result)) {
            mHandler.obtainMessage(MessageConst.ACTION_INTERNET).sendToTarget();
            return;
        }
        RInventory rInventory = JSON.parseObject(result, RInventory.class);

        if(rInventory.getMsgInfo().equals("调用成功")) {

            for (int i = rInventory.getContent().getBills().size()-1; i >=0; i--) {
                mList.add(rInventory.getContent().getBills().get(i));
            }

            initAdapter();
            mHandler.obtainMessage(MessageConst.ACTION_INVENTORY).sendToTarget();
        }else{
            mHandler.obtainMessage(MessageConst.ACTION_INVENTORY_FAIL,rInventory.getMsgInfo()).sendToTarget();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
        mAdapter.selectIndex=position;
        mAdapter.notifyDataSetChanged();
        mBuilder.setTitle("提示");
        mBuilder.setMessage("确定开始扫描 "+mAdapter.mList.get(position-1).getBillNo()+" 盘点单吗？");
        mBuilder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {// 添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {// 确定按钮的响应事件
                        Intent intent = new Intent(InventoryActivity.this,PdGoodsActivity.class);
                        intent.putExtra("billNo",mAdapter.mList.get(position-1).getBillNo());
                        startActivity(intent);
                    }
                });
        mBuilder.setNegativeButton("返回", null);
        mBuilder.show();
    }

    private void initAdapter() {
        mAdapter = new InventoryAdapter(this, items);
        int count = mAdapter.getCount();
        if(mList.size()-count<10) {
            for (int i = count; i < mList.size(); i++) {
                mAdapter.addItem(mList.get(i));
            }
        }else{
            for (int i = count; i < count + 10; i++) {
                mAdapter.addItem(mList.get(i));
            }
        }
    }

    private void loadData() {
        int count = mAdapter.getCount();
        if(mList.size()-count<10) {
            for (int i = count; i < mList.size(); i++) {
                mAdapter.addItem(mList.get(i));
            }
        }else{
            for (int i = count; i < count + 10; i++) {
                mAdapter.addItem(mList.get(i));
            }
        }
    }

    @Override
    public void onPullRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                mAdapter.notifyDataSetChanged();
                if(mList.size()==mAdapter.mList.size()) {
                    mInventoryLV.complete();
                }else{
                    mInventoryLV.completeRefresh();
                }
            }
        },2000);
    }

    @Override
    public void onLoadingMore() {
        //设置三秒延迟模仿延时获取数据
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //加载数据
                loadData();
                //更新 数据
                mAdapter.notifyDataSetChanged();
                //加载完毕
                if(mList.size()==mAdapter.mList.size()) {
                    mInventoryLV.complete();
                }else{
                    mInventoryLV.completeRefresh();
                }

            }
        },3000);
    }
}
