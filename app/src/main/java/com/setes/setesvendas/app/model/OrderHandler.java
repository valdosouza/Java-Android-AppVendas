package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdOrder;
import com.setes.setesvendas.app.util.Convert;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OrderHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_order";


    public OrderHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
    }

    public void add(EtdOrder obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("dt_record", obj.getDt_record().toString());
        contentvalues.put("note", obj.getNote());
        contentvalues.put("status", obj.getStatus());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert("tb_order", null, contentvalues);
        db.close();
    }

    public void update(EtdOrder obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("dt_record", obj.getDt_record().toString());
        contentvalues.put("note", obj.getNote());
        contentvalues.put("status", obj.getStatus());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_order", contentvalues, "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }
    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.execSQL(" CREATE TABLE if not exists tb_order (  " +
                "id integer NOT NULL,  " +
                "tb_institution_id int(11) NOT NULL,  " +
                "dt_record date DEFAULT NULL,  " +
                "note blob,  " +
                "status varchar(1),  " +
                "created_at datetime,  " +
                "updated_at datetime) ");
        db.close();
    }

    public void delete(EtdOrder obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_order", "id = ?", new String[]{
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


    public EtdOrder get(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.query("tb_order", new String[] {
            "id", "tb_institution_id", "dt_record", "note", "status", "created_at", "updated_at"
        }, "id=?", new String[] {
            String.valueOf(id)
        }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            EtdOrder obj = new EtdOrder();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
            obj.setDt_record(cursor.getString(cursor.getColumnIndex("dt_record")));
            obj.setNote(cursor.getString(cursor.getColumnIndex("note")));
            obj.setStatus(cursor.getString(cursor.getColumnIndex("status")));
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

    public ArrayList<EtdOrder> getSearch(String date, String order, String customer)
    {
        String sql = "";
        sql = "SELECT distinct tb_order.* " +
              "FROM tb_order " +
              "inner join tb_order_sale  " +
              "  on (tb_order_sale.id = tb_order.id) " +
              "  inner join tb_entity  " +
              "  on (tb_entity.id = tb_order_sale.tb_customer_id )  " +
              "where (tb_order.id is not null)";
        if (!date.equals(""))
        {
            sql = sql + " and (tb_order.dt_record = '" + Convert.StrDateToStrDateTbl(date) + "')";
        }
        if (!order.equals(""))
        {
            sql = sql + " and (tb_order_sale.number = '" + order +"')";
        }
        if (!customer.equals(""))
        {
            sql = sql + " and ( (tb_entity.name_company like '%" +customer +"%') or " +
                        "       (tb_entity.nick_trade like '%"+ customer + "%'))";
        }
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery(sql, null);
        if ( (cursor != null) && (cursor.moveToFirst()))
        {
            do
            {
                EtdOrder obj = new EtdOrder();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setDt_record(cursor.getString(cursor.getColumnIndex("dt_record")));
                obj.setNote(cursor.getString(cursor.getColumnIndex("note")));
                obj.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public List getAll()
    {
        ArrayList arraylist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_order", null);
        if ( (cursor != null) && (cursor.moveToFirst()))
        {
            do
            {
                new Date(cursor.getLong(cursor.getColumnIndex("dt_record")));
                EtdOrder obj = new EtdOrder();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setDt_record(cursor.getString(cursor.getColumnIndex("dt_record")));
                obj.setNote(cursor.getString(cursor.getColumnIndex("note")));
                obj.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                obj.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                obj.setUpdated_at(cursor.getString(cursor.getColumnIndex("updated_at")));
                arraylist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arraylist;
    }

    public int getCount()
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_order", null);
        int i = cursor.getCount();
        cursor.close();
        db.close();
        return i;
    }

    public ArrayList<EtdOrder> getSendWs(String data)
    {
        ArrayList arraylist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_order where (id > 0) and (updated_at > '" + data + "')" , null);
        if ( (cursor != null) && (cursor.moveToFirst()))
        {
            do
            {
                new Date(cursor.getLong(cursor.getColumnIndex("dt_record")));
                EtdOrder obj = new EtdOrder();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setDt_record(cursor.getString(cursor.getColumnIndex("dt_record")));
                obj.setNote(cursor.getString(cursor.getColumnIndex("note")));
                obj.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                obj.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                obj.setUpdated_at(cursor.getString(cursor.getColumnIndex("updated_at")));
                arraylist.add(obj);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arraylist;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_order");
        onCreate(sqlitedatabase);
    }

    public String sendWebService(EtdOrder obj)
    {
        String chave = "";
        chave = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                "<chaves>" +
                "<id>" + obj.getId() + "</id>" +
                "<tb_institution_id>" + obj.getTb_institution_id() + "</tb_institution_id>" +
                "</chaves>";
        String xml = "";
        xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<order>" +
                "<id>" + obj.getId() + "</id>" +
                "<tb_institution_id>" + obj.getTb_institution_id() +"</tb_institution_id>" +
                "<dt_record>" + obj.getDt_record() + "</dt_record>" +
                "<note>" + obj.getNote() +"</note>" +
                "<status>" + obj.getStatus() + "</status>" +
                "</order>";
        String res = "";
        activeWBS();
        res = (wbs.callWebService("order", "A", xml, chave));
        return res;

    }




}
