package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {



    private ArrayList<Expensse> homeExpensess;


    public HomeAdapter(ArrayList<Expensse> homeExpensess) {
        this.homeExpensess = homeExpensess;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View exView = LayoutInflater.from(parent.getContext()).inflate(R.layout.homerecycler, parent,false);

        return new HomeViewHolder(exView);
    }

    @Override
    public int getItemCount() {
        return homeExpensess.size();
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

        Expensse current = homeExpensess.get(position);
        holder.category.setText(current.getCategory());
        holder.maount.setText(current.getAmount());

    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder{

        TextView category, maount;


        public HomeViewHolder(View itemView){
            super(itemView);

            category = itemView.findViewById(R.id.textView21);
            maount = itemView.findViewById(R.id.textView23);
        }

    }

}
