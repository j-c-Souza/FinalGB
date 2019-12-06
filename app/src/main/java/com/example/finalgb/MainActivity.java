package com.example.finalgb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    public static Firebase fire = new Firebase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button login;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        login = findViewById(R.id.btnLogin);
        email = (EditText) findViewById(R.id.ETemail);
        password = (EditText) findViewById(R.id.ETpassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fire.logarFireBase(email.getText().toString(), password.getText().toString());
                if(fire.isLogged()){
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}
