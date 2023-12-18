package com.example.triv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "Users";


    // below int is our database version
    private static final int DB_VERSION = 1;

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE if not exists details  ( id INTEGER PRIMARY KEY AUTOINCREMENT, names TEXT,usernames TEXT UNIQUE,password TEXT,Score INTEGER default(0))";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public boolean newuser(String Name, String usernames, String passcode, int Score) {
        //check if user name exist already
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM details WHERE usernames = ?", new String[] {usernames});
        if (c.getCount()!=0){
            return true;
        }
        c.close();

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("names", Name);
        values.put("usernames",usernames);
        values.put("password",passcode);
        values.put("Score",Score);

        // after adding all values we are passing
        // content values to our table.
        db.insert("details", null, values);
        // at last we are closing our
        // database after adding database.
        db.close();
        return false;
    }
    public boolean check(String usernames, String passcode){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM details WHERE usernames = ?", new String[] {usernames});
        if (c.moveToFirst()){
//            int r=c.getColumnIndex("password");
            String pass = c.getString(3);
            if (pass.equals(passcode)){
                return true;
            }}
        return  false;
    }
    public boolean forgotpassword(String usernames, String passcode) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM details WHERE usernames = ?", new String[] {usernames});
        if (c.moveToFirst()){
            if (check(usernames,passcode)){
                return false;
            }
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("password",passcode);
            db.update("details",values,"usernames = ?",new String[] {usernames});
            return true;
        }

        c.close();
        db.close();
        return false;
    }
    public void getleaders(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT names and Score FROM details order by Score ASC ", new String[] {});
        while (c.moveToNext()){
            if (c.moveToFirst()){
                do {
                    // Passing values
                    String user = c.getString(2);
                    String score = c.getString(4);
                } while(c.moveToNext());
            }
        c.close();
        db.close();
        }
    }
    public boolean confirm(String usernames, String passcode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM details WHERE usernames = ? and password = ?", new String[] {usernames,passcode});
        if (c.moveToNext()){
            return true;
        }
        c.close();
        db.close();
        return false;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
//        db.execSQL("DROP TABLE IF EXISTS details");
//        onCreate(db);
    }
}