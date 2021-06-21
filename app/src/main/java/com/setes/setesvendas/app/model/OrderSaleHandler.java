package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdOrder;
import com.setes.setesvendas.app.controller.EtdOrderSale;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderSaleHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_order_sale";


    public OrderSaleHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
    }

    public void add(EtdOrderSale obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("tb_salesman_id", Integer.valueOf(obj.getTb_salesman_id()));
        contentvalues.put("tb_customer_id", Integer.valueOf(obj.getTb_customer_id()));
        contentvalues.put("number", Integer.valueOf(obj.getNumber()));
        contentvalues.put("created_at", obj.getCreated_at());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert("tb_order_sale", null, contentvalues);
        db.close();
    }

    public void update(EtdOrderSale obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("tb_salesman_id", Integer.valueOf(obj.getTb_salesman_id()));
        contentvalues.put("tb_customer_id", Integer.valueOf(obj.getTb_customer_id()));
        contentvalues.put("number", Integer.valueOf(obj.getNumber()));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_order_sale", contentvalues, "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.execSQL(" CREATE TABLE if not exists tb_order_sale (   id int(11) NOT NULL,  tb_institution_id int(11) NOT NULL,  tb_salesman_id int(11) NOT NULL,  tb_customer_id int(11) DEFAULT NULL,  number int(11) DEFAULT NULL,  created_at datetime,  updated_at datetime) ");
        db.close();
    }

    public void delete(EtdOrderSale obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_order_sale", "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }

    public void deleteById(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_order_sale", "id = ?", new String[]{
                String.valueOf(id)
        });
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, null, null);
        db.close();
    }


    public EtdOrderSale get(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.query("tb_order_sale", new String[] {
            "id", "tb_institution_id", "tb_salesman_id", "tb_customer_id", "number"
        }, "id=?", new String[] {
            String.valueOf(id)
        }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            EtdOrderSale obj = new EtdOrderSale();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
            obj.setTb_salesman_id(cursor.getInt(cursor.getColumnIndex("tb_salesman_id")));
            obj.setTb_customer_id(cursor.getInt(cursor.getColumnIndex("tb_customer_id")));
            obj.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
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

    public ArrayList<EtdOrderSale> getSearch(String date, String order, String customer)
    {
        String sql = "";
        sql = "SELECT distinct tb_order_sale.* " +
                "FROM tb_order_sale " +
                "  inner join tb_order " +
                "  on (tb_order_sale.id = tb_order.id) " +
                "  inner join tb_entity  " +
                "  on (tb_entity.id = tb_order_sale.tb_customer_id )  " +
                "where (tb_order.id is not null)";
        if (!date.equals(""))
        {
            sql = sql + " and (tb_order.dt_record = '" + date + "'";
        }
        if (!order.equals(""))
        {
            sql = sql + " and (tb_order_sale.number = '" + order +"'";
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


    public List<EtdOrderSale> getAll()
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_order_sale", null);
        if ((cursor != null) && (cursor.moveToFirst())){
            do
            {
                EtdOrderSale obj = new EtdOrderSale();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setTb_salesman_id(cursor.getInt(cursor.getColumnIndex("tb_salesman_id")));
                obj.setTb_customer_id(cursor.getInt(cursor.getColumnIndex("tb_customer_id")));
                obj.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public int getCount()
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_order_sale", null);
        int i = cursor.getCount();
        cursor.close();
        db.close();
        return i;
    }


    public int getNextNumber()
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Cursor cursor = db.query("tb_order_sale", new String[]{
                "MAX(id)"
        }, null, null, null, null, null);
        int i = 1;
        if (cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            i = cursor.getInt(0) + 1;
        }
        cursor.close();
        db.close();
        return i;
    }

    public ArrayList<EtdOrderSale> getSendWs(String data)
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_order_sale where (id > 0) and (updated_at > '" + data + "')" , null);
        if ((cursor != null) && (cursor.moveToFirst())){
            do
            {
                EtdOrderSale obj = new EtdOrderSale();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setTb_salesman_id(cursor.getInt(cursor.getColumnIndex("tb_salesman_id")));
                obj.setTb_customer_id(cursor.getInt(cursor.getColumnIndex("tb_customer_id")));
                obj.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_order_sale");
        onCreate(sqlitedatabase);
    }

    public String sendWebService(EtdOrder etdorder, EtdOrderSale obj)
    {
        String chave = "";
        chave = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                "<chaves>" +
                    "<number>" + obj.getNumber() + "</number>" +
                    "<tb_institution_id>" + obj.getTb_institution_id() + "</tb_institution_id>" +
                    "<tb_salesman_id>" + obj.getTb_salesman_id() + "</tb_salesman_id>" +
                "</chaves>";
        String xml ="";
        xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<order_sale>" +
                    "<id>" + obj.getId() + "</id>" +
                    "<tb_institution_id>" + obj.getTb_institution_id() + "</tb_institution_id>" +
                    "<tb_salesman_id>" + obj.getTb_salesman_id() + "</tb_salesman_id>" +
                    "<tb_customer_id>" + obj.getTb_customer_id() + "</tb_customer_id>" +
                    "<number>" + obj.getNumber() + "</number>" +
                    "<dt_record>" + etdorder.getDt_record() + "</dt_record>" +
                    "<note>" + etdorder.getNote() + "</note>" +
                    "<status>" + etdorder.getStatus() + "</status>" +
                "</order_sale>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("ordersale", "A", xml, chave);
        return res;
    }



    public void updateCustomer(EtdOrderSale obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("tb_salesman_id", Integer.valueOf(obj.getTb_salesman_id()));
        contentvalues.put("tb_customer_id", Integer.valueOf(obj.getTb_customer_id()));
        db.update("tb_order_sale", contentvalues, "( id = ?) and (tb_institution_id = ?) and (tb_salesman_id = ?)", new String[] {
            String.valueOf(obj.getId()), String.valueOf(obj.getTb_institution_id()), String.valueOf(obj.getTb_salesman_id())
        });
        db.close();
    }

}
