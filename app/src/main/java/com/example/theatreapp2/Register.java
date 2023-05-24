package com.example.theatreapp2;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText name1,username1,password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name1 = findViewById(R.id.register_name);
        username1 = findViewById(R.id.register_username);
        password1 = findViewById(R.id.register_password);

    }

    public void addtodatabase(View view){
        dbConnector database = new dbConnector(this);



        String lName = String.valueOf(name1.getText()) ;
        String lUsername = String.valueOf(username1.getText());
        String lPassword = String.valueOf(password1.getText());

        if(database.check_login(lUsername,lPassword) == true){
            Toast.makeText(getApplicationContext(),"This user already exists",Toast.LENGTH_LONG).show();
            Log.i(TAG, "addtodatabase: "+"This Login Already Exists");

        }else{
            database.addDetails(lName,lUsername,lPassword);
            name1.setText("");
            username1.setText("");
            password1.setText("");
            Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_LONG).show();
        }
    }

    public void alreadyAccount(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}