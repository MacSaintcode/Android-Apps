package com.example.movie_fanatics;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout parentLayout;
    private SearchView search;
    private DBHandler DBHandler;

    private Spinner spin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the parent layout defined in the XML
        parentLayout = findViewById(R.id.parentlayout);
        search=findViewById(R.id.searchView);
        DBHandler=new DBHandler(this);
        spin = findViewById(R.id.genre);

        parentLayout.setHorizontalScrollBarEnabled(true);
        DBHandler.addmovies("Firstmovie",
                5.4,"bolo","Romance");
        generate();

//        spin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                DBHandler.getmoviegenre(parent.getItemAtPosition(position).toString());
//            }
//        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Cursor c=DBHandler.moviesearch(query,spin.getSelectedItem().toString());
                int count=c.getCount();
                if(count==0){
                    nores();
                }else {
                    parentLayout.removeAllViews();
                    Toast.makeText(MainActivity.this, count+" Result", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return false;
            }
        });
    }
    void search(String newText){
        int count=0;
        if (!(newText.isBlank())){
            Cursor c=DBHandler.moviesearch(newText,spin.getSelectedItem().toString());
            count=c.getCount();
            System.out.println(count);
            if(count==0){
                nores();
            }else {
                parentLayout.removeAllViews();
                Toast.makeText(MainActivity.this, count+" Result", Toast.LENGTH_SHORT).show();
            }
            if(newText.equalsIgnoreCase("kk")){
                parentLayout.removeAllViews();
//                createview(1);
            }
        }else
        {
            generate();
        }
    }
    void nores(){
        parentLayout.removeAllViews();
        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.rt2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        img.setLayoutParams(layoutParams);
        parentLayout.addView(img);// Add ImageView to the parent layout
    }
    
    void  generate(){
        parentLayout.removeAllViews();
        int numTextViewsNeeded = 5;

        // Loop to create TextViews
//        for (int i = 1; i <= numTextViewsNeeded; i++) {
//            createview(i);
//        }

        Cursor c=DBHandler.getallmovie();
        String moviename;
        Bitmap bit;
        byte[] img;
        while (c.moveToNext()) {
            moviename=c.getString(1);
            img=c.getBlob(2);
            bit= BitmapFactory.decodeByteArray(img,0,img.length);
            createview(1,moviename,bit);
        }
    }
//    private void createview(int id) {
//        ImageView img = new ImageView(this);
//        TextView textView = new TextView(this);
//
//        img.setBackgroundColor(Color.RED);
//        img.setPadding(450,300,450,300);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                img.setBackgroundColor(Color.BLUE);
////                img.setImageBitmap();
//                textView.setTextColor(Color.BLUE);
//            }
//        });
//
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//        );
//        img.setLayoutParams(layoutParams);
//
////       -----------------------------------------------------------------------------------------------------
//
//        textView.setText("Moviename "+id);
//
//        textView.setId(id);
//        textView.setTextSize(16);
//        textView.setTypeface(null, Typeface.BOLD);
//        textView.setPadding(20,10,20,40);
//
//        layoutParams = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//        );
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setTextColor(Color.BLUE);
//                img.setBackgroundColor(Color.BLUE);
//            }
//        });
//
//        textView.setLayoutParams(layoutParams);
//
//        parentLayout.addView(img);// Add ImageView to the parent layout
//        parentLayout.addView(textView); // Add TextView to the parent layout
//    }
    private void createview(int id, String moviename,Bitmap imgs) {
        ImageView img = new ImageView(this);
        TextView textView = new TextView(this);
        img.setImageBitmap(imgs);
//        img.setPadding(450,300,450,300);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.BLUE);
            }
        });

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        img.setLayoutParams(layoutParams);

//       -----------------------------------------------------------------------------------------------------

        textView.setText(moviename+id);
        textView.setId(id);
        textView.setTextSize(16);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setPadding(20,10,20,40);

        layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.BLUE);
            }
        });

        textView.setLayoutParams(layoutParams);

        parentLayout.addView(img);// Add ImageView to the parent layout
        parentLayout.addView(textView); // Add TextView to the parent layout
    }
}