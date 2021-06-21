package com.setes.setesvendas.app.pedidovenda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;

import com.setes.setesvendas.app.R;
import com.setes.setesvendas.app.controller.EtdSyncTable;
import com.setes.setesvendas.app.model.AddressHandler;
import com.setes.setesvendas.app.model.CompanyHandler;
import com.setes.setesvendas.app.model.CustomerHandler;
import com.setes.setesvendas.app.model.EntityHandler;
import com.setes.setesvendas.app.model.MailingHandler;
import com.setes.setesvendas.app.model.OrderHandler;
import com.setes.setesvendas.app.model.OrderSaleHandler;
import com.setes.setesvendas.app.model.OrderSaleItemHandler;
import com.setes.setesvendas.app.model.PersonHandler;
import com.setes.setesvendas.app.model.PhoneHandler;
import com.setes.setesvendas.app.model.PriceHandler;
import com.setes.setesvendas.app.model.PriceListHandler;
import com.setes.setesvendas.app.model.ProductHandler;
import com.setes.setesvendas.app.model.SyncTableHandler;

public class OperationDatabase extends Activity {

    public CheckBox Pedidos;
    public CheckBox Clientes;
    public CheckBox Produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_database);
        loadElement();
        listener();
    }

    public void closeActivity(View view)
    {
        finish();
    }
    protected void loadElement()
    {
        Pedidos = (CheckBox)findViewById(R.id.chBxPedidos);
        Clientes = (CheckBox)findViewById(R.id.chBxClientes);
        Produtos = (CheckBox)findViewById(R.id.chBxProdutos);
    }

    protected void listener()
    {
        Clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(Clientes.isChecked()){
                    Pedidos.setChecked(true);
                }
            }
        });

        Produtos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Produtos.isChecked()) {
                    Clientes.setChecked(true);
                    Pedidos.setChecked(true);
                }
            }
        });


    }
    public void DeleteAll(final View view){
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Dados")
                .setMessage("Tem certeza que deseja prosseguir?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                if (Pedidos.isChecked()) {
                                    deleteOrders(view);
                                }
                                if (Clientes.isChecked()) {
                                    deleteCustomers(view);
                                }
                                if (Produtos.isChecked()){
                                    deleteProducts(view);
                                }
                            }
                            {
                            }
                        }).setNegativeButton(android.R.string.no, null).show();
    }
    public void updateSyncrony(String table){
        //Sincronia
        SyncTableHandler hdlSync = new SyncTableHandler(OperationDatabase.this);
        EtdSyncTable objEdtSyncTable = new EtdSyncTable();
        objEdtSyncTable.setId(table);
        objEdtSyncTable.setUpdateAt("1900-01-01 00:00:00");
        //Receive
        objEdtSyncTable.setDirection("R");
        hdlSync.update(objEdtSyncTable);
        //Send
        objEdtSyncTable.setDirection("S");
        hdlSync.update(objEdtSyncTable);
        hdlSync.close();
    }

    public void deleteOrders(View view){

        OrderSaleItemHandler hdlOrderSaleItem = new OrderSaleItemHandler(OperationDatabase.this);
        hdlOrderSaleItem.deleteAll();
        hdlOrderSaleItem.close();
        updateSyncrony("tb_order_sale_item");

        OrderSaleHandler hdlOrderSale = new OrderSaleHandler(OperationDatabase.this);
        hdlOrderSale.deleteAll();
        hdlOrderSale.close();
        updateSyncrony("tb_order_sale");

        OrderHandler hdlOrder = new OrderHandler(OperationDatabase.this);
        hdlOrder.deleteAll();
        hdlOrder.close();
        updateSyncrony("tb_order");

    }

    public void deleteCustomers(View view){
        CustomerHandler hdlCustomer = new CustomerHandler(OperationDatabase.this);
        hdlCustomer.deleteAll();
        hdlCustomer.close();
        updateSyncrony("tb_customer");

        CompanyHandler hdlCompany = new CompanyHandler(OperationDatabase.this);
        hdlCompany.deleteAll();
        hdlCompany.close();
        updateSyncrony("tb_company");

        PersonHandler hdlPerson = new PersonHandler(OperationDatabase.this);
        hdlPerson.deleteAll();
        hdlPerson.close();
        updateSyncrony("tb_person");

        EntityHandler hdlEntity = new EntityHandler(OperationDatabase.this);
        hdlEntity.deleteAll();
        hdlEntity.close();
        updateSyncrony("tb_entity");

        AddressHandler hdlAddress = new AddressHandler(OperationDatabase.this);
        hdlAddress.deleteAll();
        hdlAddress.close();
        updateSyncrony("tb_address");

        PhoneHandler hdlPhone = new PhoneHandler(OperationDatabase.this);
        hdlPhone.deleteAll();
        hdlPhone.close();
        updateSyncrony("tb_phone");

        MailingHandler hdlMailing = new MailingHandler(OperationDatabase.this);
        hdlMailing.deleteAll();
        hdlMailing.close();
        updateSyncrony("tb_mailing");
    }

    public void deleteProducts(View view){
        PriceHandler hdlPrice = new PriceHandler(OperationDatabase.this);
        hdlPrice.deleteAll();
        hdlPrice.close();
        updateSyncrony("tb_price");

        PriceListHandler hdlPriceList = new PriceListHandler(OperationDatabase.this);
        hdlPriceList.deleteAll();
        hdlPriceList.close();
        updateSyncrony("tb_price_list");

        ProductHandler hdlProduct = new ProductHandler(OperationDatabase.this);
        hdlProduct.deleteAll();
        hdlProduct.close();
        updateSyncrony("tb_product");
    }
}
