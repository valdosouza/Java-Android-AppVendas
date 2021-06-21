package com.setes.setesvendas.app.model;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;
        import com.setes.setesvendas.app.R;
        import com.setes.setesvendas.app.controller.EtdOrderSaleItem;
        import com.setes.setesvendas.app.controller.EtdProduct;
        import com.setes.setesvendas.app.util.Convert;

        import java.util.ArrayList;


public class OrderCopyItemAdapter extends BaseAdapter
{

    Context context;
    ArrayList<EtdOrderSaleItem> itemList;

    public OrderCopyItemAdapter(Context context1, ArrayList arraylist)
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
        EtdOrderSaleItem obj = itemList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_copy_item_list, null);
        }
        //Pega o produto
        EtdProduct objProduct = new EtdProduct();
        ProductHandler hdlProduct = new ProductHandler(context);
        objProduct = hdlProduct.get(obj.getTb_product_id());


        TextView edtDescriptionOrdemItem = (TextView) convertView.findViewById(R.id.edtDescriptionOrdemCopyItem);
        edtDescriptionOrdemItem.setText(objProduct.getDescription());
        edtDescriptionOrdemItem.setTag(objProduct.getId());

        TextView edtQttyOrdemItem = (TextView) convertView.findViewById(R.id.edtQttyOrdemCopyItem);
        edtQttyOrdemItem.setText(Convert.floatToString(obj.getQtty()));

        TextView edtUnitValueOrdemItem = (TextView) convertView.findViewById(R.id.edtUnitValueOrdemCopyItem);
        edtUnitValueOrdemItem.setText(Convert.floatToString(obj.getUnit_value()));

        TextView edtSubtotalOrdemItem = (TextView) convertView.findViewById(R.id.edtSubtotalOrdemCopyItem);
        edtSubtotalOrdemItem.setText(Convert.floatToString(obj.getUnit_value() * obj.getQtty()));


        return convertView;

    }
}