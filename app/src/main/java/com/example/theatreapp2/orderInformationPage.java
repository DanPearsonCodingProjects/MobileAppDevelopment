package com.example.theatreapp2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class orderInformationPage extends AppCompatActivity {

    String name2,seatType2,price2,ticketNumber2,code2;

    TextView uniqueCode,namePlay,seatBooked,ticketPriceBooked,quantityBooked;

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_information_page);


        name2 = getIntent().getStringExtra("name");
        seatType2 = getIntent().getStringExtra("seatType");
        price2 = getIntent().getStringExtra("Price");
        ticketNumber2 = getIntent().getStringExtra("ticketNumber");
        code2 = getIntent().getStringExtra("Code");

        uniqueCode = findViewById(R.id.uniqueCode);
        uniqueCode.setText(code2);

        namePlay = findViewById(R.id.namePlay);
        namePlay.setText(name2);

        seatBooked = findViewById(R.id.seatBooked);
        seatBooked.setText(seatType2);

        ticketPriceBooked = findViewById(R.id.ticketPriceBooked);
        ticketPriceBooked.setText("£"+price2);

        quantityBooked = findViewById(R.id.quantityBooked);
        quantityBooked.setText(ticketNumber2);

        nav = findViewById(R.id.nav);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){

                    case R.id.home:
                        navSelect(1);
                        Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG);
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

    public void cancelTicket(View view){
        SeatingConnector seatingConnector = new SeatingConnector(this);

        SeatingDetails new1 = new SeatingDetails();
        ArrayList<SeatingDetails> seatingDetails = seatingConnector.ListSeats();



        int ne = 0;

        for(SeatingDetails det: seatingDetails){
            if(name2.equals(det.getName())){
                Log.i(TAG, "buyTicket: "+"got here1");
                if(seatType2.equals(det.getSeatType())){
                    //det.setSeatNumber(det.getSeatNumber() - Integer.valueOf(ticket));
                    ne = det.getSeatNumber() + Integer.valueOf(ticketNumber2);
                    new1.setSeatNumber(ne);
                    new1.setSeatType(det.getSeatType());
                    Log.i(TAG, "buyTicket: "+det.getSeatNumber());
                    break;
                }
            }


        }


        new1.name = name2;
        //new1.seatNumber = new1.getSeatNumber() - ne;
        seatingConnector.updateDetails(new1);


        //Search the database for the column with the unique id in and then delete it from the database

        TicketCustomersConnector ticketCustomersConnector = new TicketCustomersConnector(this);
        ticketCustomersConnector.deleteRow(code2);

        Intent intent = new Intent(this,TicketsCustomerPage.class);
        startActivity(intent);

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