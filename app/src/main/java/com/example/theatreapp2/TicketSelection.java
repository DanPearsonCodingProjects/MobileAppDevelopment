package com.example.theatreapp2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


//TO DO LIST
//When the page loads it also needs to load all of the data from the main tickets database
//Make ti so if the table is empty then it puts all the data in -- This now works
//

//GONNA HAVE TO PUT THE SPINNER IN THE MAIN THEATRE ONE
public class TicketSelection extends AppCompatActivity {
    TextView txt1,ticketNum,ticketPrice,seatSelect,wheelchairRequirement,epilepsyRequirement,FullName,additionalComments;
    Spinner spinner;
    BottomNavigationView nav;
    Button buyingBtn;

    int userId,sum;

    String name,ticket,selectedSeat,wheelchair,epilepsy,idNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_selection);


        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("Pref",0);
        userId = sharedPreferences.getInt("Id",0);
        Log.i(TAG, "User id: "+userId);

        txt1 = findViewById(R.id.names);
        ticketNum = findViewById(R.id.ticketNum);
        //spinner = findViewById(R.id.spinnerID);
        ticketPrice = findViewById(R.id.ticketPrice);
        seatSelect = findViewById(R.id.seatSelect);
        wheelchairRequirement = findViewById(R.id.wheelchairRequirement);
        epilepsyRequirement = findViewById(R.id.epilepsyRequirement);
        buyingBtn = findViewById(R.id.buyingBtn);
        FullName = findViewById(R.id.FullName);
        additionalComments = findViewById(R.id.additionalComments);

        Intent intent = getIntent();
        name = intent.getStringExtra("data1");
        ticket = intent.getStringExtra("numTickets");
        selectedSeat = intent.getStringExtra("spinnerValue");
        wheelchair = intent.getStringExtra("wheelchairNeeded");
        epilepsy = intent.getStringExtra("EpilepsyWarning");
        idNum = intent.getStringExtra("idNum");
        Log.i(TAG, "onCreate: "+idNum);
        txt1.setText(name);
        ticketNum.setText(ticket);
        seatSelect.setText(selectedSeat);
        wheelchairRequirement.setText(wheelchair);
        epilepsyRequirement.setText(epilepsy);


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





        SeatingConnector seatinConnector = new SeatingConnector(this);
        //

        ArrayList<SeatingDetails> seatingDetails;

        seatingDetails = seatinConnector.ListSeats();



        //seatinConnector.del();

        ArrayList<SeatingDetails> seatingDetails1;
        seatingDetails1 = seatinConnector.ListSeats();
        Collections.sort(seatingDetails1,(d1, d2)->Integer.compare(d1.getSeatPrice(),d2.getSeatPrice()));


        List<String> options = new ArrayList<>();



        //txt1
        for(SeatingDetails det: seatingDetails1) {
            if (name.equals(det.getName())) {

                options.add(det.getSeatType());

                System.out.println("Seat Options " + det.getSeatType());
            }
            System.out.println("Seating Price " + det.getSeatPrice() + " " + det.getName());


        }


        sum = 0;
        for(SeatingDetails det1: seatingDetails1){
            if(selectedSeat.equals(det1.getSeatType())){
                //ticket
                sum = Integer.valueOf(ticket) * Integer.valueOf(det1.getSeatPrice());
                ticketPrice.setText("£"+Integer.toString(sum));
            }
        }



    }



    public void buyTicket(View view){

        //int id = Integer.valueOf(idNum);
        String person_name = FullName.getText().toString();
        String name_show = txt1.getText().toString();
        int tickNum = Integer.valueOf(ticketNum.getText().toString());
        String add = additionalComments.getText().toString();
        String select = seatSelect.getText().toString();
        //int s = Integer.valueOf(ticketPrice.getText().toString());
        String disability = wheelchairRequirement.getText().toString();
        String epilep = epilepsyRequirement.getText().toString();

        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //String alphabetLower = "adbcedfghijklmnopqrstuvwxyz";
        String num = "0123456789";
        String combined = alphabetUpper + num;

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        int len = 10;
        for(int i=0; i < len; i++){
            int ind = random.nextInt(combined.length());

            char randomChar = combined.charAt(ind);
            sb.append(randomChar);
        }

        String uniqueCode = sb.toString();





        TicketCustomersConnector ticketCustomersConnector = new TicketCustomersConnector(this);
        ticketCustomersConnector.addDetails(userId,person_name,select,name_show,sum, tickNum,
                disability + "," + epilep,add,uniqueCode);









        SeatingConnector seatingConnector = new SeatingConnector(this);

        SeatingDetails new1 = new SeatingDetails();
        ArrayList<SeatingDetails> seatingDetails = seatingConnector.ListSeats();


        int ne = 0;

        for(SeatingDetails det: seatingDetails){
            if(name.equals(det.getName())){
                Log.i(TAG, "buyTicket: "+"got here1");
                if(selectedSeat.equals(det.getSeatType())){
                    //det.setSeatNumber(det.getSeatNumber() - Integer.valueOf(ticket));
                    ne = det.getSeatNumber() - Integer.valueOf(ticket);
                    new1.setSeatNumber(ne);
                    new1.setSeatType(det.getSeatType());
                    Log.i(TAG, "buyTicket: "+det.getSeatNumber());
                    break;
                }
            }


        }


        new1.name = name;
        //new1.seatNumber = new1.getSeatNumber() - ne;
        seatingConnector.updateDetails(new1);




        Intent intent = new Intent(this,bookingConfirmed.class);
        intent.putExtra("Code",uniqueCode);
        startActivity(intent);


    }
    


    public void navSelect(int number){
        if(number == 1){
            Intent intent1 = new Intent(this,MainActivity.class);
            startActivity(intent1);
        }else if(number == 2){
            Intent intent1 = new Intent(this,TicketsCustomerPage.class);
            startActivity(intent1);
        }


    }


}