package com.setes.setesvendas.app.pedidovenda;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdOrderSaleItem;
import com.setes.setesvendas.app.model.OrderSaleItemAdapter;
import com.setes.setesvendas.app.model.OrderSaleItemHandler;

import java.util.ArrayList;
import com.setes.setesvendas.app.R;
import com.setes.setesvendas.app.util.Convert;

public class ListOrderSaleItemActivity extends Activity
{

    public ListView LstOrderItens;
    public EtdOrderSaleItem itemSelect;
    public int lastSelect;
    public TextView total;

    public ListOrderSaleItemActivity()
    {
    }

    private void setAction()
    {
        LstOrderItens.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                if (lastSelect != -1)
                {
                    unMarkPosition();
                }
                lastSelect = i;
                view.setBackgroundColor(0xff888888);
                itemSelect = (EtdOrderSaleItem)adapterview.getItemAtPosition(i);
            }
        });
    }

    public void addProduct(View view)
    {
        Intent intent = new Intent(ListOrderSaleItemActivity.this,AddOrderSaleItemActivity.class);
        intent.putExtra("id", 0);
        intent.putExtra("tb_product_id", 0);
        startActivity(intent);
        this.finish();
    }

    public void closeListOrderItemActivity(View view)
    {
        Intent intent = new Intent(ListOrderSaleItemActivity.this,OrderActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void deleteProduct(View view)
    {
        if (itemSelect != null)
        {
            new AlertDialog.Builder(ListOrderSaleItemActivity.this)
                    .setTitle("Exclusão")
                    .setMessage("Deseja excluir este Item do Pedido?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    OrderSaleItemHandler hdl = new OrderSaleItemHandler(ListOrderSaleItemActivity.this);
                                    hdl.delete(itemSelect);
                                    loadData();
                                }
                            }).setNegativeButton(android.R.string.no, null)
                    .show();
        } else
        {
            Toast.makeText(this, "Nenhum item selecionado", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void editProduct(View view)
    {
        if (itemSelect != null)
        {
            Intent intent = new Intent(this, AddOrderSaleItemActivity.class);
            intent.putExtra("id", itemSelect.getId());
            intent.putExtra("tb_product_id", 0);
            startActivity(intent);
            finish();
            return;
        } else
        {
            Toast.makeText(this, "Nenhum item selecionado", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    protected void loadData()
    {
        lastSelect = -1;
        OrderSaleItemHandler hdl = new OrderSaleItemHandler(this);
        ArrayList<EtdOrderSaleItem> listItens = hdl.getItensOrderSale(MainActivity.gbNumber);
        LstOrderItens.setAdapter(new OrderSaleItemAdapter(this, listItens));
        Double valor = hdl.totalItemsOrder(MainActivity.gbNumber);
        total.setText(Convert.floatToString(valor));
    }

    protected void loadElement()
    {
        LstOrderItens = (ListView)findViewById(R.id.lstOrdemItemList);
        total =  (TextView)findViewById(R.id.edtOrderValueTotal);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_order_sale_item_list);
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
