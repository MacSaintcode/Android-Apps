package com.example.dbview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv =findViewById(R.id.tv);

        dbconector dbhelp=new dbconector(this);
//        Insert into Database

        SQLiteDatabase db =dbhelp.getWritableDatabase();

        ContentValues value=new ContentValues();
        value.put("Id","1");
        value.put("Name","Eniola DB");
        value.put("Mark","10");

        Long row=db.insert("Student",null,value);
        System.out.println("Row Number is: "+row);

//        Retrive from Database
        db=dbhelp.getReadableDatabase();

        String Projection[]={"Id","Name","Mark"};
        Cursor c=db.query("Student",Projection,null,null,null,null,null);
        c.moveToPosition(0);
        tv.setText(c.getString(1));



    }
}