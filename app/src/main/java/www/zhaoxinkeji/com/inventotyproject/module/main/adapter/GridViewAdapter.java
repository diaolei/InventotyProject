package www.zhaoxinkeji.com.inventotyproject.module.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.module.main.bean.MainItemBean;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<MainItemBean> list;

    public GridViewAdapter(Context context, List<MainItemBean> list) {
        mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
            holder = new GridViewAdapter.ViewHolder();
            holder.main_iv = convertView.findViewById(R.id.main_iv);
            holder.main_tv = convertView.findViewById(R.id.main_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.main_iv.setImageResource(list.get(position).getResID());
        holder.main_tv.setText(list.get(position).getIconName());

        return convertView;
    }

    private class ViewHolder {
        private ImageView main_iv;
        private TextView main_tv;
    }
}
