package com.setes.setesvendas.app.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.setes.setesvendas.app.R;
import com.setes.setesvendas.app.controller.EtdPrice;
import com.setes.setesvendas.app.controller.EtdProduct;
import com.setes.setesvendas.app.util.Convert;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter
{

    Context context;
    ArrayList<EtdProduct> itemList;

    public ProductAdapter(Context context1, ArrayList arraylist)
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
        EtdProduct obj = itemList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_product, null);
        }

        TextView edtSearchProductDescription = (TextView) convertView.findViewById(R.id.edtSearchProductDescription);
        edtSearchProductDescription.setText(obj.getDescription());
        //pega o objeto preco
        TextView edtListSearchProductPrice = (TextView) convertView.findViewById(R.id.edtListSearchProductPrice);
        EtdPrice objPrice = new EtdPrice();
        PriceHandler hdlPrice = new PriceHandler(context);
        objPrice = hdlPrice.get(obj.getId());
        if (objPrice != null) {
            edtListSearchProductPrice.setText(Convert.floatToString(objPrice.getPrice_tag()));
        }else{
            edtListSearchProductPrice.setText("0,00");
        }
        hdlPrice.close();
        return convertView;

    }

}
