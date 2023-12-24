package com.example.triv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class congrats extends AppCompatActivity {
    private Button go;
    private ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);
        go=findViewById(R.id.continu);
        view =findViewById(R.id.vid);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cal=new Intent(congrats.this,MainMenu.class);
                startActivity(cal);
            }
        });

    }
}