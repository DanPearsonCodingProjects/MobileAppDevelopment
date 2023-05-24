package com.example.theatreapp2;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class SeatingConnector extends SQLiteOpenHelper {

    //This stores the data for the plays which can then be selected by other db connectors


    private static final String db_name = "TheatreSeating1";

    private static final int db_version = 1;

    private static final String table_name = "seating_details1";

    private static final String play_name = "playName";

    private static final String seatPrice = "seatPrice";

    private static final String seatingType = "seatingType";

    private static final String occupied = "occupied";

    private static final String wheelchairAccess = "wheelchairAccess";

    private static final String seatNum2 = "SeatNum";

    private static final String COLUMN_id = "id";

    public SeatingConnector(Context context){super(context, db_name, null, db_version);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + table_name +  "(" +
                COLUMN_id + " INTEGER PRIMARY KEY,"+
                play_name + " TEXT, " +
                seatingType + " TEXT, " +
                seatPrice + " INTEGER, " +
                wheelchairAccess + " TEXT, " +
                seatNum2 + " INTEGER "+")");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }


    public void addDetails(String name,String type, int price,String wheelchair, int seatNum1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(play_name,name);
        values.put(seatingType,type);
        values.put(seatPrice,price);
        values.put(wheelchairAccess,wheelchair);
        values.put(seatNum2,seatNum1);


        db.insert(table_name,null,values);



    }

    public void del(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(table_name,null,null);

    }

    public ArrayList<SeatingDetails> ListSeats(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table_name,null);
        ArrayList<SeatingDetails> arrayList1 = new ArrayList<>();

        //Because of this it updates all the rows because its not writing to anything
        //This reads all the rows and then puts them into an array list
        while(cursor.moveToNext()){
            SeatingDetails seatDets= new SeatingDetails();

            seatDets.name = cursor.getString(1);
            seatDets.seatType = cursor.getString(2);
            seatDets.seatPrice = cursor.getInt(3);
            seatDets.wheelchairAccess = cursor.getString(4);
            seatDets.seatNumber = cursor.getInt(5);

            arrayList1.add(seatDets);
        }
        return arrayList1;
    }

    public ArrayList<SeatingDetails> cheapest(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table_name + " ORDER BY "+seatPrice+" DESC",null);
        ArrayList<SeatingDetails> arrayList = new ArrayList<>();

        //Because of this it updates all the rows because its not writing to anything
        //This reads all the rows and then puts them into an array list
        while(cursor.moveToNext()){
            SeatingDetails modelContact= new SeatingDetails();
            modelContact.name = cursor.getString(0);
            modelContact.seatType = cursor.getString(1);
            modelContact.seatPrice = cursor.getInt(2);
            modelContact.wheelchairAccess = cursor.getString(3);
            modelContact.seatNumber = cursor.getInt(4);

            arrayList.add(modelContact);

        }
        cursor.close();
        return arrayList;
    }

    public void updateDetails(SeatingDetails modelContact){
        //Puts new details in the database

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(seatNum2,modelContact.getSeatNumber());
        db.update(table_name,values,play_name + " = '"+ modelContact.getName() + "' AND " + seatingType + " = '" + modelContact.getSeatType() + "'",null);
    }


    public void practiceUpdate(ArrayList<SeatingDetails> seatingDetails,String name,int ne){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        Log.i(TAG, "practiceUpdate: "+seatingDetails.get(0));


        values.put(seatNum2,ne);
        String where = "SeatNum=?";
        String[] whereArgs = {name};
        //db.update(table_name,values,where,whereArgs);

        for(int i= 0; i <  seatingDetails.size(); i++){
            if(seatingDetails.get(i).getName().equals(name)){
                Log.i(TAG, "practiceUpdate: "+"found");
                values.put(seatNum2,seatingDetails.get(i).getSeatNumber());
                db.update(table_name,values,seatNum2,null);
            }else{
                Log.i(TAG, "practiceUpdate: "+"Not found");
            }

        }


    }


    public ArrayList<SeatingDetails> tester(){

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+ table_name + " ORDER BY "+ seatPrice + " ASC";
        Cursor cursor = db.rawQuery(sql,null);

        ArrayList<SeatingDetails> det = new ArrayList<>();

        while(cursor.moveToNext()){
            SeatingDetails seatingDetails = new SeatingDetails();
            int price = cursor.getInt(cursor.getColumnIndexOrThrow("seatPrice"));
            String nam = cursor.getString(cursor.getColumnIndexOrThrow("playName"));
            Log.i(TAG, "tester: "+nam);
            seatingDetails.seatPrice = price;
            seatingDetails.name = nam;
            det.add(seatingDetails);
        }
        cursor.close();
        return det;
    }


    //Gonna need a function that can check for what the name is in the database and in the current one


}
