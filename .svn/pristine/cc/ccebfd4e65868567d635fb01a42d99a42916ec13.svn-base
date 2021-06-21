package com.setes.setesvendas.app.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.setes.setesvendas.app.R;
import com.setes.setesvendas.app.controller.EtdState;

import java.util.ArrayList;

public class StateAdapter extends BaseAdapter
{
    private ArrayList<EtdState> itemList;
    private LayoutInflater mInflater;
    private ViewHolder holder;

    static class ViewHolder{
        private TextView id;
        private TextView description;
    }

    public StateAdapter(Context context, ArrayList<EtdState> items) {
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

    public EtdState getObj(int i) {

        return (EtdState) itemList.get(i);
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
        EtdState obj = itemList.get(position);
        holder.description.setText(obj.getSigla() + " - " + obj.getName());

        return convertView;
    }

}