package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdCompany;
import com.setes.setesvendas.app.pedidovenda.MainActivity;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;


public class CompanyHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_company";


    public CompanyHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
    }

    public long add(EtdCompany obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("cnpj", obj.getCnpj());
        contentvalues.put("ie", obj.getIe());
        contentvalues.put("im", obj.getIm());
        contentvalues.put("iest", obj.getIest());
        contentvalues.put("dt_foundation", obj.getDt_foundation());
          Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        long l = db.insert("tb_company", null, contentvalues);
        db.close();
        return l;
    }

    public void update(EtdCompany etdcompany)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(etdcompany.getId()));
        contentvalues.put("cnpj", etdcompany.getCnpj());
        contentvalues.put("ie", etdcompany.getIe());
        contentvalues.put("im", etdcompany.getIm());
        contentvalues.put("iest", etdcompany.getIest());
        contentvalues.put("dt_foundation", etdcompany.getDt_foundation());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_company", contentvalues, "id = ?", new String[]{
                String.valueOf(etdcompany.getId())
        });
        db.close();
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" CREATE TABLE if not exists tb_company (  " +
                "id int(11) NOT NULL,   " +
                "cnpj char(14) DEFAULT NULL,   " +
                "ie varchar(45) DEFAULT NULL,   " +
                "im varchar(45) DEFAULT NULL,   " +
                "iest varchar(45) DEFAULT NULL,   " +
                "dt_foundation date DEFAULT NULL,   " +
                "created_at datetime,   " +
                "updated_at datetime) ");
        db.close();
    }

    public void delete(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, "id = ?", new String[]{
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

    public EtdCompany get(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("tb_company", new String[]{
                "id", "cnpj", "ie", "im", "iest", "dt_foundation"
        }, "id=?", new String[]{
                String.valueOf(id)
        }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            EtdCompany obj = new EtdCompany();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setCnpj(cursor.getString(cursor.getColumnIndex("cnpj")));
            obj.setIe(cursor.getString(cursor.getColumnIndex("ie")));
            obj.setIm(cursor.getString(cursor.getColumnIndex("im")));
            obj.setIest(cursor.getString(cursor.getColumnIndex("iest")));
            obj.setDt_foundation(cursor.getString(cursor.getColumnIndex("dt_foundation")));
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


    public List<EtdCompany> getAll()
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_company where id > 0", null);
        if (cursor.moveToFirst())
        {
            do
            {
                EtdCompany obj = new EtdCompany();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setCnpj(cursor.getString(cursor.getColumnIndex("cnpj")));
                obj.setIe(cursor.getString(cursor.getColumnIndex("ie")));
                obj.setIm(cursor.getString(cursor.getColumnIndex("im")));
                obj.setIest(cursor.getString(cursor.getColumnIndex("iest")));
                obj.setDt_foundation(cursor.getString(cursor.getColumnIndex("dt_foundation")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public int getIdByCNPJ(String cnpj)
    {
        int id = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("tb_company", new String[]{"id"}, "cnpj=?", new String[]{String.valueOf(cnpj)}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            id =  cursor.getInt(cursor.getColumnIndex("id"));
        }
        cursor.close();
        db.close();
        return id;
    }

    public String getCnpjById(int id)
    {
        String cnpj = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("tb_company", new String[]{"cnpj"}, "id=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            cnpj =  cursor.getString(cursor.getColumnIndex("cnpj"));
        }
        cursor.close();
        db.close();
        return cnpj;
    }

    public ArrayList<EtdCompany> getSendWs(String data)
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_company where (id > 0) and (updated_at > '" + data + "') ", null);
        if ( (cursor != null) && (cursor.moveToFirst()))
        {
            do
            {
                EtdCompany obj = new EtdCompany();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setCnpj(cursor.getString(cursor.getColumnIndex("cnpj")));
                obj.setIe(cursor.getString(cursor.getColumnIndex("ie")));
                obj.setIm(cursor.getString(cursor.getColumnIndex("im")));
                obj.setIest(cursor.getString(cursor.getColumnIndex("iest")));
                obj.setDt_foundation(cursor.getString(cursor.getColumnIndex("dt_foundation")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_company");
        onCreate(sqlitedatabase);
    }

    public int persisteWebService(Element element)
    {
        EtdCompany obj = new EtdCompany();
        obj.setId(Integer.parseInt(XMLfunctions.getValue(element, "id")));
        obj.setCnpj(XMLfunctions.getValue(element, "cnpj"));
        obj.setIe(XMLfunctions.getValue(element, "ie"));
        obj.setIm(XMLfunctions.getValue(element, "im"));
        obj.setIest(XMLfunctions.getValue(element, "iest"));
        obj.setDt_foundation(XMLfunctions.getValue(element, "dt_foundation"));
        obj.setCreated_at(XMLfunctions.getValue(element, "created_at"));
        if (!verifyRegisterByField("tb_company", "cnpj", obj.getCnpj())) {
            add(obj);
        } else{
            update(obj);
        }
        return obj.getId();
    }

    public Integer returnIdWeb(int id)
    {
        String cnpj = "";
        cnpj = getCnpjById(id);
        id = 0;
        if (!cnpj.equals("")) {
            String chave = "";
            chave = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                    "<chaves>" +
                    "<cnpj>" + cnpj + "</cnpj>" +
                    "</chaves>";
            String xml = "";
            xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                    "<projetos>" +
                    "<QUERY>" +
                    "SELECT id FROM  tb_company  where (cnpj = '" + cnpj + "')" +
                    "</QUERY>" +
                    "</projetos>";
            String res = "";
            activeWBS();
            res = wbs.callWebService("company", "C", xml, chave);
            Document doc = XMLfunctions.XMLfromString(res);
            if (doc != null) {
                NodeList nodes;
                nodes = doc.getElementsByTagName("dados");
                if (nodes.getLength() > 0) {
                    Element e = (Element) nodes.item(0);
                    if (e != null) {
                        id = Integer.parseInt(XMLfunctions.getValue(e, "id"));
                    }
                }
            }
        }
        return id;
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
                    "SELECT DISTINCT tb_company.* " +
                    "FROM  tb_company   " +
                    "inner join tb_institution_has_entity  " +
                    "on (tb_institution_has_entity.tb_entity_id = tb_company.id) " +
                    " inner join  tb_customer " +
                    " on (tb_customer.id = tb_institution_has_entity.tb_entity_id) " +
                    "where ((tb_company.created_at > '" + data + "') or (tb_company.created_at is null)) " +
                    "  and ( tb_customer.tb_vendor_id = '"  + Integer.toString(MainActivity.gbSalesman)  + "') " +
                    " and (tb_institution_has_entity.tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution) + "') " +
                    " order by created_at " +
                "</QUERY>" +
                "</projetos>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("company", "C", xml,chave);
        return res;
    }

    public String sendWebService(EtdCompany obj)
    {
        String chave = "";
        chave = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                "<chaves>" +
                "<cnpj>" + obj.getCnpj() + "</cnpj>"+
                "</chaves>";

        String xml = "";
        xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<company>" ;
        if (obj.getId() > 0) {
            xml = xml +"<id>" + Integer.toString(obj.getId()) + "</id>";
        }
        xml = xml +
                "<cnpj>" + obj.getCnpj()+"</cnpj>" +
                "<ie>" + obj.getIe()+ "</ie>" +
                "<im>" + obj.getIm()+"</im>" +
                "<iest>"+ obj.getIest()+ "</iest>" +
                "<dt_foundation>" + obj.getDt_foundation()+"</dt_foundation>" +
                "</company>";
        String res = "";
        activeWBS();
        res =  wbs.callWebService("company", "A", xml, chave);
        return res;
    }




}
