package com.setes.setesvendas.app.pedidovenda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.setes.setesvendas.app.R;
import com.setes.setesvendas.app.controller.EtdConfig;
import com.setes.setesvendas.app.model.AddressHandler;
import com.setes.setesvendas.app.model.CityHandler;
import com.setes.setesvendas.app.model.CompanyHandler;
import com.setes.setesvendas.app.model.ConfigHandler;
import com.setes.setesvendas.app.model.CountryHandler;
import com.setes.setesvendas.app.model.CustomerHandler;
import com.setes.setesvendas.app.model.EntityHandler;
import com.setes.setesvendas.app.model.MailingHandler;
import com.setes.setesvendas.app.model.MainMenuAdapter;
import com.setes.setesvendas.app.model.OrderHandler;
import com.setes.setesvendas.app.model.PersonHandler;
import com.setes.setesvendas.app.model.PhoneHandler;
import com.setes.setesvendas.app.model.PriceListHandler;
import com.setes.setesvendas.app.model.ProductHandler;
import com.setes.setesvendas.app.model.StateHandler;

public class MainActivity extends Activity {

    public static int gbCustomer;
    public static int gbInstitution;
    public static int gbPriceList;
    public static int gbNumber;
    public static int gbSalesman;
    public static String gbWbsPath;
    public static GridView itGridMenu;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pathWebService();
        setGridIconsMain();
        setActionIconsMain();
        setDatabaseTable();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void openActivityConfig(View view) {
        Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
        startActivity(intent);
    }

    public void openActivityOrder(View view) {
        Intent intent = new Intent(MainActivity.this, OrderActivity.class);
        OrderHandler hdl = new OrderHandler(this);
        gbNumber = hdl.getNexId("tb_order");
        EtdConfig objConfig = new EtdConfig();
        ConfigHandler hdlConfig = new ConfigHandler(this);
        objConfig = hdlConfig.get("GNL_LASTORDERSENT");
        if (objConfig != null) {
            int i = Integer.parseInt(objConfig.getContent());
            if (i > gbNumber) {
                gbNumber = i;
            }
        }
        gbCustomer = 0;
        startActivity(intent);
    }

    public void openActivityReceiveWs(View view) {
        pathWebService();
        if (validaReceiveWebservice()) {
            Intent intent = new Intent(MainActivity.this, ReceiveWsActivity.class);
            startActivity(intent);
        }
    }

    public void openActivitySearchOrder(View view) {
        startActivity(new Intent(this, SearchOrderActivity.class));
    }

    public void openActivitySendWs(View view) {
        pathWebService();
        if (validaReceiveWebservice()) {
            startActivity(new Intent(this, SendWsActivity.class));
        }
    }

    public void operCloseApp() {
        new AlertDialog.Builder(this)
                .setTitle("Sistema de Venda")
                .setMessage("Tem certeza que deseja sair do aplicativo?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                 MainActivity.this.finish();
                            }
                        }).setNegativeButton(android.R.string.no, null).show();

    }

    public void operationDatabase(View view) {
        Intent intent = new Intent(MainActivity.this, OperationDatabase.class);
        startActivity(intent);
    }

    public void operNewOrder(final View v) {
        new AlertDialog.Builder(this)
                .setTitle("Pedido")
                .setMessage("Tem certeza abrir um novo Pedido?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                openActivityOrder(v);
                            }

                            {
                            }
                        }).setNegativeButton(android.R.string.no, null).show();
    }

    public void searchProduct(View view) {

        Intent intent = new Intent(MainActivity.this, SearchProductActivity.class);
        intent.putExtra("search", 1);
        startActivity(intent);

    }


    public boolean pathWebService() {

        boolean flag = true;
        gbWbsPath = "";
        EtdConfig obj = new EtdConfig();
        ConfigHandler confighandler = new ConfigHandler(this);
        obj = confighandler.get("WBS_PATH");
        if (obj != null) {
            gbWbsPath = obj.getContent();
        }
        obj = confighandler.get("GNL_INSTITUTION");
        if (obj != null) {
            gbInstitution = Integer.parseInt(obj.getContent());
        } else {
            gbInstitution = 1;
        }
        obj = confighandler.get("GNL_SALESMAN");
        if (obj != null) {
            gbSalesman = Integer.parseInt(obj.getContent());
        } else {
            gbSalesman = 1;
        }
        obj = confighandler.get("GNL_CODIGOTABLEPRICE");
        if (obj != null) {
            gbPriceList = Integer.parseInt(obj.getContent());
        } else {
            gbPriceList = 1;
        }


        confighandler.close();
        if (gbWbsPath.equals("")) {
            flag = false;
        }
        return flag;
    }

    public void setActionIconsMain() {
        itGridMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        openActivityConfig(v);
                        break;
                    case 1:
                        operNewOrder(v);
                        break;
                    case 2:
                        openActivitySearchOrder(v);
                        break;
                    case 3:
                        openActivitySendWs(v);
                        break;
                    case 4:
                        openActivityReceiveWs(v);
                        break;
                    case 5:
                        operationDatabase(v);
                        break;
                    case 6:
                        searchProduct(v);
                        break;
                    case 7:
                        operCloseApp();
                        break;
                }
            }
        });
    }

    public void setDatabaseTable() {
        try
        {
            new CountryHandler(this);
            new StateHandler(this);
            new CityHandler(this);
            new EntityHandler(this);
            new MailingHandler(this);
            new CompanyHandler(this);
            new PersonHandler(this);
            new CustomerHandler(this);
            new AddressHandler(this);
            new PhoneHandler(this);
            new ProductHandler(this);
            new PriceListHandler(this);
            new PriceListHandler(this);
        } catch (Exception e) {
            FirebaseCrash.report(new Exception(e.getMessage()));
        }
    }

    public void setGridIconsMain() {

        itGridMenu = (GridView) findViewById(R.id.grdMainmenu);
        itGridMenu.setAdapter(new MainMenuAdapter(this));
    }

    public boolean validaReceiveWebservice() {

        if (!pathWebService()) {
            Toast.makeText(this, "WebService não definido", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }


}