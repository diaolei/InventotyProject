package www.zhaoxinkeji.com.inventotyproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.bean.RInventory;

/**
 * Created by 27631 on 2018/3/6.
 */

public class CheckReceiveAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    public List<RInventory.ContentBean.BillsBean> mList;
    public int selectIndex=-1;

    public CheckReceiveAdapter(Context context, List<RInventory.ContentBean.BillsBean> list){
        mContext=context;
        mInflater=LayoutInflater.from(mContext);
        mList=list;
    }

    @Override
    public int getCount() {
        return mList!=null?mList.size():0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null) {
            convertView=mInflater.inflate(R.layout.item_inventory,parent,false);
            holder=new ViewHolder();
            holder.billCode= (TextView) convertView.findViewById(R.id.bill_tv);
            holder.venderName= (TextView) convertView.findViewById(R.id.bill_time_tv);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.billCode.setText(mList.get(position).getBillNo());
        holder.venderName.setText(mList.get(position).getVenderName());

        if( selectIndex == position+1 ){
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
        }else{
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.white1));
        }
        return convertView;
    }

    public class ViewHolder{
        public TextView billCode;
        public TextView venderName;
    }

    public void addItem(RInventory.ContentBean.BillsBean item) {
        mList.add(item);
    }
}
