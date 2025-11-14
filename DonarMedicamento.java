package com.example.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class DonarMedicamento extends AppCompatActivity {

    private EditText etNombreDonacion, etCantidadDonacion, etFechaCaducidad, etDescripcionDonacion;
    private Button btnEnviarDonacion, btnCancelarDonacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_medicamento);

        // Vincular vistas
        etNombreDonacion = findViewById(R.id.etNombreDonacion);
        etCantidadDonacion = findViewById(R.id.etCantidadDonacion);
        etFechaCaducidad = findViewById(R.id.etFechaCaducidad);
        etDescripcionDonacion = findViewById(R.id.etDescripcionDonacion);
        btnEnviarDonacion = findViewById(R.id.btnEnviarDonacion);
        btnCancelarDonacion = findViewById(R.id.btnCancelarDonacion);

        // Evento del botón Enviar
        btnEnviarDonacion.setOnClickListener(v -> {
            String nombre = etNombreDonacion.getText().toString().trim();
            String cantidad = etCantidadDonacion.getText().toString().trim();
            String fecha = etFechaCaducidad.getText().toString().trim();
            String descripcion = etDescripcionDonacion.getText().toString().trim();

            if (nombre.isEmpty() || cantidad.isEmpty() || fecha.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {

                DB db = new DB(this);
                String resultado = db.guardarDonacion(nombre, cantidad, fecha, descripcion);

                Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();
                // Aquí se puede guardar en base de datos o enviar a Firebase
                Toast.makeText(this, "Donación registrada correctamente ", Toast.LENGTH_LONG).show();

                // Regresa al inicio
                Intent intent = new Intent(this, Inicio.class);
                startActivity(intent);
                finish();
            }
        });
        btnCancelarDonacion.setOnClickListener(v -> {
            Intent intent = new Intent(this, Inicio.class);
            startActivity(intent);
            finish();
        });
    }
}
