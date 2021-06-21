package com.setes.setesvendas.app.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.setes.setesvendas.app.R;
import com.setes.setesvendas.app.controller.EtdAddress;
import com.setes.setesvendas.app.controller.EtdCity;
import com.setes.setesvendas.app.controller.EtdState;

import java.util.ArrayList;

public class AddressAdapter extends BaseAdapter
{

    ArrayList<EtdAddress> addressList;
    Context context;

    public AddressAdapter(Context context1, ArrayList arraylist)
    {
        context = context1;
        addressList = arraylist;
    }

    public int getCount()
    {

        return addressList.size();
    }

    public Object getItem(int i)
    {

        return addressList.get(i);
    }

    public long getItemId(int i)
    {

        return (long)i;
    }

    public View getView(int position, View convertView, ViewGroup arg2) {
        EtdAddress obj = addressList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_address, null);
        }

        TextView edtListAddress = (TextView) convertView.findViewById(R.id.edtListAddress);
        edtListAddress.setText("Endereco: " + obj.getStreet() + ", "  +obj.getNmbr());

        TextView edtListNeighborhood = (TextView) convertView.findViewById(R.id.edtListNeighborhood);
        edtListNeighborhood.setText("Bairro: " + obj.getNeighborhood());

        EtdCity objCity = new EtdCity();
        CityHandler hdlCity = new CityHandler( context);
        objCity = hdlCity.get(obj.getTb_city_id());
        TextView edtListCity = (TextView) convertView.findViewById(R.id.edtListCity);
        edtListCity.setText("Cidade: " + objCity.getName());

        EtdState objState = new EtdState();
        StateHandler hdlState = new StateHandler( context);
        objState = hdlState.get(obj.getTb_state_id());
        TextView edtListState = (TextView) convertView.findViewById(R.id.edtListState);
        edtListState.setText("Estado: " + objState.getName());

        return convertView;
    }

}
