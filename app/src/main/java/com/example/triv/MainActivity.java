package com.example.triv;

import androidx.appcompat.app.AppCompatActivity;
import  java.lang.Object;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView counttext;
    Button Answer1;
    Button Answer2;
    Button Answer3;
    Button Answer4;
    ProgressBar timescrol;
    private CountDownTimer time;
    private long Milliseconds=20000;
    boolean running=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counttext=findViewById(R.id.timer);
        Answer1=findViewById(R.id.answer1);
        Answer2=findViewById(R.id.answer2);
        Answer3=findViewById(R.id.answer3);
        Answer4=findViewById(R.id.answer4);
        timescrol=findViewById(R.id.timescroll);




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