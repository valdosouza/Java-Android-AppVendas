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
import android.widget.Toast;
import com.setes.setesvendas.app.controller.EtdAddress;
import com.setes.setesvendas.app.model.AddressAdapter;
import com.setes.setesvendas.app.model.AddressHandler;

import java.util.ArrayList;
import com.setes.setesvendas.app.R;

public class ListAddressActivity extends Activity
{

    public ListView LstAddress;
    public EtdAddress itemSelect;
    public int lastSelect;

    public ListAddressActivity()
    {
    }

    private void setAction()
    {
        LstAddress.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                if (lastSelect != -1)
                {
                    unMarkPosition();
                }
                lastSelect = i;
                view.setBackgroundColor(0xff888888);
                itemSelect = (EtdAddress)adapterview.getItemAtPosition(i);
            }
        });
    }

    public void addAddress(View view)
    {
        Intent intent = new Intent(ListAddressActivity.this, AddAddressActivity.class);
        intent.putExtra("id", MainActivity.gbCustomer);
        intent.putExtra("kind", "");
        startActivity(intent);
        finish();
    }

    public void closeListAddressActivity(View view)
    {
        Intent intent = new Intent(ListAddressActivity.this, OrderActivity.class);
        startActivity(intent);
        finish();
    }

    public void deleteAddress(View view)
    {
        if (itemSelect != null){
            new AlertDialog.Builder(ListAddressActivity.this)
                    .setTitle("Exclusão")
                    .setMessage("Deseja excluir este endereço?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    AddressHandler hdl = new AddressHandler(ListAddressActivity.this);
                                    hdl.delete(itemSelect.getId(), itemSelect.getKind());
                                    loadData();
                                }
                            }).setNegativeButton(android.R.string.no, null)
                    .show();
        } else
        {
            Toast.makeText(this, "Nenhum item selecionado", Toast.LENGTH_SHORT).show();
        }
    }

    public void editAddress(View view)
    {
        if (itemSelect != null)
        {
            Intent intent = new Intent(ListAddressActivity.this, AddAddressActivity.class);
            intent.putExtra("id", itemSelect.getId());
            intent.putExtra("kind", itemSelect.getKind());
            startActivity(intent);
            finish();
        } else
        {
            Toast.makeText(this, "Nenhum item selecionado", Toast.LENGTH_SHORT ).show();
        }
    }

    protected void loadData()
    {
        lastSelect = -1;
        AddressHandler hdl = new AddressHandler(this);
        ArrayList<EtdAddress> listItems = hdl.getByCustomer(MainActivity.gbCustomer);
        LstAddress.setAdapter(new AddressAdapter(this,listItems ));
        hdl.close();
    }

    protected void loadElement()
    {
        LstAddress = (ListView)findViewById(R.id.lstAddressList);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_address_list);
        loadElement();
        loadData();
        setAction();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.itens, menu);
        return true;
    }

    public void unMarkPosition()
    {
        LstAddress.getChildAt(lastSelect).setBackgroundColor(0);
    }
}
