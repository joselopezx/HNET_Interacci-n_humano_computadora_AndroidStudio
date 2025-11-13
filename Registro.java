package com.example.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText editHospital, editTelefono, editCorreo, editEncargado, editEstado, editMunicipio;
    Button btnEnviarRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editHospital = findViewById(R.id.editHospital);
        editTelefono = findViewById(R.id.editTelefono);
        editCorreo = findViewById(R.id.editCorreo);
        editEncargado = findViewById(R.id.editEncargado);
        editEstado = findViewById(R.id.editEstado);
        editMunicipio = findViewById(R.id.editMunicipio);
        btnEnviarRegistro = findViewById(R.id.btnEnviarRegistro);

        DB db = new DB(this);

        btnEnviarRegistro.setOnClickListener(v -> {
            String hospital = editHospital.getText().toString().trim();
            String telefono = editTelefono.getText().toString().trim();
            String correo = editCorreo.getText().toString().trim();
            String encargado = editEncargado.getText().toString().trim();
            String estado = editEstado.getText().toString().trim();
            String municipio = editMunicipio.getText().toString().trim();

            if (hospital.isEmpty() || telefono.isEmpty() || correo.isEmpty() ||
                    encargado.isEmpty() || estado.isEmpty() || municipio.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Contraseña por defecto
            String contrasenia = "12345";

            // Guardamos el registro en la base
            String resultado = db.guardar(hospital, telefono, correo, encargado, estado, municipio, contrasenia);

            Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();

            if (resultado.contains("correctamente")) {
                Toast.makeText(this, "Contraseña asignada: 12345", Toast.LENGTH_LONG).show();
                finish(); // Regresa al login
            }
        });
    }
}
