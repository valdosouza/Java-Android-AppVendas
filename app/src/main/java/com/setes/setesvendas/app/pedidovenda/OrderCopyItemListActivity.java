package com.setes.setesvendas.app.pedidovenda;
        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.setes.setesvendas.app.R;
        import com.setes.setesvendas.app.controller.EtdOrder;
        import com.setes.setesvendas.app.controller.EtdOrderSale;
        import com.setes.setesvendas.app.controller.EtdOrderSaleItem;
        import com.setes.setesvendas.app.model.OrderCopyItemAdapter;
        import com.setes.setesvendas.app.model.OrderHandler;
        import com.setes.setesvendas.app.model.OrderSaleHandler;
        import com.setes.setesvendas.app.model.OrderSaleItemHandler;
        import com.setes.setesvendas.app.util.Convert;

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;

public class OrderCopyItemListActivity extends Activity
{

    public ListView LstOrderItens;
    public EtdOrderSaleItem itemSelect;
    public int lastSelect;
    public TextView total;
    public int tbOrderId;
    public int nextOrder;

    public OrderCopyItemListActivity()
    {
    }

    private void setAction()
    {
        LstOrderItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView adapterview, View view, int i, long l) {
                if (lastSelect != -1) {
                    unMarkPosition();
                }
                lastSelect = i;
                view.setBackgroundColor(0xff888888);
                itemSelect = (EtdOrderSaleItem) adapterview.getItemAtPosition(i);
            }
        });
    }


    public void closeListOrderCopyItemActivity(View view)
    {
        this.finish();
    }

    protected void loadData()
    {
        lastSelect = -1;
        OrderSaleItemHandler hdl = new OrderSaleItemHandler(this);
        ArrayList<EtdOrderSaleItem> listItens = hdl.getItensOrderSale(tbOrderId);
        LstOrderItens.setAdapter(new OrderCopyItemAdapter(this, listItens));
        Double valor = hdl.totalItemsOrder(tbOrderId);
        total.setText(Convert.floatToString(valor));
    }
    public boolean validaCopy(){

        View v;
        boolean check = false;
        CheckBox chb;
        for (int i = 0; i < LstOrderItens.getChildCount(); i++) {
            v = LstOrderItens.getChildAt(i);
            chb = (CheckBox) v.findViewById(R.id.chbItenOrdemCopyItem);
            if (chb.isChecked()) {
                check = true;
            }
        }
        if (!check){
            Toast.makeText(this, "Nenhum Item Selecionado", Toast.LENGTH_SHORT).show();
        }

        return check;
    }

    public void executCopyOrder(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        OrderHandler hdl = new OrderHandler(this);
        EtdOrder obj = new EtdOrder();
        obj = hdl.get(tbOrderId);
        nextOrder = hdl.getNexId("tb_order");

        //Insere Pedido
        obj.setId(nextOrder);
        obj.setDt_record(formatter.format(date));
        obj.setStatus("N");
        hdl.add(obj);
        hdl.close();
    }

    public void executCopyOrderSale(){
        EtdOrderSale obj = new EtdOrderSale();
        OrderSaleHandler hdl = new OrderSaleHandler(this);
        obj = hdl.get(tbOrderId);
        //Altera o codigo da Ordem
        obj.setId(nextOrder);
        //Insere o Pedido de Venda
        hdl.add(obj);
        hdl.close();
    }

    public void executCopyItems(){
        OrderSaleItemHandler hdl = new OrderSaleItemHandler(this);
        EtdOrderSaleItem obj = new EtdOrderSaleItem();
        TextView description;
        TextView qtty;
        TextView unitValue;
        View v;
        String Msg = "";
        CheckBox chb;
        for (int i = 0; i < LstOrderItens.getChildCount(); i++) {
            v = LstOrderItens.getChildAt(i);
            chb = (CheckBox) v.findViewById(R.id.chbItenOrdemCopyItem);
            if (chb.isChecked()) {
                //Pega os campos da lista
                description = (TextView) v.findViewById(R.id.edtDescriptionOrdemCopyItem);
                qtty = (TextView) v.findViewById(R.id.edtQttyOrdemCopyItem);
                unitValue = (TextView) v.findViewById(R.id.edtUnitValueOrdemCopyItem);
                //Preenche o Objeto
                obj.setId(hdl.getNexId("tb_order_sale_item"));
                obj.setTb_institution_id(MainActivity.gbInstitution);
                obj.setTb_order_sale_id(nextOrder);
                obj.setTb_salesman_id(MainActivity.gbSalesman);
                obj.setTb_product_id( (Integer) description.getTag());
                obj.setItem(1);
                obj.setQtty(Double.parseDouble(qtty.getText().toString().replace(",", ".")));
                obj.setUnit_value(Double.parseDouble(unitValue.getText().toString().replace(",", ".")));
                obj.setDiscount_aliquot(0.0D);
                obj.setDiscount_value(0.0D);
                hdl.add(obj);
            }
        }
        hdl.close();;    }

    public void executCopy(){
        executCopyOrder();
        executCopyOrderSale();
        executCopyItems();
    }

    public void copyOrder(View view)
    {
        if (validaCopy()){
            executCopy();
            Toast.makeText(this, "Cópia Efetuado Com Sucesso", Toast.LENGTH_LONG).show();
        }
    }

    protected void loadElement()
    {
        LstOrderItens = (ListView)findViewById(com.setes.setesvendas.app.R.id.lstOrdemItemList);
        total =  (TextView)findViewById(com.setes.setesvendas.app.R.id.edtOrderValueTotal);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(com.setes.setesvendas.app.R.layout.activity_order_copy_item_list);
        getWindow().setSoftInputMode(3);
        tbOrderId = getIntent().getExtras().getInt("tbOrderId");
        loadElement();
        loadData();
        setAction();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }

    public void unMarkPosition()
    {

        LstOrderItens.getChildAt(lastSelect).setBackgroundColor(0);
    }
}

