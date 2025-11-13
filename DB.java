package com.example.proyecto_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    public DB(Context context){
       super(context, "dataBase", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table clientes(nombreHospital text, telefono text, correo text, encargado text, estado text, municipio text, contrasenia text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

    public String guardar (String nombreHospital, String telefono, String correo, String encargado, String estado, String municipio, String contrasenia){
        String mensaje="";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombreHospital", nombreHospital);
        contenedor.put("telefono", telefono);
        contenedor.put("correo", correo);
        contenedor.put("encargado", encargado);
        contenedor.put("estado", estado);
        contenedor.put("municipio", municipio);
        contenedor.put("contrasenia", contrasenia);

        try{
            database.insertOrThrow("clientes", null, contenedor);
            mensaje="Ingresado correctamente";
        }catch (SQLException e){
            mensaje="no ingresado";
        }
        database.close();
        return mensaje;
    }

}
