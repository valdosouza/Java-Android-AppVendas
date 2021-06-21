package com.setes.setesvendas.app.pedidovenda;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.setes.setesvendas.app.controller.EtdAddress;
import com.setes.setesvendas.app.controller.EtdCompany;
import com.setes.setesvendas.app.controller.EtdEntity;
import com.setes.setesvendas.app.controller.EtdMailing;
import com.setes.setesvendas.app.controller.EtdOrder;
import com.setes.setesvendas.app.controller.EtdOrderSale;
import com.setes.setesvendas.app.controller.EtdOrderSaleItem;
import com.setes.setesvendas.app.controller.EtdPerson;
import com.setes.setesvendas.app.controller.EtdPhone;
import com.setes.setesvendas.app.model.AddressHandler;
import com.setes.setesvendas.app.model.CompanyHandler;
import com.setes.setesvendas.app.model.EntityHandler;
import com.setes.setesvendas.app.model.MailingHandler;
import com.setes.setesvendas.app.model.OrderHandler;
import com.setes.setesvendas.app.model.OrderSaleHandler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.setes.setesvendas.app.R;
import com.setes.setesvendas.app.model.OrderSaleItemHandler;
import com.setes.setesvendas.app.model.PersonHandler;
import com.setes.setesvendas.app.model.PhoneHandler;

import com.setes.setesvendas.app.model.SyncTableHandler;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.util.List;

public class SendWsActivityByOne extends Activity
{

    public String MsgWebServiceDialog;

    Thread ThWsOrderSaleItemSend;
    Thread ThWsOrderSaleSend;
    Thread ThWsFinalizeProcess;
    private Runnable changeMessage;
    public ProgressDialog dialog;
    public NodeList nodes;
    public int progresso;
    public int tbEntityId;
    public int tbOrderId;
    public int tbEntityIdWeb;
    public Bundle errorMessage  = new Bundle();


    public SendWsActivityByOne(){
        progresso = 0;
        changeMessage = new Runnable() {
            public void run()
            {
                dialog.setTitle(MsgWebServiceDialog);
            }
        };

        ThWsOrderSaleSend = new Thread() {
            public @Override void run() {
            try
            {
                OrderSaleHandler hdl = new OrderSaleHandler(SendWsActivityByOne.this);
                String table = "tb_order_sale";
                if (hdl.webService(SendWsActivityByOne.this))
                {
                    MsgWebServiceDialog = "Sincronizando Vendas";
                    runOnUiThread(changeMessage);
                    EtdOrderSale obj = hdl.get(tbOrderId);
                    dialog.setMax(7);
                    progresso = 0;
                    dialog.setProgress(progresso);
                    OrderHandler hdlOrder = new OrderHandler(SendWsActivityByOne.this);
                    EtdOrder objOrder = new EtdOrder();
                    tbEntityId = 0;
                    tbEntityIdWeb = 0;
                    objOrder = hdlOrder.get(obj.getId());
                    if (objOrder != null) {
                        tbOrderId = obj.getId();
                        tbEntityId = obj.getTb_customer_id();
                        //Verifica o codigo do cliente na internet
                        EntitySend();
                        PersonSend();
                        CompanySend();
                        AddressSend();
                        PhoneSend();
                        MailingSend();
                        obj.setTb_customer_id(tbEntityIdWeb);
                        hdl.sendWebService(objOrder, obj);
                    }
                    progresso++;
                    dialog.setProgress(progresso);
                }
                hdl.close();
            } catch (Exception e) {
                FirebaseCrash.report(new Exception(e.getMessage()));
            }
            }

        };

        ThWsOrderSaleItemSend = new Thread() {
            public @Override void run() {
            try {
                ThWsOrderSaleSend.join();
            } catch (InterruptedException ex) {

            };
            try
            {
                OrderSaleItemHandler hdl = new OrderSaleItemHandler(SendWsActivityByOne.this);
                String table = "tb_order_sale_item";
                if (hdl.webService(SendWsActivityByOne.this))
                {
                    MsgWebServiceDialog = "Sincronizando Itens do Pedido";
                    runOnUiThread(changeMessage);
                    List<EtdOrderSaleItem> objList = hdl.getItensOrderSale(tbOrderId);
                    objList.size();
                    if (objList.size() > 0){
                        dialog.setMax(objList.size());
                        progresso = 0;
                        dialog.setProgress(progresso);
                        //Order Sale
                        OrderSaleHandler hdlOrderSale = new OrderSaleHandler(SendWsActivityByOne.this);
                        EtdOrderSale objOrderSale = new EtdOrderSale();
                        for (EtdOrderSaleItem obj : objList) {
                            objOrderSale = hdlOrderSale.get(obj.getTb_order_sale_id());
                            if (objOrderSale != null) {
                                hdl.sendWebService(objOrderSale, obj);
                            }
                            progresso++;
                            dialog.setProgress(progresso);
                        }
                        objList.clear();
                    }
                }
                hdl.close();
            } catch (Exception e) {
                FirebaseCrash.report(new Exception(e.getMessage()));
            }
            }
        };

        ThWsFinalizeProcess = new Thread() {
            public
            @Override
            void run() {
                try {
                    ThWsOrderSaleItemSend.join();
                } catch (InterruptedException ex) {};
                finish();
            }
        };
    };

    public void closeSendWsActivityByOne(View view)
    {
        finish();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_send_ws_by_one );
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(3);
        tbOrderId = getIntent().getExtras().getInt("tbOrderId");
        if (dialog == null)
        {
            dialog = new ProgressDialog(this);
            dialog.setCancelable(false);
            dialog.setProgressStyle(1);
        }
    }

    @Override
    public void onDestroy(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        super.onDestroy();
        if ( dialog!=null && dialog.isShowing() ){
            dialog.cancel();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {

        return true;
    }

    public void startSendWsByOne(View view)
    {
        MsgWebServiceDialog = "Sincronizando WebService...";
        runOnUiThread(changeMessage);
        dialog.show();
        ThWsOrderSaleSend.start();
        ThWsOrderSaleItemSend.start();
        ThWsFinalizeProcess.start();

    }

    public class GetWebCompanyId extends Thread {
        @Override
        public void run(){
            synchronized(this){
                tbEntityIdWeb = -1;
                CompanyHandler hdlCompany = new CompanyHandler(SendWsActivityByOne.this);
                tbEntityIdWeb = hdlCompany.returnIdWeb(tbEntityId);
                while (tbEntityIdWeb < 0) { }
                hdlCompany.close();
                this.notify();
            }
        }
    }

    public class GetWebPersonId extends Thread {
        @Override
        public void run(){
            synchronized(this){
                tbEntityIdWeb = -1;
                int total = 0;
                PersonHandler hdlPerson = new PersonHandler(SendWsActivityByOne.this);
                tbEntityIdWeb = hdlPerson.returnIdWeb(tbEntityId);
                while (tbEntityIdWeb < 0) { }
                hdlPerson.close();
                this.notify();
            }
        }
    }

    public class SendEntityWeb extends Thread {
        @Override
        public void run(){
            synchronized(this){
                tbEntityIdWeb = 0;
                String retorno;
                EntityHandler hdlEntity = new EntityHandler(SendWsActivityByOne.this);
                EtdEntity obj = hdlEntity.get(tbEntityId);
                if (obj != null) {
                    obj.setId(tbEntityIdWeb);
                    hdlEntity.sendWebService(obj);
                    retorno = hdlEntity.sendWebService(obj);
                    Document doc = XMLfunctions.XMLfromString(retorno);
                    nodes = doc.getElementsByTagName("ENTITY");
                    if (nodes != null) {
                        Element e = (Element) nodes.item(0);
                        tbEntityIdWeb = Integer.parseInt(XMLfunctions.getElementValue(e));
                    }
                }
                while (tbEntityIdWeb == 0) { }
                hdlEntity.close();
                this.notify();
            }
        }
    }

    public void EntitySend() {
        EntityHandler hdl = new EntityHandler(SendWsActivityByOne.this);
        String table = "tb_entity";
        if (hdl.webService(SendWsActivityByOne.this)) {
            MsgWebServiceDialog = "Sincronizando Entidade";
            runOnUiThread(changeMessage);
            dialog.setProgress(progresso);
            tbEntityIdWeb = 0;
            //Verifica se a entidade é uma pessoa Juridica
            try
            {
                GetWebCompanyId widcompany = new GetWebCompanyId();
                widcompany.start();
                synchronized(widcompany) {
                    try {
                        widcompany.wait();
                        } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                FirebaseCrash.report(new Exception(e.getMessage()));
            }
            //Se retornou zero é por que é uma pessoa Fisica
            if (tbEntityIdWeb == 0) {
                try
                {
                    GetWebPersonId widperson = new GetWebPersonId();
                    widperson.start();
                    synchronized(widperson){
                        try {
                            widperson.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
            //Se o ID continuar zero não tem como enviar os dados da entidade
            if (tbEntityIdWeb == 0) {
                try
                {
                    SendEntityWeb sendEntity = new SendEntityWeb();
                    sendEntity.start();
                    synchronized(sendEntity){
                        try {
                            sendEntity.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
        }
        hdl.close();
    };

    public void CompanySend() {
        try
        {
            CompanyHandler hdl = new CompanyHandler(SendWsActivityByOne.this);
            String table = "tb_company";
            if (hdl.webService(SendWsActivityByOne.this))
            {
                MsgWebServiceDialog = "Sincronizando Pessoa Jurídica";
                runOnUiThread(changeMessage);
                EtdCompany obj = hdl.get(tbEntityId);
                if (obj != null) {
                    obj.setId(tbEntityIdWeb);
                    hdl.sendWebService(obj);
                }
                progresso++;
                dialog.setProgress(progresso);
                hdl.close();
            }
        } catch (Exception e) {
            FirebaseCrash.report(new Exception(e.getMessage()));
        }
    };

    public void PersonSend(){
        try
        {
            PersonHandler hdl = new PersonHandler(SendWsActivityByOne.this);
            String table = "tb_person";
            if (hdl.webService(SendWsActivityByOne.this)) {
                MsgWebServiceDialog = "Sincronizando Pessoa Física";
                runOnUiThread(changeMessage);
                EtdPerson obj = hdl.get(tbEntityId);
                if (obj != null) {
                    obj.setId(tbEntityIdWeb);
                    hdl.sendWebService(obj);
                }
            }
            progresso++;
            dialog.setProgress(progresso);
            hdl.close();
        } catch (Exception e) {
            FirebaseCrash.report(new Exception(e.getMessage()));
        }
    }


    public void AddressSend() {
        try
        {
            AddressHandler hdl = new AddressHandler(SendWsActivityByOne.this);
            String table = "tb_address";
            if (hdl.webService(SendWsActivityByOne.this))
            {
                MsgWebServiceDialog = "Sincronizando Endereços";
                runOnUiThread(changeMessage);
                EtdAddress obj = hdl.get(tbEntityId,"Comercial");
                if (obj != null) {
                    obj.setId(tbEntityIdWeb);
                    hdl.sendWebService(obj);
                }
            }
            hdl.close();
            progresso++;
            dialog.setProgress(progresso);
        } catch (Exception e) {
            FirebaseCrash.report(new Exception(e.getMessage()));
        }
    };

    public void PhoneSend(){
        try
        {
            PhoneHandler hdl = new PhoneHandler(SendWsActivityByOne.this);
            String table = "tb_phone";
            if (hdl.webService(SendWsActivityByOne.this)) {
                MsgWebServiceDialog = "Sincronizando Telefones";
                runOnUiThread(changeMessage);
                EtdPhone obj = hdl.get(tbEntityId, "Comercial");
                if (obj != null) {
                    obj.setId(tbEntityIdWeb);
                    hdl.sendWebService(obj);
                }
                hdl.close();
                progresso++;
                dialog.setProgress(progresso);
            }
        } catch (Exception e) {
            FirebaseCrash.report(new Exception(e.getMessage()));
        }
    };

    public void MailingSend() {
        try
        {
            MailingHandler hdl = new MailingHandler(SendWsActivityByOne.this);
            String table = "tb_mailing";
            if (hdl.webService(SendWsActivityByOne.this)) {
                MsgWebServiceDialog = "Sincronizando e-mails";
                runOnUiThread(changeMessage);
                EtdMailing obj = hdl.get(tbEntityId,"Comercial");
                if (obj != null) {
                    obj.setId(tbEntityIdWeb);
                    hdl.sendWebService(obj);
                }
                hdl.close();
                progresso++;
                dialog.setProgress(progresso);
            }
        } catch (Exception e) {
            FirebaseCrash.report(new Exception(e.getMessage()));
        }
    };
}
