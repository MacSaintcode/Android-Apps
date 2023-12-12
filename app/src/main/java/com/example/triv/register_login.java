package com.example.triv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class register_login extends AppCompatActivity {

    ImageView back;
    TextView forgot;
    TextView account;

    Button login;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);

        back=findViewById(R.id.back);
        forgot=  findViewById(R.id.fpassword);
        account= findViewById(R.id.caccount);
        login=findViewById(R.id.login);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call=new Intent(register_login.this,MainMenu.class);
                startActivity(call);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call=new Intent(register_login.this,ForgotPassword.class);
                startActivity(call);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call=new Intent(register_login.this,New_user.class);
                startActivity(call);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if user exist in db first

                Intent call=new Intent(register_login.this,MainActivity.class);
                startActivity(call);
            }
        });

    }



}