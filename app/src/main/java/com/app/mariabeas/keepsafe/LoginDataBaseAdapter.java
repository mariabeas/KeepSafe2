package com.app.mariabeas.keepsafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Maria on 04/02/2016.
 */
public class LoginDataBaseAdapter {
    //DECLARAR LAS VARIABLES
    static final String DATABASE_NAME="login.db";
    static final int DATABASE_VERSION=1;
    public static final int NAME_COLUMN=1;

    //CREAR LA DB
    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text); ";

    //DECLARAR LA VARIABLE PARA INSTANCIAR LA DB
    public static SQLiteDatabase db;
    //DECLARAR LA VARIABLE PARA ABRIR Y ACTUALIZAR EL HELPER
    private DBHelper dbHelper;

    public LoginDataBaseAdapter(Context context) {
        dbHelper = new DBHelper(context, DATABASE_NAME,DATABASE_VERSION);
    }

   //METODO PARA ABRIR LA DB Y ESCRIBIR EN ELLA
    public LoginDataBaseAdapter open(){
        db=dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        db.close();
    }
    public SQLiteDatabase getDatabaseInstance(){
        return db;
    }
    public String getSingleEntry(String user) {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{user}, null, null, null);
        if(cursor.getCount()<1){ //EL USUARIO NO EXISTE
            cursor.close();
            return "El usuario no existe";
        }
        cursor.moveToFirst();
        String password=cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    //METODO PARA GUARDAR LOS VALORES QUE INSERTAMOS EN LA DB
    public static void insertEntry(String usuario, String pass) {
        ContentValues values=new ContentValues();
        // ASIGNAR VALORES PARA CADA FILA
        values.put("USERNAME", usuario);
        values.put("PASSWORD", pass);

        //INSERTAR LA FILA EN LA TABLE
        db.insert("LOGIN",null,values);
    }

    //METODO  PARA BORRAR ENTRADAS DE LA DB
    public int deleteEntry(String usuario){
        String where="USERNAME=?";
        int numEntryDelete= db.delete("LOGIN", where, new String[]{usuario}) ;
        return numEntryDelete;
    }

    //METODO PARA ACTUALIZAR LAS ENTRADAS
    public void  updateEntry(String usuario,String contra)
    {
        // Definiar las filas actualizadas
        ContentValues updatedValues = new ContentValues();
        // Asignar valores para cada fila
        updatedValues.put("USERNAME", usuario);
        updatedValues.put("PASSWORD",contra);

        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{usuario});
    }
}
