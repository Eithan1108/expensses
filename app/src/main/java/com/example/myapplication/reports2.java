package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class reports2 extends AppCompatActivity implements View.OnClickListener {

    Button makeReport;
    EditText year1, month1, year2, month2;
    public String year1Value, month1Value, year2Value, month2Value;

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

        if (id == R.id.action_home) {

            startActivity(new Intent(this, home.class));

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
        setContentView(R.layout.activity_reports2);



        makeReport = (Button)findViewById(R.id.button3);

        year1 = findViewById(R.id.editTextNumber8);
        month1 = findViewById(R.id.editTextNumber4);
        year2 = findViewById(R.id.editTextNumber9);
        month2 = findViewById(R.id.editTextNumber7);



        makeReport.setOnClickListener(this);
    }







    @Override
    public void onClick(View view) {

        if (view == makeReport)
        {

            year1Value = year1.getText().toString();
            month1Value = month1.getText().toString();
            year2Value = year2.getText().toString();
            month2Value = month2.getText().toString();


           Intent i =  new Intent(this, reportMofak.class);
           i.putExtra("year1Value", year1Value);
           i.putExtra("month1Value", month1Value);
           i.putExtra("year2Value", year2Value);
           i.putExtra("month2Value", month2Value);
           startActivity(i);



        }

    }
}