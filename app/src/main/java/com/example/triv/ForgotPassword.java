package com.example.triv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    ImageView back;
    EditText pass;
    EditText cpass;
    EditText username;
    Button btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);

        back=findViewById(R.id.back);
        username=findViewById(R.id.users);
        cpass=findViewById(R.id.cpassword);
        pass=findViewById(R.id.password);
        btn=findViewById(R.id.btnchange);

        String passs=pass.getText().toString();
        String Use=username.getText().toString();
        String cpasss=cpass.getText().toString();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call=new Intent(ForgotPassword.this,register_login.class);
                startActivity(call);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passs.isEmpty()|| Use.isEmpty()||cpasss.isEmpty() ){
                    Toast.makeText(ForgotPassword.this, "All fields must be filled", Toast.LENGTH_SHORT).show();

                } else if (!passs.equals(cpasss)) {
                    Toast.makeText(ForgotPassword.this, "Password Does not match!", Toast.LENGTH_SHORT).show();

                } else {

                    Intent call=new Intent(ForgotPassword.this,register_login.class);
                    startActivity(call);
            }}
        });

    }

}