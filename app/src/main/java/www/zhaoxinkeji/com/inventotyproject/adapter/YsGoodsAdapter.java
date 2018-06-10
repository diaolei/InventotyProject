package www.zhaoxinkeji.com.inventotyproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.bean.Goods;

/**
 * Created by 27631 on 2018/3/6.
 */

public class YsGoodsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    public List<Goods.ContentBean.GoodsBean> mList;
    public int selectIndex=-1;
    public int backGround=-1;

    public YsGoodsAdapter(Context context, List<Goods.ContentBean.GoodsBean> list){
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
            convertView=mInflater.inflate(R.layout.item_check_receive,parent,false);
            holder=new ViewHolder();
            holder.barcode= (TextView) convertView.findViewById(R.id.barcode_tv);
            holder.goodsname= (TextView) convertView.findViewById(R.id.goodsname_tv);
            holder.goodsnum= (TextView) convertView.findViewById(R.id.goodsnum_tv);
            holder.scannum= (TextView) convertView.findViewById(R.id.scannum_tv);
            holder.giftnum= (TextView) convertView.findViewById(R.id.gift_tv);
            holder.goodscode= (TextView) convertView.findViewById(R.id.goodscode_tv);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.barcode.setText(mList.get(position).getBarcodeid());
        holder.goodsname.setText(mList.get(position).getGoodsName());
        holder.goodsnum.setText(String.valueOf((int)(Double.parseDouble(mList.get(position).getGoodsNum()))));
        holder.scannum.setText(String.valueOf((int)(Double.parseDouble(mList.get(position).getScanNum()))));
        holder.giftnum.setText(String.valueOf((int)(Double.parseDouble(mList.get(position).getGifNum()))));
        holder.goodscode.setText(mList.get(position).getGoodsId());


        if(position==0&&backGround==0) {
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
        }else{
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.white1));
        }

        if((int)(Double.parseDouble(mList.get(position).getGoodsNum()))
                ==(int)(Double.parseDouble(mList.get(position).getScanNum()))) {
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.green));
        }

        if((int)(Double.parseDouble(mList.get(position).getGoodsNum()))
                <(int)(Double.parseDouble(mList.get(position).getScanNum()))) {
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.yellow));
        }
        return convertView;
    }

    public class ViewHolder{
        public TextView barcode;
        public TextView goodsname;
        public TextView goodsnum;
        public TextView scannum;
        public TextView giftnum;
        public TextView goodscode;
    }
}
