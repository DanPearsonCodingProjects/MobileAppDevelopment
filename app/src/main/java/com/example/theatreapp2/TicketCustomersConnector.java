package com.example.theatreapp2;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class TicketCustomersConnector extends SQLiteOpenHelper {

    //Stores the customer who has bought the ticket

    private static final String db_name = "CustomersTicketsInfo";

    private static final int db_version = 1;

    private static final String table_name = "CustomerTicketInformation";

    private static final String customer_id = "id";

    private static final String customer_name = "name";
    private static final String seatingType = "seatingType";

    private static final String playName = "playName";

    private static final String ticketCost = "ticketCost";

    private static final String numTickets = "numTickets";

    private static final String additionalRequirements = "additionalRequirements";

    private static final String extraNotes = "extraNotes";

    private static final String UniqueCode = "UniqueCode";

    public TicketCustomersConnector(Context context){super(context,db_name,null,db_version);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + table_name +  "(" +
                customer_id + " INTEGER,"+
                customer_name + " TEXT, " +
                seatingType + " TEXT, " +
                playName + " TEXT, " +
                ticketCost + " INTEGER, " +
                numTickets + " INTEGER, " +
                additionalRequirements + " TEXT, " +
                extraNotes + " TEXT, " +
                UniqueCode + " TEXT " +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }


    public void addDetails(int id,String user_name, String seatType,String theatreName, int ticketsPrice,
                           int ticketNum,String additional, String notes,String code){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(customer_id,id);
        values.put(customer_name,user_name);
        values.put(seatingType,seatType);
        values.put(playName,theatreName);
        values.put(ticketCost,ticketsPrice);
        values.put(numTickets,ticketNum);
        values.put(additionalRequirements,additional);
        values.put(extraNotes,notes);
        values.put(UniqueCode,code);


        db.insert(table_name,null,values);
    }

    public ArrayList<orderInfo> ListSeats(String idNum){



        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table_name,null);
        ArrayList<orderInfo> arrayList1 = new ArrayList<>();

        //Because of this it updates all the rows because its not writing to anything
        //This reads all the rows and then puts them into an array list
        while(cursor.moveToNext()){
            if(cursor.getString(0).equals(idNum)){
                orderInfo seatDets= new orderInfo(cursor.getString(3),cursor.getString(2),
                        cursor.getInt(4),cursor.getInt(5),cursor.getString(8));

                arrayList1.add(seatDets);

            }

            /*
            seatDets.name = cursor.getString(3);
            seatDets.Seat = cursor.getString(2);
            seatDets.priceTicket = cursor.getInt(4);
            seatDets.ticketNum = cursor.getInt(5);
            seatDets.uniqueCode = cursor.getString(8);

             */


        }
        return arrayList1;
    }

    public void deleteRow(String code){
        SQLiteDatabase db = getWritableDatabase();

        String sql = "DELETE FROM "+table_name +" WHERE "+UniqueCode+ " = '" + code +"'";
        db.execSQL(sql);

    }
}
