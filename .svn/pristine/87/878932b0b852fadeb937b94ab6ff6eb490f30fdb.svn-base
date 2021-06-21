package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdPrice;
import com.setes.setesvendas.app.pedidovenda.MainActivity;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.w3c.dom.Element;


public class PriceHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_price";

    public PriceHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
    }

    public void add(EtdPrice obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("tb_price_list_id", Integer.valueOf(obj.getTb_price_list_id()));
        contentvalues.put("tb_product_id", Integer.valueOf(obj.getTb_product_id()));
        contentvalues.put("price_tag", Double.valueOf(obj.getPrice_tag()));
        contentvalues.put("aliq_profit", Double.valueOf(obj.getAliq_profit()));
        contentvalues.put("aliq_kickback", Double.valueOf(obj.getAliq_kickback()));
        contentvalues.put("quantity", Double.valueOf(obj.getQuantity()));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert("tb_price", null, contentvalues);
        db.close();
    }

    public void update(EtdPrice obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("tb_price_list_id", Integer.valueOf(obj.getTb_price_list_id()));
        contentvalues.put("tb_product_id", Integer.valueOf(obj.getTb_product_id()));
        contentvalues.put("price_tag", Double.valueOf(obj.getPrice_tag()));
        contentvalues.put("aliq_profit", Double.valueOf(obj.getAliq_profit()));
        contentvalues.put("aliq_kickback", Double.valueOf(obj.getAliq_kickback()));
        contentvalues.put("quantity", Double.valueOf(obj.getQuantity()));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_price", contentvalues, "(tb_institution_id = ?) and (tb_price_list_id = ?) and (tb_product_id = ?)", new String[]{
                String.valueOf(obj.getTb_institution_id()),String.valueOf(obj.getTb_price_list_id()),String.valueOf(obj.getTb_product_id())
        });
        db.close();
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.execSQL(" CREATE TABLE if not exists tb_price ( " +
                "tb_institution_id int(11) NOT NULL,  " +
                "tb_price_list_id int(11) NOT NULL,  " +
                "tb_product_id int(11) NOT NULL,  " +
                "price_tag decimal(10,6) DEFAULT NULL,  " +
                "aliq_profit decimal(10,3) DEFAULT NULL,  " +
                "aliq_kickback decimal(10,2) DEFAULT NULL,  " +
                "quantity decimal(10,3) DEFAULT NULL,  " +
                "created_at datetime,  " +
                "updated_at datetime) ");
        db.close();
    }

    public void delete(EtdPrice obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_price", "id = ?", new String[]{
                String.valueOf(obj.getTb_institution_id())
        });
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, null, null);
        db.close();
    }

    public EtdPrice get( int id)
    {

        String select = "SELECT  * FROM " + TABLE +
                " where (tb_product_id = '" + Integer.toString(id) + "') and (tb_price_list_id = '" + Integer.toString(MainActivity.gbPriceList) + "') ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        if (cursor != null && cursor.moveToFirst())
        {
            EtdPrice obj = new EtdPrice();
            obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
            obj.setTb_price_list_id(cursor.getInt(cursor.getColumnIndex("tb_price_list_id")));
            obj.setTb_product_id(cursor.getInt(cursor.getColumnIndex("tb_product_id")));
            obj.setPrice_tag(cursor.getDouble(cursor.getColumnIndex("price_tag")));
            obj.setAliq_profit(cursor.getDouble(cursor.getColumnIndex("aliq_profit")));
            obj.setAliq_kickback(cursor.getDouble(cursor.getColumnIndex("aliq_kickback")));
            obj.setQuantity(cursor.getDouble(cursor.getColumnIndex("quantity")));
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
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_price", null);
        if (cursor.moveToFirst())
        {
            do
            {
                EtdPrice obj = new EtdPrice();
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setTb_price_list_id(cursor.getInt(cursor.getColumnIndex("tb_price_list_id")));
                obj.setTb_product_id(cursor.getInt(cursor.getColumnIndex("tb_product_id")));
                obj.setPrice_tag(cursor.getDouble(cursor.getColumnIndex("price_tag")));
                obj.setAliq_profit(cursor.getDouble(cursor.getColumnIndex("aliq_profit")));
                obj.setAliq_kickback(cursor.getDouble(cursor.getColumnIndex("aliq_kickback")));
                obj.setQuantity(cursor.getDouble(cursor.getColumnIndex("quantity")));
                arraylist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arraylist;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_price");
        onCreate(sqlitedatabase);
    }

    public boolean  verifyRegister(int tb_institution_id, int tb_price_list_id, int tb_product_id)
    {
        String select = "SELECT  tb_institution_id FROM " + TABLE +
                " where (tb_institution_id = '" + tb_institution_id + "') " +
                " and (tb_price_list_id = '" + tb_price_list_id + "')"+
                " and (tb_product_id = '" + tb_product_id + "')";
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
        EtdPrice obj = new EtdPrice();
        obj.setTb_institution_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_institution_id")));
        obj.setTb_price_list_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_price_list_id")));
        obj.setTb_product_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_product_id")));
        if (!XMLfunctions.getValue(element, "price_tag").equals("")) {
            obj.setPrice_tag(Double.parseDouble(XMLfunctions.getValue(element, "price_tag")));
        }else{
            obj.setPrice_tag(0.0);
        }
        if (!XMLfunctions.getValue(element, "aliq_profit").equals("")) {
            obj.setAliq_profit(Double.parseDouble(XMLfunctions.getValue(element, "aliq_profit")));
        }else{
            obj.setAliq_profit(0.0);
        }
        if (!XMLfunctions.getValue(element, "aliq_kickback").equals("")) {
            obj.setAliq_kickback(Double.parseDouble(XMLfunctions.getValue(element, "aliq_kickback")));
        }else{
            obj.setAliq_kickback(0.0);
        }
        if (!XMLfunctions.getValue(element, "quantity").equals("")) {
            obj.setQuantity(Double.parseDouble(XMLfunctions.getValue(element, "quantity")));
        }else{
            obj.setQuantity(0.0);
        }
        obj.setCreated_at(XMLfunctions.getValue(element, "created_at"));
        if (!verifyRegister(obj.getTb_institution_id(), obj.getTb_price_list_id(), obj.getTb_product_id()))
        {
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
        String xml ="";
        xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<projetos>" +
                "<QUERY>" +
                "SELECT DISTINCT tb_price.* " +
                "FROM  tb_price " +
                "  INNER JOIN tb_product "+
                "  on (tb_product.id = tb_price.tb_product_id ) "+
                "where ( (tb_price.created_at > '" + data + "') or (tb_price.created_at is null) ) " +
                " and (tb_price.tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution)  + "') " +
                " and (tb_product.tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution)  + "') " +
                //" and (tb_price_list_id = '" + Integer.toString(MainActivity.gbPriceList)  + "') " +
                " and (tb_product.published =  'S') " +
                "order by tb_price.created_at " +
                "</QUERY>" +
                "</projetos>";
        String res = "";
        activeWBS();
        res =  wbs.callWebService("price", "C", xml,chave );
        return res;
    }



}
