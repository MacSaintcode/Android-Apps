package com.example.rate;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;

import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class MainActivity extends Activity implements RatingBar.OnRatingBarChangeListener {

    EditText moviename;
    EditText reviews;
    EditText year;
    EditText duration;
    RatingBar ratings;
    EditText starring;
    EditText dirctor;
    Button save;
    private Spinner mGenres;
    private ArrayAdapter<String> mAdapter;
    /*Array containing the genres to which a movie can belong*/
    private String[] mGenresData =
            {
                    "-Select Genre-",
                    "Action","Adventure","Animation","Biography",
                    "Comedy","Crime","Documentary","Drama",
                    "Family","Fantasy","Film-Noir","Game-Show",
                    "History","Horror","Music","Musical",
                    "Mystery","News","Reality-TV","Romance",
                    "Sci-Fi","Sport","Talk-Show","Thriller",
                    "War","Western"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviename=(EditText)findViewById(R.id.name);
        reviews=(EditText)findViewById(R.id.Review);
        year=(EditText)findViewById(R.id.year);
        duration=(EditText)findViewById(R.id.duration);
        starring=(EditText)findViewById(R.id.stars);
        dirctor=(EditText) findViewById(R.id.direct);
        ratings=(RatingBar) findViewById(R.id.rating);
        save=(Button) findViewById(R.id.save);

        ratings.setOnRatingBarChangeListener(this);
        save.setOnClickListener(new OnButtonClick());
        mGenres = (Spinner)findViewById(R.id.spinner1);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,mGenresData);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenres.setAdapter(mAdapter);
        movieDirector.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent mAutoCompleteIntent = new
                        Intent(MovieRatingActivity.this,DirectorName.class);
                startActivityForResult(mAutoCompleteIntent, 0123456);
            }
        });
    }

    public class OnButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String nam=moviename.getText().toString();
            String revew=reviews.getText().toString();
            String yea=year.getText().toString();
            String durat=duration.getText().toString();
            String starin=starring.getText().toString();
            String directo=dirctor.getText().toString();


            if(nam.equalsIgnoreCase("")||revew.equalsIgnoreCase("")||
                   yea.equalsIgnoreCase("")||durat.equalsIgnoreCase("")||
                    starin.equalsIgnoreCase("")||directo.equalsIgnoreCase("")){
                Toast.makeText(MainActivity.this, "Must Fill Every Field!", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(MainActivity.this, "Sucessfully Rated!", Toast.LENGTH_SHORT).show();
                moviename.setText("");
                reviews.setText("");
                year.setText("");
                duration.setText("");
                starring.setText("");
                dirctor.setText("");
                ratings.setRating(0);
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if ((requestCode == 0123456 ) && (resultCode ==
                    Activity.RESULT_OK)){
                Bundle myResults = data.getExtras();
                String vresult = myResults.getString("key");
                movieDirector.setText(vresult);
            }
        }
        catch (Exception e) {
            movieDirector.setText("Oops! - " + requestCode + " " +
                    resultCode);
        }
    }
    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        ratings.setRating(v);

    }
}