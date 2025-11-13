package com.example.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editCodigo, editPassword;
    Button btnContinuar, btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editCodigo = findViewById(R.id.editCodigo);
        editPassword = findViewById(R.id.editPassword);
        btnContinuar = findViewById(R.id.btnContinuar);
        btnRegistro = findViewById(R.id.btnRegistro);

        // Instancia de la base de datos
        DB db = new DB(this);

        // Botón "Continuar"
        btnContinuar.setOnClickListener(v -> {
            String codigo = editCodigo.getText().toString().trim(); // correo o código
            String password = editPassword.getText().toString().trim();

            if (codigo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validar en la base de datos
            boolean valido = db.verificarLogin(codigo, password);

            if (valido) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                // Redirigir a otra pantalla si lo deseas
                // Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                // startActivity(intent);
                // finish();
            } else {
                Toast.makeText(this, "Código o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón "Registro"
        btnRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, Registro.class);
            startActivity(intent);
        });
    }
}
