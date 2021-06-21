package com.setes.setesvendas.app.pedidovenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdProduct;
import com.setes.setesvendas.app.model.ProductAdapter;
import com.setes.setesvendas.app.model.ProductHandler;

import java.util.ArrayList;
import com.setes.setesvendas.app.R;

public class SearchProductActivity extends Activity
{

    public EtdProduct itItemSelect;
    public ListView itLstProduct;
    public EditText itSearchDescription;
    public Button itBtnView;
    public int lastSelect;
    int kindSearch;

    public SearchProductActivity()
    {
    }

    private void setAction()
    {
        itLstProduct.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterview, View view, int i, long l) {
                view.setSelected(true);
                lastSelect = i;
                itItemSelect = (EtdProduct) adapterview.getItemAtPosition(i);
            }
        });
    }

    public void BackProduct(View view)
    {
        if (kindSearch == 0) {
            Intent intent = new Intent(SearchProductActivity.this, AddOrderSaleItemActivity.class);
            intent.putExtra("tb_product_id", 0);
            startActivity(intent);
        }
        finish();
    }

    protected void loadData()
    {
        lastSelect = -1;
        ProductHandler hdl = new ProductHandler(this);
        ArrayList<EtdProduct> listItems = hdl.searchItems(itSearchDescription.getText().toString());
        itLstProduct.setAdapter(new ProductAdapter(this, listItems));
    }

    protected void loadElement()
    {
        itLstProduct = (ListView)findViewById(R.id.lstSearchProduct);
        itSearchDescription = (EditText)findViewById(R.id.edtSearchProductDescription);
        itSearchDescription.addTextChangedListener(new TextWatcher() {
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
        itBtnView = (Button)findViewById(R.id.btnSelectSearchProduct);

        if (kindSearch == 1){
            itBtnView.setVisibility(View.INVISIBLE);
        }else {
            itBtnView.setVisibility(View.VISIBLE);
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search_product);
        bundle = getIntent().getExtras();
        kindSearch = bundle.getInt("search");
        loadElement();
        setAction();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search_product, menu);
        return true;
    }

    public void selectItemProduct(View view)
    {
        if (itItemSelect != null)
        {
            Intent intent  = new Intent(this, AddOrderSaleItemActivity.class);
            intent.putExtra("tb_product_id", itItemSelect.getId());
            startActivity(intent);
            finish();
        } else
        {
            Toast.makeText(this, "Nenhum item selecionado", Toast.LENGTH_SHORT).show();
        }
    }
}
