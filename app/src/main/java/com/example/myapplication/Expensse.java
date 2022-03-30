package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

public class Expensse implements Parcelable {

    private String category;
    private String amount;
    private String date;
    private String year;
    private String month;
    private String day;



    private String key;






    public Expensse() {
    }



    public Expensse(String category, String amount, String date, String year, String month, String day, String key ) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.year = year;
        this.month = month;
        this.day = day;
        this.key = key;



    }

    protected Expensse(Parcel in) {
        category = in.readString();
        amount = in.readString();
        date = in.readString();
        year = in.readString();
        month = in.readString();
        day = in.readString();
        key = in.readString();
    }

    public static final Creator<Expensse> CREATOR = new Creator<Expensse>() {
        @Override
        public Expensse createFromParcel(Parcel in) {
            return new Expensse(in);
        }

        @Override
        public Expensse[] newArray(int size) {
            return new Expensse[size];
        }
    };

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }




    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }


    @Override
    public String toString() {
        return "Expensse{" +
                "category=" + category +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(category);
        parcel.writeString(amount);
        parcel.writeString(date);
        parcel.writeString(year);
        parcel.writeString(month);
        parcel.writeString(day);
        parcel.writeString(key);
    }
}
