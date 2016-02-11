package com.app.mariabeas.keepsafe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Maria on 04/02/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    //LLAMAR CUANDO LA BD NO EXISTA EN DISCO PARA CREARLA
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);
        db.execSQL(LoginDataBaseAdapter.AGENDA_CREATE);
        db.execSQL(LoginDataBaseAdapter.MENSAJES_CREATE);
        db.execSQL(LoginDataBaseAdapter.UBICACION_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //ESCRIBIR LA VERSIÓN ACTUALIZADA
        Log.w("TaskDBAdapter", "Actualizando la versión " +oldVersion + " a " +newVersion + ", se destruirán los datos anteriores.");
        //ACTUALIZAR
        db.execSQL("Eliminar las tablas si existen"+"TEMPLATE");
        //CREAR UNA NUEVA
        onCreate(db);
    }
}
