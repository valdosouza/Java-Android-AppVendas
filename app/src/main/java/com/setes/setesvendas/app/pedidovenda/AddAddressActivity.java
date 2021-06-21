package com.setes.setesvendas.app.pedidovenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.setes.setesvendas.app.controller.EtdAddress;
import com.setes.setesvendas.app.controller.EtdCity;
import com.setes.setesvendas.app.controller.EtdState;
import com.setes.setesvendas.app.model.AddressHandler;
import com.setes.setesvendas.app.model.CityAdapter;
import com.setes.setesvendas.app.model.CityHandler;
import com.setes.setesvendas.app.model.StateAdapter;
import com.setes.setesvendas.app.model.StateHandler;
import com.setes.setesvendas.app.util.Mask;
import com.setes.setesvendas.app.R;
import java.util.ArrayList;
import java.util.List;

public class AddAddressActivity extends Activity {

    Spinner addressKind;
    Spinner city;
    EditText complement;
    int id;
    EtdCity itemCitySelect;
    String itemKindAddressSelect;
    EtdState itemStateSelect;
    String kind;
    EditText neighbourhood;
    EditText number;
    Spinner state;
    EditText street;
    EditText zipcode;

    private GoogleApiClient client;

    public AddAddressActivity() {
        itemStateSelect = new EtdState();
        itemCitySelect = new EtdCity();
    }

    private void loadCity(int state, int select) {
        CityHandler hdl = new CityHandler(this);
        ArrayList<EtdCity> obj = hdl.getAll(state);
        CityAdapter adapter = new CityAdapter(getApplicationContext(), obj);
        // Configura o spinner para usar o adapter.
        int position = -1;
        this.city.setAdapter(null);
        this.city.setSelection(position);
        this.city.setId(-1);
        this.city.setPrompt("Escolha uma Cidade");
        if (select > 0) {
            for (int i = 0; i < adapter.getCount(); i++) {
                this.itemCitySelect = adapter.getObj(i);
                int comp = this.itemCitySelect.getId();
                if (comp == select) {
                    position = i;
                    break;
                }
            }
        }
        this.city.setAdapter(adapter);
        this.city.setSelection(position);
        this.city.setPrompt("Escolha uma Cidade");
    }

    private void loadState(int select) {
        StateHandler hdl = new StateHandler(this);
        ArrayList<EtdState> obj = hdl.getAll();
        StateAdapter adapter = new StateAdapter(getApplicationContext(), obj);
        // Configura o spinner para usar o adapter.
        int position = -1;
        this.state.setAdapter(null);
        this.state.setSelection(position);
        this.state.setId(-1);
        this.state.setPrompt("Escolha um Estado");
        if (select > 0) {
            for (int i = 0; i < adapter.getCount(); i++) {
                this.itemStateSelect = adapter.getObj(i);
                int comp = this.itemStateSelect.getId();
                if (comp == select) {
                    position = i;
                    break;
                }
            }
        }
        this.state.setAdapter(adapter);
        this.state.setSelection(position);
        this.state.setPrompt("Escolha um Estado");
    }

    public void addItemsOnAddressKind(String select) {

        List<String> tipos = new ArrayList<String>();
        tipos.add("Comercial");
        tipos.add("Residencial");
        tipos.add("Entrega");
        tipos.add("Cobrança");

        ArrayAdapter<String> listItems = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tipos);
        ArrayAdapter<String> spinnerArrayAdapter = listItems;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addressKind.setAdapter(spinnerArrayAdapter);
        int position = -1;
        if (!select.equals("")) {
            for (int i = 0; i < spinnerArrayAdapter.getCount(); i++) {
                String comp = spinnerArrayAdapter.getItem(i);
                if (comp.equals(select)) {
                    position = i;
                    break;
                }
            }
        }
        if (position > -1)
            addressKind.setId(position);
        addressKind.setPrompt("Escolha um Tipo");
    }

    public void cancelAddressActivity(View view) {
        openActivityListAddress();
        finish();
    }

    protected void listerners() {
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView adapterview, View view, int i, long l) {
                itemStateSelect = (EtdState) adapterview.getItemAtPosition(i);
                if (itemStateSelect != null) {
                    loadCity(itemStateSelect.getId(),itemCitySelect.getId());
                }
            }

            public void onNothingSelected(AdapterView adapterview) {
            }

        });
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView adapterview, View view, int i, long l) {
                itemCitySelect = (EtdCity) adapterview.getItemAtPosition(i);
            }

            public void onNothingSelected(AdapterView adapterview) {
            }
        });
        addressKind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView adapterview, View view, int i, long l) {
                itemKindAddressSelect = adapterview.getItemAtPosition(i).toString();
            }

            public void onNothingSelected(AdapterView adapterview) {
            }
        });
    }

    protected void loadData() {
        EtdAddress etdaddress = (new AddressHandler(this)).get(id, kind);
        if (etdaddress != null) {
            zipcode.setText(etdaddress.getZip_code());
            loadState(etdaddress.getTb_state_id());
            loadCity(etdaddress.getTb_state_id(), etdaddress.getTb_city_id());
            street.setText(etdaddress.getStreet());
            number.setText(etdaddress.getNmbr());
            complement.setText(etdaddress.getComplement());
            neighbourhood.setText(etdaddress.getNeighborhood());
            addItemsOnAddressKind(etdaddress.getKind());
        } else {
            loadState(0);
            loadCity(41,0);
            addItemsOnAddressKind("");
        }
    }

    protected void loadElement() {
        zipcode = (EditText) findViewById(R.id.edtZipCode);
        zipcode.addTextChangedListener(Mask.insert("##.###-###", zipcode));
        state = (Spinner) findViewById(R.id.edtState);
        city = (Spinner) findViewById(R.id.edtCity);
        street = (EditText) findViewById(R.id.edtAddress);
        number = (EditText) findViewById(R.id.edtNmbAddress);
        complement = (EditText) findViewById(R.id.edtComplement);
        neighbourhood = (EditText) findViewById(R.id.edtNeighborhood);
        addressKind = (Spinner) findViewById(R.id.edtAddressKind);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_address);
        getWindow().setSoftInputMode(3);
        bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        kind = bundle.getString("kind");
        loadElement();
        loadData();
        listerners();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.itens, menu);
        return true;
    }

    public void openActivityListAddress() {
        Intent intent = new Intent(AddAddressActivity.this, ListAddressActivity.class);
        intent.putExtra("ID", id);
        intent.putExtra("KIND", kind);
        startActivity(intent);
        finish();
    }

    public void saveAddress(View view) {

        EtdAddress address = new EtdAddress();
        address.setId(MainActivity.gbCustomer);
        address.setTb_country_id(1058);
        address.setZip_code(Mask.unmask(zipcode.getText().toString()));
        address.setTb_state_id(itemStateSelect.getId());
        address.setTb_city_id(itemCitySelect.getId());
        address.setStreet(street.getText().toString());
        address.setNmbr(number.getText().toString());
        address.setComplement(complement.getText().toString());
        address.setNeighborhood(neighbourhood.getText().toString());
        address.setKind(itemKindAddressSelect);
        AddressHandler addresshandler = new AddressHandler(this);
        if (addresshandler.verifyRegister(MainActivity.gbCustomer, itemKindAddressSelect)) {
            addresshandler.update(address);
            return;
        } else {
            addresshandler.add(address);
            return;
        }
    }

    public void saveAddressActivity(View view) {
        if (validaAddAddress()) {
            saveAddress(view);
            Toast.makeText(this, "Informação Atualizada", Toast.LENGTH_SHORT).show();
            openActivityListAddress();
            finish();
        }
    }

    public boolean validaAddAddress() {
        if (zipcode.getText().toString().equals("")) {
            Toast.makeText(this, "Cep informado", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (itemStateSelect == null) {
            Toast.makeText(this, "Estado não informado", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (itemCitySelect == null) {
            Toast.makeText(this, "Cidade não informada", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (street.getText().toString().equals("")) {
            Toast.makeText(this, "Logradouro não informado", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (number.getText().toString().equals("")) {
            Toast.makeText(this, "Número não informado", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (neighbourhood.getText().toString().equals("")) {
            Toast.makeText(this, "Bairro não informado", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}
