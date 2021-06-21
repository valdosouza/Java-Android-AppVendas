package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdAddress;
import com.setes.setesvendas.app.pedidovenda.MainActivity;
import com.setes.setesvendas.app.util.WebService;
import com.setes.setesvendas.app.util.XMLfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AddressHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_address";

    public AddressHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" CREATE TABLE if not exists tb_address ( " +
                "id int(11) NOT NULL,  " +
                "street varchar(100) ,  " +
                "nmbr varchar(10) NOT NULL,  " +
                "complement varchar(100) NOT NULL,  " +
                "neighborhood varchar(100),  " +
                "region varchar(100) ,  " +
                "kind varchar(100) NOT NULL,  " +
                "zip_code varchar(15) DEFAULT NULL,  " +
                "tb_country_id int(11) NOT NULL,  " +
                "tb_state_id int(11) NOT NULL,  " +
                "tb_city_id int(11) NOT NULL,  " +
                "main char(1),  " +
                "created_at datetime,  " +
                "updated_at datetime) ");
        db.close();
    }
    public void add(EtdAddress etdaddress)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(etdaddress.getId()));
        contentvalues.put("street", etdaddress.getStreet());
        contentvalues.put("nmbr", etdaddress.getNmbr());
        contentvalues.put("complement", etdaddress.getComplement());
        contentvalues.put("neighborhood", etdaddress.getNeighborhood());
        contentvalues.put("region", etdaddress.getRegion());
        contentvalues.put("kind", etdaddress.getKind());
        contentvalues.put("zip_code", etdaddress.getZip_code());
        contentvalues.put("tb_country_id", Integer.valueOf(etdaddress.getTb_country_id()));
        contentvalues.put("tb_state_id", Integer.valueOf(etdaddress.getTb_state_id()));
        contentvalues.put("tb_city_id", Integer.valueOf(etdaddress.getTb_city_id()));
        contentvalues.put("main", etdaddress.getMain());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("created_at", formatter.format(date));
        contentvalues.put("updated_at", formatter.format(date));
        db.insert("tb_address", null, contentvalues);
        db.close();
    }

    public void update(EtdAddress etdaddress)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", Integer.valueOf(etdaddress.getId()));
        contentvalues.put("street", etdaddress.getStreet());
        contentvalues.put("nmbr", etdaddress.getNmbr());
        contentvalues.put("complement", etdaddress.getComplement());
        contentvalues.put("neighborhood", etdaddress.getNeighborhood());
        contentvalues.put("region", etdaddress.getRegion());
        contentvalues.put("kind", etdaddress.getKind());
        contentvalues.put("zip_code", etdaddress.getZip_code());
        contentvalues.put("tb_country_id", Integer.valueOf(etdaddress.getTb_country_id()));
        contentvalues.put("tb_state_id", Integer.valueOf(etdaddress.getTb_state_id()));
        contentvalues.put("tb_city_id", Integer.valueOf(etdaddress.getTb_city_id()));
        contentvalues.put("main", etdaddress.getMain());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2015-11-23 16:10:26
        contentvalues.put("updated_at", formatter.format(date));
        db.update("tb_address", contentvalues, "(id= ?) and (kind=?) ", new String[]{
                String.valueOf(etdaddress.getId()), etdaddress.getKind()
        });
        db.close();
    }



    public void delete(int id, String kind)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tb_address", "(id= ?) and (kind=?) ",
                new String[] { String.valueOf(id), kind});
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, null, null);
        db.close();
    }

    public EtdAddress get(int id, String kind)
    {
        String selectQuery = "SELECT  * " +
                             "FROM " + TABLE +
                             " where (id ='" + id + "') ";
        if (!kind.equals("")) {
            selectQuery = selectQuery + " and (kind = '" + kind + "')";
        }
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if ((cursor != null) && cursor.getCount() > 0) {
            cursor.moveToFirst();
            EtdAddress obj = new EtdAddress();
            obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
            obj.setKind(cursor.getString(cursor.getColumnIndex("kind")));
            obj.setMain(cursor.getString(cursor.getColumnIndex("main")));
            obj.setStreet(cursor.getString(cursor.getColumnIndex("street")));
            obj.setNmbr(cursor.getString(cursor.getColumnIndex("nmbr")));
            obj.setComplement(cursor.getString(cursor.getColumnIndex("complement")));
            obj.setNeighborhood(cursor.getString(cursor.getColumnIndex("neighborhood")));
            obj.setRegion(cursor.getString(cursor.getColumnIndex("region")));
            obj.setZip_code(cursor.getString(cursor.getColumnIndex("zip_code")));
            obj.setTb_country_id(cursor.getInt(cursor.getColumnIndex("tb_country_id")));
            obj.setTb_state_id(cursor.getInt(cursor.getColumnIndex("tb_state_id")));
            obj.setTb_city_id(cursor.getInt(cursor.getColumnIndex("tb_city_id")));
            obj.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
            obj.setUpdated_at(cursor.getString(cursor.getColumnIndex("updated_at")));
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

    public ArrayList<EtdAddress> getByCustomer(int id)
    {
        ArrayList objlist = new ArrayList();
        String selectQuery = "SELECT  * " +
                "FROM " + TABLE +
                " where (id ='" + Integer.toString(id) + "')" ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if ((cursor != null) && cursor.getCount() > 0) {

            cursor.moveToFirst();
            do {
                EtdAddress obj = new EtdAddress();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setKind(cursor.getString(cursor.getColumnIndex("kind")));
                obj.setMain(cursor.getString(cursor.getColumnIndex("main")));
                obj.setStreet(cursor.getString(cursor.getColumnIndex("street")));
                obj.setNmbr(cursor.getString(cursor.getColumnIndex("nmbr")));
                obj.setComplement(cursor.getString(cursor.getColumnIndex("complement")));
                obj.setNeighborhood(cursor.getString(cursor.getColumnIndex("neighborhood")));
                obj.setRegion(cursor.getString(cursor.getColumnIndex("region")));
                obj.setZip_code(cursor.getString(cursor.getColumnIndex("zip_code")));
                obj.setTb_country_id(cursor.getInt(cursor.getColumnIndex("tb_country_id")));
                obj.setTb_state_id(cursor.getInt(cursor.getColumnIndex("tb_state_id")));
                obj.setTb_city_id(cursor.getInt(cursor.getColumnIndex("tb_city_id")));
                obj.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                obj.setUpdated_at(cursor.getString(cursor.getColumnIndex("updated_at")));
                objlist.add(obj);
            } while (cursor.moveToNext());
        }
         cursor.close();
         db.close();
         return objlist;
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

    public void persisteWebService(Element element,int id)
    {
        EtdAddress obj = new EtdAddress();
        obj.setId(id);
        obj.setStreet(XMLfunctions.getValue(element, "street"));
        obj.setNmbr(XMLfunctions.getValue(element, "nmbr"));
        obj.setComplement(XMLfunctions.getValue(element, "complement"));
        obj.setNeighborhood(XMLfunctions.getValue(element, "neighborhood"));
        obj.setRegion(XMLfunctions.getValue(element, "region"));
        obj.setKind(XMLfunctions.getValue(element, "kind"));
        obj.setZip_code(XMLfunctions.getValue(element, "zip_code"));
        if (!XMLfunctions.getValue(element, "tb_country_id").equals(""))
        {
            obj.setTb_country_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_country_id")));
        } else
        {
            obj.setTb_country_id(0);
        }
        if (!XMLfunctions.getValue(element, "tb_state_id").equals(""))
        {
            obj.setTb_state_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_state_id")));
        } else
        {
            obj.setTb_state_id(0);
        }
        if (!XMLfunctions.getValue(element, "tb_city_id").equals(""))
        {
            obj.setTb_city_id(Integer.parseInt(XMLfunctions.getValue(element, "tb_city_id")));
        } else
        {
            obj.setTb_city_id(0);
        }
        obj.setMain(XMLfunctions.getValue(element, "main"));
        obj.setCreated_at(XMLfunctions.getValue(element, "created_at"));
        if (verifyRegister(obj.getId(),obj.getKind()))
        {
            update(obj);
        } else
        {
            add(obj);
        }
    }

    public ArrayList<EtdAddress> getSendWs(String data)
    {
        ArrayList objlist = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE + " where (id > 0) and (updated_at > '" + data + "') ",null);
        if ( (cursor != null) && (cursor.moveToFirst()))
        {
            do
            {
                EtdAddress obj = new EtdAddress();
                obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                obj.setKind(cursor.getString(cursor.getColumnIndex("kind")));
                obj.setMain(cursor.getString(cursor.getColumnIndex("main")));
                obj.setStreet(cursor.getString(cursor.getColumnIndex("street")));
                obj.setNmbr(cursor.getString(cursor.getColumnIndex("nmbr")));
                obj.setComplement(cursor.getString(cursor.getColumnIndex("complement")));
                obj.setNeighborhood(cursor.getString(cursor.getColumnIndex("neighborhood")));
                obj.setRegion(cursor.getString(cursor.getColumnIndex("region")));
                obj.setZip_code(cursor.getString(cursor.getColumnIndex("zip_code")));
                obj.setTb_country_id(cursor.getInt(cursor.getColumnIndex("tb_country_id")));
                obj.setTb_state_id(cursor.getInt(cursor.getColumnIndex("tb_state_id")));
                obj.setTb_city_id(cursor.getInt(cursor.getColumnIndex("tb_city_id")));
                obj.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                obj.setUpdated_at(cursor.getString(cursor.getColumnIndex("updated_at")));
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
        String chave;
        chave = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                "<chaves>" +
                "<id>0</id>" +
                "<tb_instituition_id>0</tb_instituition_id>" +
                "</chaves>";
        String xml;
        xml ="<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<projetos>" +
                "<QUERY>" +
                    "SELECT tb_address.* , tb_person.cpf doc " +
                    "FROM tb_address " +
                    "  INNER JOIN tb_person " +
                    "  ON ( tb_person.id = tb_address.id )   " +
                    "  inner join tb_institution_has_entity  " +
                    "  on (tb_institution_has_entity.tb_entity_id = tb_person.id) " +
                    "  inner join  tb_customer " +
                    "  on (tb_customer.id = tb_institution_has_entity.tb_entity_id) " +
                    "where ((tb_address.created_at > '" + data + "') or (tb_address.created_at is null))  " +
                    "  and ( tb_customer.tb_vendor_id = '"  + Integer.toString(MainActivity.gbSalesman)  + "') " +
                    "  and (tb_institution_has_entity.tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution) + "') " +
                    "UNION " +
                    "SELECT tb_address.*, tb_company.cnpj doc " +
                    "FROM tb_address " +
                    "  INNER JOIN tb_company " +
                    "  ON ( tb_company.id = tb_address.id ) " +
                    "  inner join tb_institution_has_entity  " +
                    "  on (tb_institution_has_entity.tb_entity_id = tb_company.id) " +
                    "  inner join  tb_customer " +
                    "  on (tb_customer.id = tb_institution_has_entity.tb_entity_id) " +
                    "where ((tb_address.created_at > '" + data  +"') or (tb_address.created_at is null))  " +
                    "  and ( tb_customer.tb_vendor_id = '"  + Integer.toString(MainActivity.gbSalesman)  + "') " +
                    "and (tb_institution_has_entity.tb_institution_id = '" + Integer.toString(MainActivity.gbInstitution)+"')"+
                "</QUERY>" +
                "</projetos>";
        String res = "";
        activeWBS();
        res = wbs.callWebService("address", "C", xml, chave);
        return res;
    }

    public String sendWebService(EtdAddress obj)
    {
        String chaves;
        chaves = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>" +
                "<chaves>" +
                    "<id>" + Integer.toString(obj.getId()) + "</id>" +
                    "<kind>" + obj.getKind() + "</kind>" +
                "</chaves>";
        String xml;
        xml = "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes' ?>" +
                "<address>" +
                    "<id>" + Integer.toString(obj.getId()) + "</id>" +
                    "<street><![CDATA[" + obj.getStreet() + "]]></street>"+
                    "<nmbr><![CDATA[" + obj.getNmbr() + "]]></nmbr>"+
                    "<complement><![CDATA[" + obj.getComplement() + "]]></complement>"+
                    "<neighborhood><![CDATA[" + obj.getNeighborhood() + "]]></neighborhood>"+
                    "<region><![CDATA[" + obj.getRegion() + "]]></region>"+
                    "<kind><![CDATA[" + obj.getKind() + "]]></kind>"+
                    "<zip_code>" + obj.getZip_code() + "</zip_code>"+
                    "<tb_country_id>" + Integer.toString(obj.getTb_country_id()) + "</tb_country_id>"+
                    "<tb_state_id>" + Integer.toString(obj.getTb_state_id()) + "</tb_state_id>"+
                    "<tb_city_id>" + Integer.toString(obj.getTb_city_id()) + "</tb_city_id>" +
                "<main>" + obj.getMain() + "</main>" +
                "</address>";
        String retorno = "";
        activeWBS();
        retorno = wbs.callWebService("address", "A", xml, chaves);
        Document doc = XMLfunctions.XMLfromString(retorno);
        if (doc != null) {
            org.w3c.dom.Node f = doc.getFirstChild();
            return XMLfunctions.getElementValue(f);
        }else {
            return "";
        }
    }

}
