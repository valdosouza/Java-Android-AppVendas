package com.setes.setesvendas.app.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.setes.setesvendas.app.R;
import com.setes.setesvendas.app.controller.EtdPhone;
import com.setes.setesvendas.app.util.Mask;


import java.util.ArrayList;

public class PhoneAdapter extends BaseAdapter
{

    Context context;
    ArrayList<EtdPhone> itemList;

    public PhoneAdapter(Context context1, ArrayList<EtdPhone> arraylist)
    {
        context = context1;
        itemList = arraylist;
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
        EtdPhone obj = itemList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_phone, null);
        }

        TextView itemLstKindPhone = (TextView) convertView.findViewById(R.id.itemLstKindPhone);
        itemLstKindPhone.setText("Tipo do Telefone: " + obj.getKind());

        TextView itemLstPhoneNumber = (TextView) convertView.findViewById(R.id.itemLstPhoneNumber);
        itemLstPhoneNumber.setText("Número do Telefone: " + Mask.addMaskTelefone(obj.getNumber()));

        return convertView;

    }
}
