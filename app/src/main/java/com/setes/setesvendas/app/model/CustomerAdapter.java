package com.setes.setesvendas.app.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.setes.setesvendas.app.R;
import com.setes.setesvendas.app.controller.EtdCustomer;
import com.setes.setesvendas.app.controller.EtdEntity;

import java.util.ArrayList;

public class CustomerAdapter extends BaseAdapter
{

    ArrayList<EtdEntity> itemList;
    Context context;
    char kindnamecompany;

    public CustomerAdapter(Context context1, ArrayList arraylist, char KindNameCompany)
    {
        context = context1;
        itemList = arraylist;
        kindnamecompany = KindNameCompany;
    }

    public int getCount()
    {
        return itemList.size();
    }

    public Object getItem(int i)
    {
        return itemList.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }


    public View getView(int position, View convertView, ViewGroup arg2) {
        EtdEntity obj = itemList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_customer, null);
        }

        TextView edtListCustomerName = (TextView) convertView.findViewById(R.id.edtListCustomerName);
        if ( kindnamecompany == 'C') {
            edtListCustomerName.setText(obj.getName_company());
        }else{
            edtListCustomerName.setText(obj.getNick_trade());
        }
       return convertView;

    }
}
