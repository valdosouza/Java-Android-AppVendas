package com.setes.setesvendas.app.pedidovenda;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.setes.setesvendas.app.controller.EtdSyncTable;
import com.setes.setesvendas.app.model.AddressHandler;
import com.setes.setesvendas.app.model.CityHandler;
import com.setes.setesvendas.app.model.CompanyHandler;
import com.setes.setesvendas.app.model.CountryHandler;
import com.setes.setesvendas.app.model.CustomerHandler;
import com.setes.setesvendas.app.model.EntityHandler;
import com.setes.setesvendas.app.model.MailingHandler;
import com.setes.setesvendas.app.model.PersonHandler;
import com.setes.setesvendas.app.model.PhoneHandler;
import com.setes.setesvendas.app.model.PriceHandler;
import com.setes.setesvendas.app.model.PriceListHandler;
import com.setes.setesvendas.app.model.ProductHandler;
import com.setes.setesvendas.app.model.StateHandler;
import com.setes.setesvendas.app.model.SyncTableHandler;
import com.setes.setesvendas.app.util.XMLfunctions;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.setes.setesvendas.app.R;

public class ReceiveWsActivity extends Activity
{

    public String MsgWebServiceDialog;

    Thread ThWsAddressReceive;
    Thread ThWsCityReceive;
    Thread ThWsCompanyReceive;
    Thread ThWsCountryReceive;
    Thread ThWsCustomerReceive;
    Thread ThWsMailingReceive;
    Thread ThWsEntityReceive;
    Thread ThWsPersonReceive;
    Thread ThWsPhoneReceive;
    Thread ThWsPriceListReceive;
    Thread ThWsPriceReceive;
    Thread ThWsProductReceive;
    Thread ThWsStateReceive;
    private Runnable changeMessage;
    public ProgressDialog dialog;
    public int maxRegs;
    public NodeList nodes;
    public int progresso;
    public Bundle errorMessage  = new Bundle();


    public void updateReceiver(SyncTableHandler hdl, String table) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        EtdSyncTable objSynTable = new EtdSyncTable();
        objSynTable.setId(table);
        objSynTable.setUpdateAt(formatter.format(date));
        //Atualizar recebimento
        objSynTable.setDirection("R");
        if (hdl.verifyRegister(table, "R")) {
            hdl.update(objSynTable);
        } else {
            hdl.add(objSynTable);
        }
        //Atualizar envio
        objSynTable.setDirection("S");
        if (hdl.verifyRegister(table, "S")) {
            hdl.update(objSynTable);
        } else {
            hdl.add(objSynTable);
        }
        hdl.close();
    }

    public ReceiveWsActivity()
    {
        maxRegs = 0;
        progresso = 0;
        changeMessage = new Runnable() {
            public void run()
            {
                dialog.setTitle(MsgWebServiceDialog);
            }
        };


        ThWsCountryReceive = new Thread() {
            public void run()
            {
                try{
                    CountryHandler hdl = new CountryHandler(ReceiveWsActivity.this);
                    String table = "tb_country";
                    if (hdl.webService(ReceiveWsActivity.this))
                    {
                        MsgWebServiceDialog = "Sincronizando Paises";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(ReceiveWsActivity.this);
                        String retorno ="";
                        retorno = hdlSyncTable.getDateTime(table, "R");
                        retorno = hdl.receiveWebService(retorno);
                        Document doc = XMLfunctions.XMLfromString(retorno);
                        if (doc != null) {
                            nodes = doc.getElementsByTagName("dados");
                            maxRegs = nodes.getLength();
                        }else{
                            maxRegs = 0;
                        }
                        dialog.setMax(maxRegs);
                        progresso = 0;
                        dialog.setProgress(progresso);
                        while (progresso <= maxRegs - 1 && maxRegs > 0)
                        {
                            Element e = (Element)nodes.item(progresso);
                            hdl.persisteWebService(e);
                            //Atualizar a data do ultimo registro recebido
                            progresso++;
                            dialog.setProgress(progresso);
                        }
                        if (maxRegs > 0){
                            updateReceiver(hdlSyncTable, table);
                        }
                    }
                    hdl.close();
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
        };

        ThWsStateReceive = new Thread() {
            public void run()
            {
                try
                {
                    ThWsCountryReceive.join();
                }
                catch (InterruptedException interruptedexception)
                {
                    FirebaseCrash.report(new Exception(interruptedexception.getMessage()));
                }

                try
                {
                    StateHandler hdl = new StateHandler(ReceiveWsActivity.this);
                    String table = "tb_state";
                    if (hdl.webService(ReceiveWsActivity.this))
                    {
                        MsgWebServiceDialog = "Sincronizando Estados";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(ReceiveWsActivity.this);
                        String retorno ="";
                        retorno = hdlSyncTable.getDateTime(table, "R");
                        retorno = hdl.receiveWebService(retorno);
                        Document doc = XMLfunctions.XMLfromString(retorno);
                        if (doc != null) {
                            nodes = doc.getElementsByTagName("dados");
                            maxRegs = nodes.getLength();
                        }else{
                            maxRegs = 0;
                        }
                        dialog.setMax(maxRegs);
                        progresso = 0;
                        dialog.setProgress(progresso);
                        while (progresso <= maxRegs - 1 && maxRegs > 0)
                        {
                            Element e = (Element)nodes.item(progresso);
                            hdl.persisteWebService(e);
                            //Atualizar a data do ultimo registro recebido
                            progresso++;
                            dialog.setProgress(progresso);
                        }
                        if (maxRegs > 0){
                            updateReceiver(hdlSyncTable, table);
                        }
                    }
                    hdl.close();
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
        };
        ThWsCityReceive = new Thread() {
            public void run()
            {
                try
                {
                    ThWsStateReceive.join();
                }
                catch (InterruptedException interruptedexception)
                {
                    FirebaseCrash.report(new Exception(interruptedexception.getMessage()));
                }
                try
                {
                    CityHandler hdl = new CityHandler(ReceiveWsActivity.this);
                    String table = "tb_city";
                    if (hdl.webService(ReceiveWsActivity.this))
                    {
                        MsgWebServiceDialog = "Sincronizando Cidade";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(ReceiveWsActivity.this);
                        String retorno ="";
                        retorno = hdlSyncTable.getDateTime(table, "R");
                        retorno = hdl.receiveWebService(retorno);
                        Document doc = XMLfunctions.XMLfromString(retorno);
                        if (doc != null) {
                            nodes = doc.getElementsByTagName("dados");
                            maxRegs = nodes.getLength();
                        }else{
                            maxRegs = 0;
                        }
                        dialog.setMax(maxRegs);
                        progresso = 0;
                        dialog.setProgress(progresso);
                        while (progresso <= maxRegs - 1 && maxRegs > 0)
                        {
                            Element e = (Element)nodes.item(progresso);
                            hdl.persisteWebService(e);
                            //Atualizar a data do ultimo registro recebido
                            progresso++;
                            dialog.setProgress(progresso);
                        }
                        if (maxRegs > 0){
                            updateReceiver(hdlSyncTable, table);
                        }
                    }
                    hdl.close();
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
        };

        ThWsPersonReceive = new Thread() {
            public void run()
            {
                try
                {
                    ThWsCityReceive.join();
                }
                catch (InterruptedException interruptedexception) {
                    FirebaseCrash.report(new Exception(interruptedexception.getMessage()));
                }
                try
                {
                    PersonHandler hdl = new PersonHandler(ReceiveWsActivity.this);
                    String table = "tb_person";
                    if (hdl.webService(ReceiveWsActivity.this))
                    {
                        MsgWebServiceDialog = "Sincronizando Pessoas Físicas";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(ReceiveWsActivity.this);
                        String retorno ="";
                        retorno = hdlSyncTable.getDateTime(table, "R");
                        retorno = hdl.receiveWebService(retorno);
                        Document doc = XMLfunctions.XMLfromString(retorno);
                        if (doc != null) {
                            nodes = doc.getElementsByTagName("dados");
                            maxRegs = nodes.getLength();
                        }else{
                            maxRegs = 0;
                        }
                        dialog.setMax(maxRegs);
                        progresso = 0;
                        dialog.setProgress(progresso);
                        while (progresso <= maxRegs - 1 && maxRegs > 0)
                        {
                            Element e = (Element)nodes.item(progresso);
                            hdl.persisteWebService(e);
                            //Atualizar a data do ultimo registro recebido
                            progresso++;
                            dialog.setProgress(progresso);
                        }
                        if (maxRegs > 0){
                            updateReceiver(hdlSyncTable, table);
                        }
                    }
                    hdl.close();
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
        };

        ThWsCompanyReceive = new Thread() {
            public void run() {
                try {
                    ThWsPersonReceive.join();
                } catch (InterruptedException interruptedexception) {
                    FirebaseCrash.report(new Exception(interruptedexception.getMessage()));
                    ;
                }
                try
                {
                    CompanyHandler hdl = new CompanyHandler(ReceiveWsActivity.this);
                    String table = "tb_company";
                    if (hdl.webService(ReceiveWsActivity.this))
                    {
                        MsgWebServiceDialog = "Sincronizando Pessoas Juridicas";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(ReceiveWsActivity.this);
                        String retorno ="";
                        retorno = hdlSyncTable.getDateTime(table, "R");
                        retorno = hdl.receiveWebService(retorno);
                        Document doc = XMLfunctions.XMLfromString(retorno);
                        if (doc != null) {
                            nodes = doc.getElementsByTagName("dados");
                            maxRegs = nodes.getLength();
                        }else{
                            maxRegs = 0;
                        }
                        dialog.setMax(maxRegs);
                        progresso = 0;
                        dialog.setProgress(progresso);
                        while (progresso <= maxRegs - 1 && maxRegs > 0)
                        {
                            Element e = (Element)nodes.item(progresso);
                            hdl.persisteWebService(e);
                            //Atualizar a data do ultimo registro recebido
                            progresso++;
                            dialog.setProgress(progresso);
                        }
                        if (maxRegs > 0){
                            updateReceiver(hdlSyncTable, table);
                        }
                    }
                    hdl.close();
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
        };

        ThWsEntityReceive = new Thread() {
            public void run()
            {
                try
                {
                    ThWsCompanyReceive.join();
                }
                catch (InterruptedException interruptedexception) {
                    FirebaseCrash.report(new Exception(interruptedexception.getMessage()));
                }
                try
                {
                    EntityHandler hdlEntity = new EntityHandler(ReceiveWsActivity.this);
                    CompanyHandler hdlCompany = new CompanyHandler(ReceiveWsActivity.this);
                    PersonHandler hdlPerson = new PersonHandler(ReceiveWsActivity.this);
                    String table = "tb_entity";
                    if (hdlEntity.webService(ReceiveWsActivity.this))
                    {
                        MsgWebServiceDialog = "Sincronizando Entidades";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(ReceiveWsActivity.this);
                        String retorno ="";
                        retorno = hdlSyncTable.getDateTime(table, "R");
                        retorno = hdlEntity.receiveWebService(retorno);
                        Document doc = XMLfunctions.XMLfromString(retorno);
                        if (doc != null) {
                            nodes = doc.getElementsByTagName("dados");
                            maxRegs = nodes.getLength();
                        }else{
                            maxRegs = 0;
                        }
                        dialog.setMax(maxRegs);
                        progresso = 0;
                        dialog.setProgress(progresso);
                        String docRef = "";
                        int id = 0;
                        while (progresso <= maxRegs - 1 && maxRegs > 0)
                        {
                            Element e = (Element)nodes.item(progresso);
                            docRef = XMLfunctions.getValue(e, "doc");
                            //Verifica o Id da Entidade no Tablet
                            if (docRef.length() == 11)
                            {
                                id = hdlPerson.getIdByCpf(docRef);
                            } else
                            {
                                id = hdlCompany.getIdByCNPJ(docRef);
                            }
                            hdlEntity.persisteWebService(e,id);
                            //Atualizar a data do ultimo registro recebido
                            progresso++;
                            dialog.setProgress(progresso);
                        }
                        if (maxRegs > 0){
                            updateReceiver(hdlSyncTable, table);
                        }
                    }
                    hdlEntity.close();
                    hdlCompany.close();
                    hdlPerson.close();
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
        };

        ThWsMailingReceive = new Thread() {
            public void run()
            {
                try
                {
                    ThWsEntityReceive.join();
                }
                catch (InterruptedException interruptedexception) {
                    FirebaseCrash.report(new Exception(interruptedexception.getMessage()));
                }
                try
                {
                    MailingHandler hdlMailing = new MailingHandler(ReceiveWsActivity.this);
                    CompanyHandler hdlCompany = new CompanyHandler(ReceiveWsActivity.this);
                    PersonHandler hdlPerson = new PersonHandler(ReceiveWsActivity.this);
                    String table = "tb_mailing";
                    if (hdlMailing.webService(ReceiveWsActivity.this))
                    {
                        MsgWebServiceDialog = "Sincronizando e-mails";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(ReceiveWsActivity.this);
                        String retorno ="";
                        retorno = hdlSyncTable.getDateTime(table, "R");
                        retorno = hdlMailing.receiveWebService(retorno);
                        Document doc = XMLfunctions.XMLfromString(retorno);
                        if (doc != null) {
                            nodes = doc.getElementsByTagName("dados");
                            maxRegs = nodes.getLength();
                        }else{
                            maxRegs = 0;
                        }
                        dialog.setMax(maxRegs);
                        progresso = 0;
                        dialog.setProgress(progresso);
                        String docRef = "";
                        int id = 0;
                        while (progresso <= maxRegs - 1 && maxRegs > 0)
                        {
                            Element e = (Element)nodes.item(progresso);
                            docRef = XMLfunctions.getValue(e, "doc");
                            //Verifica o Id da Entidade no Tablet
                            if (docRef.length() == 11)
                            {
                                id = hdlPerson.getIdByCpf(docRef);
                            } else
                            {
                                id = hdlCompany.getIdByCNPJ(docRef);
                            }
                            hdlMailing.persisteWebService(e,id);
                            //Atualizar a data do ultimo registro recebido
                            progresso++;
                            dialog.setProgress(progresso);
                        }
                        if (maxRegs > 0){
                            updateReceiver(hdlSyncTable, table);
                        }
                    }
                    hdlMailing.close();
                    hdlCompany.close();
                    hdlPerson.close();
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
        };
        ThWsAddressReceive = new Thread() {
            public void run()
            {
                try
                {
                    ThWsMailingReceive.join();
                }
                catch (InterruptedException interruptedexception) {
                    FirebaseCrash.report(new Exception(interruptedexception.getMessage()));
                }
                try
                {
                    AddressHandler hdlAddress = new AddressHandler(ReceiveWsActivity.this);
                    CompanyHandler hdlCompany = new CompanyHandler(ReceiveWsActivity.this);
                    PersonHandler hdlPerson = new PersonHandler(ReceiveWsActivity.this);
                    String table = "tb_address";
                    if (hdlAddress.webService(ReceiveWsActivity.this))
                    {
                        MsgWebServiceDialog = "Sincronizando Endereços";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(ReceiveWsActivity.this);
                        String retorno ="";
                        retorno = hdlSyncTable.getDateTime(table, "R");
                        retorno = hdlAddress.receiveWebService(retorno);
                        Document doc = XMLfunctions.XMLfromString(retorno);
                        if (doc != null) {
                            nodes = doc.getElementsByTagName("dados");
                            maxRegs = nodes.getLength();
                        }else{
                            maxRegs = 0;
                        }
                        dialog.setMax(maxRegs);
                        progresso = 0;
                        dialog.setProgress(progresso);
                        String docRef = "";
                        int id = 0;
                        while (progresso <= maxRegs - 1 && maxRegs > 0)
                        {
                            Element e = (Element)nodes.item(progresso);
                            docRef = XMLfunctions.getValue(e, "doc");
                            //Verifica o Id da Entidade no Tablet
                            if (docRef.length() == 11)
                            {
                                id = hdlPerson.getIdByCpf(docRef);
                            } else
                            {
                                id = hdlCompany.getIdByCNPJ(docRef);
                            }
                            hdlAddress.persisteWebService(e,id);
                            //Atualizar a data do ultimo registro recebido
                            progresso++;
                            dialog.setProgress(progresso);
                        }
                        if (maxRegs > 0){
                            updateReceiver(hdlSyncTable, table);
                        }
                    }
                    hdlAddress.close();
                    hdlCompany.close();
                    hdlPerson.close();
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
        };
        ThWsPhoneReceive = new Thread() {
            public void run()
            {
                try
                {
                    ThWsAddressReceive.join();
                }
                catch (InterruptedException interruptedexception) {
                    FirebaseCrash.report(new Exception(interruptedexception.getMessage()));
                }
                try
                {
                    PhoneHandler hdlPhone = new PhoneHandler(ReceiveWsActivity.this);
                    CompanyHandler hdlCompany = new CompanyHandler(ReceiveWsActivity.this);
                    PersonHandler hdlPerson = new PersonHandler(ReceiveWsActivity.this);
                    String table = "tb_phone";
                    if (hdlPhone.webService(ReceiveWsActivity.this))
                    {
                        MsgWebServiceDialog = "Sincronizando Telefones";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(ReceiveWsActivity.this);
                        String retorno ="";
                        retorno = hdlSyncTable.getDateTime(table, "R");
                        retorno = hdlPhone.receiveWebService(retorno);

                        Document doc = XMLfunctions.XMLfromString(retorno);
                        if (doc != null) {
                            nodes = doc.getElementsByTagName("dados");
                            maxRegs = nodes.getLength();
                        }else{
                            maxRegs = 0;
                        }
                        dialog.setMax(maxRegs);
                        progresso = 0;
                        dialog.setProgress(progresso);
                        String docRef = "";
                        int id = 0;
                        while (progresso <= maxRegs - 1 && maxRegs > 0)
                        {
                            Element e = (Element)nodes.item(progresso);
                            docRef = XMLfunctions.getValue(e, "doc");
                            //Verifica o Id da Entidade no Tablet
                            if (docRef.length() == 11)
                            {
                                id = hdlPerson.getIdByCpf(docRef);
                            } else
                            {
                                id = hdlCompany.getIdByCNPJ(docRef);
                            }
                            hdlPhone.persisteWebService(e,id);
                            progresso++;
                            dialog.setProgress(progresso);
                        }
                        if (maxRegs > 0){
                            updateReceiver(hdlSyncTable, table);
                        }
                    }
                    hdlPhone.close();
                    hdlCompany.close();
                    hdlPerson.close();
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
        };

        ThWsCustomerReceive = new Thread() {
            public void run()
            {
                try
                {
                    ThWsPhoneReceive.join();
                }
                catch (InterruptedException interruptedexception) {
                    FirebaseCrash.report(new Exception(interruptedexception.getMessage()));
                }
                try
                {
                    CustomerHandler hdlCustomer = new CustomerHandler(ReceiveWsActivity.this);
                    CompanyHandler hdlCompany = new CompanyHandler(ReceiveWsActivity.this);
                    PersonHandler hdlPerson = new PersonHandler(ReceiveWsActivity.this);
                    String table = "tb_customer";
                    if (hdlCustomer.webService(ReceiveWsActivity.this))
                    {
                        MsgWebServiceDialog = "Sincronizando Clientes";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(ReceiveWsActivity.this);
                        String retorno ="";
                        retorno = hdlSyncTable.getDateTime(table, "R");
                        retorno = hdlCustomer.receiveWebService(retorno);
                        Document doc = XMLfunctions.XMLfromString(retorno);
                        if (doc != null) {
                            nodes = doc.getElementsByTagName("dados");
                            maxRegs = nodes.getLength();
                        }else{
                            maxRegs = 0;
                        }
                        dialog.setMax(maxRegs);
                        progresso = 0;
                        dialog.setProgress(progresso);
                        String docRef = "";
                        int id = 0;
                        while (progresso <= maxRegs - 1 && maxRegs > 0)
                        {
                            Element e = (Element)nodes.item(progresso);
                            docRef = XMLfunctions.getValue(e, "doc");
                            //Verifica o Id da Entidade no Tablet
                            if (docRef.length() == 11)
                            {
                                id = hdlPerson.getIdByCpf(docRef);
                            } else
                            {
                                id = hdlCompany.getIdByCNPJ(docRef);
                            }
                            hdlCustomer.persisteWebService(e,id);
                            progresso++;
                            dialog.setProgress(progresso);
                        }
                        if (maxRegs > 0){
                            updateReceiver(hdlSyncTable, table);
                        }
                    }
                    hdlCustomer.close();
                    hdlCompany.close();
                    hdlPerson.close();
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
        };

        ThWsProductReceive = new Thread() {
            public void run()
            {
                try
                {
                    ThWsCustomerReceive.join();
                }
                catch (InterruptedException interruptedexception) {
                    FirebaseCrash.report(new Exception(interruptedexception.getMessage()));
                }
                try
                {
                    ProductHandler hdl = new ProductHandler(ReceiveWsActivity.this);
                    String table = "tb_product";
                    if (hdl.webService(ReceiveWsActivity.this))
                    {
                        MsgWebServiceDialog = "Sincronizando Produtos";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(ReceiveWsActivity.this);
                        String retorno ="";
                        retorno = hdlSyncTable.getDateTime(table, "R");
                        retorno = hdl.receiveWebService(retorno);
                        Document doc = XMLfunctions.XMLfromString(retorno);
                        if (doc != null) {
                            nodes = doc.getElementsByTagName("dados");
                            maxRegs = nodes.getLength();
                        }else{
                            maxRegs = 0;
                        }
                        dialog.setMax(maxRegs);
                        progresso = 0;
                        dialog.setProgress(progresso);
                        while (progresso <= maxRegs - 1 && maxRegs > 0)
                        {
                            Element e = (Element)nodes.item(progresso);
                            hdl.persisteWebService(e);
                            progresso++;
                            dialog.setProgress(progresso);
                        }
                        if (maxRegs > 0){
                            updateReceiver(hdlSyncTable, table);
                        }
                    }
                    hdl.close();
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
        };

        ThWsPriceListReceive = new Thread() {
            public void run()
            {
                try
                {
                    ThWsProductReceive.join();
                }
                catch (InterruptedException interruptedexception) {
                    FirebaseCrash.report(new Exception(interruptedexception.getMessage()));
                }
                try
                {
                    PriceListHandler hdl = new PriceListHandler(ReceiveWsActivity.this);
                    String table = "tb_price_list";
                    if (hdl.webService(ReceiveWsActivity.this))
                    {
                        MsgWebServiceDialog = "Sincronizando Lista de Preços";
                        runOnUiThread(changeMessage);
                        SyncTableHandler hdlSyncTable = new SyncTableHandler(ReceiveWsActivity.this);
                        String retorno ="";
                        retorno = hdlSyncTable.getDateTime(table, "R");
                        retorno = hdl.receiveWebService(retorno);
                        Document doc = XMLfunctions.XMLfromString(retorno);
                        if (doc != null) {
                            nodes = doc.getElementsByTagName("dados");
                            maxRegs = nodes.getLength();
                        }else{
                            maxRegs = 0;
                        }
                        dialog.setMax(maxRegs);
                        progresso = 0;
                        dialog.setProgress(progresso);
                        while (progresso <= maxRegs - 1 && maxRegs > 0)
                        {
                            Element e = (Element)nodes.item(progresso);
                            hdl.persisteWebService(e);
                            progresso++;
                            dialog.setProgress(progresso);
                        }
                        if (maxRegs > 0){
                            updateReceiver(hdlSyncTable, table);
                        }
                    }
                    hdl.close();
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception(e.getMessage()));
                }
            }
        };

        ThWsPriceReceive = new Thread() {
            public void run()
            {
            try
            {
                ThWsPriceListReceive.join();
            }
            catch (InterruptedException interruptedexception) {
                FirebaseCrash.report(new Exception(interruptedexception.getMessage()));
            }
            try
            {
                PriceHandler hdl = new PriceHandler(ReceiveWsActivity.this);
                String table = "tb_price";
                if (hdl.webService(ReceiveWsActivity.this))
                {
                    MsgWebServiceDialog = "Sincronizando os Preços";
                    runOnUiThread(changeMessage);
                    SyncTableHandler hdlSyncTable = new SyncTableHandler(ReceiveWsActivity.this);
                    String retorno ="";
                    retorno = hdlSyncTable.getDateTime(table, "R");
                    retorno = hdl.receiveWebService(retorno);
                    Document doc = XMLfunctions.XMLfromString(retorno);
                    if (doc != null) {
                        nodes = doc.getElementsByTagName("dados");
                        maxRegs = nodes.getLength();
                    }else{
                        maxRegs = 0;
                    }
                    dialog.setMax(maxRegs);
                    progresso = 0;
                    dialog.setProgress(progresso);
                    while (progresso <= maxRegs - 1 && maxRegs > 0)
                    {
                        Element e = (Element)nodes.item(progresso);
                        hdl.persisteWebService(e);
                        progresso++;
                        dialog.setProgress(progresso);
                    }
                    if (maxRegs > 0){
                        updateReceiver(hdlSyncTable, table);
                    }
                }
                hdl.close();
                dialog.dismiss();
                finish();
            } catch (Exception e) {
                FirebaseCrash.report(new Exception(e.getMessage()));
            }
            }
        };
    }

    public void closeReceiveWsActivity(View view)
    {
        finish();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_receive_ws);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (dialog == null)
        {
            dialog = new ProgressDialog(ReceiveWsActivity.this);
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
        getMenuInflater().inflate(R.menu.receive_ws, menu);
        return true;
    }

    public void startReceiveWs(View view)
        throws InterruptedException
    {
        MsgWebServiceDialog = "Sincronizando WebService...";
        runOnUiThread(changeMessage);
        dialog.show();
        ThWsCountryReceive.start();
        ThWsStateReceive.start();
        ThWsCityReceive.start();
        ThWsPersonReceive.start();
        ThWsCompanyReceive.start();
        ThWsEntityReceive.start();
        ThWsMailingReceive.start();
        ThWsAddressReceive.start();
        ThWsPhoneReceive.start();
        ThWsCustomerReceive.start();
        ThWsProductReceive.start();
        ThWsPriceListReceive.start();
        ThWsPriceReceive.start();

    }

}
