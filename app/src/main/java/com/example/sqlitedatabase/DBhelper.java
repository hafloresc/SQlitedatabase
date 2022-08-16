package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "Userdatabase", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table INFORMACION(id TEXT primary key, pais TEXT, tipo TEXT, usuario TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS INFORMACION");

    }


    public boolean insertar(String id, String pais, String tipo , String usuario){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("pais",pais);
        contentValues.put("tipo",tipo);
        contentValues.put("usuario",usuario);
        long result=DB.insert("INFORMACION",null,contentValues);

        if (result==-1){
            return false;
        }else{
            return true;
        }
    }

    public boolean actualizar(String id, String pais, String tipo , String usuario){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("pais",pais);
        contentValues.put("tipo",tipo);
        contentValues.put("usuario",usuario);
        Cursor cursor=DB.rawQuery("select * from INFORMACION where id = ?",new String[]{id});
        if (cursor.getCount()>0){
            long result=DB.update("INFORMACION",contentValues,"id=?",new String[]{id});

            if (result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }

    }

    public boolean borrar(String id){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("select * from INFORMACION where id = ?",new String[]{id});
        if (cursor.getCount()>0){
            long result=DB.delete("INFORMACION","id=?",new String[]{id});

            if (result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }

    }

    public Cursor getdata(){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("select * from INFORMACION",null);
        return cursor;

    }

}
