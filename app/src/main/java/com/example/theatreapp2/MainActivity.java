package com.example.theatreapp2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button soonbtn,cheapestbtn;

    ArrayList<details> detail3;
    adapter adapterObj;

    SearchView searchView;

    BottomNavigationView nav;

    boolean x = false; //Check if soon on
    boolean y = false; // Check if access is on

    boolean filterEarly = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav = findViewById(R.id.nav);

        Login login = new Login();
        int n = login.getIdNum();
        Log.i(TAG, "Login Num: "+n);

        soonbtn = findViewById(R.id.soon_button);
        cheapestbtn = findViewById(R.id.cheapeast_button);


        //searchView = findViewById(R.id.search);

        dbConnector db = new dbConnector(this);




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

        detail3 = new ArrayList<>();

        //Details Needed to add
        //Special Requirements access, wheelchair,flashing lights,blood splatter
        //Location

        detail3.add(new details("The Merchant of Venice","Good Play","23\nApr",R.drawable.merchant,20,true,false,"Merchant Adventurer's Hall, York"));
        detail3.add(new details("Hamlet","Good Play","19\nMay",R.drawable.hamlet,18,false,true,"Clifford's Tower, York"));
        detail3.add(new details("A Midsummer Nights Dream","Good Play","3 \n Jun",R.drawable.midsummer,19,true,true,"Dean's Park, York"));
        detail3.add(new details("Oedipus The King","Good Play","28\nJul",R.drawable.oedipus,20,true,false,"St Mary's Abbey, Museum Gardens, York"));
        detail3.add(new details("The Tempest","Good Play","19\nAug",R.drawable.tempest,14,true,false,"Milleneum Bridge, York"));
        detail3.add(new details("Antigone","Good Play","20\nSep",R.drawable.antigone,21,false,false,"Crypt, York Minster York"));

        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        //Creates an adapter that then has two parameters: context and a list
        adapterObj = new adapter(this,detail3);
        recyclerView.setAdapter(adapterObj);




    /*
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });


     */

    }



    private void filter(String newText) {

        //Create a new list variable called filtered list that
        //has the attributes of the details class
        //Reason for new arraylist is because you will be storing the desired
        //values in a new list which can then be assigned
        List<details> filteredList = new ArrayList<>();


        //Detail3 is a new ArrayList<> just like the one above and stores the
        //theatre details inside it

        for(details item: detail3){
            if(item.getTitle().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapterObj.filterList(filteredList);
    }




    public void soonest(View view){
        //List<details> soonFilterList = new ArrayList<>();
        if(x == true) { //If X is pressed twice
            y = false;
            x = false;
            detail3 = new ArrayList<>();

            detail3.add(new details("The Merchant of Venice", "Good Play", "23\nApr", R.drawable.merchant, 20, true, false, "Merchant Adventurer's Hall, York"));
            detail3.add(new details("Hamlet", "Good Play", "19\nMay", R.drawable.hamlet, 18, false, true, "Clifford's Tower, York"));
            detail3.add(new details("A Midsummer Nights Dream", "Good Play", "3 \n Jun", R.drawable.midsummer, 19, true, true, "Dean's Park, York"));
            detail3.add(new details("Oedipus The King", "Good Play", "28\nJul", R.drawable.oedipus, 20, true, false, "St Mary's Abbey, Museum Gardens, York"));
            detail3.add(new details("The Tempest", "Good Play", "19\nAug", R.drawable.tempest, 14, true, false, "Milleneum Bridge, York"));
            detail3.add(new details("Antigone", "Good Play", "20\nSep", R.drawable.antigone, 21, false, false, "Crypt, York Minster York"));

            adapterObj.filterList(detail3);

            both_off();

        }else if(y == true && x == false){
                y = false;
                x = true;
                ArrayList<details> detail4 = detail3;
                Collections.sort(detail4,(d1,d2)->Integer.compare(d1.getTime(), d2.getTime()));
                adapterObj.filterList(detail4);

                access_off();

            }
        else{
            x = true;
            y = false;
            //detail3 is the array list
            ArrayList<details> detail4 = detail3;
            Collections.sort(detail4,(d1,d2)->Integer.compare(d1.getTime(), d2.getTime()));
            adapterObj.filterList(detail4);

            access_off();
        }


    }

    public void hasAccess(View view){
        if(y == true) { //if y is pressed twice
            y = false;
            x = false;
            both_off();
            detail3 = new ArrayList<>();

            detail3.add(new details("The Merchant of Venice","Good Play","23\nApr",R.drawable.merchant,20,true,false,"Merchant Adventurer's Hall, York"));
            detail3.add(new details("Hamlet","Good Play","19\nMay",R.drawable.hamlet,18,false,true,"Clifford's Tower, York"));
            detail3.add(new details("A Midsummer Nights Dream","Good Play","3 \n Jun",R.drawable.midsummer,19,true,true,"Dean's Park, York"));
            detail3.add(new details("Oedipus The King","Good Play","28\nJul",R.drawable.oedipus,20,true,false,"St Mary's Abbey, Museum Gardens, York"));
            detail3.add(new details("The Tempest","Good Play","19\nAug",R.drawable.tempest,14,true,false,"Milleneum Bridge, York"));
            detail3.add(new details("Antigone","Good Play","20\nSep",R.drawable.antigone,21,false,false,"Crypt, York Minster York"));

            adapterObj.filterList(detail3);

        }else if(y == false && x == true) {
            y = true;
            x = false;
            access_on();
            cheapestConnecting();

        }else{

            y = true;
            x = false;
            access_on();

            cheapestConnecting();

        }



    }

    public void access_off(){
        ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.darkpink));
        ColorStateList colorStateList1 = ColorStateList.valueOf(getResources().getColor(R.color.lightgrey));
        soonbtn.setBackgroundTintList(colorStateList);
        cheapestbtn.setBackgroundTintList(colorStateList1);

        //x = false;

    }



    public void both_off(){
        ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.darkpink));
        soonbtn.setBackgroundTintList(colorStateList);
        cheapestbtn.setBackgroundTintList(colorStateList);
        //x = true;
        //y = true;

    }


    public void access_on(){
        ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.darkpink));
        ColorStateList colorStateList1 = ColorStateList.valueOf(getResources().getColor(R.color.lightgrey));
        soonbtn.setBackgroundTintList(colorStateList1);
        cheapestbtn.setBackgroundTintList(colorStateList);

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

    public void cheapestConnecting(){
        ArrayList<details> detail4 = detail3;

        ArrayList<details> detail5 = new ArrayList<>();
        Collections.sort(detail4,(d1,d2)->Integer.compare(d1.getTime(), d2.getTime()));

        for(int i=0;i < detail4.size();i++){
            if(detail4.get(i).getWheelchairAccess() == Boolean.TRUE){
                detail5.add(detail4.get(i));
            }
        }
        adapterObj.filterList(detail5);
    }








    


}