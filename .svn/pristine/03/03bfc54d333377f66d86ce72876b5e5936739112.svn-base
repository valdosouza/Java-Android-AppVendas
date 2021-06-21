package com.setes.setesvendas.app.pedidovenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdEntity;
import com.setes.setesvendas.app.model.CustomerAdapter;
import com.setes.setesvendas.app.model.EntityHandler;

import java.util.ArrayList;
import com.setes.setesvendas.app.R;

public class SearchCustomerActivity extends Activity
{

    public EditText Edt_searchCpfCnpj;
    public EditText Edt_searchCustomer;
    public TextView Lbl_searchCpfCnpj;
    public TextView Lbl_searchCustomer;
    public ListView LstCustomer;
    public RadioButton Rdb_person;
    public RadioGroup Rdg_KindCustomer;
    public RadioGroup Rdg_KindNameCompany;
    public RadioButton Rdb_Namechosse;
    public EtdEntity itemSelect;
    public int lastSelect;

    public SearchCustomerActivity()
    {
    }

    private void openActivityCustomer(View view)
    {
        Intent intent = new Intent(SearchCustomerActivity.this, CustomerActivity.class);
        startActivity(intent);
    }

    private void setAction()
    {

        LstCustomer.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                view.setSelected(true);
                lastSelect = i;
                itemSelect = (EtdEntity)adapterview.getItemAtPosition(i);
            }
        });
    }

    public void BackCustomer(View view)
    {
        openActivityCustomer(view);
        finish();
    }

    protected void loadData()
    {
        lastSelect = -1;
        EntityHandler hdl = new EntityHandler(this);
        Rdb_person = (RadioButton)findViewById(R.id.rdb_person);
        int tpPessoa;
        if (Rdb_person.isChecked())
        {
            tpPessoa = 0;
        } else
        {
            tpPessoa = 1;
        }
        Rdb_Namechosse = (RadioButton)findViewById(R.id.rdb_nicktrade);
        char tpKindCompany = 'T';

        if (Rdb_Namechosse.isChecked())
        {
            tpKindCompany = 'T';
        } else
        {
            tpKindCompany = 'C';
        }

        ArrayList<EtdEntity> listItems = hdl.getSearchCustomer(tpPessoa, Edt_searchCustomer.getText().toString(), Edt_searchCpfCnpj.getText().toString());
        LstCustomer.setAdapter(new CustomerAdapter(this, listItems,tpKindCompany));
    }

    protected void loadElement()
    {
        LstCustomer = (ListView)findViewById(R.id.lstSearchCustomer);
        Rdg_KindCustomer = (RadioGroup)findViewById(R.id.rgbSearchKindCustomer);
        Rdg_KindCustomer.getCheckedRadioButtonId();
        Rdg_KindCustomer.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radiogroup, int i) {
                i = radiogroup.indexOfChild((RadioButton) radiogroup.findViewById(i));
                Lbl_searchCustomer = (TextView) findViewById(R.id.lblSearchNameCustomer);
                Lbl_searchCpfCnpj = (TextView) findViewById(R.id.lblSearchCNPJCPFCustomer);
                if (i == 0) {
                    Lbl_searchCustomer.setText("Nome do Cliente");
                    Lbl_searchCpfCnpj.setText("C.P.F.");
                } else {
                    Lbl_searchCustomer.setText("Razão Social");
                    Lbl_searchCpfCnpj.setText("C.N.P.J");
                }
            }
        });
        Edt_searchCustomer = (EditText)findViewById(R.id.edtSearchCustomerName);
        Edt_searchCustomer.addTextChangedListener(new TextWatcher() {
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

        Rdg_KindNameCompany = (RadioGroup)findViewById(R.id.rgbSearchKindNameCompany);

        Rdg_KindNameCompany.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radiogroup, int i) {
                loadData();
            }
        });

        Edt_searchCpfCnpj = (EditText)findViewById(R.id.edtSearchCustomerCNPJCPF);
        Edt_searchCpfCnpj.addTextChangedListener(new TextWatcher() {
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

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search_customer);
        getWindow().setSoftInputMode(3);
        loadElement();
        setAction();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search_customer, menu);
        return true;
    }

    public void selectItemCustomer(View view)
    {
        if (itemSelect != null)
        {
            MainActivity.gbCustomer = itemSelect.getId();
            openActivityCustomer(view);
            finish();
        } else
        {
            Toast.makeText(this, "Nenhum item selecionado", Toast.LENGTH_SHORT).show();
        }
    }

}
