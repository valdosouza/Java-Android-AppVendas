package com.setes.setesvendas.app.pedidovenda;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdAddress;
import com.setes.setesvendas.app.controller.EtdEntity;
import com.setes.setesvendas.app.controller.EtdOrder;
import com.setes.setesvendas.app.controller.EtdOrderSale;
import com.setes.setesvendas.app.model.AddressHandler;
import com.setes.setesvendas.app.model.EntityHandler;
import com.setes.setesvendas.app.model.OrderHandler;
import com.setes.setesvendas.app.model.OrderMenuAdapter;
import com.setes.setesvendas.app.model.OrderSaleHandler;
import com.setes.setesvendas.app.model.OrderSaleItemHandler;
import com.setes.setesvendas.app.util.Convert;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.setes.setesvendas.app.R;

public class OrderActivity extends Activity
{
    private AlertDialog alerta;
    public static GridView gridOrderMenu;
    public TextView customer;
    public TextView trade;
    public TextView id;
    EtdOrder objOrder;
    public TextView total;
    public EditText obs;
    private View v;

    public OrderActivity()
    {
        objOrder = new EtdOrder();
    }

    private void openActivityAddress(View view)
    {
        if (MainActivity.gbCustomer > 0)
        {
            Intent intent = new Intent(OrderActivity.this, ListAddressActivity.class);
            intent.putExtra("ID", MainActivity.gbCustomer);
            intent.putExtra("KIND", "");
            startActivity(intent);
            finish();
        } else
        {
            Toast.makeText(this, "Cliente não informado", Toast.LENGTH_SHORT).show();
        }
    }

    private void openActivityCustomer(View view)
    {
        Intent intent = new Intent(OrderActivity.this, CustomerActivity.class);
        intent.putExtra("ID", MainActivity.gbCustomer);
        startActivity(intent);
        finish();
    }

    private void openActivityItensProduct(View view)
    {
        if (MainActivity.gbCustomer > 0)
        {
            Intent intent = new Intent(OrderActivity.this, ListOrderSaleItemActivity.class);
            intent.putExtra("id", MainActivity.gbNumber);
            startActivity(intent);
            finish();
        } else
        {
            Toast.makeText(this, "Cliente não informado", Toast.LENGTH_SHORT).show();
        }
    }

    private void openActivityPhone(View view)
    {
        if (MainActivity.gbCustomer > 0)
        {
            Intent intent = new Intent(OrderActivity.this, ListPhoneActivity.class);
            intent.putExtra("ID", MainActivity.gbCustomer);
            intent.putExtra("KIND", "");
            startActivity(intent);
            finish();
        } else
        {
            Toast.makeText(this, "Cliente não informado", Toast.LENGTH_SHORT).show();
        }
    }

    public void setActionIconsOrderMain(){
        gridOrderMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        openActivityCustomer(v);
                        break;
                    case 1:
                        openActivityAddress(v);
                        break;
                    case 2:
                        openActivityPhone(v);
                        break;
                    case 3:
                        openActivityItensProduct(v);
                        break;
                }
            }
        });
    }

    private void setGridIconsOrderMain()
    {
        gridOrderMenu.setAdapter(new OrderMenuAdapter(this));
    }

    public void closeOrderActivity(View view)
    {
        MainActivity.gbCustomer = 0;
        MainActivity.gbNumber = 0;
        this.finish();
    }

    protected void loadData()
    {
        //Inicia variaveis
        MainActivity.gbCustomer = 0;
        customer.setText("Cliente não informado");
        trade.setText("");

        OrderHandler hdlOrder = new OrderHandler(this);
        EtdEntity objEntity = new EtdEntity();
        EntityHandler hdlEntity = new EntityHandler(this);
        if (objOrder == null){objOrder = new EtdOrder(); }
        objOrder = hdlOrder.get(MainActivity.gbNumber);
        id.setText(String.format("%06d", new Object[]{
                Integer.valueOf(MainActivity.gbNumber)
            }));
        if (objOrder != null) {
            EtdOrderSale objOrderSale =  new EtdOrderSale();
            OrderSaleHandler hdlOrderSale = new OrderSaleHandler(this);
            objOrderSale = hdlOrderSale.get(MainActivity.gbNumber);
            if (objOrderSale != null) {
                MainActivity.gbCustomer = objOrderSale.getTb_customer_id();
                objEntity = hdlEntity.get(MainActivity.gbCustomer);
                if (objEntity != null) {
                    customer.setText(objEntity.getName_company());
                    trade.setText(objEntity.getNick_trade());
                }
            }
            OrderSaleItemHandler hdlOrderSaleItem = new OrderSaleItemHandler(this);
            Double valor = hdlOrderSaleItem.totalItemsOrder(objOrderSale.getId());
            total.setText(Convert.floatToString(valor));
            obs.setText(objOrder.getNote());
            //Finaliza
            hdlOrderSale.close();
            hdlEntity.close();
            hdlOrderSaleItem.close();
        }
        hdlOrder.close();
    }

    protected void loadElement()
    {
        id = (TextView)findViewById(R.id.EdtOrderProvisoryNumber);
        customer = (TextView)findViewById(R.id.edtOrderCustomerName);
        trade = (TextView)findViewById(R.id.edtOrderCustomerTrade);
        gridOrderMenu = (GridView)findViewById(R.id.grdordermenu);
        total = (TextView)findViewById(R.id.edtOrderValueTotal);
        obs = (EditText)findViewById(R.id.edtOrderObservacao);
    }

    public int newOrder()
    {

        return 0;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_order);
        loadElement();
        setGridIconsOrderMain();
        setActionIconsOrderMain();
        loadData();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.order, menu);
        return true;
    }

    public void saveOrder(View view)
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (objOrder == null)
        {
            objOrder = new EtdOrder();
        }
        objOrder.setId(MainActivity.gbNumber);
        objOrder.setTb_institution_id(MainActivity.gbInstitution);
        objOrder.setDt_record(formatter.format(date));
        objOrder.setNote(obs.getText().toString());
        objOrder.setStatus("N");
        OrderHandler hdlOrder = new OrderHandler(this);
        if (hdlOrder.verifyRegisterByField("tb_order", "id", Integer.toString(MainActivity.gbNumber))) {
            hdlOrder.update(objOrder);
        } else {
            hdlOrder.add(objOrder);
        }
    }

    public void saveOrderActivity(View view)
    {
        if (validaOrder())
        {
            saveOrder(view);
            saveOrderSale(view);
            Toast.makeText(this, "Informação Atualizada", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void saveOrderSale(View view)
    {
        EtdOrderSale obj = new EtdOrderSale();
        obj.setId(MainActivity.gbNumber);
        obj.setTb_institution_id(MainActivity.gbInstitution);
        obj.setTb_salesman_id(MainActivity.gbSalesman);
        obj.setTb_customer_id(MainActivity.gbCustomer);
        obj.setNumber(MainActivity.gbNumber);
        OrderSaleHandler hdl = new OrderSaleHandler(this);
        if (hdl.verifyRegisterByField("tb_order_sale", "id", Integer.toString(MainActivity.gbNumber)))
        {
            hdl.update(obj);
        } else
        {
            hdl.add(obj);
        }
    }

    public boolean validaOrder()
    {
        EtdAddress objAddress = new EtdAddress();
        AddressHandler hdlAddress = new AddressHandler(OrderActivity.this);
        objAddress = hdlAddress.get(MainActivity.gbCustomer,"Comercial");
        if (objAddress == null){
            //LayoutInflater é utilizado para inflar nosso layout em uma view.
            // -pegamos nossa instancia da classe
            LayoutInflater li = getLayoutInflater();
            //inflamos o layout alerta.xml na view
            View view =   li.inflate(R.layout.warning_msg, null);
            //definimos para o botão do layout um clickListener
            view.findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                //exibe um Toast informativo.
                // Toast.makeText(MainActivity.this, "alerta.dismiss()", Toast.LENGTH_SHORT).show();
                // desfaz o alerta.
                alerta.dismiss(); } });

            TextView textView = (TextView)view.findViewById(R.id.edtMsg);
            textView.setText("O Cliente precisa ter um endereço.\nPor favor Verifique!!");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Mensagem de Alerta");
            builder.setView(view);
            alerta = builder.create();
            alerta.show();
            return false;




        }


        return true;
    }




}
