package com.example.triv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Leaderboard extends AppCompatActivity {

    ImageView back;
    TextView user;
    TextView scores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        back=findViewById(R.id.back);
        scores=findViewById(R.id.sco);
        user=findViewById(R.id.nam);
//        13 top players
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call=new Intent(Leaderboard.this,MainMenu.class);
                startActivity(call);
            }
        });
    }
}