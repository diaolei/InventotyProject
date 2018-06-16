package www.zhaoxinkeji.com.inventotyproject.module.purchaseReceive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import www.zhaoxinkeji.com.inventotyproject.R;

public class PurchaseReceiveAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    public List<String> mList;

    public PurchaseReceiveAdapter(Context context, List<String> list){
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
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null) {
            convertView=mInflater.inflate(R.layout.item_purchase_out_goods,parent,false);
            holder=new ViewHolder();
            holder.billCode= (TextView) convertView.findViewById(R.id.barcode_tv);
            holder.state= (TextView) convertView.findViewById(R.id.state_tv);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.billCode.setText("订单");
        holder.state.setText("状态");

        return convertView;
    }

    public class ViewHolder{
        public TextView billCode;
        public TextView state;
    }
}
