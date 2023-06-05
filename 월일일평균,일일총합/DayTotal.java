package com.pcwk.ehr;

import java.util.List;

public class DayTotal {
    private String date;
    private double totalCalories;
    private double totalCarbohydrates;
    private double totalProtein;
    private double totalFat;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public double getTotalCarbohydrates() {
        return totalCarbohydrates;
    }

    public void setTotalCarbohydrates(double totalCarbohydrates) {
        this.totalCarbohydrates = totalCarbohydrates;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public static void calculateTotalsForDayStatsList(List<DayTotal> dayStatsList) {
        for (DayTotal dayStats : dayStatsList) {
            dayStats.setTotalCalories(dayStats.getTotalCalories());
            dayStats.setTotalCarbohydrates(dayStats.getTotalCarbohydrates());
            dayStats.setTotalProtein(dayStats.getTotalProtein());
            dayStats.setTotalFat(dayStats.getTotalFat());
        }
    }
}
