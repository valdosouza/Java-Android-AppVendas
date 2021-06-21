package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.setes.setesvendas.app.controller.EtdSyncTable;

import java.util.ArrayList;
import java.util.List;


public class SyncTableHandler extends OpenHelper
{

    public static Context Contexto;
    private static final String TABLE = "tb_sync_table";

    public SyncTableHandler(Context context)
    {
        super(context);
        Contexto = context;
        createTable();
    }

    public void add(EtdSyncTable obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", obj.getId());
        contentvalues.put("direction", obj.getDirection());
        contentvalues.put("updated_at", obj.getUpdateAt());
        db.insert("tb_sync_table", null, contentvalues);
        db.close();
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.execSQL(" CREATE TABLE if not exists tb_sync_table ( " +
                "id varchar(100) NOT NULL,  " +
                "direction char(1),  " +
                "updated_at varchar(20)) ");
        db.close();
    }

    public void delete(EtdSyncTable obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        db.delete("tb_sync_table", "(id= ?) ", new String[]{
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


    public EtdSyncTable get(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        Cursor cursor = db.query(TABLE, new String[]{
                "id", "direction", "update_at"
        }, "id=?", new String[]{
                String.valueOf(id)
        }, null, null, null, null);
        if ((cursor != null) && cursor.getCount() > 0) {
            cursor.moveToFirst();
            EtdSyncTable obj = new EtdSyncTable();
            obj.setId(cursor.getString(cursor.getColumnIndex("id")));
            obj.setDirection(cursor.getString(cursor.getColumnIndex("direction")));
            obj.setUpdateAt(cursor.getString(cursor.getColumnIndex("update_at")));
            cursor.close();
            db.close();
            return obj;
        }else {
            cursor.close();
            db.close();
            return null;
        }
    }


    public String getDateTime(String id, String direction)
    {
        String selectQuery = "SELECT  * " +
                "FROM " + TABLE +
                " where (id ='" + id + "') and (direction = '" + direction+ "')";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if ((cursor != null) && cursor.getCount() > 0) {
            cursor.moveToFirst();
            String res = "";
            res = cursor.getString(cursor.getColumnIndex("updated_at"));
            if (res.equals("")) {
                res = "1900/01/01 00:00:00";
            }
            cursor.close();
            db.close();
            return res;
        }else{
            cursor.close();
            db.close();
            return "1900/01/01 00:00:00";
        }
    }

    public boolean  verifyRegister(String id, String direction)
    {
        String select = "SELECT  * " +
                "FROM " + TABLE +
                " where (id ='" + id + "') and (direction = '" + direction+ "')";
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

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_sync_table");
        onCreate(sqlitedatabase);
    }

    public void update(EtdSyncTable obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", obj.getId());
        contentvalues.put("direction", obj.getDirection());
        contentvalues.put("updated_at", obj.getUpdateAt());
        db.update("tb_sync_table", contentvalues, "( id = ?) and (direction = ?)", new String[] {
            String.valueOf(obj.getId()), String.valueOf(obj.getDirection())
        });
        db.close();
    }

}
