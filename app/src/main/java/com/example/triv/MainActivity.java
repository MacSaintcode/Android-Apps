package com.example.triv;

import androidx.appcompat.app.AppCompatActivity;
import  java.lang.Object;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView counttext;

    TextView score;
    TextView head;
    TextView question;
    Button Answer1;
    Button Answer2;
    Button Answer3;
    Button Answer4;
    ProgressBar timescrol;
    private DBHandler dbHandler;
    private CountDownTimer time;
    private long Milliseconds=15000;
    boolean running=true;
//    String name= new register_login().users.getText().toString();
    String questarr[]={"What is the name of the worlds largest ocean",
        "What is the approximate ratio of people to sheep in New Zealand?",
        "What is the only continent that does not have any active volcanoes?",
            "What is the most common letter in the English alphabet?",
        "What is the smallest country in the world by land area?",
        "In which country is it illegal to own only one guinea pig,as a lone guinea pig might get lonely?",
        "What is the name of the worlds largest desert",
        "Who is the author of the Harry Potter series",
//        "What is the capital of Australia"
    };
    String answer[][]={{"Pacific Ocean","Atlantic Ocean","River Jordan","Meridian Ocean"},
            {"7 people per 1 sheep","3 people per 1 sheep", "1 people per 1 sheep","1 people per 3 sheep",},
            {"Australia","Europe","Africa","Asia"},{"A","I","O","E"},
            {"Monaco","Vatican City","San Marino","Nauru"},{"New Jersey","Switzerland","Minniesota","Lagos"},
            {"Antarctic","Zahara","Cairo","Swiele"},
            {"Jason Stark" ,"Christian Storm","J.K.Rowling","Britney Spares"}};

    String rans[]={"Pacific Ocean","1 people per 1 sheep","Australia","E","Vatican City","Switzerland","Antarctic"};
    int n=1;
    int ci=0;
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
        head=findViewById(R.id.Questionshead);
        question=findViewById(R.id.Questions);
        score=findViewById(R.id.score);



        Answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.cancel();
                if (Answer1.getText().toString().equalsIgnoreCase(rans[ci])){
                    addpoint();
                    Answer1.setBackgroundColor(Color.GREEN);
                }else{
                    Answer1.setBackgroundColor(Color.RED);
                }
                check(Answer1);
            }
        });
        Answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.cancel();
                if (Answer2.getText().toString().equalsIgnoreCase(rans[ci])){
                    addpoint();
                    Answer2.setBackgroundColor(Color.GREEN);
                }else{
                    Answer2.setBackgroundColor(Color.RED);
                }
                check(Answer2);

            }

        });
        Answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Answer3.getText().toString().equalsIgnoreCase(rans[ci])){
                    addpoint();
                    Answer3.setBackgroundColor(Color.GREEN);
                }else{
                    Answer3.setBackgroundColor(Color.RED);
                }
                check(Answer3);
            }
        });
        Answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.cancel();
                if (Answer4.getText().toString().equalsIgnoreCase(rans[ci])){
                    addpoint();
                    Answer4.setBackgroundColor(Color.GREEN);
                }else{
                    Answer4.setBackgroundColor(Color.RED);
                }
                check(Answer4);


            }
        });
            starttimer();
            QandA();

    }
    void check(Button btn){
        if(!movement(btn)){
            Intent ca=new Intent(MainActivity.this,congrats.class);
            startActivity(ca);
        }

    }
    void addpoint(){
        int sco=Integer.parseInt(score.getText().toString());
        sco+=5;
        String got=String.valueOf(sco);
        score.setText(got);
    }

    boolean movement(Button myButton){
        Button arr[]={Answer1,Answer2,Answer3,Answer4};
        for (int i = 0; i < 4; i++) {
            arr[i].setClickable(false);
        }
        time.cancel();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    arr[i].setClickable(true);
                }
                // Revert button color to default after the delay
                myButton.setBackgroundColor(Color.rgb(106, 90, 205));
                starttimer();
                QandA();
            }
        }, 2000);
        if(QandA()){
            ci++;
            return true;
        }else{
            Intent ca=new Intent(MainActivity.this,congrats.class);
            startActivity(ca);
            return false;
        }
    }

    boolean QandA(){
        if(n!=questarr.length){
            head.setText("Question "+n);
            question.setText(questarr[n-1]);
            Button arr[]={Answer1,Answer2,Answer3,Answer4};
            for (int i = 0; i < 4; i++) {
                arr[i].setText(answer[n-1][i]);
            }
            n++;
            return true;

        }else{

            return false;
//          congratulations on finising the trivial questions
//           trigger intent to go back to main menu
        }
    }
    void starttimer(){
        timescrol.setProgress(15);
        counttext.setText("15");
        Milliseconds=15000;
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
    Button correction(){
        if (Answer4.getText().toString().equalsIgnoreCase(rans[ci])){
            Answer4.setBackgroundColor(Color.GREEN);
            return Answer4;
        }else if(Answer3.getText().toString().equalsIgnoreCase(rans[ci])){
            Answer3.setBackgroundColor(Color.GREEN);
            return Answer3;
        }else if(Answer2.getText().toString().equalsIgnoreCase(rans[ci])){
            Answer2.setBackgroundColor(Color.GREEN);
            return Answer2;
        }else{
            Answer1.setBackgroundColor(Color.GREEN);
            return Answer1;
    }
    }

    public void updatetimer(){
        int second=(int) Milliseconds % 15000 / 1000;
        String tim ="";
        if(second<10){
            tim+="0";
            tim+=second;
        }else {
            tim+=second;
        }
        counttext.setText(tim);
        timescrol.setProgress(second);

        if(second==0){
            check(correction());
        }
    }

}