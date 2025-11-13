package com.example.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class CrearSolicitud extends AppCompatActivity {

    private EditText etNombreMedicamento, etCantidad, etDescripcion;
    private Button btnEnviarSolicitud, btnCancelarSolicitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_solucitud);

        // Vincular vistas
        etNombreMedicamento = findViewById(R.id.etNombreMedicamento);
        etCantidad = findViewById(R.id.etCantidad);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnEnviarSolicitud = findViewById(R.id.btnEnviarSolicitud);
        btnCancelarSolicitud = findViewById(R.id.btnCancelarSolicitud);

        // Evento del botón Enviar
        btnEnviarSolicitud.setOnClickListener(v -> {
            String nombre = etNombreMedicamento.getText().toString().trim();
            String cantidad = etCantidad.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();

            if (nombre.isEmpty() || cantidad.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                DB db = new DB(this);
                String resultado = db.guardarSolicitud(nombre, cantidad, descripcion);
                // En esta parte puedes guardar los datos en una base de datos o enviar a Firebase
                Toast.makeText(this, "Solicitud enviada correctamente", Toast.LENGTH_LONG).show();

                // Después de enviar, podrías regresar al inicio
                Intent intent = new Intent(this, Inicio.class);
                startActivity(intent);
                finish();
            }
        });

        // Evento del botón Cancelar
        btnCancelarSolicitud.setOnClickListener(v -> {
            // Regresa al inicio sin guardar
            Intent intent = new Intent(this, Inicio.class);
            startActivity(intent);
            finish();
        });
    }
}
