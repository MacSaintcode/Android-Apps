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

public class New_user extends AppCompatActivity {

    ImageView back;
    private EditText name;
    private EditText username;
    private EditText password;
    private EditText cpassword;

    private DBHandler DBHandler;
     Button post;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call=new Intent(New_user.this,register_login.class);
                startActivity(call);
            }
        });



        post = findViewById(R.id.post);
        cpassword = findViewById(R.id.cpass);
        password = findViewById(R.id.pass);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);

        String cpass=post.getText().toString();
       String pass=password.getText().toString();
       String names = name.getText().toString();
       String user=username.getText().toString();

       click(cpass,pass,names,user);
    }
    void click(String cpass, String pass,String names, String user){
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass.isEmpty()|| user.isEmpty()||names.isEmpty()||cpass.isEmpty()){
                    Toast.makeText(New_user.this, "All field must be filled", Toast.LENGTH_SHORT).show();

                } else if (!pass.equals(cpass)) {
                    Toast.makeText(New_user.this, "Password Does not match!", Toast.LENGTH_SHORT).show();

                } else {

                    DBHandler.newuser(names,user,pass,0);
                    Toast.makeText(New_user.this, "Registered Sucessfully", Toast.LENGTH_SHORT).show();
                    Intent call=new Intent(New_user.this,MainMenu.class);
                    startActivity(call);
                }
            }
        });

    }

}