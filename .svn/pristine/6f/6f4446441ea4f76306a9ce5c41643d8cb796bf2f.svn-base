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

import com.setes.setesvendas.app.controller.EtdPhone;
import com.setes.setesvendas.app.model.PhoneAdapter;
import com.setes.setesvendas.app.model.PhoneHandler;

import java.util.ArrayList;
import com.setes.setesvendas.app.R;

public class ListPhoneActivity extends Activity
{

    public ListView LstItens;
    public EtdPhone itemSelect;
    public int lastSelect;

    public ListPhoneActivity()
    {
    }

    private void setAction()
    {
        LstItens.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                if (lastSelect != -1)
                {
                    unMarkPosition();
                }
                lastSelect = i;
                view.setBackgroundColor(0xff888888);
                itemSelect = (EtdPhone)adapterview.getItemAtPosition(i);
            }
        });
    }

    public void addPhone(View view)
    {
        Intent intent = new Intent(this, AddPhoneActivity.class);
        intent.putExtra("id", MainActivity.gbCustomer);
        intent.putExtra("kind", "");
        startActivity(intent);
        finish();
    }

    public void closeListPhoneActivity(View view)
    {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
        finish();
    }

    public void deletePhone(View view)
    {
        if (itemSelect != null){
            new AlertDialog.Builder(ListPhoneActivity.this)
                    .setTitle("Exclusão")
                    .setMessage("Deseja excluir este Telefone?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    PhoneHandler hdl = new PhoneHandler(ListPhoneActivity.this);
                                    hdl.delete(itemSelect);

                                }
                            }).setNegativeButton(android.R.string.no, null)
                    .show();
            loadData();
        } else
        {
            Toast.makeText(this, "Nenhum item selecionado", Toast.LENGTH_SHORT).show();
        }
    }

    public void editPhone(View view)
    {
        if (itemSelect != null)
        {
            Intent intent = new Intent(this, AddPhoneActivity.class);
            intent.putExtra("id", itemSelect.getId());
            intent.putExtra("kind", itemSelect.getKind());
            startActivity(intent);
            finish();
        } else
        {
            Toast.makeText(this, "Nenhum item selecionado", Toast.LENGTH_SHORT).show();
        }
    }

    protected void loadData()
    {
        lastSelect = -1;
        PhoneHandler hdl = new PhoneHandler(this);
        ArrayList<EtdPhone> listItems = hdl.getByCystomer(MainActivity.gbCustomer);
        LstItens.setAdapter(new PhoneAdapter(this,listItems ));

    }

    protected void loadElement()
    {

        LstItens = (ListView)findViewById(R.id.lstPhoneList);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_phone_list);
        loadElement();
        loadData();
        setAction();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.itens , menu);
        return true;
    }

    public void unMarkPosition()
    {
        LstItens.getChildAt(lastSelect).setBackgroundColor(0);
    }
}
