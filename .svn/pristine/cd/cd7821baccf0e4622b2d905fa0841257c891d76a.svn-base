package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdCustomer;
import com.setes.setesvendas.app.pedidovenda.MainActivity;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.w3c.dom.Element;

// Referenced classes of package com.setes.model:
//            OpenHelper

public class CustomerHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_customer";

    public CustomerHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
        firstRegister();
    }

    public void add(EtdCustomer obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("tb_vendor_id", Integer.valueOf(obj.getTb_vendor_id()));
        contentvalues.put("tb_carrier_id", Integer.valueOf(obj.getTb_carrier_id()));
        contentvalues.put("credit_status", obj.getCredit_status());
        contentvalues.put("credit_value", Double.valueOf(obj.getCredit_value()));
        contentvalues.put("wallet", obj.getWallet());
        contentvalues.put("note", obj.getNote());
        contentvalues.put("consumer", obj.getConsumer());
        contentvalues.put("dealer", obj.getDealer());
        contentvalues.put("state", obj.getState());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert("tb_customer", null, contentvalues);
        db.close();
    }

    public void update(EtdCustomer obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("tb_vendor_id", Integer.valueOf(obj.getTb_vendor_id()));
        contentvalues.put("tb_carrier_id", Integer.valueOf(obj.getTb_carrier_id()));
        contentvalues.put("credit_status", obj.getCredit_status());
        contentvalues.put("credit_value", Double.valueOf(obj.getCredit_value()));
        contentvalues.put("wallet", obj.getWallet());
        contentvalues.put("note", obj.getNote());
        contentvalues.put("consumer", obj.getConsumer());
        contentvalues.put("dealer", obj.getDealer());
        contentvalues.put("state", obj.getState());
        contentvalues.put("created_at", obj.getCreated_at());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_customer", contentvalues, "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" CREATE TABLE if not exists tb_customer ( id int(11) NOT NULL , tb_institution_id int(11) NOT NULL , tb_vendor_id int(11) DEFAULT NULL, tb_carrier_id int(11) DEFAULT NULL, credit_status decimal(10,2) DEFAULT NULL, credit_value decimal(10,2) DEFAULT NULL, wallet char(1) DEFAULT NULL, note blob, consumer char(1) DEFAULT NULL, dealer char(1) DEFAULT NULL, state char(1) DEFAULT NULL,  created_at datetime,  updated_at datetime) ");
        db.close();
    }

    public void delete(EtdCustomer obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tb_customer", "id = ?", new String[] {
            String.valueOf(obj.getId())
        });
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, null, null);
        db.close();
    }

    public void firstRegister()
    {
        if (!verifyRegisterByField("tb_customer", "id", "0"))
        {
            EtdCustomer obj = new EtdCustomer();
            obj.setId(0);
            add(obj);
        }
    }

    public EtdCustomer get(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE, new String[]{"id", "tb_institution_id", "tb_vendor_id", "tb_carrier",
                        "credit_status", "credit_value", "wallet", "note", "consumer",
                        "dealer", "state", "created_at", "updated_at"}, "id=?",
                new String[]{String.valueOf(Integer.toString(id))}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            EtdCustomer obj = new EtdCustomer();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
            obj.setTb_vendor_id(cursor.getInt(cursor.getColumnIndex("tb_vendor_id")));
            obj.setTb_carrier_id(cursor.getInt(cursor.getColumnIndex("tb_carrier_id")));
            obj.setCredit_status(cursor.getString(cursor.getColumnIndex("credit_status")));
            obj.setCredit_value(cursor.getDouble(cursor.getColumnIndex("credit_value")));
            obj.setWallet(cursor.getString(cursor.getColumnIndex("wallet")));
            obj.setNote(cursor.getString(cursor.getColumnIndex("note")));
            obj.setConsumer(cursor.getString(cursor.getColumnIndex("consumer")));
            obj.setDealer(cursor.getString(cursor.getColumnIndex("dealer")));
            obj.setState(cursor.getString(cursor.getColumnIndex("state")));
            cursor.close();
            db.close();
            return obj;
        } else
        {
            cursor.close();
            db.close();
            return null;
        }
    }

    public List getAll()
    {
        ArrayList arraylist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_customer", null);
        if (cursor.moveToFirst())
        {
            do
            {
                EtdCustomer obj = new EtdCustomer();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setTb_vendor_id(cursor.getInt(cursor.getColumnIndex("tb_vendor_id")));
                obj.setTb_carrier_id(cursor.getInt(cursor.getColumnIndex("tb_carrier_id")));
                obj.setCredit_status(cursor.getString(cursor.getColumnIndex("credit_status")));
                obj.setCredit_value(cursor.getDouble(cursor.getColumnIndex("credit_value")));
                obj.setWallet(cursor.getString(cursor.getColumnIndex("wallet")));
                obj.setNote(cursor.getString(cursor.getColumnIndex("note")));
                obj.setConsumer(cursor.getString(cursor.getColumnIndex("consumer")));
                obj.setDealer(cursor.getString(cursor.getColumnIndex("dealer")));
                obj.setState(cursor.getString(cursor.getColumnIndex("state")));
                arraylist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arraylist;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_customer");
        onCreate(sqlitedatabase);
    }

    public void persisteWebService(Element element, long l)
    {
        EtdCustomer obj = new EtdCustomer();
        obj.setId((int)l);
        obj.setTb_institution_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_institution_id")));
        obj.setTb_vendor_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_vendor_id")));
        obj.setTb_carrier_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_carrier_id")));
        obj.setCredit_status(XMLfunctions.getValue(element, "credit_status"));
        obj.setCredit_value(Double.parseDouble(XMLfunctions.getValue(element, "credit_value")));
        obj.setWallet(XMLfunctions.getValue(element, "wallet"));
        obj.setNote(XMLfunctions.getValue(element, "note"));
        obj.setConsumer(XMLfunctions.getValue(element, "consumer"));
        obj.setDealer(XMLfunctions.getValue(element, "dealer"));
        obj.setState(XMLfunctions.getValue(element, "state"));
        obj.setCreated_at(XMLfunctions.getValue(element, "created_at"));
        if (verifyRegisterByField("tb_customer", "id", Long.toString(l)))
        {
            update(obj);
            return;
        } else
        {
            add(obj);
            return;
        }
    }

    public String receiveWebService(String data)
    {
        createTable();
        String chave = "";
        chave = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                "<chaves>" +
                "<id>0</id>" +
                "</chaves>";
        String xml = "";
        xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<projetos>" +
                "<QUERY>" +
                "SELECT tb_customer.* , tb_person.cpf doc " +
                "FROM tb_customer " +
                "  INNER JOIN tb_person " +
                "  ON ( tb_person.id = tb_customer.id )   " +
                "  inner join tb_institution_has_entity  " +
                "  on (tb_institution_has_entity.tb_entity_id = tb_customer.id) " +
                "where ((tb_customer.created_at > '" + data + "') or (tb_customer.created_at is null)) " +
                "  and ( tb_customer.tb_vendor_id = '"  + Integer.toString(MainActivity.gbSalesman)  + "') " +
                "  and (tb_institution_has_entity.tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution) + "') " +
                "  and (tb_institution_has_entity.tb_institution_id > 0) "+
                "UNION " +
                "SELECT tb_customer.*, tb_company.cnpj doc " +
                "FROM tb_customer " +
                "  INNER JOIN tb_company " +
                "  ON ( tb_company.id = tb_customer.id ) " +
                "  inner join tb_institution_has_entity  " +
                "  on (tb_institution_has_entity.tb_entity_id = tb_customer.id) " +
                "where ((tb_customer.created_at > '" + data + "') or (tb_customer.created_at is null))  " +
                "  and ( tb_customer.tb_vendor_id = '"  + Integer.toString(MainActivity.gbSalesman)  + "') " +
                "  and (tb_institution_has_entity.tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution) + "') " +
                "  and (tb_institution_has_entity.tb_institution_id > 0) "+
                "</QUERY>" +
                "</projetos>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("customer", "C", xml, chave);
        return res;
    }



}
