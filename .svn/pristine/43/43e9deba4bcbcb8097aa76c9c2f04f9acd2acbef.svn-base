package com.setes.setesvendas.app.pedidovenda;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.setes.setesvendas.app.R;
import com.setes.setesvendas.app.controller.EtdAddress;
import com.setes.setesvendas.app.controller.EtdCompany;
import com.setes.setesvendas.app.controller.EtdEntity;
import com.setes.setesvendas.app.controller.EtdMailing;
import com.setes.setesvendas.app.controller.EtdOrder;
import com.setes.setesvendas.app.controller.EtdOrderSale;
import com.setes.setesvendas.app.controller.EtdOrderSaleItem;
import com.setes.setesvendas.app.controller.EtdPerson;
import com.setes.setesvendas.app.controller.EtdPhone;
import com.setes.setesvendas.app.controller.EtdSyncTable;
import com.setes.setesvendas.app.model.AddressHandler;
import com.setes.setesvendas.app.model.CompanyHandler;
import com.setes.setesvendas.app.model.EntityHandler;
import com.setes.setesvendas.app.model.MailingHandler;
import com.setes.setesvendas.app.model.OrderHandler;
import com.setes.setesvendas.app.model.OrderSaleHandler;
import com.setes.setesvendas.app.model.OrderSaleItemHandler;
import com.setes.setesvendas.app.model.PersonHandler;
import com.setes.setesvendas.app.model.PhoneHandler;
import com.setes.setesvendas.app.model.SyncTableHandler;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SendWsActivity extends Activity
{

    public String MsgWebServiceDialog;
    Thread ThWsOrderSaleItemSend;
    Thread ThWsOrderSaleSend;
    Thread ThWsFinalizeProcess;
    private Runnable changeMessage;
    public ProgressDialog dialog;
    public int maxRegs;
    public NodeList nodes;
    public int progresso;
    public int tbOrderId;
    public int tbEntityId;
    public int tbEntityIdWeb;
    public Bundle errorMessage  = new Bundle();


    public void updateSender(SyncTableHandler hdl, String table) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        EtdSyncTable objSynTable = new EtdSyncTable();
        objSynTable.setId(table);
        objSynTable.setUpdateAt(formatter.format(date));
        objSynTable.setDirection("S");
        if (hdl.verifyRegister(table, "S")) {
            hdl.update(objSynTable);
        } else {
            hdl.add(objSynTable);
        }
        hdl.close();
    }

    public SendWsActivity() {
        maxRegs = 0;
        progresso = 0;
        changeMessage = new Runnable() {
            public void run() {
                dialog.setTitle(MsgWebServiceDialog);
            }
        };

        ThWsOrderSaleSend = new Thread() {
            public @Override void run() {
                try {
                    OrderSaleHandler hdl = new OrderSaleHandler(SendWsActivity.this);
                    String table = "tb_order_sale";
                    if (hdl.webService(SendWsActivity.this)) {
                        MsgWebServiceDialog = "Sincronizando Vendas";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(SendWsActivity.this);
                        String data = "";
                        data = hdlSyncTable.getDateTime(table, "S");
                        List<EtdOrderSale> objList = hdl.getSendWs(data);
                        maxRegs = objList.size();
                        if (maxRegs > 0) {
                            dialog.setMax(maxRegs);
                            progresso = 0;
                            dialog.setProgress(progresso);
                            OrderHandler hdlOrder = new OrderHandler(SendWsActivity.this);
                            EtdOrder objOrder = new EtdOrder();
                            for (EtdOrderSale obj : objList) {
                                tbOrderId = 0;
                                tbEntityId = 0;
                                tbEntityIdWeb = 0;
                                objOrder = hdlOrder.get(obj.getId());
                                if (objOrder != null) {
                                    tbOrderId = obj.getId();
                                    tbEntityId = obj.getTb_customer_id();
                                    //Verifica o codigo do cliente na internet
                                    EntitySend();
                                    if (tbEntityIdWeb > 0) {
                                        PersonSend();
                                        CompanySend();
                                        AddressSend();
                                        PhoneSend();
                                        MailingSend();
                                        obj.setTb_customer_id(tbEntityIdWeb);
                                        hdl.sendWebService(objOrder, obj);
                                    }
                                }
                                progresso++;
                                dialog.setProgress(progresso);
                            }
                            objList.clear();
                            updateSender(hdlSyncTable, table);
                        }
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
                    OrderSaleItemHandler hdl = new OrderSaleItemHandler(SendWsActivity.this);
                    String table = "tb_order_sale_item";
                    if (hdl.webService(SendWsActivity.this))
                    {
                        MsgWebServiceDialog = "Sincronizando Itens do Pedido";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(SendWsActivity.this);
                        String data = "";
                        data = hdlSyncTable.getDateTime(table, "S");
                        List<EtdOrderSaleItem> objList = hdl.getSendWs(data);
                        maxRegs = objList.size();
                        if (maxRegs > 0){
                            dialog.setMax(maxRegs);
                            progresso = 0;
                            dialog.setProgress(progresso);
                            //Order Sale
                            OrderSaleHandler hdlOrderSale = new OrderSaleHandler(SendWsActivity.this);
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
                            updateSender(hdlSyncTable,table);
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

    public class GetWebCompanyId extends Thread {
        @Override
        public void run(){
            synchronized(this){
                tbEntityIdWeb = -1;
                MsgWebServiceDialog = "Buscando código Pessoa Jurídica na Web";
                runOnUiThread(changeMessage);
                CompanyHandler hdlCompany = new CompanyHandler(SendWsActivity.this);
                tbEntityIdWeb = hdlCompany.returnIdWeb(tbEntityId);
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
                MsgWebServiceDialog = "Buscando código Pessoa Física na Web";
                runOnUiThread(changeMessage);
                PersonHandler hdlPerson = new PersonHandler(SendWsActivity.this);
                tbEntityIdWeb = hdlPerson.returnIdWeb(tbEntityId);
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
                EntityHandler hdlEntity = new EntityHandler(SendWsActivity.this);
                EtdEntity obj = hdlEntity.get(tbEntityId);
                if (obj != null) {
                    obj.setId(tbEntityIdWeb);
                    hdlEntity.sendWebService(obj);
                    retorno = hdlEntity.sendWebService(obj);
                    Document doc = XMLfunctions.XMLfromString(retorno);
                    nodes = doc.getElementsByTagName("ENTITY");
                    if (nodes != null) {
                        Element e = (Element) nodes.item(0);
                        if (e != null) {
                            tbEntityIdWeb = Integer.parseInt(XMLfunctions.getElementValue(e));
                        }
                    }
                }
                hdlEntity.close();
                this.notify();
            }
        }
    }

    public void EntitySend()  {
        EntityHandler hdl = new EntityHandler(SendWsActivity.this);
        String table = "tb_entity";
        if (hdl.webService(SendWsActivity.this)) {
            MsgWebServiceDialog = "Sincronizando Entidade";
            runOnUiThread(changeMessage);
            EtdEntity obj = hdl.get(tbEntityId);
            dialog.setProgress(progresso);
            tbEntityIdWeb = 0;
            //Verifica se a entidade é uma pessoa Juridica
            try
            {
                GetWebCompanyId widcompany = new GetWebCompanyId();
                widcompany.start();
                synchronized(widcompany){
                    try{
                        widcompany.wait();
                    }catch(InterruptedException e){
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
                        try{
                            widperson.wait();
                        }catch(InterruptedException e){
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
                        try{
                            sendEntity.wait();
                        }catch(InterruptedException e){
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
        CompanyHandler hdl = new CompanyHandler(SendWsActivity.this);
        String table = "tb_company";
        if (hdl.webService(SendWsActivity.this)){
            MsgWebServiceDialog = "Sincronizando Pessoa Jurídica";
            runOnUiThread(changeMessage);
            EtdCompany obj = hdl.get(tbEntityId);
            if (obj != null) {
                obj.setId(tbEntityIdWeb);
                hdl.sendWebService(obj);
            }
            hdl.close();
        }
    };

    public void PersonSend(){
        try
        {
            PersonHandler hdl = new PersonHandler(SendWsActivity.this);
            String table = "tb_person";
            if (hdl.webService(SendWsActivity.this)) {
                MsgWebServiceDialog = "Sincronizando Pessoa Física";
                runOnUiThread(changeMessage);
                EtdPerson obj = hdl.get(tbEntityId);
                if (obj != null) {
                    obj.setId(tbEntityIdWeb);
                    hdl.sendWebService(obj);
                }
            }
            hdl.close();
        } catch (Exception e) {
            FirebaseCrash.report(new Exception(e.getMessage()));
        }
    }


    public void AddressSend() {
        try
        {
            AddressHandler hdl = new AddressHandler(SendWsActivity.this);
            String table = "tb_address";
            if (hdl.webService(SendWsActivity.this)){
                MsgWebServiceDialog = "Sincronizando Endereços";
                runOnUiThread(changeMessage);
                EtdAddress obj = hdl.get(tbEntityId,"Comercial");
                if (obj != null) {
                    obj.setId(tbEntityIdWeb);
                    hdl.sendWebService(obj);
                }
            }
            hdl.close();
        } catch (Exception e) {
            FirebaseCrash.report(new Exception(e.getMessage()));
        }
    };

    public void PhoneSend(){
        try
        {
            PhoneHandler hdl = new PhoneHandler(SendWsActivity.this);
            String table = "tb_phone";
            if (hdl.webService(SendWsActivity.this)) {
                EtdPhone obj = hdl.get(tbEntityId, "Comercial");
                if (obj != null) {
                    obj.setId(tbEntityIdWeb);
                    hdl.sendWebService(obj);
                }
                hdl.close();
            }
        } catch (Exception e) {
            FirebaseCrash.report(new Exception(e.getMessage()));
        }
    };

    public void MailingSend() {
        try
        {
            MailingHandler hdl = new MailingHandler(SendWsActivity.this);
            String table = "tb_mailing";
            if (hdl.webService(SendWsActivity.this)) {
                MsgWebServiceDialog = "Sincronizando e-mails";
                runOnUiThread(changeMessage);
                EtdMailing obj = hdl.get(tbEntityId,"Comercial");
                if (obj != null) {
                    obj.setId(tbEntityIdWeb);
                    hdl.sendWebService(obj);
                }
                hdl.close();
            }
        } catch (Exception e) {
            FirebaseCrash.report(new Exception(e.getMessage()));
        }
    };



    public void closeSendWsActivity(View view)
    {
        finish();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_send_ws);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(3);
        if (dialog == null)
        {
            dialog = new ProgressDialog(this);
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
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
        getMenuInflater().inflate(R.menu.send_ws, menu);
        return true;
    }

    public void startSendWs(View view)
    {
        MsgWebServiceDialog = "Sincronizando WebService...";
        runOnUiThread(changeMessage);
        dialog.show();
        ThWsOrderSaleSend.start();
        ThWsOrderSaleItemSend.start();
        ThWsFinalizeProcess.start();
    }

}
