package com.example.triv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button play;
    Button leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call=new Intent(MainMenu.this,register_login.class);
            }
        });
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call=new Intent(MainMenu.this,Leaderboard.class);
            }
        });
    }


}