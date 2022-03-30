package com.example.myapplication;

import java.util.List;

public class Stats {

    private List<Expensse> expenssesToStat;

    public Stats() {
    }

    public Stats(List<Expensse> expenssesToStat) {
        this.expenssesToStat = expenssesToStat;
    }

    public List<Expensse> getExpenssesToStat() {
        return expenssesToStat;
    }

    public void setExpenssesToStat(List<Expensse> expenssesToStat) {
        this.expenssesToStat = expenssesToStat;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "expenssesToStat=" + expenssesToStat +
                '}';
    }
}
