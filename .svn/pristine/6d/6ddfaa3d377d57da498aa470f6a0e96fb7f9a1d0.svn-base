package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdCity;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Element;


public class CityHandler extends OpenHelper {

    public static Context Contexto;
    private static final String TABLE = "tb_city";

    public CityHandler(Context context) {
        super(context);
        Contexto = context;
        createTable();
    }

    public void add(EtdCity etdcity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(etdcity.getId()));
        contentvalues.put("ibge", etdcity.getName());
        contentvalues.put("name", etdcity.getName());
        contentvalues.put("tb_state_id", Integer.valueOf(etdcity.getTb_state_id()));
        contentvalues.put("zip_code", etdcity.getZip_code());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert("tb_city", null, contentvalues);
        db.close();
    }

    public void update(EtdCity obj) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("ibge", obj.getIbge());
        contentvalues.put("name", obj.getName());
        contentvalues.put("tb_state_id", Integer.valueOf(obj.getTb_state_id()));
        contentvalues.put("zip_code", obj.getZip_code());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_city", contentvalues, "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }

    public void createTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE if not exists tb_city ( " +
                " id int(11) NOT NULL,  " +
                "ibge int(11) DEFAULT NULL, " +
                "name varchar(100) NOT NULL, " +
                "tb_state_id int(11) DEFAULT NULL, " +
                "zip_code varchar(15) DEFAULT NULL,  " +
                "created_at datetime,  " +
                "updated_at datetime) ");
        db.close();
    }

    public void delete(EtdCity etdcity) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tb_city", "id = ?", new String[]{
                String.valueOf(etdcity.getId())
        });
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, null, null);
        db.close();
    }


    public EtdCity get(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("tb_city", new String[]{
                "id", "ibge", "name", "tb_state_id", "zip_code"
        }, "id=?", new String[]{
                String.valueOf(id)
        }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            EtdCity obj = new EtdCity();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setIbge(cursor.getString(cursor.getColumnIndex("ibge")));
            obj.setName(cursor.getString(cursor.getColumnIndex("name")));
            obj.setTb_state_id(cursor.getInt(cursor.getColumnIndex("tb_state_id")));
            obj.setZip_code(cursor.getString(cursor.getColumnIndex("zip_code")));
            cursor.close();
            db.close();
            return obj;
        } else {
            cursor.close();
            db.close();
            return null;
        }
    }

    public ArrayList<EtdCity> getAll(int state) {
        ArrayList objlist = new ArrayList();
        String selectQuery = "";
        selectQuery = "SELECT  * FROM tb_city where (tb_state_id = '" + Integer.toString(state) + "') order by name";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if ((cursor != null) && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do
            {
                EtdCity obj = new EtdCity();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setName(cursor.getString(cursor.getColumnIndex("name")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;

    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j) {
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS tb_city");
        onCreate(sqlitedatabase);
    }

    public void persisteWebService(Element element) {
        EtdCity obj = new EtdCity();
        obj.setId(Integer.parseInt(XMLfunctions.getValue(element, "id")));
        obj.setIbge(XMLfunctions.getValue(element, "ibge"));
        obj.setName(XMLfunctions.getValue(element, "name").toUpperCase());
        obj.setTb_state_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_state_id")));
        obj.setZip_code(XMLfunctions.getValue(element, "zip_code"));
        obj.setCreated_at(XMLfunctions.getValue(element, "created_at"));
        if (!verifyRegisterByField("tb_city","id",XMLfunctions.getValue(element, "id"))){
            add(obj);
        }else{
            update(obj);
        }

    }

    public String receiveWebService(String data) {
        createTable();
        String chave = "";
        chave = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                "<chaves>" +
                    "<ibge>0</ibge>" +
                "</chaves>";

        String xml = "";
        xml =   "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                    "<projetos>" +
                        "<QUERY>" +
                            "SELECT DISTINCT tb_city.* " +
                            "FROM  tb_city where (created_at > '" + data + "') or (created_at is null) "+
                            "order by created_at "+
                        "</QUERY>"+
                    "</projetos>";
        activeWBS();
        return wbs.callWebService("city", "C", xml, chave);
    }



}
