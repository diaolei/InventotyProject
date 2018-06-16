package www.zhaoxinkeji.com.inventotyproject.module.main.presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import www.zhaoxinkeji.com.inventotyproject.R;
import www.zhaoxinkeji.com.inventotyproject.app.AppConstant;
import www.zhaoxinkeji.com.inventotyproject.base.BasePresenter;
import www.zhaoxinkeji.com.inventotyproject.module.main.bean.MainItemBean;
import www.zhaoxinkeji.com.inventotyproject.module.main.contract.MainContract;
import www.zhaoxinkeji.com.inventotyproject.utils.SharePreferanceUtil;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {

    public MainPresenter(MainContract.View rootView) {
        super(rootView);
    }

    /**
     * 初始化数据集合
     *
     * @param list
     */
    public void initDataSetting(List<MainItemBean> list) {
        //根据登录的值显示不同的数据集合
        int userType = (int) SharePreferanceUtil.getSpUtil().get_sp(AppConstant.USER_TYPE, 0);
        switch (userType) {
            case 1://供应商
                list.add(new MainItemBean(R.drawable.collect, "采购出库"));
                break;
            case 2://企业仓库
                list.add(new MainItemBean(R.drawable.collect, "采购收货"));
                list.add(new MainItemBean(R.drawable.collect, "调拨收货"));
                list.add(new MainItemBean(R.drawable.collect, "生产入库"));
                list.add(new MainItemBean(R.drawable.collect, "销售出库"));
                list.add(new MainItemBean(R.drawable.collect, "调拨出库"));
                list.add(new MainItemBean(R.drawable.collect, "退货入库"));
                list.add(new MainItemBean(R.drawable.collect, "拆托"));
                list.add(new MainItemBean(R.drawable.collect, "拼托"));
                list.add(new MainItemBean(R.drawable.collect, "替换"));
                break;
            case 3://经销商
                list.add(new MainItemBean(R.drawable.collect, "收货入库"));
                list.add(new MainItemBean(R.drawable.collect, "销售出库"));
                list.add(new MainItemBean(R.drawable.collect, "退货入库"));
                list.add(new MainItemBean(R.drawable.collect, "退货出库"));
                list.add(new MainItemBean(R.drawable.collect, "盘点"));
                break;
        }
    }

    /**
     * 初始化gridview
     *
     * @param menuGv
     * @param list
     */
    public void initGridView(GridView menuGv, List<MainItemBean> list) {
        menuGv.setAdapter(new GridViewAdapter(list));
    }


    class GridViewAdapter extends BaseAdapter {
        private List<MainItemBean> list;

        public GridViewAdapter(List<MainItemBean> list) {
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
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
                holder = new ViewHolder();
                holder.main_iv = convertView.findViewById(R.id.main_iv);
                holder.main_tv = (TextView) convertView.findViewById(R.id.main_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.main_iv.setImageResource(list.get(position).getResID());
            holder.main_tv.setText(list.get(position).getIconName());

            return null;
        }

        class ViewHolder {
            private ImageView main_iv;
            private TextView main_tv;
        }
    }
}
