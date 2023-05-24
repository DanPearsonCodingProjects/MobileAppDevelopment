package com.example.theatreapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class bookingConfirmed extends AppCompatActivity {
    TextView unique;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmed);
        unique = findViewById(R.id.unique);
        String code = getIntent().getStringExtra("Code");
        unique.setText(code);

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
            Intent intent1 = new Intent(this,TicketsCustomerPage.class);
            startActivity(intent1);
        }


    }


}