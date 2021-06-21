package com.setes.setesvendas.app.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.setes.setesvendas.app.R;
import com.setes.setesvendas.app.controller.EtdCity;
import com.setes.setesvendas.app.controller.EtdState;

import java.util.ArrayList;

public class CityAdapter extends BaseAdapter {

    private ArrayList<EtdCity> itemList;
    private LayoutInflater mInflater;
    Context context;
    private ViewHolder holder;

    static class ViewHolder{
        private TextView id;
        private TextView description;
    }

    public CityAdapter(Context context, ArrayList<EtdCity> items) {
        mInflater = LayoutInflater.from(context);
        this.itemList= items;
    }


    public int getCount() {
        return itemList.size();
    }

    public Object getItem(int i) {

        return itemList.get(i);
    }

    public long getItemId(int i) {

        return (long) i;
    }

    public EtdCity getObj(int i) {

        return (EtdCity) itemList.get(i);
    }

    public View getView(int position, View convertView, ViewGroup arg2) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.single_choice, null);
            holder = new ViewHolder();
            holder.description = (TextView) convertView.findViewById(R.id.edtListRadioView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        EtdCity obj = itemList.get(position);
        holder.description.setText(obj.getName());
        return convertView;
    }

}
