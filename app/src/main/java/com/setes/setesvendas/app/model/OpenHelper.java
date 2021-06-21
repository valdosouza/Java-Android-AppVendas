package com.setes.setesvendas.app.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.setes.setesvendas.app.util.WebService;


public class OpenHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;
    public WebService wbs;
    // Database Name
    private static final String DATABASE_NAME = "pedidovenda";
	
	public static SQLiteDatabase db;


	public OpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// N�o usa, pois n�s damos um new na classe
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// N�o usa, pois n�s damos um new na classe
	}

    // Getting Find By Field 
    public boolean  verifyRegisterByField(String table, String field, String value) 
    {   
		String select = "SELECT  * FROM " + table + " where " + field + "='" + value + "'";
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

    public int getNexId(String table) {
        String select = "SELECT max(id) id FROM " + table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        Integer res = 1;
        if (cursor != null && cursor.moveToFirst()){
            res =  cursor.getInt(cursor.getColumnIndex("id"));
        }
        cursor.close();
        db.close();
        return res + 1;
    }

    public void activeWBS(){
        if (wbs == null){
            wbs = new WebService();
        }

    }
    public boolean webService(Context c)
    {
        activeWBS();
        if (wbs.checkInternetConnection(c))
        {
            return true;
        } else
        {
            Toast.makeText(c, "Sem conexão com a internet", Toast.LENGTH_SHORT).show();;
            return false;
        }
    }

}
