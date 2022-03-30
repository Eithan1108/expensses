package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;







public class ExpensseAdapter extends RecyclerView.Adapter<ExpensseAdapter.ExpensseViewHolder> {

    private ArrayList<Expensse> expenssesList;
    private View.OnClickListener onClickToEdit;
    private Context context;


    public ExpensseAdapter(ArrayList<Expensse> expenssesList, Activity c) {
        this.expenssesList = expenssesList;
        this.context = c;
    }

    @NonNull
    @Override
    public ExpensseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View expensseView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleritem_expensse, parent,false);
        return new ExpensseViewHolder(expensseView);
    }




    public void setOnClickToEdit(View.OnClickListener itemOnClickToEdit){

//        Toast.makeText(context, "Empty field!", Toast.LENGTH_LONG).show();


        onClickToEdit = itemOnClickToEdit;


//        Intent i = new Intent(context, EditExpens.class);
//        context.startActivity(i);




    }




    @Override
    public void onBindViewHolder(@NonNull ExpensseViewHolder holder, int position) {

        Expensse current = expenssesList.get(position);
        holder.tarih.setText(""+ current.getDay()+ "." + current.getMonth()+"."+current.getYear());
        holder.category.setText(current.getCategory());
        holder.maount.setText(current.getAmount());

        holder.edit.setOnClickListener((view -> {
            Log.d("works", "Works!");
            Intent intent = new Intent(context, EditExpens.class);
            intent.putExtra("selected_expenses", expenssesList.get(position));
            context.startActivity(intent);
        }));














    }

    @Override
    public int getItemCount() {
        return expenssesList.size();
    }


    public class ExpensseViewHolder extends RecyclerView.ViewHolder {

        public TextView tarih;
        public ImageButton edit;
        public TextView category;
        public TextView maount;
        private View rootView;








        public ExpensseViewHolder (@NonNull View itemView) {
            super(itemView);

            tarih = itemView.findViewById(R.id.textView3);
            edit = itemView.findViewById(R.id.imageButton5);
            category = itemView.findViewById(R.id.textView4);
            maount = itemView.findViewById(R.id.textView5);


//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, EditExpens.class);
////                intent.putExtra("selected_expenses", expenssesList.get(position));
//                    context.startActivity(intent);
//                }
//            });











        }


    }





}
