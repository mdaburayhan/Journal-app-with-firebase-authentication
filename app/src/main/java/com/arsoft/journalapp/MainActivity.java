package com.arsoft.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Widgets
    Button loginBtn, createAccountBtn;
    private EditText emailEt, passEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAccountBtn = findViewById(R.id.create_account);

        createAccountBtn.setOnClickListener(v ->  {
            // OnClick()
            Intent i = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(i);

        });

    }
}