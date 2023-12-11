package com.example.triv;

import androidx.appcompat.app.AppCompatActivity;
import  java.lang.Object;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView counttext;
    private CountDownTimer time;
    private long Milliseconds=20000;
    boolean running=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counttext=findViewById(R.id.timer);



    }
    void starttimer(){
        time=new CountDownTimer(Milliseconds,1000) {
            @Override
            public void onTick(long l) {
                Milliseconds=l;
                updatetimer();

            }

            @Override
            public void onFinish() {

            }
        }.start();
        running=true;
    }

    public void updatetimer(){
        int second=(int) Milliseconds % 20000 / 1000;
        String tim ="";
        if(second<10){
            tim+="0";
            tim+=second;
        }
        counttext.setText(tim);

    }

}