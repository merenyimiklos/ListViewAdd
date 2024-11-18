package com.example.listviewadd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout emailTextInputLayout;
    private TextInputLayout passwordTextInputLayout;
    private Button registerButton;
    private Button listOfUsersButton;
    //azért kell, hogy static legyen, mert az activity-k közötti adatátvitelhez kell
    private static ArrayList<Users> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidRegister()) {
                    String email = emailTextInputLayout.getEditText().getText().toString();
                    String password = passwordTextInputLayout.getEditText().getText().toString();
                    Users user = new Users(email, password);
                    users.add(user);
                    Toast.makeText(MainActivity.this, "Sikeres regisztráció", Toast.LENGTH_SHORT).show();
                }
            }
        });
        listOfUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!users.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Nincs felhasználó", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isValidRegister() {
        if (emailTextInputLayout.getEditText().getText().toString().isEmpty()) {
            emailTextInputLayout.setError("Email nem lehet üres");
            return false;
        } else {
            emailTextInputLayout.setError(null);
            emailTextInputLayout.setErrorEnabled(false);
        }
        if (passwordTextInputLayout.getEditText().getText().toString().isEmpty()) {
            passwordTextInputLayout.setError("Jelszó nem lehet üres");
            return false;
        } else {
            passwordTextInputLayout.setError(null);
            passwordTextInputLayout.setErrorEnabled(false);
        }
        return true;
    }


    public void init() {
        emailTextInputLayout = findViewById(R.id.emailTextInputLayout);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);
        registerButton = findViewById(R.id.registerButton);
        listOfUsersButton = findViewById(R.id.listOfUsersButton);
        users = new ArrayList<>();
    }


    public static ArrayList<Users> getUsers() {
        return users;
    }
}