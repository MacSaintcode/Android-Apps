package com.example.myservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button startbtn,stopbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbtn=findViewById(R.id.start);
        stopbtn=findViewById(R.id.stop);

        startbtn.setOnClickListener(this);
        stopbtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view==startbtn){
            startService(new Intent(this,Myservice.class));


        }else if (view==stopbtn){
            stopService(new Intent(this,Myservice.class));


        }
    }
}