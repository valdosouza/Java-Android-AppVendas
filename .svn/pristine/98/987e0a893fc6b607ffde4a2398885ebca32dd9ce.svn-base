package com.setes.setesvendas.app.pedidovenda;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdConfig;
import com.setes.setesvendas.app.controller.EtdOrder;
import com.setes.setesvendas.app.model.ConfigHandler;
import com.setes.setesvendas.app.model.OrderAdapter;
import com.setes.setesvendas.app.model.OrderHandler;
import com.setes.setesvendas.app.model.OrderSaleHandler;
import com.setes.setesvendas.app.model.OrderSaleItemHandler;
import com.setes.setesvendas.app.util.Mask;
import com.setes.setesvendas.app.R;
import java.util.ArrayList;


public class SearchOrderActivity extends Activity
{

    public EditText EdtSearchCustomer;
    public EditText EdtSearchData;
    public EditText EdtSearchNumber;
    public ListView LstOrder;

    public EtdOrder itemSelect;
    public int lastSelect;
    public int maxRegs;

    public int progresso;


    public SearchOrderActivity()
    {
        maxRegs = 0;
        progresso = 0;
    }

    private void setAction()
    {
        LstOrder.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView adapterview, View view, int i, long l) {
                if (lastSelect != -1) {
                    unMarkPosition();
                }
                lastSelect = i;
                view.setBackgroundColor(0xff888888);
                itemSelect = (EtdOrder) adapterview.getItemAtPosition(i);
            }
        });
    }

    public void closeSearchOrderActivity(View view)
    {
        finish();
    }

    public void deleteOrder(View view)
    {
        if (itemSelect != null){
            new AlertDialog.Builder(SearchOrderActivity.this)
                    .setTitle("Exclusão")
                    .setMessage("Deseja excluir este Pedido?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //Order
                                    OrderHandler hdl = new OrderHandler(SearchOrderActivity.this);
                                    hdl.delete(itemSelect);
                                    //Order - Sale
                                    OrderSaleHandler hdlOrderSale = new OrderSaleHandler(SearchOrderActivity.this);
                                    hdlOrderSale.deleteById(itemSelect.getId());
                                    //Order - Item Sale
                                    OrderSaleItemHandler hdlOrderSaleItem = new OrderSaleItemHandler(SearchOrderActivity.this);
                                    hdlOrderSaleItem.deleteByOrderId(itemSelect.getId());
                                    loadData();
                                }
                            }).setNegativeButton(android.R.string.no, null)
                    .show();
        } else
        {
            Toast.makeText(this, "Nenhum item selecionado", Toast.LENGTH_SHORT).show();
        }
    }

    public void OpenCopyOrder(View view)
    {
        if (itemSelect != null)
        {
            Intent intent = new Intent(this, OrderCopyItemListActivity.class);
            intent.putExtra("tbOrderId", itemSelect.getId());
            startActivity(intent);
        } else
        {
            Toast.makeText(this, "Nenhum item selecionado", Toast.LENGTH_SHORT).show();
        }
    }

    public void listers()
    {
        EdtSearchData.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable)
            {
                if (editable.length() == 10)
                {
                    loadData();
                }
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }
        });
        EdtSearchNumber.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable)
            {
                loadData();
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }
        });
        EdtSearchCustomer.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable)
            {
                loadData();
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }
        });
    }

    protected void loadData()
    {
        lastSelect = -1;
        OrderHandler hdl = new OrderHandler(this);
        ArrayList<EtdOrder> listItems = hdl.getSearch(EdtSearchData.getText().toString(), EdtSearchNumber.getText().toString(), EdtSearchCustomer.getText().toString());
        LstOrder.setAdapter(new OrderAdapter(this, listItems));

    }

    protected void loadElement()
    {
        EdtSearchData = (EditText)findViewById(R.id.edtSearchDateOrder);
        EdtSearchData.addTextChangedListener(Mask.insert("##/##/####", EdtSearchData));
        EdtSearchNumber = (EditText)findViewById(R.id.edtSearchNumberOrder);
        EdtSearchCustomer = (EditText)findViewById(R.id.edtSearchCustomerOrder);
        LstOrder = (ListView)findViewById(R.id.lstOrderListt);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search_order);
        loadElement();
        setAction();
        listers();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }

    public boolean pathWebService()
    {
        boolean flag = true;
        MainActivity.gbWbsPath = "";
        ConfigHandler confighandler = new ConfigHandler(this);
        EtdConfig etdconfig = confighandler.get("WBS_PATH");
        if (etdconfig != null)
        {
            MainActivity.gbWbsPath = etdconfig.getContent();
        }
        etdconfig = confighandler.get("GNL_INSTITUTION");
        if (etdconfig != null)
        {
            MainActivity.gbInstitution = Integer.parseInt(etdconfig.getContent());
        } else
        {
            MainActivity.gbInstitution = 1;
        }
        etdconfig = confighandler.get("GNL_SALESMAN");
        if (etdconfig != null)
        {
            MainActivity.gbSalesman = Integer.parseInt(etdconfig.getContent());
        } else
        {
            MainActivity.gbSalesman = 1;
        }
        confighandler.close();
        if (MainActivity.gbWbsPath.equals(""))
        {
            flag = false;
        }
        return flag;
    }

    public void selectItemOrder(View view)
    {
        if (itemSelect != null)
        {
            Intent intent = new Intent(this, OrderActivity.class);
            MainActivity.gbNumber = itemSelect.getId();
            startActivity(intent);
        } else
        {
            Toast.makeText(this, "Nenhum item selecionado", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendOrderWsByOne(View view)
    {
        if (itemSelect != null){
            Intent intent = new Intent(this, SendWsActivityByOne.class);
            intent.putExtra("tbOrderId", itemSelect.getId());
            startActivity(intent);
        } else
        {
            Toast.makeText(this, "Nenhum item selecionado", Toast.LENGTH_SHORT).show();
        }
    }

    public void unMarkPosition()
    {
        LstOrder.getChildAt(lastSelect).setBackgroundColor(0);
    }

    public boolean validaReceiveWebservice()
    {
        if (!pathWebService())
        {
            Toast.makeText(this, "WebService não definido", Toast.LENGTH_SHORT).show();
            return false;
        } else
        {
            return true;
        }
    }
}
