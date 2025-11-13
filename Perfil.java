package com.example.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Perfil extends AppCompatActivity {

    private ImageView imgPerfil;
    private TextView txtNombreHospital, txtCorreo;
    private Button btnEditarPerfil, btnCambiarPassword, btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Vincular vistas
        imgPerfil = findViewById(R.id.imgPerfil);
        txtNombreHospital = findViewById(R.id.txtNombreHospital);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnEditarPerfil = findViewById(R.id.btnEditarPerfil);
        btnCambiarPassword = findViewById(R.id.btnCambiarPassword);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        // Obtener el correo del usuario desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("sesion", MODE_PRIVATE);
        String correoUsuario = prefs.getString("correo", null);

        if (correoUsuario != null) {
            mostrarDatosCliente(correoUsuario);
        } else {
            Toast.makeText(this, "No se encontró la sesión del usuario", Toast.LENGTH_SHORT).show();
        }

        // Botón editar perfil
        btnEditarPerfil.setOnClickListener(v -> {
            Toast.makeText(this, "Función Editar perfil (en desarrollo)", Toast.LENGTH_SHORT).show();
        });

        // Botón cambiar contraseña
        btnCambiarPassword.setOnClickListener(v -> {
            Toast.makeText(this, "Función Cambiar contraseña (en desarrollo)", Toast.LENGTH_SHORT).show();
        });

        // Botón cerrar sesión
        btnCerrarSesion.setOnClickListener(v -> {
            // Eliminar datos de sesión
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.apply();

            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Inicio.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
    private void mostrarDatosCliente(String correo) {
        DB db = new DB(this);
        Cursor cursor = db.obtenerClientePorCorreo(correo);

        if (cursor != null && cursor.moveToFirst()) {
            // Obtener los datos del cliente
            String nombreHospital = cursor.getString(cursor.getColumnIndexOrThrow("nombreHospital"));
            String correoCliente = cursor.getString(cursor.getColumnIndexOrThrow("correo"));

            // Mostrar en la interfaz
            txtNombreHospital.setText(nombreHospital);
            txtCorreo.setText(correoCliente);

            cursor.close();
        } else {
            Toast.makeText(this, "No se encontraron datos del cliente", Toast.LENGTH_SHORT).show();
        }
    }
}
