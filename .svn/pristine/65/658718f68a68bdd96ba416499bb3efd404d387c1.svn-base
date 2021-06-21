package com.setes.setesvendas.app.model;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdOrderSale;
import com.setes.setesvendas.app.controller.EtdOrderSaleItem;
import com.setes.setesvendas.app.util.WebService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.w3c.dom.NodeList;



public class OrderSaleItemHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_order_sale_item";
    public ProgressDialog dialog;
    public Handler h;
    public int maxRegs;
    public NodeList nodes;
    public int progresso;
    public long tempo;


    public OrderSaleItemHandler(Context context)
    {
        super(context);
        maxRegs = 100;
        progresso = 0;
        tempo = 1L;
        Contexto = context;
        createTable();
    }

    public void add(EtdOrderSaleItem obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("tb_order_sale_id", Integer.valueOf(obj.getTb_order_sale_id()));
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("tb_salesman_id", Integer.valueOf(obj.getTb_salesman_id()));
        contentvalues.put("tb_product_id", Integer.valueOf(obj.getTb_product_id()));
        contentvalues.put("item", Integer.valueOf(obj.getItem()));
        contentvalues.put("qtty", Double.valueOf(obj.getQtty()));
        contentvalues.put("unit_value", Double.valueOf(obj.getUnit_value()));
        contentvalues.put("discount_aliquot", Double.valueOf(obj.getDiscount_aliquot()));
        contentvalues.put("discount_value", Double.valueOf(obj.getDiscount_value()));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert("tb_order_sale_item", null, contentvalues);
        db.close();
    }

    public void update(EtdOrderSaleItem obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("tb_order_sale_id", Integer.valueOf(obj.getTb_order_sale_id()));
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("tb_salesman_id", Integer.valueOf(obj.getTb_salesman_id()));
        contentvalues.put("tb_product_id", Integer.valueOf(obj.getTb_product_id()));
        contentvalues.put("item", Integer.valueOf(obj.getItem()));
        contentvalues.put("qtty", Double.valueOf(obj.getQtty()));
        contentvalues.put("unit_value", Double.valueOf(obj.getUnit_value()));
        contentvalues.put("discount_aliquot", Double.valueOf(obj.getDiscount_aliquot()));
        contentvalues.put("discount_value", Double.valueOf(obj.getDiscount_value()));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_order_sale_item", contentvalues, "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }
    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.execSQL(" CREATE TABLE if not exists tb_order_sale_item (  " +
                "id INT( 11 ) NOT NULL ,  " +
                "tb_institution_id INT( 11 ) NOT NULL ,  " +
                "tb_order_sale_id INT( 11 ) NOT NULL ,  " +
                "tb_salesman_id INT( 11 ) NOT NULL ,  " +
                "item INT( 111 ) NOT NULL ,  " +
                "tb_product_id INT( 11 ) NOT NULL ,  " +
                "qtty DECIMAL( 10, 4 ) DEFAULT NULL ,  " +
                "unit_value DECIMAL( 10, 6 ) DEFAULT NULL ,  " +
                "discount_aliquot DECIMAL( 10, 2 ) DEFAULT NULL ,  " +
                "discount_value DECIMAL( 10, 6 ) DEFAULT NULL ,  " +
                "created_at DATETIME DEFAULT NULL ,  " +
                "updated_at DATETIME DEFAULT NULL,  " +
                "PRIMARY KEY (  id, tb_institution_id ,  tb_order_sale_id ,  tb_salesman_id ) ) ");
        db.close();
    }

    public void delete(EtdOrderSaleItem obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_order_sale_item", "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }

    public void deleteByOrderId(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_order_sale_item", "tb_order_sale_id = ?", new String[]{
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


    public EtdOrderSaleItem get(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.query(TABLE, new String[] {"id", "tb_order_sale_id", "tb_institution_id",
                                                      "tb_salesman_id", "tb_product_id", "item", "qtty",
                                                      "unit_value", "discount_aliquot", "discount_value"}, "id=?",
                                        new String[] { String.valueOf(id)}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()){
            EtdOrderSaleItem obj = new EtdOrderSaleItem();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
            obj.setTb_order_sale_id(cursor.getInt(cursor.getColumnIndex("tb_order_sale_id")));
            obj.setTb_salesman_id(cursor.getInt(cursor.getColumnIndex("tb_salesman_id")));
            obj.setItem(cursor.getInt(cursor.getColumnIndex("item")));
            obj.setTb_product_id(cursor.getInt(cursor.getColumnIndex("tb_product_id")));
            obj.setQtty(cursor.getDouble(cursor.getColumnIndex("qtty")));
            obj.setUnit_value(cursor.getDouble(cursor.getColumnIndex("unit_value")));
            obj.setDiscount_aliquot(cursor.getDouble(cursor.getColumnIndex("discount_aliquot")));
            obj.setDiscount_value(cursor.getDouble(cursor.getColumnIndex("discount_value")));
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

    public ArrayList<EtdOrderSaleItem> getAll(String OrderId)
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_order_sale_item where (tb_order_sale_id = '" + OrderId + "')", null);
        if ((cursor != null) && (cursor.moveToFirst())) {
            do
            {
                EtdOrderSaleItem obj = new EtdOrderSaleItem();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setTb_order_sale_id(cursor.getInt(cursor.getColumnIndex("tb_order_sale_id")));
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setTb_salesman_id(cursor.getInt(cursor.getColumnIndex("tb_salesman_id")));
                obj.setTb_product_id(cursor.getInt(cursor.getColumnIndex("tb_product_id")));
                obj.setItem(cursor.getInt(cursor.getColumnIndex("item")));
                obj.setQtty(cursor.getDouble(cursor.getColumnIndex("qtty")));
                obj.setUnit_value(cursor.getDouble(cursor.getColumnIndex("unit_value")));
                obj.setDiscount_aliquot(cursor.getDouble(cursor.getColumnIndex("discount_aliquot")));
                obj.setDiscount_value(cursor.getDouble(cursor.getColumnIndex("discount_value")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public ArrayList<EtdOrderSaleItem> getSendWs(String data)
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_order_sale_item where (id > 0) and (updated_at > '" + data + "')", null);
        if ((cursor != null) && (cursor.moveToFirst())) {
            do
            {
                EtdOrderSaleItem obj = new EtdOrderSaleItem();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setTb_order_sale_id(cursor.getInt(cursor.getColumnIndex("tb_order_sale_id")));
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setTb_salesman_id(cursor.getInt(cursor.getColumnIndex("tb_salesman_id")));
                obj.setTb_product_id(cursor.getInt(cursor.getColumnIndex("tb_product_id")));
                obj.setItem(cursor.getInt(cursor.getColumnIndex("item")));
                obj.setQtty(cursor.getDouble(cursor.getColumnIndex("qtty")));
                obj.setUnit_value(cursor.getDouble(cursor.getColumnIndex("unit_value")));
                obj.setDiscount_aliquot(cursor.getDouble(cursor.getColumnIndex("discount_aliquot")));
                obj.setDiscount_value(cursor.getDouble(cursor.getColumnIndex("discount_value")));
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
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_order_sale_item", null);
        int i = cursor.getCount();
        cursor.close();
        db.close();
        return i;
    }

    public ArrayList getItensOrderSale(int id)
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_order_sale_item where tb_order_sale_id = '" + id + "'", null);
        if ((cursor != null) && (cursor.moveToFirst())) {
            do
            {
                EtdOrderSaleItem obj = new EtdOrderSaleItem();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setTb_order_sale_id(cursor.getInt(cursor.getColumnIndex("tb_order_sale_id")));
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setTb_salesman_id(cursor.getInt(cursor.getColumnIndex("tb_salesman_id")));
                obj.setTb_product_id(cursor.getInt(cursor.getColumnIndex("tb_product_id")));
                obj.setItem(cursor.getInt(cursor.getColumnIndex("item")));
                obj.setQtty(cursor.getDouble(cursor.getColumnIndex("qtty")));
                obj.setUnit_value(cursor.getDouble(cursor.getColumnIndex("unit_value")));
                obj.setDiscount_aliquot(cursor.getDouble(cursor.getColumnIndex("discount_aliquot")));
                obj.setDiscount_value(cursor.getDouble(cursor.getColumnIndex("discount_value")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }


    public ArrayList<EtdOrderSaleItem> list(int id)
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT * FROM tb_order_sale_item where tb_order_sale_id = '" + id + "'", null);
        if ((cursor != null) && (cursor.moveToFirst())){
            do
            {
                EtdOrderSaleItem obj = new EtdOrderSaleItem();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setTb_order_sale_id(cursor.getInt(cursor.getColumnIndex("tb_order_sale_id")));
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setTb_salesman_id(cursor.getInt(cursor.getColumnIndex("tb_salesman_id")));
                obj.setTb_product_id(cursor.getInt(cursor.getColumnIndex("tb_product_id")));
                obj.setItem(cursor.getInt(cursor.getColumnIndex("item")));
                obj.setQtty(cursor.getDouble(cursor.getColumnIndex("qtty")));
                obj.setUnit_value(cursor.getDouble(cursor.getColumnIndex("unit_value")));
                obj.setDiscount_aliquot(cursor.getDouble(cursor.getColumnIndex("discount_aliquot")));
                obj.setDiscount_value(cursor.getDouble(cursor.getColumnIndex("discount_value")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_order_sale_item");
        onCreate(sqlitedatabase);
    }

    public String sendWebService(EtdOrderSale objOrdersale, EtdOrderSaleItem objOrderSaleItem)
    {
        String chaves = "";
        chaves = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                "<chaves>" +
                "<external_id>" + objOrderSaleItem.getId() + "</external_id>" +
                "<tb_institution_id>" + objOrderSaleItem.getTb_institution_id() + "</tb_institution_id>" +
                "<tb_order_sale_id>" + objOrderSaleItem.getTb_order_sale_id() + "</tb_order_sale_id>" +
                "<tb_salesman_id>" + objOrderSaleItem.getTb_salesman_id() + "</tb_salesman_id>" +
                "<number>" + objOrdersale.getNumber() + "</number>" +
                "</chaves>";
        String xml ="";
        xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<tb_order_sale_item>" +
                    "<id>" + objOrderSaleItem.getId() + "</id>" +
                    "<tb_institution_id>" + objOrderSaleItem.getTb_institution_id() + "</tb_institution_id>" +
                    "<tb_order_sale_id>" + objOrderSaleItem.getTb_order_sale_id() + "</tb_order_sale_id>" +
                    "<tb_salesman_id>" + objOrderSaleItem.getTb_salesman_id()  +"</tb_salesman_id>" +
                    "<external_id>" + objOrderSaleItem.getId() + "</external_id>" +
                    "<tb_product_id>" + objOrderSaleItem.getTb_product_id() + "</tb_product_id>" +
                    "<item>" + objOrderSaleItem.getItem() +"</item>" +
                    "<qtty>" + objOrderSaleItem.getQtty() + "</qtty>" +
                    "<unit_value>" + objOrderSaleItem.getUnit_value() + "</unit_value>" +
                    "<discount_aliquot>" + objOrderSaleItem.getDiscount_aliquot() + "</discount_aliquot>" +
                    "<discount_value>" + objOrderSaleItem.getDiscount_value() + "</discount_value>" +
                "</tb_order_sale_item>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("ordersaleitem", "A", xml, chaves);
        return  res;
    }

    public Double totalItemsOrder(int id)
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT sum(qtty * unit_value) FROM tb_order_sale_item where tb_order_sale_id = '" + id + "'", null);
        Double valor = Double.valueOf(0);
        if ((cursor != null) && (cursor.moveToFirst())){
            valor = Double.valueOf(cursor.getDouble(0));
        }
        cursor.close();
        db.close();
        return valor;
    }




}
