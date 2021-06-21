package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdPerson;
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


public class PersonHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_person";

    public PersonHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
    }

    public long add(EtdPerson obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("cpf", obj.getCpf());
        contentvalues.put("rg", obj.getRg());
        contentvalues.put("birthday", obj.getBirthday());
        contentvalues.put("tb_profession_id", Integer.valueOf(obj.getTb_profession_id()));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        long l = db.insert("tb_person", null, contentvalues);
        db.close();
        return l;
    }

    public void update(EtdPerson obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(obj.getId()));
        contentvalues.put("cpf", obj.getCpf());
        contentvalues.put("rg", obj.getRg());
        contentvalues.put("birthday", obj.getBirthday());
        contentvalues.put("tb_profession_id", Integer.valueOf(obj.getTb_profession_id()));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_person", contentvalues, "id = ?", new String[]{
                String.valueOf(obj.getId())
        });
        db.close();
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.execSQL(" CREATE TABLE if not exists tb_person ( " +
                   " id int(11) NOT NULL,  " +
                   " cpf char(11) DEFAULT NULL,  " +
                   " rg char(20) DEFAULT NULL,  " +
                   " birthday char(5) DEFAULT NULL,  " +
                   " tb_profession_id int(11) NOT NULL,  " +
                   " created_at datetime,  " +
                   " updated_at datetime) ");
        db.close();
    }

    public void delete(EtdPerson obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_person", "id = ?", new String[]{
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


    public EtdPerson get(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.query("tb_person", new String[] {
            "id", "cpf", "rg", "birthday", "tb_profession_id"
        }, "id=?", new String[] {
            String.valueOf(id)
        }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            EtdPerson obj = new EtdPerson();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
            obj.setRg(cursor.getString(cursor.getColumnIndex("rg")));
            obj.setBirthday(cursor.getString(cursor.getColumnIndex("birthday")));
            obj.setTb_profession_id(cursor.getInt(cursor.getColumnIndex("tb_profession_id")));
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

    public int getIdByCpf(String cpf)
    {
        int id = 0;
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.query(TABLE, new String[]{"id"}, "cpf=?",
                new String[]{cpf}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst())
        {
            id = cursor.getInt(cursor.getColumnIndex("id"));
        }
        cursor.close();
        db.close();
        return id;
    }

    public String getCpfById(int id)
    {
        String cpf = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("tb_person", new String[]{"cpf"}, "id=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            cpf =  cursor.getString(cursor.getColumnIndex("cpf"));
        }
        cursor.close();
        db.close();
        return cpf;
    }

    public List getAll()
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_person where id > 0", null);
        if ( (cursor != null) && (cursor.moveToFirst()))
        {
            do
            {
                EtdPerson obj = new EtdPerson();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
                obj.setRg(cursor.getString(cursor.getColumnIndex("rg")));
                obj.setBirthday(cursor.getString(cursor.getColumnIndex("birthday")));
                obj.setTb_profession_id(cursor.getInt(cursor.getColumnIndex("tb_profession_id")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public ArrayList<EtdPerson> getSendWs(String data)
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_person where (id > 0) and (updated_at > '" + data + "') ", null);
        if ( (cursor != null) && (cursor.moveToFirst()))
        {
            do
            {
                EtdPerson obj = new EtdPerson();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
                obj.setRg(cursor.getString(cursor.getColumnIndex("rg")));
                obj.setBirthday(cursor.getString(cursor.getColumnIndex("birthday")));
                obj.setTb_profession_id(cursor.getInt(cursor.getColumnIndex("tb_profession_id")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return objlist;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_person");
        onCreate(sqlitedatabase);
    }

    public long persisteWebService(Element element)
    {
        EtdPerson obj = new EtdPerson();
        obj.setId(Integer.parseInt(XMLfunctions.getValue(element, "id")));
        obj.setCpf(XMLfunctions.getValue(element, "cpf"));
        obj.setRg(XMLfunctions.getValue(element, "rg"));
        obj.setBirthday(XMLfunctions.getValue(element, "birthday"));
        obj.setTb_profession_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_profession_id")));
        obj.setCreated_at(XMLfunctions.getValue(element, "created_at"));
        if (!verifyRegisterByField(TABLE, "cpf", obj.getCpf())) {
            return add(obj);
        } else {
            update(obj);
            return (long)obj.getId();
        }
    }

    public Integer returnIdWeb(int id)
    {
        String cpf = "";
        cpf = getCpfById(id);
        id = 0;
        if (!cpf.equals("")) {
            String chave = "";
            chave = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                    "<chaves>" +
                    "<cpf>" + cpf + "</cpf>" +
                    "</chaves>";
            String xml = "";
            xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                    "<projetos>" +
                    "<QUERY>" +
                    "SELECT id FROM  tb_person  where (cpf = '" + cpf + "')" +
                    "</QUERY>" +
                    "</projetos>";
            String res = "";
            activeWBS();
            res = wbs.callWebService("person", "C", xml, chave);
            Document doc = XMLfunctions.XMLfromString(res);
            if (doc != null) {
                NodeList nodes;
                nodes = doc.getElementsByTagName("dados");
                Element e = (Element) nodes.item(0);
                if (e != null) {
                    id = Integer.parseInt(XMLfunctions.getValue(e, "id"));
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
                "<cpf>0</cpf>" +
                "</chaves>";
        String xml = "";
        xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<projetos>" +
                "<QUERY>" +
                "SELECT DISTINCT tb_person.* " +
                "FROM  tb_person   " +
                "inner join tb_institution_has_entity  " +
                " on (tb_institution_has_entity.tb_entity_id = tb_person.id)  " +
                " inner join  tb_customer " +
                " on (tb_customer.id = tb_institution_has_entity.tb_entity_id) " +
                "where ((tb_person.created_at > '" + data +"') or (tb_person.created_at is null)) " +
                "  and ( tb_customer.tb_vendor_id = '"  + Integer.toString(MainActivity.gbSalesman)  + "') " +
                "  and (tb_institution_has_entity.tb_institution_id = '"  + Integer.toString(MainActivity.gbInstitution)  + "') " +
                "order by created_at " +
                "</QUERY>" +
                "</projetos>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("person", "C", xml, chave);
        return res;
    }

    public String sendWebService(EtdPerson obj)
    {
        String chave ="";
        chave = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                "<chaves>" +
                "<cpf>"  + obj.getCpf() + "</cpf>" +
                "</chaves>";
        String xml = "";
        xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<person>";
        if (obj.getId() > 0) {
            xml = xml +"<id>" + Integer.toString(obj.getId()) + "</id>";
        }
        xml = xml +
                "<cpf>" + obj.getCpf() +"</cpf>" +
                "<rg>" + obj.getRg() + "</rg>" +
                "<birthday>" + obj.getBirthday() + "</birthday>" +
                "<tb_profession_id>" + obj.getTb_profession_id() + "</tb_profession_id>" +
                "</person>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("person", "A", xml, chave);
        return res;
    }


}
