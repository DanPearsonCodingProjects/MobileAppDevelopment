package com.example.theatreapp2;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbConnector extends SQLiteOpenHelper {

    //This deals with the login in and registering of the app
    private static final String db_name = "TheatreLoginDetails1";

    private static final String db_name1 = "TheatreLoginDetails";

    private static final int db_version = 1;

    private static final String table_name = "login_details2";

    private static final String customers_id = "id";

    private static final String name = "name";

    private static final String username = "username";

    private static final String password = "password";


    int number;

    public dbConnector(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + table_name +  "("
                + customers_id + " INTEGER PRIMARY KEY, "+
                name + " STRING, "
                + username + " STRING, " +
                password + " STRING "+")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public void del(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(table_name,null,null);

    }




    public void addDetails(String login_name, String login_username,String password_name){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(name,login_name);
        values.put(username,login_username);
        values.put(password,password_name);

        db.insert(table_name,null,values);

    }

    public ArrayList<detailsList> check_List(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table_name,null);

        ArrayList<detailsList> arrayList = new ArrayList<>();

        //Because of this it updates all the rows because its not writing to anything
        //This reads all the rows and then puts them into an array list
        while(cursor.moveToNext()){
            detailsList modelContact= new detailsList();

            //Sets the login1 in the modelContact variable to = the value
            //of the row as the cursor is going through
            modelContact.login1 = cursor.getString(0);
            modelContact.password1 = cursor.getString(1);

            arrayList.add(modelContact);

        }
        for(detailsList d:arrayList){
            System.out.println(d.getName1());
        }
        return arrayList;
    }


    public boolean check_login(String username1,String password1){
        boolean result = false;

        //Creates a query

        String query = " SELECT * FROM " + table_name +
                " WHERE " + username + " = \""+ username1 + "\"";


        //Connects to this database
        SQLiteDatabase db = this.getReadableDatabase();

        //Applies the query
        Cursor cursor = db.rawQuery(query,null);

        String i;

        if(cursor.moveToFirst()){
            if(cursor.getString(3).equals(password1)){
                result = true;
                Log.i(TAG, "check_login: "+"found");
                i =cursor.getString(0);
                number = cursor.getInt(0);
                Log.i(TAG, "check_login: "+i);

            }
        }else{
            result = false;
            Log.i(TAG, "check_login: "+"NOT FOUND");
        }

        cursor.close();
        //db.close();
        return result;

    }

    public int id_Num(String username1, String password1){
        boolean result = false;

        //Creates a query

        String query = " SELECT * FROM " + table_name +
                " WHERE " + username + " = \""+ username1 + "\"";


        //Connects to this database
        SQLiteDatabase db = this.getReadableDatabase();

        //Applies the query
        Cursor cursor = db.rawQuery(query,null);

        String i;

        if(cursor.moveToFirst()){
            if(cursor.getString(3).equals(password1)){
                result = true;
                Log.i(TAG, "check_login: "+"found");
                i =cursor.getString(0);
                number = cursor.getInt(0);
                Log.i(TAG, "check_login: "+i);
                return number;

            }
        }else{
            result = false;
            Log.i(TAG, "check_login: "+"NOT FOUND");
        }

        return number;

    }
}
