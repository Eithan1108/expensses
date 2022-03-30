package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ExpensessActivity extends AppCompatActivity implements View.OnClickListener {



    ImageButton addButton,addButton11,addButton12,addButton13;
    private View.OnClickListener onExpensClick;
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

        } else if (id == R.id.action_settings) {

            startActivity(new Intent(this, setings.class));

        } else if (id == R.id.action_home) {

            startActivity(new Intent(this, home.class));

        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);



        RecyclerView recyclerview = findViewById(R.id.recyclerview_expensse);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        final ArrayList<Expensse> expensseArray = new ArrayList<Expensse>();
        ExpensseAdapter expensseAdapter = new ExpensseAdapter(expensseArray, this);
        recyclerview.setAdapter(expensseAdapter);
        expensseArray.add(new Expensse("Gas", "243", "543.3.2455", "3","5","20" , "TYUDig32HGB8n"));


        recyclerview.setLayoutManager(layoutManager);







        FirebaseDatabase database = FirebaseDatabase.getInstance("https://expensse-9d787-default-rtdb.firebaseio.com/");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUserid = mAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("Users/" + currentUserid.getUid() + "/Receivers");
        myRef.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                expensseArray.clear();

                for (DataSnapshot userSnapshot : snapshot.getChildren()){

                    Expensse currentUser = userSnapshot.getValue((Expensse.class));

                    expensseArray.add(currentUser);
                }
                expensseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));



        addButton = (ImageButton) findViewById(R.id.imageButton);
        addButton.setOnClickListener(this);



//        RecyclerView recyclerview = findViewById(R.id.recyclerview_expensse);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerview.setLayoutManager(layoutManager);
//
//
//        ExpensseAdapter expensseAdapter = new ExpensseAdapter(expensseArray);
//        recyclerview.setAdapter(expensseAdapter);





        onExpensClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int position = viewHolder.getAdapterPosition();



                startActivity(new Intent(getApplicationContext(), EditExpens.class));




            }
        };

        expensseAdapter.setOnClickToEdit(onExpensClick);


    }

    @Override
    public void onClick(View view) {
         if (view == addButton) {
            startActivity(new Intent(this, addExpense.class));
        }
    }
}