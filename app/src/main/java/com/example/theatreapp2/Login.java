package com.example.theatreapp2;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText username,password;
    private int idNum = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);

        dbConnector db1 = new dbConnector(this);





        dbConnector db = new dbConnector(this);
        ArrayList<detailsList> newDets = db.check_List();
        for(detailsList dets:newDets){
            System.out.println(dets.getName1());
        }


    }

    public void check(View view){
        String Lname = String.valueOf(username.getText());
        String Lpassword = String.valueOf(password.getText());

        dbConnector database = new dbConnector(this);

        if(database.check_login(Lname,Lpassword) == true){
            idNum = database.id_Num(Lname,Lpassword);


            SharedPreferences sharedPreferences;
            sharedPreferences = getApplicationContext().getSharedPreferences("Pref",0);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt("Id",idNum);
            editor.commit();

            //Intent intent1 = new Intent(this,TicketSelection.class);
            //intent1.putExtra("idNum",idNum);
            //startActivity(intent1);

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else if(database.check_login(Lname,Lpassword) == false){
            Toast.makeText(getApplicationContext(),"Login Not Found",Toast.LENGTH_LONG).show();
            username.setText("");
            password.setText("");
            Log.i(TAG, "check: "+"Not found");
        }
    }

    public void Account(View view){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

    public int getIdNum(){
        return idNum;
    }

}