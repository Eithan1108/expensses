package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class home extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView ePerMonth;



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

        } else if (id == R.id.action_settings) {

            startActivity(new Intent(this, setings.class));

        } else if (id == R.id.action_expenses) {

            startActivity(new Intent(this, ExpensessActivity.class));

        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);






        ePerMonth = findViewById(R.id.textView17);




        ArrayList<Expensse> expenssesMonth = new ArrayList<Expensse>();
        RecyclerView homeRecycl = findViewById(R.id.home_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        homeRecycl.setLayoutManager(layoutManager);

        HomeAdapter homeAdapter = new HomeAdapter(expenssesMonth);
        homeRecycl.setAdapter(homeAdapter);





        FirebaseDatabase database = FirebaseDatabase.getInstance("https://expensse-9d787-default-rtdb.firebaseio.com/");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUserid = mAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("Users/" + currentUserid.getUid() + "/Receivers");
        String mot = LocalDateTime.now().getMonth().toString();



        LocalDate today = LocalDate.now();
        int mon = today.getMonthValue();
        int localYear = today.getYear();





        myRef.addValueEventListener((new ValueEventListener() {
            int ePM = 0;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                expenssesMonth.clear();


                for (DataSnapshot userSnapshot : snapshot.getChildren()){

                    Expensse currentUser = userSnapshot.getValue((Expensse.class));


                    if (Integer.parseInt(currentUser.getMonth()) == mon && Integer.parseInt(currentUser.getYear()) == localYear){

                            expenssesMonth.add(currentUser);

                            ePM = ePM + Integer.parseInt(currentUser.getAmount());

                            ePerMonth.setText("     "+ePM);

                    }

                }

                homeAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        }));








    }
}