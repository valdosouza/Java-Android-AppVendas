package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdMailing;
import com.setes.setesvendas.app.pedidovenda.MainActivity;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.w3c.dom.Element;

// Referenced classes of package com.setes.model:
//            OpenHelper

public class MailingHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_mailing";

    public MailingHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
    }

    public void add(EtdMailing obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("email", obj.getEmail());
        contentvalues.put("kind", obj.getKind());
        contentvalues.put("news", obj.getNews());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert("tb_mailing", null, contentvalues);
        db.close();
    }

    public void update(EtdMailing obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("email", obj.getEmail());
        contentvalues.put("kind", obj.getKind());
        contentvalues.put("news", obj.getNews());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_mailing", contentvalues, "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.execSQL(" CREATE TABLE if not exists tb_mailing ( " +
                "id int(11) not null,  " +
                "email varchar(100) NOT NULL,  " +
                "kind varchar(45) DEFAULT NULL,  " +
                "news char(1) DEFAULT 'N',  " +
                "created_at datetime,  " +
                "updated_at datetime) ");
        db.close();
    }

    public void delete(EtdMailing obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_mailing", "id = ?", new String[]{
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


    public EtdMailing get(int id, String kind)
    {
        String selectQuery = "SELECT  * " +
                "FROM " + TABLE +
                " where (id ='" + id + "')" ;
        if (!kind.equals("")){
            selectQuery = selectQuery + " and (kind = '" + kind + "')";
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if ((cursor != null) && cursor.getCount() > 0) {
            cursor.moveToFirst();
            EtdMailing obj = new EtdMailing();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            obj.setKind(cursor.getString(cursor.getColumnIndex("kind")));
            obj.setNews(cursor.getString(cursor.getColumnIndex("news")));
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

    public ArrayList getAll()
    {
        ArrayList arraylist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE + " where id > 0", null);
        if ((cursor != null) && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do
            {
                EtdMailing obj = new EtdMailing();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                obj.setKind(cursor.getString(cursor.getColumnIndex("kind")));
                obj.setNews(cursor.getString(cursor.getColumnIndex("news")));
                arraylist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return (ArrayList)arraylist;
    }


    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_mailing");
        onCreate(sqlitedatabase);
    }

    public boolean  verifyRegister(int id, String kind)
    {
        String select = "SELECT  * FROM " + TABLE +
                        " where (id = '" + id + "') " +
                        " and (kind = '" + kind + "')";
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

    public void persisteWebService(Element element, long id)
    {
        EtdMailing obj = new EtdMailing();
        obj.setId((int) id);
        obj.setEmail(XMLfunctions.getValue(element, "email").toLowerCase());
        if (XMLfunctions.getValue(element, "kind").equals("")){
            obj.setKind("Comercial");
         }else{
        obj.setKind(XMLfunctions.getValue(element, "kind").toUpperCase());
        }
        obj.setNews(XMLfunctions.getValue(element, "news").toUpperCase());
        obj.setCreated_at(XMLfunctions.getValue(element, "created_at"));
        if (verifyRegister(obj.getId(),obj.getKind())) {
            update(obj);
        } else {
            add(obj);
        }
    }

    public ArrayList<EtdMailing> getSendWs(String data)
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE +
                                    " where (id > 0) " +
                                    " and (email like '%@%') "+
                                    " and (updated_at > '" + data + "') ",null);
        if ( (cursor != null) && (cursor.moveToFirst()))
        {
            do
            {
                EtdMailing obj = new EtdMailing();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                obj.setKind(cursor.getString(cursor.getColumnIndex("kind")));
                obj.setNews(cursor.getString(cursor.getColumnIndex("news")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
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
                "<QUERY>SELECT tb_mailing.* , tb_person.cpf doc " +
                "FROM tb_mailing " +
                "  INNER JOIN tb_person " +
                "  ON ( tb_person.id = tb_mailing.id ) " +
                "  inner join tb_institution_has_entity  " +
                "  on (tb_institution_has_entity.tb_entity_id = tb_person.id)  " +
                "  inner join  tb_customer " +
                "  on (tb_customer.id = tb_institution_has_entity.tb_entity_id) " +
                "where ( (tb_mailing.created_at > '" + data  +"') or (tb_mailing.created_at is null) ) " +
                "  and ( tb_customer.tb_vendor_id = '"  + Integer.toString(MainActivity.gbSalesman)  + "') " +
                "  and (tb_mailing.email LIKE  '%@%') " +
                "  and (tb_institution_has_entity.tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution) + "') " +
                "UNION " +
                "SELECT tb_mailing.*, tb_company.cnpj doc " +
                "FROM tb_mailing " +
                "  INNER JOIN tb_company " +
                "  ON ( tb_company.id = tb_mailing.id ) " +
                "  inner join tb_institution_has_entity  " +
                "  on (tb_institution_has_entity.tb_entity_id = tb_company.id) " +
                "  inner join  tb_customer " +
                "  on (tb_customer.id = tb_institution_has_entity.tb_entity_id) " +
                "where ( (tb_mailing.created_at > '" + data + "') or (tb_mailing.created_at is null) ) " +
                "  and ( tb_customer.tb_vendor_id = '"  + Integer.toString(MainActivity.gbSalesman)  + "') " +
                "  and (tb_mailing.email LIKE  '%@%') " +
                "  and (tb_institution_has_entity.tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution) + "') " +
                "</QUERY>" +
                "</projetos>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("mailing", "C", xml, chave);
        return res;
    }

    public String sendWebService(EtdMailing obj)
    {
        String chave = "";
        chave = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                "<chaves>" +
                "<id>" + obj.getId() + "</id>" +
                "</chaves>";
        String xml = "";
        xml ="<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
             "<mailing>" +
                "<id>" + obj.getId() + "</id>" +
                "<email>" + obj.getEmail() + "</email>" +
                "<kind>" + obj.getKind() + "</kind>" +
                "<news>" + obj.getNews() + "</news>" +
             "</mailing>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("mailing", "A", xml, chave);
        return res;
    }


}
