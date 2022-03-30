package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class setings extends AppCompatActivity {

    TextView userName, userMail;
    FirebaseAuth mAuth;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_reports,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if (id == R.id.action_reports) {

            startActivity(new Intent(this, reports2.class));

        } else if (id == R.id.action_home) {

            startActivity(new Intent(this, home.class));

        } else if (id == R.id.action_expenses) {

            startActivity(new Intent(this, ExpensessActivity.class));

        }

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);


        userMail = (TextView) findViewById(R.id.textView46);
        userName = (TextView) findViewById(R.id.textView45);


        userName.setText("hey");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://expensse-9d787-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("Users/" + currentUser.getUid());


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users name = snapshot.getValue(Users.class);
                userName.setText(name.getUserName());
                userMail.setText(name.getUserEmail());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

}