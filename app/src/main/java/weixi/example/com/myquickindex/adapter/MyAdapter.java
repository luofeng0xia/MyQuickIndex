package weixi.example.com.myquickindex.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import weixi.example.com.myquickindex.R;
import weixi.example.com.myquickindex.bean.Haohan;

/**
 * ListView的数据填充
 * Created by weixi on 2016/7/14.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;

    public MyAdapter(Context context, List<Haohan> list) {
        this.context = context;
        this.list = list;
    }

    private List<Haohan> list;

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
        View view = convertView;
        if (convertView == null) {
            view = View.inflate(context, R.layout.item_list, null);
        }

        ViewHolder holder = ViewHolder.getViewHolder(view);
        Haohan h = list.get(position);
        String currentLetter = h.getPinyin().charAt(0) + "";
        String str = null;
        if (position == 0) {
            str = currentLetter;
        } else {
            String lastLetter = list.get(position - 1).getPinyin().charAt(0) + "";
            if (!TextUtils.equals(currentLetter, lastLetter)) {
                str = currentLetter;
            }
        }
        holder.index.setVisibility(str==null?View.GONE:View.VISIBLE);
        holder.index.setText(currentLetter);
        holder.name.setText(h.getName());
        return view;
    }

    static class ViewHolder {
        TextView index;
        TextView name;

        public static ViewHolder getViewHolder(View view) {
            Object tag = view.getTag();
            if (tag != null) {
                return (ViewHolder) tag;
            } else {
                ViewHolder holder = new ViewHolder();
                holder.index = (TextView) view.findViewById(R.id.item_index);
                holder.name = (TextView) view.findViewById(R.id.item_name);
                view.setTag(holder);
                return holder;
            }
        }
    }
}
