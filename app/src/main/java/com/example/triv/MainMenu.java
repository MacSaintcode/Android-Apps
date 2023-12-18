package com.example.triv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
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

        play=findViewById(R.id.play);
        leaderboard=findViewById(R.id.leader);

//        backgroundmusic();
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
//    void backgroundmusic(){
//        MediaPlayer mp=new MediaPlayer();
//        try {
//            mp.setDataSource("C:/Users/Saintcoded/AndroidStudioProjects/triv/app/src/main/res/drawable/snop.mp3");
//            mp.prepare();
//            mp.start();
//            mp.setLooping(true);
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }
//    System.exit();
}