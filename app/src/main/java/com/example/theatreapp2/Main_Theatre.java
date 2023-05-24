package com.example.theatreapp2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.SavedStateHandleAttacher;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_Theatre extends AppCompatActivity {
    String data1,data2,data3,wheelChairAccess,epilpetic,location,ticks;


    boolean wheelChairAccessBool,epilepsyWarningBool;
    TextView titletxt,desctxt,datetxt,timetxt,numTickets,wheelchairTXT,locationTXT;
    ImageView imgtxt;

    CheckBox wheelChair,Epilepsy,bloodSplatter;
    int img,data4;

    int tickets = 1;

    BottomNavigationView nav;

    Dialog myDialog;

    Button button;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_theatre);


        String idNu;
        //Intent intent = new Intent();
        /*
        idNu = getIntent().getStringExtra("idNum");
        Log.i(TAG, "onCreate: "+idNu);


         */


        spinner = findViewById(R.id.spinnerID1);
        numTickets = findViewById(R.id.numTickets);
        ticks = Integer.toString(tickets);
        numTickets.setText(ticks);

        titletxt = findViewById(R.id.titletxt);
        //desctxt = findViewById(R.id.desctxt);
        datetxt = findViewById(R.id.datetxt);
        imgtxt = findViewById(R.id.imgtxt);
        timetxt = findViewById(R.id.timetxt);


        wheelChair = findViewById(R.id.wheelChair);
        Epilepsy = findViewById(R.id.Epilepsy);
        wheelchairTXT = findViewById(R.id.wheelchairTXT);
        locationTXT = findViewById(R.id.locationTXT);
        bloodSplatter = findViewById(R.id.bloodSplatter);


        getData();
        setData();

        //POPUP CODE
        myDialog = new Dialog(this);
        button = findViewById(R.id.buyBtn);
        locationTXT.setText(location);

        if(data1.equals("Hamlet")){
            bloodSplatter.setVisibility(View.VISIBLE);}




        if(wheelChairAccessBool == true){
            wheelChair.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked == true){
                        wheelChairAccess = "Wheelchair Access Needed";
                    }
                    else{
                        wheelChairAccess = "";
                    }
                }
            });
        }else{
            wheelchairTXT.setVisibility(View.VISIBLE);
            if(wheelChair.getVisibility() == View.VISIBLE){
                wheelChair.setVisibility(View.INVISIBLE);

            }
        }

        if(epilepsyWarningBool == true){
            Epilepsy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked == true){
                        epilpetic = "Epileptic";
                    }else{
                        epilpetic = "";
                    }
                }
            });
        }else{

            if(Epilepsy.getVisibility() == View.VISIBLE){
                Epilepsy.setVisibility(View.INVISIBLE);
            }

        }




        //Send data to popup



        ///

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



        //SPINNER CODE

        SeatingConnector seatinConnector = new SeatingConnector(this);
        //

        ArrayList<SeatingDetails> seatingDetails;

        seatingDetails = seatinConnector.ListSeats();

        //seatinConnector.del();

        if(seatingDetails.isEmpty()){
            seatinConnector.addDetails("The Merchant of Venice","Seated",8,"yes",17);
            seatinConnector.addDetails("Hamlet","Seated",8,"no",8);
            seatinConnector.addDetails("Hamlet","Standing",7,"no",12);
            seatinConnector.addDetails("A Midsummer Nights Dream","On Stage",4,"yes",11);
            seatinConnector.addDetails("A Midsummer Nights Dream","Grass",4,"yes",29);
            seatinConnector.addDetails("Oedipus","Seated",9,"yes",5);
            seatinConnector.addDetails("Oedipus","Standing",12,"yes",12);
            seatinConnector.addDetails("The Tempest","Boat A",9,"yes",6);
            seatinConnector.addDetails("The Tempest","Boat B",9,"yes",4);
            seatinConnector.addDetails("The Tempest","Riverbank",7,"yes",10);
            seatinConnector.addDetails("Antigone","Inner Circle",16,"no",5);
            seatinConnector.addDetails("Antigone","Outer Circle",13,"no",8);
            seatinConnector.addDetails("Antigone","Standing",10,"no",10);
        }



        ArrayList<SeatingDetails> seatingDetails1;
        seatingDetails1 = seatinConnector.ListSeats();
        Collections.sort(seatingDetails1,(d1, d2)->Integer.compare(d1.getSeatPrice(),d2.getSeatPrice()));


        List<String> options = new ArrayList<>();



        //txt1
        for(SeatingDetails det: seatingDetails1) {
            if (data1.equals(det.getName())) {

                options.add(det.getSeatType());

                System.out.println("Seat Options " + det.getSeatType());
            }
            System.out.println("Seating Price " + det.getSeatPrice() + " " + det.getName());


        }

        ArrayAdapter<String> adapterOptions = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,options);
        spinner.setAdapter(adapterOptions);




    }


    private void getData(){
        data1 = getIntent().getStringExtra("title");
        data2 = getIntent().getStringExtra("desc");
        data3 = getIntent().getStringExtra("date");
        data4 = getIntent().getIntExtra("time",1);
        img = getIntent().getIntExtra("img",1);
        wheelChairAccessBool = getIntent().getBooleanExtra("wheelchairAccess",false);
        epilepsyWarningBool = getIntent().getBooleanExtra("epilepsyWarning",false);
        location = getIntent().getStringExtra("location");


    }

    private void setData(){
       titletxt.setText(data1);
       //desctxt.setText(data2);
       datetxt.setText(data3);
       timetxt.setText(String.valueOf(data4)+":00");
       imgtxt.setImageResource(img);

    }

    public void add(View view){
        if (tickets >=10){
            tickets = 10;
        }else{
            tickets = tickets + 1;
            String ticks = Integer.toString(tickets);
            numTickets.setText(ticks);


        }


    }

    public void subtract(View view){
        if (tickets <= 1 ){
            tickets = 1;
        }else {
            tickets = tickets - 1;
            String ticks = Integer.toString(tickets);
            numTickets.setText(ticks);
        }
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


    public void send(View view){


        //if name == hamlet blood splatter visible

        if(data1.equals("Hamlet")){
            if(bloodSplatter.isChecked()){
                String selectedSeat;
                selectedSeat = spinner.getSelectedItem().toString();
                SeatingConnector seatingConnector = new SeatingConnector(this);
                ArrayList<SeatingDetails> seatingDetails = seatingConnector.ListSeats();

                for (SeatingDetails det : seatingDetails) {

                    if (data1.equals(det.getName())) {
                        if (selectedSeat.equals(det.getSeatType())) {
                            //det.setSeatNumber(det.getSeatNumber() - Integer.valueOf(ticket));
                            if (det.getSeatNumber() - tickets < 0) {
                                Toast.makeText(getApplicationContext(), "NOT ENOUGH TICKETS LEFT", Toast.LENGTH_LONG).show();
                                break;
                            } else {
                                Intent intent = new Intent(this, TicketSelection.class);
                                intent.putExtra("data1", titletxt.getText().toString());
                                intent.putExtra("numTickets", numTickets.getText().toString());
                                intent.putExtra("spinnerValue", selectedSeat);
                                intent.putExtra("wheelchairNeeded", wheelChairAccess);
                                intent.putExtra("EpilepsyWarning", epilpetic);
                                startActivity(intent);
                            }

                        }
                    }
                }

            }else{
                Toast.makeText(getApplicationContext(),"MUST AGREE TO BLOOD SPLATTER",Toast.LENGTH_LONG).show();
            }


        }else {


            String selectedSeat;
            selectedSeat = spinner.getSelectedItem().toString();
            SeatingConnector seatingConnector = new SeatingConnector(this);
            ArrayList<SeatingDetails> seatingDetails = seatingConnector.ListSeats();

            for (SeatingDetails det : seatingDetails) {
                if (data1.equals(det.getName())) {
                    if (selectedSeat.equals(det.getSeatType())) {
                        //det.setSeatNumber(det.getSeatNumber() - Integer.valueOf(ticket));
                        if (det.getSeatNumber() - tickets < 0) {
                            Toast.makeText(getApplicationContext(), "NOT ENOUGH TICKETS LEFT", Toast.LENGTH_LONG).show();
                            break;
                        } else {
                            Intent intent = new Intent(this, TicketSelection.class);
                            intent.putExtra("data1", titletxt.getText().toString());
                            intent.putExtra("numTickets", numTickets.getText().toString());
                            intent.putExtra("spinnerValue", selectedSeat);
                            intent.putExtra("wheelchairNeeded", wheelChairAccess);
                            intent.putExtra("EpilepsyWarning", epilpetic);
                            startActivity(intent);
                        }

                    }
                }
            }

        }



    }


    //JUST CREATE ONE DATABASE AND THEN CREATE TABLES WITH ALL THE DIFFERENT NAMES OF THE SHOWS


}