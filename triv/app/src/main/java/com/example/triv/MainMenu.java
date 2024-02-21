package com.example.triv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    private Button play;
    private Button leaderboard;
    boolean Exit=false;
    private DBHandler DBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        play=findViewById(R.id.play);
        leaderboard=findViewById(R.id.leader);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call=new Intent(MainMenu.this,register_login.class);
                startActivity(call);
            }
        });
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call=new Intent(MainMenu.this,Leaderboard.class);
                startActivity(call);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("got here");
        onDestroy();

    }
}