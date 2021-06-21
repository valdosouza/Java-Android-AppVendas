package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdPhone;
import com.setes.setesvendas.app.pedidovenda.MainActivity;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Element;


public class PhoneHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_phone";

    public PhoneHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
    }

    public void add(EtdPhone obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("kind", obj.getKind());
        contentvalues.put("contact", obj.getContact());
        contentvalues.put("number", obj.getNumber());
        contentvalues.put("address_kind", obj.getAddress_kind());
        contentvalues.put("created_at", obj.getCreated_at());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert("tb_phone", null, contentvalues);
        db.close();
    }

    public void update(EtdPhone obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("kind", obj.getKind());
        contentvalues.put("contact", obj.getContact());
        contentvalues.put("number", obj.getNumber());
        contentvalues.put("address_kind", obj.getAddress_kind());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_phone", contentvalues, "(id= ?) and (kind=?) ", new String[]{
                String.valueOf(obj.getId()), obj.getKind()
        });
        db.close();
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.execSQL(" CREATE TABLE if not exists tb_phone ( " +
                "id int(11) NOT NULL , " +
                "kind varchar(20) NOT NULL, " +
                "contact varchar(100) NOT NULL, " +
                "number varchar(20) NOT NULL, " +
                "address_kind varchar(100) NOT NULL,  " +
                "created_at datetime,  " +
                "updated_at datetime) ");
        db.close();
    }

    public void delete(EtdPhone obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_phone", "(id= ?) and (kind=?) ", new String[]{
                String.valueOf(obj.getId()), obj.getKind()
        });
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, null, null);
        db.close();
    }


    public EtdPhone get(int id, String kind)
    {

        String selectQuery = "SELECT  * " +
                             "FROM " + TABLE +
                             " where (id ='" + Integer.toString(id) + "')";
        if (!kind.equals("")) {
            selectQuery = selectQuery + " and (kind = '" + kind + "')";
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if ((cursor != null) && cursor.getCount() > 0) {
            cursor.moveToFirst();
            EtdPhone obj = new EtdPhone();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setKind(cursor.getString(cursor.getColumnIndex("kind")));
            obj.setContact(cursor.getString(cursor.getColumnIndex("contact")));
            obj.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            obj.setAddress_kind(cursor.getString(cursor.getColumnIndex("address_kind")));
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

    public ArrayList<EtdPhone> getAll()
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_phone where id > 0", null);
        if ((cursor != null) && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                EtdPhone obj = new EtdPhone();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setKind(cursor.getString(cursor.getColumnIndex("kind")));
                obj.setContact(cursor.getString(cursor.getColumnIndex("contact")));
                obj.setNumber(cursor.getString(cursor.getColumnIndex("number")));
                obj.setAddress_kind(cursor.getString(cursor.getColumnIndex("address_kind")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public ArrayList<EtdPhone> getByCystomer(int id)
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_phone where (id ='" + id + "')", null);
        if ((cursor != null) && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                EtdPhone obj = new EtdPhone();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setKind(cursor.getString(cursor.getColumnIndex("kind")));
                obj.setContact(cursor.getString(cursor.getColumnIndex("contact")));
                obj.setNumber(cursor.getString(cursor.getColumnIndex("number")));
                obj.setAddress_kind(cursor.getString(cursor.getColumnIndex("address_kind")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_phone");
        onCreate(sqlitedatabase);
    }

    public boolean  verifyRegister(int id, String kind)
    {
        String select = "SELECT  id FROM " + TABLE +
                        " where (id = '" + id+ "') and (kind = '" + kind + "')";
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

    public void persisteWebService(Element element, long l)
    {
        EtdPhone obj = new EtdPhone();
        obj.setId((int)l);
        obj.setKind(XMLfunctions.getValue(element, "kind"));
        obj.setContact(XMLfunctions.getValue(element, "contact"));
        obj.setNumber(XMLfunctions.getValue(element, "number"));
        obj.setAddress_kind(XMLfunctions.getValue(element, "address_kind").toUpperCase());
        obj.setCreated_at(XMLfunctions.getValue(element, "created_at"));
        if (verifyRegister(obj.getId(), obj.getKind())) {
            update(obj);
        } else {
            add(obj);
        }
    }

    public ArrayList<EtdPhone> getSendWs(String data)
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE + " where (id > 0) and (updated_at > '" + data + "') ",null);
        if ( (cursor != null) && (cursor.moveToFirst()))
        {
            do
            {
                EtdPhone obj = new EtdPhone();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setKind(cursor.getString(cursor.getColumnIndex("kind")));
                obj.setContact(cursor.getString(cursor.getColumnIndex("contact")));
                obj.setNumber(cursor.getString(cursor.getColumnIndex("number")));
                obj.setAddress_kind(cursor.getString(cursor.getColumnIndex("address_kind")));
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
                "<kind>0</kind>"+
                "</chaves>";
        String xml = "";
        xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<projetos>" +
                "<QUERY>" +
                "SELECT tb_phone.* , tb_person.cpf doc " +
                "FROM tb_phone   " +
                "  INNER JOIN tb_person " +
                "  ON ( tb_person.id = tb_phone.id )   " +
                "  inner join tb_institution_has_entity  " +
                "  on (tb_institution_has_entity.tb_entity_id = tb_person.id) " +
                "  inner join  tb_customer " +
                "  on (tb_customer.id = tb_institution_has_entity.tb_entity_id) " +
                "where ((tb_phone.created_at > '" + data + "') or (tb_phone.created_at is null))  " +
                "  and ( tb_customer.tb_vendor_id = '"  + Integer.toString(MainActivity.gbSalesman)  + "') " +
                "  and (tb_institution_has_entity.tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution)  + "') " +
                "UNION " +
                "SELECT tb_phone.*, tb_company.cnpj doc " +
                "FROM tb_phone " +
                "  INNER JOIN tb_company " +
                "  ON ( tb_company.id = tb_phone.id ) " +
                "  inner join tb_institution_has_entity  " +
                "  on (tb_institution_has_entity.tb_entity_id = tb_company.id) " +
                "  inner join  tb_customer " +
                "  on (tb_customer.id = tb_institution_has_entity.tb_entity_id) " +
                "where ((tb_phone.created_at > '"+ data + "') or (tb_phone.created_at is null))  " +
                "  and ( tb_customer.tb_vendor_id = '"  + Integer.toString(MainActivity.gbSalesman)  + "') " +
                " and (tb_institution_has_entity.tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution)  + "') " +
                "</QUERY>" +
                "</projetos>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("phone", "C", xml, chave);
        return res;
    }

    public String sendWebService(EtdPhone obj)
    {
        String chave = "";
        chave = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                "<chaves><id>" + obj.getId() + "</id>" +
                "<kind>" + obj.getKind() + "</kind>" +
                "</chaves>";
        String xml = "";
        xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<phone>" +
                "<id>" + obj.getId() + "</id>" +
                "<kind>"  + obj.getKind() + "</kind>" +
                "<contact>" + obj.getContact() + "</contact>" +
                "<number>" + obj.getNumber() + "</number>" +
                "<address_kind>" + obj.getAddress_kind() + "</address_kind>" +
                "</phone>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("phone", "A", xml, chave);
        return res;
    }


}
