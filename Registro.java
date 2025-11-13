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

        // Instanciamos la base de datos
        DB db = new DB(this);

        btnEnviarRegistro.setOnClickListener(v -> {
            String hospital = editHospital.getText().toString().trim();
            String telefonoStr = editTelefono.getText().toString().trim();
            String correo = editCorreo.getText().toString().trim();
            String encargado = editEncargado.getText().toString().trim();
            String estado = editEstado.getText().toString().trim();
            String municipio = editMunicipio.getText().toString().trim();

            // Validación de campos vacíos
            if (hospital.isEmpty() || telefonoStr.isEmpty() || correo.isEmpty() ||
                    encargado.isEmpty() || estado.isEmpty() || municipio.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                String telefono = editTelefono.getText().toString().trim();
                String resultado = db.guardar(hospital, telefono, correo, encargado, estado, municipio);

                // Mostramos mensaje de éxito o error
                Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();

                // Si se guardó correctamente, cerramos la pantalla
                if (resultado.contains("correctamente")) {
                    finish(); // Regresa a la pantalla de login
                }

            } catch (NumberFormatException e) {
                Toast.makeText(this, "El teléfono debe ser un número válido", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
