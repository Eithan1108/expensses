package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.util.Util;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Stack;

public class reportMofak extends AppCompatActivity {

//    GraphView graph;
    BarChart mpBarChart;

    // variable for our bar chart
    BarChart barChart;

    String year1Value, month1Value, year2Value, month2Value;

    // variable for our bar data set.
    BarDataSet barDataSet1, barDataSet2;

    // array list for storing entries.
    ArrayList barEntries1, barEntries2, allBarEntries;

    // creating a string array for displaying days.
    String[] days = new String[]{"Home", "Gas", "Food", "Car", "Cloths", "Education"};

    int home1 =0 , home2= 0, gas1= 0, gas2= 0, food1= 0, food2= 0, car1= 0, car2= 0, cloths1= 0, cloths2= 0, education1= 0, education2= 0;

    ArrayList<ArrayList<BarEntry>> [] x;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_mofak);


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://expensse-9d787-default-rtdb.firebaseio.com/");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUserid = mAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("Users/" + currentUserid.getUid() + "/Receivers");
        String mot = LocalDateTime.now().getMonth().toString();





        Intent i = getIntent();
         year1Value = i.getExtras().getString("year1Value");
         month1Value = i.getExtras().getString("month1Value");
         year2Value = i.getExtras().getString("year2Value");
         month2Value = i.getExtras().getString("month2Value");




        ArrayList<ArrayList<BarEntry>> bar;

        getBarEntriesOne(year1Value,month1Value,year2Value,month2Value);


;


        // initializing variable for bar chart.

    }



//    private int []  getData(String year1Value, String month1Value, int home, int gas, int food, int car, int cloths, int education) {
//
//        int [] data = new int [6];
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance("https://expensse-9d787-default-rtdb.firebaseio.com/");
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUserid = mAuth.getCurrentUser();
//        DatabaseReference myRef = database.getReference("Users/" + currentUserid.getUid() + "/Receivers");
//
//        myRef.addValueEventListener((new ValueEventListener() {
//
//            int home = 40;
//            int gas = 0;
//            int food = 0;
//            int car = 0;
//            int cloths = 0;
//            int education = 0;
//
//
//
//
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//                for (DataSnapshot userSnapshot : snapshot.getChildren()){
//
//                    Expensse currentUser = userSnapshot.getValue((Expensse.class));
//
//
//                    if (currentUser.getMonth() == month1Value && currentUser.getYear() == year1Value){
//                        if(currentUser.getCategory() == "Home"){
//                            home = home + Integer.parseInt(currentUser.getAmount());
//                        } else if(currentUser.getCategory() == "Gas"){
//                            gas = gas + Integer.parseInt(currentUser.getAmount());
//                        }else if(currentUser.getCategory() == "Food"){
//                            food = food + Integer.parseInt(currentUser.getAmount());
//                        }else if(currentUser.getCategory() == "Car"){
//                            car = car + Integer.parseInt(currentUser.getAmount());
//                        }else if(currentUser.getCategory() == "Cloths"){
//                            cloths = cloths + Integer.parseInt(currentUser.getAmount());
//                        }else if(currentUser.getCategory() == "Education"){
//                            education = education + Integer.parseInt(currentUser.getAmount());
//                        }
//
//                        data[0] = home;
//                        data[1] = gas;
//                        data[2] = food;
//                        data[3] = car;
//                        data[4] = cloths;
//                        data[5] = education;
//                    }
//
//
//
//
//
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//
//        }));
//
//
//
//
//
//
//
//        Toast.makeText(this, "Home = " + data[0], Toast.LENGTH_LONG).show();
//        return data;
//    }










    // array list for first set
    private void  getBarEntriesOne(String year1Value, String month1Value, String year2Value, String month2Value) {

        x = new ArrayList[1];




        FirebaseDatabase database = FirebaseDatabase.getInstance("https://expensse-9d787-default-rtdb.firebaseio.com/");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUserid = mAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("Users/" + currentUserid.getUid() + "/Receivers");

        myRef.addListenerForSingleValueEvent( (new ValueEventListener() {
            @Override
            public void  onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot userSnapshot : snapshot.getChildren()) {

                    Expensse currentUser = userSnapshot.getValue((Expensse.class));
                    Log.d(currentUser.getAmount() + "", "");


                    if (currentUser.getMonth().equals(month1Value) && currentUser.getYear().equals(year1Value)) {
                        if (currentUser.getCategory().equals("Home")) {
                            home1 = home1 + Integer.parseInt(currentUser.getAmount());
                        }  if (currentUser.getCategory().equals("Gas")) {
                            gas1 = gas1 + Integer.parseInt(currentUser.getAmount());
                        }  if (currentUser.getCategory().equals("Food")) {
                            food1 = food1 + Integer.parseInt(currentUser.getAmount());
                        }  if (currentUser.getCategory().equals("Car")) {
                            car1 = car1 + Integer.parseInt(currentUser.getAmount());
                        }  if (currentUser.getCategory().equals("Cloths")) {
                            cloths1 = cloths1 + Integer.parseInt(currentUser.getAmount());
                        }  if (currentUser.getCategory().equals("Education")) {
                            education1 = education1 + Integer.parseInt(currentUser.getAmount());
                        }

                    }
                     else if (currentUser.getMonth().equals(month2Value) && currentUser.getYear().equals(year2Value)) {
                        if (currentUser.getCategory().equals("Home")) {
                            home2 = home2 + Integer.parseInt(currentUser.getAmount());
                        }  if (currentUser.getCategory().equals("Gas")) {
                            gas2 = gas2 + Integer.parseInt(currentUser.getAmount());
                        }  if (currentUser.getCategory().equals("Food")) {
                            food2 = food2 + Integer.parseInt(currentUser.getAmount());
                        }  if (currentUser.getCategory().equals("Car")) {
                            car2 = car2 + Integer.parseInt(currentUser.getAmount());
                        }  if (currentUser.getCategory().equals("Cloths")) {
                            cloths2 = cloths2 + Integer.parseInt(currentUser.getAmount());
                        }  if (currentUser.getCategory().equals("Education")) {
                            education2 = education2 + Integer.parseInt(currentUser.getAmount());
                        }

                    }
                }

                getBarEntriesOne();

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }




        }));


    }


    private void getBarEntriesOne() {

        allBarEntries = new ArrayList<ArrayList<BarEntry>>();

        barEntries1 = new ArrayList<>();



        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.


        barEntries1.add(new BarEntry(1f, home1));
        barEntries1.add(new BarEntry(2f, gas1));
        barEntries1.add(new BarEntry(3f, food1));
        barEntries1.add(new BarEntry(4f, car1));
        barEntries1.add(new BarEntry(5f, cloths1));
        barEntries1.add(new BarEntry(6f, education1));

        barEntries2 = new ArrayList<>();


        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntries2.add(new BarEntry(1f, home2));
        barEntries2.add(new BarEntry(2f, gas2));
        barEntries2.add(new BarEntry(3f, food2));
        barEntries2.add(new BarEntry(4f, car2));
        barEntries2.add(new BarEntry(5f, cloths2));
        barEntries2.add(new BarEntry(6f, education2));

        allBarEntries.add(barEntries1);
        allBarEntries.add(barEntries2);

        barChart = findViewById(R.id.chart);

//        getBarEntriesOne(year1Value, month1Value, year2Value, month2Value);

        // creating a new bar data set.
        barDataSet1 = new BarDataSet(barEntries1,  month1Value + "." +year1Value );
        barDataSet1.setColor(getApplicationContext().getResources().getColor(R.color.teal_700));
        barDataSet2 = new BarDataSet(barEntries2, month2Value + "." +year2Value);
        barDataSet2.setColor(Color.BLUE);





        // below line is to add bar data set to our bar data.
        BarData data = new BarData(barDataSet1, barDataSet2);

        // after adding data to our bar data we
        // are setting that data to our bar chart.
        barChart.setData(data);

        // below line is to remove description
        // label of our bar chart.
        barChart.getDescription().setEnabled(false);

        // below line is to get x axis
        // of our bar chart.
        XAxis xAxis = barChart.getXAxis();

        // below line is to set value formatter to our x-axis and
        // we are adding our days to our x axis.
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));

        // below line is to set center axis
        // labels to our bar chart.
        xAxis.setCenterAxisLabels(true);

        // below line is to set position
        // to our x-axis to bottom.
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        // below line is to set granularity
        // to our x axis labels.
        xAxis.setGranularity(1);

        // below line is to enable
        // granularity to our x axis.
        xAxis.setGranularityEnabled(true);

        // below line is to make our
        // bar chart as draggable.
        barChart.setDragEnabled(true);

        // below line is to make visible
        // range for our bar chart.
        barChart.setVisibleXRangeMaximum(3);

        // below line is to add bar
        // space to our chart.
        float barSpace = 0.1f;

        // below line is use to add group
        // spacing to our bar chart.
        float groupSpace = 0.5f;

        // we are setting width of
        // bar in below line.
        data.setBarWidth(0.15f);

        // below line is to set minimum
        // axis to our chart.
        barChart.getXAxis().setAxisMinimum(0);

        // below line is to
        // animate our chart.
        barChart.animate();

        // below line is to group bars
        // and add spacing to it.
        barChart.groupBars(0, groupSpace, barSpace);

        // below line is to invalidate
        // our bar chart.
        barChart.invalidate();



    }





   private ArrayList<BarEntry> getBarEntriesTwo() {

        // creating a new array list
        barEntries2 = new ArrayList<>();


        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntries2.add(new BarEntry(1f, 1500));
        barEntries2.add(new BarEntry(2f, 1000));
        barEntries2.add(new BarEntry(3f, 2000));
        barEntries2.add(new BarEntry(4f, 2500));
        barEntries2.add(new BarEntry(5f, 1000));
        barEntries2.add(new BarEntry(6f, 500));

       return barEntries2;

    }
}