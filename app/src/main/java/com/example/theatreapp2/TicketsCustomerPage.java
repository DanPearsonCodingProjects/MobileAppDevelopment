package com.example.theatreapp2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class TicketsCustomerPage extends AppCompatActivity {

    //This links the recycler view to the adapter I believe
    ArrayList<orderInfo> details1;
    customerOrderAdapter adapterObj;

    ArrayList<orderInfo> details2;

    BottomNavigationView nav;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_customer_page);
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("Pref",0);
        String userId;
        userId = String.valueOf(sharedPreferences.getInt("Id",0));

        TicketCustomersConnector ticketCustomersConnector =  new TicketCustomersConnector(this);

        details1 = ticketCustomersConnector.ListSeats(userId);

        details2 = new ArrayList<>();




        for(orderInfo det: details1){

            details2.add(new orderInfo(det.name,det.Seat,det.priceTicket,det.ticketNum,det.uniqueCode));
            Log.i(TAG, "onCreate: "+det.name);
        }

        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recyclerID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        //Creates an adapter that then has two parameters: context and a list
        adapterObj = new customerOrderAdapter(this,details2);
        recyclerView.setAdapter(adapterObj);

        nav = findViewById(R.id.nav);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){

                    case R.id.home:
                        navSelect(1);

                        break;

                    case R.id.profile:
                        navSelect(2);

                        break;
                    default:
                }
                return false;
            }
        });


    }

    public void navSelect(int number){
        if(number == 1){
            Intent intent1 = new Intent(this,MainActivity.class);
            startActivity(intent1);
        }else if(number == 2){
            //Main theatre cannot be accessed obvs because it is a recyler
            Intent intent1 = new Intent(this,TicketsCustomerPage.class);
            startActivity(intent1);
        }


    }



}