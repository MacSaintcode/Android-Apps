package com.example.movie_fanatics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Reviews extends AppCompatActivity {

    private ImageView img;

    private RatingBar rate;
    private TextView moviename;
    private ImageView imgview;
    private EditText comment;
    DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        img=findViewById(R.id.movieimg);
        rate=findViewById(R.id.rating);
        moviename=findViewById(R.id.Moviename);
        imgview=findViewById(R.id.send);
        comment=findViewById(R.id.comment);

        db=new DBHandler(this);
        Intent get=getIntent();
        int id=get.getIntExtra("id",5);
        byte[] imgs;
        Bitmap bit;
        System.out.println(id);

        Cursor c=db.getmovie(id);
        while(c.moveToNext()){
            moviename.setText(c.getString(1));
            imgs=c.getBlob(2);
            bit= BitmapFactory.decodeByteArray(imgs,0,imgs.length);
            rate.setRating(c.getFloat(4));
            img.setImageBitmap(bit);
        }
        comment.setHint("Comment on "+moviename.getText().toString());
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String com=comment.getText().toString();
                if(com.length()>0){
                    db.setreviews(id,com);
                    comment.setText("");
                }
                else {
                    Toast.makeText(Reviews.this, "Comment field can't be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}