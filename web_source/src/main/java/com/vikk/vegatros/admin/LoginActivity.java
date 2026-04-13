package com.vikk.vegatros;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        EditText etEmail = findViewById(R.id.et_email);
        EditText etPass = findViewById(R.id.et_password);
        Button btn = findViewById(R.id.btn_login);
        btn.setOnClickListener(v -> {
            String e = etEmail.getText().toString().trim();
            String p = etPass.getText().toString().trim();
            if(e.isEmpty() || p.isEmpty()) return;
            mAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "ACCESS DENIED", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
