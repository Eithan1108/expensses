package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditExpens extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText amount, time, year, month, day;
    Button save, delete;
    FirebaseAuth mAuth;
    Spinner dropdown;
    String cat, key;
    Expensse expensse, expensseFromDataBase;
    FirebaseDatabase database;
    DatabaseReference posRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);

        mAuth = FirebaseAuth.getInstance();

        expensse = getIntent().getParcelableExtra("selected_expenses");

        key = expensse.getKey();

        amount =  findViewById(R.id.editTextNumber);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUserid = mAuth.getCurrentUser();
        posRef = database.getReference("Users/" + currentUserid.getUid() + "/Receivers/" + key);












        year =  findViewById(R.id.editTextNumber10);
        day = findViewById(R.id.editTextNumber12);
        month = findViewById(R.id.editTextNumber11);

        dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"Food", "Gas", "Shopping", "Car", "Cloths", "Home", "Education"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        save = (Button)findViewById(R.id.button5);
        delete = (Button)findViewById(R.id.button6);

        this.retrieve();




//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                FirebaseDatabase database = FirebaseDatabase.getInstance("https://expensse-9d787-default-rtdb.firebaseio.com/");
//                mAuth = FirebaseAuth.getInstance();
//                FirebaseUser currentUserid = mAuth.getCurrentUser();
//                DatabaseReference myRef = database.getReference("Users/" + currentUserid.getUid() + "/Receivers/" + expensse.getKey());
//                myRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Expensse currentUser = snapshot.getValue((Expensse.class));
//
//                        String datetime = day.getText().toString() + "." +  month.getText().toString() + "." + year.getText().toString();
//                        currentUser.setCategory(cat);
//                        currentUser.setAmount(amount.getText().toString());
//                        currentUser.setYear(year.getText().toString());
//                        currentUser.setMonth(month.getText().toString());
//                        currentUser.setDay(day.getText().toString());
//                        currentUser.setDate(datetime);
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(EditExpens.this, "not", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//
//
//
//
//
//
//
//
//
//
//
//            }
//        });
//


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posRef = database.getReference("Users/" + currentUserid.getUid() + "/Receivers/" + expensseFromDataBase.getKey());


                String datetime = day.getText().toString() + "." +  month.getText().toString() + "." + year.getText().toString();
                expensseFromDataBase.setCategory(cat);
                expensseFromDataBase.setAmount(amount.getText().toString());
                expensseFromDataBase.setYear(year.getText().toString());
                expensseFromDataBase.setMonth(month.getText().toString());
                expensseFromDataBase.setDay(day.getText().toString());
                expensseFromDataBase.setDate(datetime);

                posRef.setValue(expensseFromDataBase);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posRef = database.getReference("Users/" + currentUserid.getUid() + "/Receivers/" + expensseFromDataBase.getKey());



            }
        });
    }


    public void retrieve(){
        posRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                expensseFromDataBase = snapshot.getValue(Expensse.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
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
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}