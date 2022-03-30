package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;


public class addExpense extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText amount, time, year, month, day;
    Button save;
    FirebaseAuth mAuth;
    Spinner dropdown;
    String cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);


        mAuth = FirebaseAuth.getInstance();

        amount =  findViewById(R.id.editTextNumber2);
//        time =  findViewById(R.id.editTextTextPersonName4);



        year =  findViewById(R.id.editTextNumber3);
        day = findViewById(R.id.editTextNumber6);
        month = findViewById(R.id.editTextNumber5);

        dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Food", "Gas", "Shopping", "Car", "Cloths", "Home", "Education"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);





        save = (Button)findViewById(R.id.button7);
        save.setOnClickListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                cat = "Food";
                break;
            case 1:
                cat = "Gas";
                break;
            case 2:
                cat = "Shopping";
                break;
            case 3:
                cat = "Car";
                break;
            case 4:
                cat = "Cloths";
                break;
            case 5:
                cat = "Home";
                break;
            case 6:
                cat = "Education";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }




    @Override
    public void onClick(View view) {
        if(view == save){

            FirebaseUser currentUser = mAuth.getCurrentUser();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Users/" + currentUser.getUid() + "/Receivers").push();

            String datetime = day.getText().toString() + "." +  month.getText().toString() + "." + year.getText().toString();

            Expensse newEx = new Expensse(cat, amount.getText().toString(),datetime, year.getText().toString(), month.getText().toString(), day.getText().toString(),myRef.getKey());
            myRef.setValue(newEx);

        }
    }
}