package com.setes.setesvendas.app.pedidovenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdPhone;
import com.setes.setesvendas.app.model.PhoneHandler;
import com.setes.setesvendas.app.util.Mask;

import java.util.ArrayList;
import java.util.List;
import com.setes.setesvendas.app.R;

public class AddPhoneActivity extends Activity
{

    int id;
    String itemKindPhoneSelect;
    String kind;
    Spinner kindPhone;
    EditText phoneNumber;

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_phone);
        getWindow().setSoftInputMode(3);
        bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        kind = bundle.getString("kind");
        loadElement();
        loadData();
        listerners();
    }

    public void cancelPhoneActivity(View view)
    {
        openActivityListPhone();
        finish();
    }

    protected void listerners()
    {
        kindPhone.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {


            public void onItemSelected(AdapterView adapterview, View view, int i, long l) {
                itemKindPhoneSelect = adapterview.getItemAtPosition(i).toString();
            }

            public void onNothingSelected(AdapterView adapterview) {
            }
        });
    }

    protected void loadData()
    {
        EtdPhone obj = new EtdPhone();
        PhoneHandler hdl = new PhoneHandler(AddPhoneActivity.this);
        obj = hdl.get(id, kind);
        if (obj != null) {
            addItemsOnPhoneKind(obj.getKind());
            phoneNumber.setText(obj.getNumber());
        }else{
            addItemsOnPhoneKind("");
        }
        hdl.close();
    }

    public void addItemsOnPhoneKind(String kind) {

        List<String> list = new ArrayList<String>();
        list.add("Fone");
        list.add("Fax");
        list.add("Celular");
        list.add("Comercial");
        list.add("Portaria");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddPhoneActivity.this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kindPhone.setAdapter(dataAdapter);

        for (int i = 0; i < kindPhone.getCount(); i++) {
            if (kindPhone.getItemAtPosition(i).equals(kind)) {
                kindPhone.setSelection(i);
                break;
            }
        }
    }

    protected void loadElement()
    {
        kindPhone = (Spinner)findViewById(R.id.spnKindPhone);
        phoneNumber = (EditText)findViewById(R.id.edtPhoneNumber);
        phoneNumber.addTextChangedListener(Mask.insert("(##)#####-####", phoneNumber));
    }



    public void openActivityListPhone()
    {
        Intent intent = new Intent(AddPhoneActivity.this, ListPhoneActivity.class);
        intent.putExtra("ID", MainActivity.gbCustomer);
        intent.putExtra("KIND", "");
        startActivity(intent);
        finish();
    }

    public void savePhone(View view)
    {
        EtdPhone obj = new EtdPhone();
        obj.setId(id);
        obj.setKind(itemKindPhoneSelect);
        obj.setNumber(Mask.unmask( phoneNumber.getText().toString()));
        obj.setContact("");
        obj.setAddress_kind(itemKindPhoneSelect);
        PhoneHandler hdlPhone = new PhoneHandler(this);
        if (hdlPhone.verifyRegister(MainActivity.gbCustomer, itemKindPhoneSelect))
        {
            hdlPhone.update(obj);
        } else
        {
            hdlPhone.add(obj);
        }
    }

    public void savePhoneActivity(View view)
    {
        if (validaAddPhone())
        {
            savePhone(view);
            Toast.makeText(this, "Informação Atualizada", Toast.LENGTH_SHORT).show();
            openActivityListPhone();
            finish();
        }
    }

    public boolean validaAddPhone()
    {
        if (itemKindPhoneSelect.equals(""))
        {
            Toast.makeText(this, "Tipo não informado", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phoneNumber.toString().equals(""))
        {
            Toast.makeText(this, "Telefone não informado", Toast.LENGTH_SHORT).show();
            return false;
        } else
        {
            return true;
        }
    }
}
