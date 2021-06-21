package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdState;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.w3c.dom.Element;

public class StateHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_state";

    public StateHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
    }

    public void add(EtdState obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("sigla", obj.getSigla());
        contentvalues.put("name", obj.getName());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert("tb_state", null, contentvalues);
        db.close();
    }

    public void update(EtdState obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("sigla", obj.getSigla());
        contentvalues.put("name", obj.getName());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_state", contentvalues, "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.execSQL(" CREATE TABLE if not exists tb_state ( " +
                "id int(11) NOT NULL,  " +
                "sigla varchar(2), " +
                "name varchar(100) DEFAULT NULL ,  " +
                "created_at datetime,  " +
                "updated_at datetime) ");
        db.close();
    }

    public void delete(EtdState obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_state", "id = ?", new String[] {
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


    public EtdState get(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.query("tb_state", new String[] {
            "id", "sigla", "name"
        }, "id=?", new String[] {
            String.valueOf(id)
        }, null, null, null, null);
        if ((cursor != null) && cursor.getCount() > 0) {
            cursor.moveToFirst();
            EtdState obj = new EtdState();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setSigla(cursor.getString(cursor.getColumnIndex("sigla")));
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

    public ArrayList<EtdState> getAll()
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_state order by name ", null);
        if (cursor.moveToFirst())
        {
            do
            {
                EtdState obj = new EtdState();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setSigla(cursor.getString(cursor.getColumnIndex("sigla")));
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
        db.execSQL("DROP TABLE IF EXISTS tb_state");
        onCreate(sqlitedatabase);
    }

    public void persisteWebService(Element element)
    {
        EtdState obj = new EtdState();
        obj.setId(Integer.parseInt(XMLfunctions.getValue(element, "id")));
        obj.setSigla(XMLfunctions.getValue(element, "abbreviation").toUpperCase());
        obj.setName(XMLfunctions.getValue(element, "name").toUpperCase());
        obj.setCreated_at(XMLfunctions.getValue(element, "created_at"));
        if (!verifyRegisterByField("tb_state","id", Integer.toString(obj.getId()))) {
            add(obj);
        }else{
            update(obj);
        }
    }

    public String receiveWebService(String data)
    {
        createTable();
        String chave = "";
        chave = "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>" +
                "<chaves>" +
                "<id>0</id>" +
                "</chaves>";
        String xml= "";
        xml = "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>" +
                "<projetos>" +
                "<QUERY>" +
                "SELECT tb_state.* " +
                "FROM  tb_state " +
                "where ( (created_at > '" + data + "') or (created_at is null) ) " +
                //" and (id > 0) "+
                "order by created_at " +
                "</QUERY>" +
                "</projetos>";
        String res = "";
        activeWBS();
        res =  wbs.callWebService("state", "C", xml, chave);
        return res;
    }


}
