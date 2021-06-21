package com.setes.setesvendas.app.pedidovenda;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.setes.setesvendas.app.controller.EtdConfig;
import com.setes.setesvendas.app.model.ConfigHandler;
import com.setes.setesvendas.app.R;

public class ConfigActivity extends Activity
{
    public EtdConfig objConfig;
    public ConfigHandler hdlConfig;
    public EditText Edt_rowid;
    public EditText ItInstitution;
    public EditText ItMobile;
    public EditText ItPathWbs;
    public EditText lastOrderSent;
    public EditText CodigoTablePrice;

    public ConfigActivity()
    {

    }

    public void closeConfigActivity(View view)
    {
        finish();
    }

    public void loadData()
    {
        objConfig = new EtdConfig();
        objConfig = hdlConfig.get("WBS_PATH");
        if (objConfig != null)
        {
            ItPathWbs.setText(objConfig.getContent());
        } else
        {
            ItPathWbs.setText("http://www.gestaosetes.com.br/ws/index.php");
        }
        objConfig = hdlConfig.get("GNL_INSTITUTION");
        if (objConfig != null)
        {
            ItInstitution.setText(objConfig.getContent());
        } else
        {
            ItInstitution.setText("30");
        }
        objConfig = hdlConfig.get("GNL_SALESMAN");
        if (objConfig != null)
        {
            ItMobile.setText(objConfig.getContent());
        } else
        {
            ItMobile.setText("1");
        }
        objConfig = hdlConfig.get("GNL_LASTORDERSENT");
        if (objConfig != null)
        {
            lastOrderSent.setText(objConfig.getContent());
        } else
        {
            lastOrderSent.setText("1");

        }
        objConfig = hdlConfig.get("GNL_CODIGOTABLEPRICE");
        if (objConfig != null)
        {
            CodigoTablePrice.setText(objConfig.getContent());
        } else
        {
            CodigoTablePrice.setText("1");
        }
        return;
    }

    protected void loadElement()
    {
        ItPathWbs = (EditText)findViewById(R.id.edtPathWebService);
        ItInstitution = (EditText)findViewById(R.id.edtCodInstitution);
        ItMobile = (EditText)findViewById(R.id.edtCodSalesman);
        lastOrderSent = (EditText)findViewById(R.id.edtLastOrderSent);
        CodigoTablePrice = (EditText)findViewById(R.id.edtCodigoTablePrice);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_config);
        getWindow().setSoftInputMode(3);
        hdlConfig = new ConfigHandler(this);
        hdlConfig.createTable();
        loadElement();
        loadData();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.config, menu);
        return true;
    }

    public void saveCodigoInstitution(View view)
    {
        objConfig = new EtdConfig();
        objConfig.setId("GNL_INSTITUTION");
        objConfig.setContent(ItInstitution.getText().toString());
        if (hdlConfig.verifyRegisterByField("tb_config","id","GNL_INSTITUTION"))
        {
            hdlConfig.update(objConfig);
        } else{
            hdlConfig.add(objConfig);
        }
    }

    public void saveCodigoSalesman(View view)
    {
        objConfig = new EtdConfig();
        objConfig.setId("GNL_SALESMAN");
        objConfig.setContent(ItMobile.getText().toString());
        if (hdlConfig.verifyRegisterByField("tb_config","id","GNL_SALESMAN"))
        {
            hdlConfig.update(objConfig);
        } else
        {
            hdlConfig.add(objConfig);
        }
    }

    public void saveConfigActivity(View view)
    {
        if (validaSaveConfig())
        {
            savePathWebService(view);
            saveCodigoInstitution(view);
            saveCodigoSalesman(view);
            saveLastOrderSent(view);
            saveCodigoTablePrice(view);
            Toast.makeText(this, "Informação Atualizada", Toast.LENGTH_SHORT ).show();
            finish();
        }
    }



    public void saveLastOrderSent(View view)
    {
        objConfig = new EtdConfig();
        objConfig.setId("GNL_LASTORDERSENT");
        objConfig.setContent(lastOrderSent.getText().toString());
        if (hdlConfig.verifyRegisterByField("tb_config", "id", "GNL_LASTORDERSENT"))
        {
            hdlConfig.update(objConfig);
        } else
        {
            hdlConfig.add(objConfig);
        }
    }

    public void saveCodigoTablePrice(View view)
    {
        objConfig = new EtdConfig();
        objConfig.setId("GNL_CODIGOTABLEPRICE");
        objConfig.setContent(CodigoTablePrice.getText().toString());
        if (hdlConfig.verifyRegisterByField("tb_config", "id", "GNL_CODIGOTABLEPRICE"))
        {
            hdlConfig.update(objConfig);
        } else
        {
            hdlConfig.add(objConfig);
        }
        MainActivity.gbPriceList = Integer.parseInt( objConfig.getContent());
    }

    public void savePathWebService(View view)
    {
        objConfig = new EtdConfig();
        objConfig.setId("WBS_PATH");
        objConfig.setContent(ItPathWbs.getText().toString());
        if (hdlConfig.verifyRegisterByField("tb_config","id","WBS_PATH"))
        {
            hdlConfig.update(objConfig);
            return;
        } else
        {
            hdlConfig.add(objConfig);
            return;
        }
    }

    public boolean validaSaveConfig()
    {

        return true;
    }
}


