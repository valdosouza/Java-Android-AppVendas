package com.setes.setesvendas.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.setes.setesvendas.app.controller.EtdConfig;

import java.util.ArrayList;
import java.util.List;


public class ConfigHandler extends OpenHelper
{

    private static final String TABLE = "tb_config";

    public ConfigHandler(Context context)
    {
        super(context);
        createTable();
    }

    public void add(EtdConfig obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", obj.getId());
        contentvalues.put("content", obj.getContent());
        db.insert("tb_config", null, contentvalues);
        db.close();
    }

    public void createTable()
    {

        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("CREATE TABLE if not exists tb_config ( " +
                   "id VARCHAR( 100 ) primary key,  " +
                   "content VARCHAR( 100 ));");
        db.close();

    }

    public void delete(EtdConfig obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tb_config", "id = ?", new String[] {
            String.valueOf(obj.getId())
        });
        db.close();
    }

    public EtdConfig get(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE,
                new String[]{"id", "content"}, "id=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            EtdConfig obj = new EtdConfig();
            obj.setId(cursor.getString(cursor.getColumnIndex("id")));
            obj.setContent(cursor.getString(cursor.getColumnIndex("content")));
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


    public int getCount()
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM tb_config", null);
        int i = cursor.getCount();
        cursor.close();
        db.close();
        return i;
    }


    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        db.execSQL("DROP TABLE IF EXISTS tb_config");
        onCreate(sqlitedatabase);
        db.close();
    }

    public void update(EtdConfig etdconfig)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", etdconfig.getId());
        contentvalues.put("content", etdconfig.getContent());
        db.update("tb_config", contentvalues, "id = ?", new String[] {
            String.valueOf(etdconfig.getId())
        });
        db.close();
    }
}
