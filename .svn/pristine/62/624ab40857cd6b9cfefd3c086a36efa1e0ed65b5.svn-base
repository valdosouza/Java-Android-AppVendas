package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdEntity;
import com.setes.setesvendas.app.pedidovenda.MainActivity;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

// Referenced classes of package com.setes.model:
//            OpenHelper

public class EntityHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_entity";

    public EntityHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
        firstRegister();
    }

    public void add(EtdEntity obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("name_company", obj.getName_company());
        contentvalues.put("nick_trade", obj.getNick_trade());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert("tb_entity", null, contentvalues);
        db.close();
    }

    public void update(EtdEntity obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("name_company", obj.getName_company());
        contentvalues.put("nick_trade", obj.getNick_trade());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_entity", contentvalues, "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.execSQL(" CREATE TABLE if not exists tb_entity ( " +
                "id int(11) NOT NULL,  " +
                "name_company varchar(100) NOT NULL, " +
                "nick_trade varchar(100) NOT NULL,  " +
                "created_at datetime,  " +
                "updated_at datetime) ");
        db.close();
    }

    public void delete(EtdEntity obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_entity", "id = ?", new String[]{
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


    public void firstRegister()
    {
        if (!verifyRegisterByField("tb_entity", "id", "0"))
        {
            EtdEntity obj = new EtdEntity();
            obj.setId(0);
            obj.setName_company("Cliente não informado");
            obj.setNick_trade("Cliente não informado");
            add(obj);
        }
    }

    public EtdEntity get(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.query(TABLE, new String[] {
            "id", "name_company", "nick_trade"
        }, "id=?", new String[] {
            String.valueOf(Integer.toString(id))
        }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            EtdEntity obj = new EtdEntity();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setName_company(cursor.getString(cursor.getColumnIndex("name_company")));
            obj.setNick_trade(cursor.getString(cursor.getColumnIndex("nick_trade")));
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

    public ArrayList<EtdEntity> getSearchCustomer(int tpPessoa, String name, String doc)
    {
        String sql = "";
        if (tpPessoa == 0)
        {
            sql = "SELECT  tb_entity.id, tb_entity.name_company, tb_entity.nick_trade  " +
                  "FROM tb_entity " +
                  "  INNER JOIN tb_customer " +
                  "  ON ( tb_customer.id = tb_entity.id ) " +
                  "  INNER JOIN tb_person " +
                  "  ON (tb_person.id = tb_entity.id) ";
        } else
        {
            sql = "SELECT  tb_entity.id, tb_entity.name_company, tb_entity.nick_trade  " +
                    "FROM tb_entity " +
                    "  INNER JOIN tb_customer " +
                    "  ON ( tb_customer.id = tb_entity.id ) " +
                    "  INNER JOIN tb_company " +
                    "  ON (tb_company.id = tb_entity.id)";
        }
        sql = sql + " Where (tb_entity.id is not null) ";
        if (!name.equals(""))
        {
            sql = sql + " and ( (tb_entity.name_company like '%" + name + "%') or (tb_entity.nick_trade like '%" + name + "%') ) ";
        }
        if (!doc.equals("")){
            if (doc.length() == 11){
                sql = sql + "  and (cpf like= '%" + doc  + "%') ";
            }else{
                sql = sql + "  and (cnpj like '%" + doc  + "%') ";
            }
        }
        sql = sql + " order by tb_entity.nick_trade ";

        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery(sql, null);
        if ( (cursor != null) && (cursor.moveToFirst()))
        {
            do
            {
                EtdEntity obj = new EtdEntity();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setName_company(cursor.getString(cursor.getColumnIndex("name_company")));
                obj.setNick_trade(cursor.getString(cursor.getColumnIndex("nick_trade")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public List<EtdEntity> getAll()
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_entity where id > 0", null);
        if ( (cursor != null) && (cursor.moveToFirst()))
        {
            do
            {
                EtdEntity obj = new EtdEntity();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setName_company(cursor.getString(cursor.getColumnIndex("name_company")));
                obj.setNick_trade(cursor.getString(cursor.getColumnIndex("nick_trade")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public ArrayList<EtdEntity> getSearch()
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_entity", null);
        if ( (cursor != null) && (cursor.moveToFirst()))
        {
            do
            {
                EtdEntity obj = new EtdEntity();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setName_company(cursor.getString(cursor.getColumnIndex("name_company")));
                obj.setNick_trade(cursor.getString(cursor.getColumnIndex("nick_trade")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public ArrayList<EtdEntity> getSendWs(String data)
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE + " where (id > 0) and (updated_at > '" + data + "') ",null);
        if ( (cursor != null) && (cursor.moveToFirst()))
        {
            do
            {
                EtdEntity obj = new EtdEntity();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setName_company(cursor.getString(cursor.getColumnIndex("name_company")));
                obj.setNick_trade(cursor.getString(cursor.getColumnIndex("nick_trade")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_entity");
        onCreate(sqlitedatabase);
    }

    public void persisteWebService(Element element, long l)
    {
        EtdEntity obj = new EtdEntity();
        obj.setId((int)l);
        obj.setName_company(XMLfunctions.getValue(element, "name_company"));
        obj.setNick_trade(XMLfunctions.getValue(element, "nick_trade"));
        obj.setCreated_at(XMLfunctions.getValue(element, "created_at"));
        if (verifyRegisterByField("tb_entity", "id", Long.toString(l)))
        {
            update(obj);
            return;
        } else
        {
            add(obj);
            return;
        }
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
                "SELECT tb_entity.* , tb_person.cpf doc " +
                "FROM tb_entity   " +
                "   INNER JOIN tb_person " +
                "   ON ( tb_person.id = tb_entity.id )   " +
                "   inner join tb_institution_has_entity  " +
                "   on (tb_institution_has_entity.tb_entity_id = tb_entity.id) " +
                "   inner join  tb_customer " +
                "   on (tb_customer.id = tb_institution_has_entity.tb_entity_id) " +
                "where ( (tb_entity.created_at > '" + data +"') or (tb_entity.created_at is null) )" +
                "  and ( tb_customer.tb_vendor_id = '"  + Integer.toString(MainActivity.gbSalesman)  + "') " +
                "  and (tb_institution_has_entity.tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution)  + "') " +
                "  and (tb_institution_has_entity.tb_institution_id > 0) "+
                "  and (tb_entity.id > 0) "+
                "UNION " +
                "SELECT tb_entity.*, tb_company.cnpj doc " +
                "FROM tb_entity " +
                "  INNER JOIN tb_company " +
                "  ON ( tb_company.id = tb_entity.id ) " +
                "  inner join tb_institution_has_entity  " +
                "  on (tb_institution_has_entity.tb_entity_id = tb_entity.id) " +
                "  inner join  tb_customer " +
                "  on (tb_customer.id = tb_institution_has_entity.tb_entity_id) " +
                "where ( (tb_entity.created_at > '" + data +"') or (tb_entity.created_at is null) )" +
                "  and ( tb_customer.tb_vendor_id = '"  + Integer.toString(MainActivity.gbSalesman)  + "') " +
                "  and (tb_institution_has_entity.tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution)  + "') " +
                "  and (tb_institution_has_entity.tb_institution_id > 0) "+
                "  and (tb_entity.id > 0) "+
                "</QUERY>" +
                "</projetos>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("entity", "C", xml, chave);
        return res;
    }

    public String sendWebService(EtdEntity obj)
    {
        String chave = "";
        chave = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                "<chaves>" +
                "<id>" + Integer.toString(obj.getId()) + "</id>" +
                "</chaves>";
        String xml = "";
        xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<entity>" +
                "<id>" + Integer.toString(obj.getId()) + "</id>"+
                "<name_company><![CDATA[" + obj.getName_company() + "]]></name_company>" +
                "<nick_trade><![CDATA[" + obj.getNick_trade() + "]]></nick_trade>" +
                "</entity>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("entity", "A", xml, chave);
        return res;

    }



}
