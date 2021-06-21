package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdPriceList;
import com.setes.setesvendas.app.pedidovenda.MainActivity;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Element;



public class PriceListHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_price_list";

    public PriceListHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
    }

    public void add(EtdPriceList obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("description", obj.getDescription());
        contentvalues.put("validity", obj.getValidity());
        contentvalues.put("modality", obj.getModality());
        contentvalues.put("aliq_profit", obj.getAliq_profit());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert("tb_price_list", null, contentvalues);
        db.close();
    }

    public void update(EtdPriceList obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("description", obj.getDescription());
        contentvalues.put("validity", obj.getValidity());
        contentvalues.put("modality", obj.getModality());
        contentvalues.put("aliq_profit", obj.getAliq_profit());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_price_list", contentvalues, "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.execSQL(" CREATE TABLE if not exists tb_price_list ( " +
                "id int(11) NOT NULL,  " +
                "tb_institution_id int(11) NOT NULL,  " +
                "description varchar(45) NOT NULL,  " +
                "validity date DEFAULT NULL,  " +
                "modality varchar(1) DEFAULT NULL,  " +
                "aliq_profit decimal(10,0) DEFAULT NULL,  " +
                "created_at datetime,  " +
                "updated_at datetime) ");
        db.close();
    }

    public void delete(EtdPriceList obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_price_list", "id = ?", new String[]{
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


    public EtdPriceList get(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.query("tb_price_list", new String[] {
            "id", "tb_institution_id", "description", "validity", "modality", "aliq_profit"
        }, "id=?", new String[] {
            String.valueOf(id)
        }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            EtdPriceList obj = new EtdPriceList();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
            obj.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            obj.setValidity(cursor.getString(cursor.getColumnIndex("validity")));
            obj.setModality(cursor.getString(cursor.getColumnIndex("modality")));
            obj.setAliq_profit(Double.valueOf(cursor.getDouble(cursor.getColumnIndex("aliq_profit"))));
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

    public ArrayList<EtdPriceList> getAll()
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_price_list", null);
        if (cursor.moveToFirst())
        {
            do
            {
                EtdPriceList obj = new EtdPriceList();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                obj.setValidity(cursor.getString(cursor.getColumnIndex("validity")));
                obj.setModality(cursor.getString(cursor.getColumnIndex("modality")));
                obj.setAliq_profit(Double.valueOf(cursor.getDouble(cursor.getColumnIndex("aliq_profit"))));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_price_list");
        onCreate(sqlitedatabase);
    }

    public boolean  verifyRegister(int id, int tb_institution_id)
    {
        String select = "SELECT  tb_institution_id FROM " + TABLE +
                " where (tb_institution_id = '" + tb_institution_id + "') " +
                " and (id = '" + id + "')";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        boolean retorno = false;
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            retorno = (cursor.getCount() > 0);
        }
        cursor.close();
        db.close();
        return retorno;
    }

    public void persisteWebService(Element element)
    {
        EtdPriceList obj = new EtdPriceList();
        obj.setId(Integer.parseInt(XMLfunctions.getValue(element, "id")));
        obj.setTb_institution_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_institution_id")));
        obj.setDescription(XMLfunctions.getValue(element, "description"));
        obj.setValidity(XMLfunctions.getValue(element, "validity"));
        obj.setModality(XMLfunctions.getValue(element, "modality"));
        if (!XMLfunctions.getValue(element, "aliq_profit").equals("")) {
            obj.setAliq_profit(Double.valueOf(Double.parseDouble(XMLfunctions.getValue(element, "aliq_profit"))));
        }else{
            obj.setAliq_profit(0.0);
        }
        obj.setCreated_at(XMLfunctions.getValue(element, "created_at"));
        if (!verifyRegister(obj.getId(), obj.getTb_institution_id())){
            add(obj);
        }else{
            update(obj);
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
                "SELECT  * " +
                "FROM  tb_price_list " +
                "where ( (created_at > '" + data + "') or (created_at is null) ) " +
                " and (tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution)  + "') " +
          //      " and (id = '" + Integer.toString(MainActivity.gbPriceList)  + "') " +
                "order by created_at " +
                "</QUERY>" +
                "</projetos>";
        String res = "";
        activeWBS();
        res =  wbs.callWebService("price", "C", xml,chave );
        return res;
    }



}
