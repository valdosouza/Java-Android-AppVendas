package com.setes.setesvendas.app.model;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdProduct;
import com.setes.setesvendas.app.pedidovenda.MainActivity;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// Referenced classes of package com.setecursor.model:
//            OpenHelper

public class ProductHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_product";
    public ProgressDialog dialog;
    public int maxRegs;
    public NodeList nodes;
    public int progresso;
    public long tempo;

    public ProductHandler(Context context)
    {
        super(context);
        maxRegs = 100;
        progresso = 0;
        tempo = 1L;
        Contexto = context;
    }

    public void add(EtdProduct obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("identifier", obj.getIdentifier());
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("description", obj.getDescription());
        contentvalues.put("tb_category_id", Integer.valueOf(obj.getTb_category_id()));
        contentvalues.put("promotion", obj.getPromotion());
        contentvalues.put("highlights", obj.getHighlights());
        contentvalues.put("enabled", obj.getEnabled());
        contentvalues.put("published", obj.getPublished());
        contentvalues.put("note", obj.getNote());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert(TABLE, null, contentvalues);
        db.close();
    }

    public void update(EtdProduct obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("identifier", obj.getIdentifier());
        contentvalues.put("tb_institution_id", Integer.valueOf(obj.getTb_institution_id()));
        contentvalues.put("description", obj.getDescription());
        contentvalues.put("tb_category_id", Integer.valueOf(obj.getTb_category_id()));
        contentvalues.put("promotion", obj.getPromotion());
        contentvalues.put("highlights", obj.getHighlights());
        contentvalues.put("enabled", obj.getEnabled());
        contentvalues.put("published", obj.getPublished());
        contentvalues.put("note", obj.getNote());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update(TABLE, contentvalues, "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }


    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.execSQL(" CREATE TABLE if not exists " + TABLE +" (  " +
                "id int(11) NOT NULL,  " +
                "identifier varchar(50) NOT NULL,  " +
                "tb_institution_id int(11) NOT NULL,  " +
                "description varchar(100) NOT NULL,  " +
                "tb_category_id int(11) NOT NULL,  " +
                "promotion char(1) DEFAULT NULL,  " +
                "highlights char(1) DEFAULT NULL,  " +
                "enabled char(1) DEFAULT NULL,  " +
                "published char(1) DEFAULT NULL,  " +
                "note blob,  " +
                "created_at datetime DEFAULT NULL,  " +
                "updated_at datetime DEFAULT NULL)");
        db.close();
    }

    public void delete(EtdProduct obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete(TABLE, "id = ?", new String[]{
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


    public EtdProduct get(int id)
    {
        String selectQuery = "SELECT  * " +
                "FROM " + TABLE +
                " where (id ='" + id + "')" ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if ((cursor != null) && cursor.getCount() > 0) {
            cursor.moveToFirst();
            EtdProduct obj = new EtdProduct();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setIdentifier(cursor.getString(cursor.getColumnIndex("identifier")));
            obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
            obj.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            obj.setTb_category_id(cursor.getInt(cursor.getColumnIndex("tb_category_id")));
            obj.setPromotion(cursor.getString(cursor.getColumnIndex("promotion")));
            obj.setHighlights(cursor.getString(cursor.getColumnIndex("highlights")));
            obj.setEnabled(cursor.getString(cursor.getColumnIndex("enabled")));
            obj.setPublished(cursor.getString(cursor.getColumnIndex("published")));
            obj.setNote(cursor.getString(cursor.getColumnIndex("note")));
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

    public ArrayList searchItems(String product)
    {
        ArrayList Objlist = new ArrayList();
        String selectQuery = "";
        selectQuery = "SELECT  pro.* "+
                      "FROM tb_product pro "+
                      "  inner join tb_price pri "+
                      "  on (pri.tb_product_id = pro.id) and (pri.tb_price_list_id = '" + MainActivity.gbPriceList + "')";
        if (!product.equals("")){
            selectQuery = selectQuery + " where (pro.description like '%" + product + "%')";
        }
        selectQuery = selectQuery + " order by pro.description ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if ((cursor != null) && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do
            {
                EtdProduct obj = new EtdProduct();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setIdentifier(cursor.getString(cursor.getColumnIndex("identifier")));
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                obj.setTb_category_id(cursor.getInt(cursor.getColumnIndex("tb_category_id")));
                obj.setPromotion(cursor.getString(cursor.getColumnIndex("promotion")));
                obj.setHighlights(cursor.getString(cursor.getColumnIndex("highlights")));
                obj.setEnabled(cursor.getString(cursor.getColumnIndex("enabled")));
                obj.setPublished(cursor.getString(cursor.getColumnIndex("published")));
                obj.setNote(cursor.getString(cursor.getColumnIndex("note")));
                Objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Objlist;
    }


    public List getAll()
    {
        ArrayList arraylist = new ArrayList();
        Cursor cursor = getWritableDatabase().rawQuery("SELECT  * FROM " + TABLE, null);
        if (cursor.moveToFirst())
        {
            do
            {
                EtdProduct obj = new EtdProduct();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setIdentifier(cursor.getString(cursor.getColumnIndex("identifier")));
                obj.setTb_institution_id(cursor.getInt(cursor.getColumnIndex("tb_institution_id")));
                obj.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                obj.setTb_category_id(cursor.getInt(cursor.getColumnIndex("tb_category_id")));
                obj.setPromotion(cursor.getString(cursor.getColumnIndex("promotion")));
                obj.setHighlights(cursor.getString(cursor.getColumnIndex("highlights")));
                obj.setEnabled(cursor.getString(cursor.getColumnIndex("enabled")));
                obj.setPublished(cursor.getString(cursor.getColumnIndex("published")));
                obj.setNote(cursor.getString(cursor.getColumnIndex("note")));
                obj.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                obj.setUpdated_at(cursor.getString(cursor.getColumnIndex("updated_at")));
                arraylist.add(obj);
            } while (cursor.moveToNext());
        }
        return arraylist;
    }

    public int getCount()
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT id FROM " + TABLE, null);
        int i = cursor.getCount();
        cursor.close();
        db.close();
        return i;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE);
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
        EtdProduct obj = new EtdProduct();
        obj.setId(Integer.parseInt(XMLfunctions.getValue(element, "id")));
        obj.setIdentifier(XMLfunctions.getValue(element, "identifier"));
        obj.setTb_institution_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_institution_id")));
        obj.setDescription(XMLfunctions.getValue(element, "description"));
        obj.setTb_category_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_category_id")));
        obj.setPromotion(XMLfunctions.getValue(element, "promotion"));
        obj.setHighlights(XMLfunctions.getValue(element, "highlights"));
        obj.setEnabled(XMLfunctions.getValue(element, "enabled"));
        obj.setPublished(XMLfunctions.getValue(element, "published"));
        obj.setNote(XMLfunctions.getValue(element, "note"));
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
                    "<tb_institution_id>0</tb_institution_id>"+
                "</chaves>";
        String xml = "";
        xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<projetos>" +
                "<QUERY>" +
                    "SELECT  * " +
                    "FROM  tb_product " +
                    "where ( (created_at > '" + data + "') or (created_at is null) ) " +
                    " and (tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution) + "') " +
                    " and (published = 'S') " +
                    "order by created_at " +
                "</QUERY>" +
                "</projetos>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("product", "C", xml, chave);
        return res;
    }

}
