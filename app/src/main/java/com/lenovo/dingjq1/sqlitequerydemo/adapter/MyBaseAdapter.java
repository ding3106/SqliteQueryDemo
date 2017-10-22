package com.lenovo.dingjq1.sqlitequerydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lenovo.dingjq1.sqlitequerydemo.R;
import com.lenovo.dingjq1.sqlitequerydemo.com.imooc.bean.Person;

import java.util.List;

/**
 * Created by dingjq on 2017/10/20.
 */

public class MyBaseAdapter extends BaseAdapter {

    private Context context;
    private List<Person> list;

    public MyBaseAdapter(Context context, List<Person> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<Person> list) {
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_age = (TextView) convertView.findViewById(R.id.tv_age);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tv_id.setText(list.get(position).get_id()+"");
        viewHolder.tv_name.setText(list.get(position).getName());
        viewHolder.tv_age.setText(list.get(position).getAge()+"");

        return convertView;
    }

    static class ViewHolder {
        TextView tv_id, tv_name, tv_age;

    }
}
