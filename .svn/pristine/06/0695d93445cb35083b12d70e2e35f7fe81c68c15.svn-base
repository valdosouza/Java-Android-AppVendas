package com.setes.setesvendas.app.model;

import android.content.Context;
import android.content.Entity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.setes.setesvendas.app.R;
import com.setes.setesvendas.app.controller.EtdEntity;
import com.setes.setesvendas.app.controller.EtdOrder;
import com.setes.setesvendas.app.controller.EtdOrderSale;
import com.setes.setesvendas.app.util.Convert;

import java.text.Format;
import java.util.ArrayList;

// Referenced classes of package com.setes.model:
//            OrderSaleHandler, EntityHandler

public class OrderAdapter extends BaseAdapter
{

    private ArrayList<EtdOrder> itemList;
    private LayoutInflater mInflater;
    Context context;

    public OrderAdapter(Context context1, ArrayList arraylist)
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
        EtdOrder obj = itemList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_order, null);
        }

        TextView edtDateOrderList = (TextView) convertView.findViewById(R.id.edtDateOrderList);
        edtDateOrderList.setText(Convert.StrDateToStrDateBR(obj.getDt_record()) );

        TextView edtNumberOrderList = (TextView) convertView.findViewById(R.id.edtNumberOrderList);
        edtNumberOrderList.setText(String.format("%06d", new Object[]{
                Integer.valueOf(obj.getId())
        }));
        //pega objeto ordem de vendas
        EtdOrderSale objOrderSale = new EtdOrderSale();
        OrderSaleHandler hdlordersale = new OrderSaleHandler(context);
        objOrderSale = hdlordersale.get(obj.getId());
        //pega o objeto nome do cliente
        EtdEntity objetd = new EtdEntity();
        EntityHandler hdledt = new EntityHandler(context);
        objetd = hdledt.get(objOrderSale.getTb_customer_id());
        TextView edtCustomerNameList = (TextView) convertView.findViewById(R.id.edtCustomerNameList);
        edtCustomerNameList.setText(objetd.getName_company());

       return convertView;

    }
}
