package com.setes.setesvendas.app.pedidovenda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.setes.setesvendas.app.controller.EtdOrderSaleItem;
import com.setes.setesvendas.app.controller.EtdPrice;
import com.setes.setesvendas.app.controller.EtdProduct;
import com.setes.setesvendas.app.model.OrderSaleItemHandler;
import com.setes.setesvendas.app.model.PriceHandler;
import com.setes.setesvendas.app.model.ProductHandler;
import com.setes.setesvendas.app.util.Convert;

import java.text.NumberFormat;
import com.setes.setesvendas.app.R;

public class AddOrderSaleItemActivity extends Activity {

    public EditText Edt_Description;
    public EditText Edt_Identifier;
    public EditText Edt_Qtty;
    public EditText Edt_SubTotal;
    public EditText Edt_UnitValue;
    public EditText Edt_tb_product_id;
    public int id;
    public NumberFormat itFormat;
    public int tb_product_id;

    public AddOrderSaleItemActivity() {
        id = 0;
        tb_product_id = 0;
        itFormat = NumberFormat.getCurrencyInstance();
    }

    private void returnSearch() {
        Edt_Identifier.setText("");
        Edt_Description.setText("");
        Edt_UnitValue.setText("0,00");
        Edt_SubTotal.setText("0,00");
        EtdProduct obj = new EtdProduct();
        ProductHandler hdl = new ProductHandler(this);
        obj = hdl.get(tb_product_id);
        if (obj != null) {
            Edt_tb_product_id.setText(Integer.toString(obj.getId()));
            Edt_Identifier.setText(obj.getIdentifier());
            Edt_Description.setText(obj.getDescription());
            EtdPrice objPrice = new EtdPrice();
            PriceHandler hdlPrice = new PriceHandler(this);
            objPrice = hdlPrice.get(obj.getId());
            if (objPrice != null) {
                Edt_UnitValue.setText(Convert.floatToString(objPrice.getPrice_tag()));
            }
        }
    }

    protected void calculate() {
        double qtty = 0.0D;
        double valor= 0.0D;
        double total = 0.0D;
        String strQtty = "0,00";
        String strValor = "0,00";
        if ((!Edt_Qtty.getText().toString().equals("")) && (!Edt_Qtty.getText().toString().equals(",")))
            strQtty = Edt_Qtty.getText().toString();
        if ((!Edt_UnitValue.getText().toString().equals("")) && (!Edt_UnitValue.getText().toString().equals(",")))
            strValor = Edt_UnitValue.getText().toString();

        qtty = Double.parseDouble(strQtty.replace(",", "."));
        valor = Double.parseDouble(strValor.replace(",", "."));
        total = qtty * valor;
        Edt_SubTotal.setText(Convert.floatToString(total));
    }

    public void closeAddSAleItemActivity(View view) {
        Intent intent = new Intent(AddOrderSaleItemActivity.this, ListOrderSaleItemActivity.class);
        intent.putExtra("id", MainActivity.gbNumber);
        startActivity(intent);
        finish();
    }

    protected void loadData() {
        EtdOrderSaleItem obj = new EtdOrderSaleItem();
        OrderSaleItemHandler hdl = new OrderSaleItemHandler(this);
        obj = hdl.get(id);
        if (obj != null && id > 0) {
            EtdProduct objProddut = new EtdProduct();
            ProductHandler hdlProduct = new ProductHandler(this);
            objProddut = hdlProduct.get(obj.getTb_product_id());
            if (objProddut != null) {
                Edt_tb_product_id.setText(Integer.toString(objProddut.getId()));
                Edt_Identifier.setText(objProddut.getIdentifier());
                Edt_Description.setText(objProddut.getDescription());
            }
            Edt_Qtty.setText(Convert.floatToString(obj.getQtty()));
            Edt_UnitValue.setText(Convert.floatToString(obj.getUnit_value()));
            Edt_SubTotal.setText(Convert.floatToString(obj.getQtty() * obj.getUnit_value()));
            hdlProduct.close();
        }
        hdl.close();
    }

    protected void loadElement() {
        Edt_tb_product_id = (EditText) findViewById(R.id.edtCodigoProduct);
        Edt_Identifier = (EditText) findViewById(R.id.edtCodigoInternal);
        Edt_Description = (EditText) findViewById(R.id.edtDescriptionProduct);
        Edt_Qtty = (EditText) findViewById(R.id.edtQtdeItensProduct);
        Edt_UnitValue = (EditText) findViewById(R.id.edtUnitValueItensProduct);
        Edt_SubTotal = (EditText) findViewById(R.id.edtSubtotalValueItensProduct);
    }

    protected void loadListeners() {
        Edt_tb_product_id.addTextChangedListener(new TextWatcher() {
            Context context = AddOrderSaleItemActivity.this;
            public void afterTextChanged(Editable editable) {
                Edt_Identifier.setText("");
                Edt_Description.setText("");
                Edt_UnitValue.setText("0,00");
                Edt_SubTotal.setText("0,00");
                if (Edt_tb_product_id.getText().length() > 0) {
                    EtdProduct objProduct = new EtdProduct();
                    ProductHandler hdlProduct = new ProductHandler(context);
                    tb_product_id = Integer.parseInt(Edt_tb_product_id.getText().toString());
                    objProduct = hdlProduct.get(tb_product_id);
                    if (objProduct != null) {
                        Edt_Identifier.setText(objProduct.getIdentifier());
                        Edt_Description.setText(objProduct.getDescription());
                        EtdPrice objPrice = new EtdPrice();
                        PriceHandler hdlPrice = new PriceHandler(context);
                        objPrice = hdlPrice.get(objProduct.getId());
                        if (objPrice != null) {
                            Edt_UnitValue.setText(Convert.floatToString(objPrice.getPrice_tag()));
                        }
                    }
                }
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k) {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k) {
            }
        });
        Edt_Qtty.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k) {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k) {
                calculate();
            }

        });
        Edt_UnitValue.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k) {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k) {
                calculate();
            }
        });
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_sale_item);
        getWindow().setSoftInputMode(3);
        bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        tb_product_id = bundle.getInt("tb_product_id");
        loadElement();
        if (tb_product_id != 0) {
            returnSearch();
        } else {
            loadData();
        }
        loadListeners();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_product, menu);
        return true;
    }

    public void saveAddProductActivity(View view) {
        if (validaAddProduct()) {
            saveOrderSaleItem(view);
            finish();
            Toast.makeText(this, "Informação Atualizada", Toast.LENGTH_SHORT).show();
            closeAddSAleItemActivity(view);
        }
    }

    public void saveOrderSaleItem(View view) {

        OrderSaleItemHandler hdl = new OrderSaleItemHandler(this);
        EtdOrderSaleItem obj = new EtdOrderSaleItem();
        obj.setId(id);
        obj.setTb_institution_id(MainActivity.gbInstitution);
        obj.setTb_order_sale_id(MainActivity.gbNumber);
        obj.setTb_salesman_id(MainActivity.gbSalesman);
        obj.setTb_product_id(Integer.parseInt(Edt_tb_product_id.getText().toString()));
        obj.setItem(1);
        obj.setQtty(Double.parseDouble(Edt_Qtty.getText().toString().replace(",", ".")));
        obj.setUnit_value(Double.parseDouble(Edt_UnitValue.getText().toString().replace(",", ".")));
        obj.setDiscount_aliquot(0.0D);
        obj.setDiscount_value(0.0D);
        if (id == 0) {
            obj.setId(hdl.getNexId("tb_order_sale_item"));
            hdl.add(obj);
        } else {
            hdl.update(obj);
        }
    }

    public void searchProduct(View view) {
        Intent intent = new Intent(AddOrderSaleItemActivity.this, SearchProductActivity.class);
        intent.putExtra("search", 0);
        startActivity(intent);
        finish();
    }

    public boolean validaAddProduct() {
        if (Edt_tb_product_id.getText().toString().equals("")) {
            Toast.makeText(this, "Código do Produto não informado",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Edt_Description.getText().toString().equals("")) {
            Toast.makeText(this, "Produto nãoo informado", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Edt_Qtty.getText().toString().equals("")) {
            Toast.makeText(this, "Quantidade não informada", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Double.parseDouble(Edt_Qtty.getText().toString().replace(",", ".")) == 0.0D) {
            Toast.makeText(this, "Quantidade deve ser maior que zero (0,00)", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Double.parseDouble(Edt_UnitValue.getText().toString().replace(",", ".")) == 0.0D) {
            Toast.makeText(this, "Valor Unitário deve ser maior que zero (0,00)", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}
