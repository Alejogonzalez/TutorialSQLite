package com.alejandrogonzalezv.tutorialsqlite;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alejandro on 24/05/2015.
 */
public class DataBaseManager {
    public static final String TABLE_NAME = "contactos";
    public static final String CN_ID = "_id";  // Nombre columna
    public static final String CN_NAME = "nombre";
    public static final String CN_PHONE = "telefono";
    // create table contactos(
    //                          _id integer primary key autoincrement,
    //                          nombre text not null,
    //                          telefono text);
    public static final String CREATE_TABLE = "create table "+ TABLE_NAME+ " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_PHONE + " text);";

    DBhelper helper;
    SQLiteDatabase db;
    public DataBaseManager(Context context) {
        helper = new DBhelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generarContentValues (String Nombre, String Telefono){
        ContentValues valores = new ContentValues();
        valores.put(CN_NAME,Nombre);
        valores.put(CN_PHONE,Telefono);
        return valores;
    }

    public void insertar(String Nombre, String Telefono){
        db.insert(TABLE_NAME,null,generarContentValues(Nombre,Telefono));
    }

    public Cursor cargarCursorContactos(){
        String [] columnas = new String[]{CN_ID,CN_NAME,CN_PHONE};
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }

    public Cursor buscarContacto(String Nombre){
        String [] columnas = new String[]{CN_ID,CN_NAME,CN_PHONE};
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return db.query(TABLE_NAME,columnas,CN_NAME + "=?",new String[]{Nombre},null,null,null);
    }
}
