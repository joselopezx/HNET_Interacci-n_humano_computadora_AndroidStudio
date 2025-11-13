package com.example.proyecto_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    public DB(Context context) {
        super(context, "dataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table clientes(nombreHospital text, telefono text, correo text, encargado text, estado text, municipio text, contrasenia text)");
        db.execSQL("create table solicitudes(id INTEGER PRIMARY KEY AUTOINCREMENT, nombreMedicamento text, cantidad text, descripcion text, fechaSolicitud TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public String guardar(String nombreHospital, String telefono, String correo, String encargado, String estado, String municipio, String contrasenia) {
        String mensaje = "";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombreHospital", nombreHospital);
        contenedor.put("telefono", telefono);
        contenedor.put("correo", correo);
        contenedor.put("encargado", encargado);
        contenedor.put("estado", estado);
        contenedor.put("municipio", municipio);
        contenedor.put("contrasenia", contrasenia);

        try {
            database.insertOrThrow("clientes", null, contenedor);
            mensaje = "Ingresado correctamente";
        } catch (SQLException e) {
            mensaje = "no ingresado";
        }
        database.close();
        return mensaje;
    }

    public boolean verificarLogin(String correo, String contrasenia) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM clientes WHERE correo = ? AND contrasenia = ?";
        String[] args = {correo, contrasenia};
        boolean existe = false;

        try (android.database.Cursor cursor = db.rawQuery(query, args)) {
            existe = cursor.moveToFirst(); // Si hay al menos un resultado, el usuario existe
        }

        db.close();
        return existe;
    }

    public String guardarSolicitud(String nombreMedicamento, String cantidad, String descripcion) {
        String mensaje;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombreMedicamento", nombreMedicamento);
        contenedor.put("cantidad", cantidad);
        contenedor.put("descripcion", descripcion);

        try {
            database.insertOrThrow("solicitudes", null, contenedor);
            mensaje = "Solicitud guardada correctamente";
        } catch (SQLException e) {
            mensaje = "Error al guardar solicitud: " + e.getMessage();
        }
        database.close();
        return mensaje;
    }

    public Cursor obtenerClientePorCorreo(String correo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM clientes WHERE correo = ?";
        return db.rawQuery(query, new String[]{correo});
    }


}
