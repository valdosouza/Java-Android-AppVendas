package com.setes.setesvendas.app.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.setes.setesvendas.app.R;
import com.setes.setesvendas.app.controller.EtdCountry;

import java.util.ArrayList;

public class CountryAdapter extends BaseAdapter {

    private ArrayList<EtdCountry> itemList;
    private LayoutInflater mInflater;
    Context context;

    public CountryAdapter(Context context, ArrayList arraylist) {
        mInflater = LayoutInflater.from(context);
        itemList = arraylist;
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

    public EtdCountry getObj(int i) {

        return (EtdCountry) itemList.get(i);
    }

    public View getView(int position, View convertView, ViewGroup arg2) {
        EtdCountry obj = itemList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.single_choice, null);
        }

        TextView edtListRadioView = (TextView) convertView.findViewById(R.id.edtListRadioView);
        edtListRadioView.setText(obj.getName());

        return convertView;

    }

}
