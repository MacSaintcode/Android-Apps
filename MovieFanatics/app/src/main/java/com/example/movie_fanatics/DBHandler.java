package com.example.movie_fanatics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(@Nullable Context context) {
        super(context, "Movie_Handler", null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Movie_Table="CREATE TABLE if not exists Movie_Detailes (Id INTEGER PRIMARY KEY AUTOINCREMENT, Movie_Names TEXT,Movie_image BLOB,Genre TEXT,Ratings DOUBLE,Description TEXT)";
        String Movie_Reviews="CREATE TABLE if not exists Movie_Reviews (Id INTEGER REFERENCES Movie_Details(Id),Rating DOUBLE,Review TEXT)";
        db.execSQL(Movie_Table);
        db.execSQL(Movie_Reviews);
    }
    void addmovies(String moviename, String image, double rating, String description,String genre){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put("Movie_Names",moviename);
         try{
             FileInputStream file=new FileInputStream(image);
             byte[]img=new byte[file.available()];
             file.read(img);
             values.put("Movie_image",img);
         }catch (Exception e){
             e.printStackTrace();
        }
        values.put("Genre",genre);
        values.put("Ratings",rating);
        values.put("Description",description);
        db.insert("Movie_Detailes",null,values);

    }
    Cursor getallmovie(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c= db.rawQuery("Select * from Movie_Detailes",new String[]{});
        return  c;
    }

     Cursor getmoviegenre(String genre){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c= db.rawQuery("Select * from Movie_Detailes where Genre =?",new String[]{genre});
        return  c;
    }
    Cursor getreviews(int id,String review){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("Select * from Movie_Reviews",new String[]{});
        return c;
    }
    void setreviews(Float rating,String review){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("Ratings",rating);
        values.put("Review",review);

        db.insert("Movie_Reviews",null,values);
    }
    Cursor moviesearch(String name,String genre){
        SQLiteDatabase db;
        Cursor c;
    db=this.getReadableDatabase();
    if (genre.equals("Genre")){
        c=db.rawQuery("Select * from Movie_Detailes where Movie_Names like ? ",new String[]{"%"+name+"%"});
    }else{
        c=db.rawQuery("Select * from Movie_Detailes where Movie_Names like ? and Genre = ? ",new String[]{"%"+name+"%",genre});
    }
        return c;
    }
    Cursor getmovie(String name,int num){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("Select * from Movie_Detailes where Movie_Names=? and Id=? ",new String[]{name, String.valueOf(num)});
        return c;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String upgrade="DROP TABLE Movie_Detailes";
        db.execSQL(upgrade);
        onCreate(db);

    }
}
