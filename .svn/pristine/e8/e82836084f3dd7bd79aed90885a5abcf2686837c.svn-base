package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdCountry;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.w3c.dom.Element;


public class CountryHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_country";

    public CountryHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
    }

    public void add(EtdCountry obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("name", obj.getName());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert("tb_country", null, contentvalues);
        db.close();
    }

    public void update(EtdCountry obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("name", obj.getName());
        contentvalues.put("created_at", obj.getCreated_at());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_country", contentvalues, "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" CREATE TABLE if not exists tb_country ( " +
                "id int(11) NOT NULL,  " +
                "name varchar(100) DEFAULT NULL,  " +
                "created_at datetime,  " +
                "updated_at datetime) ");
        db.close();
    }

    public void delete(EtdCountry obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_country", "id = ?", new String[] {
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

    public EtdCountry get(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE,
                new String[]{"id", "name"}, "id=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            EtdCountry obj = new EtdCountry();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setName(cursor.getString(cursor.getColumnIndex("name")));
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

    public ArrayList<EtdCountry> getAll()
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE, null);
        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                EtdCountry obj = new EtdCountry();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setName(cursor.getString(cursor.getColumnIndex("name")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_country");
        onCreate(sqlitedatabase);
    }

    public void persisteWebService(Element element)
    {
        EtdCountry obj = new EtdCountry();
        obj.setId(Integer.parseInt(XMLfunctions.getValue(element, "id")));
        obj.setName(XMLfunctions.getValue(element, "name").toUpperCase());
        obj.setCreated_at(XMLfunctions.getValue(element, "created_at"));
        if (!verifyRegisterByField(TABLE,"id",Integer.toString(obj.getId()))) {
            add(obj);
        }else{
            update(obj);
        }

    }

    public String receiveWebService(String data)
    {
        createTable();
        String chave ="";
        chave = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                "<chaves>" +
                "<id>0</id>" +
                "</chaves>";
        String xml = "";
        xml = "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>" +
                "<projetos>" +
                "<QUERY>" +
                "SELECT tb_country.* " +
                "FROM  tb_country " +
                "where ((created_at > '" + data +"') or (created_at is null)) " +
                " and (name is not null) "+
                "order by created_at " +
                "</QUERY>" +
                "</projetos>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("country", "C", xml, chave);
        return res;
    }



}
