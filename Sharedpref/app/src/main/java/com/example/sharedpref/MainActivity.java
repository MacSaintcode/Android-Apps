package com.example.sharedpref;

import static android.content.Context.MODE_PRIVATE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ed1 = findViewById(R.id.ed1);
        EditText ed2 = findViewById(R.id.ed2);
        TextView tv = findViewById(R.id.tv);
        Button save = findViewById(R.id.btn1);
        Button retrieve = findViewById(R.id.btn2);
        Button refresh = findViewById(R.id.btn0);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("stdinfo",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userName",ed1.getText().toString());
                editor.putString("email",ed2.getText().toString());
                editor.commit();
                ed1.setText("");
                ed2.setText("");
                tv.setText("Values stored successfully");
                Toast.makeText(MainActivity.this,"Success", Toast.LENGTH_SHORT).show();
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("stdinfo",MODE_PRIVATE);
                String userName = sp.getString("userName","");
                String email = sp.getString("email","");
                ed1.setText(userName);
                ed2.setText(email);

                tv.setText("Username: " + userName + "\n" + "Email: " + email);
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed1.setHint("Username");
                ed2.setHint("Email");

                tv.setText("Status");
            }
        });
    }
}