package com.pcwk.ehr;

import java.util.List;

public class MonthAvg {
    private String month;
    private double averageCalories;
    private double averageCarbohydrates;
    private double averageProtein;
    private double averageFat;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAverageCalories() {
        return averageCalories;
    }

    public void setAverageCalories(double averageCalories) {
        this.averageCalories = averageCalories;
    }

    public double getAverageCarbohydrates() {
        return averageCarbohydrates;
    }

    public void setAverageCarbohydrates(double averageCarbohydrates) {
        this.averageCarbohydrates = averageCarbohydrates;
    }

    public double getAverageProtein() {
        return averageProtein;
    }

    public void setAverageProtein(double averageProtein) {
        this.averageProtein = averageProtein;
    }

    public double getAverageFat() {
        return averageFat;
    }

    public void setAverageFat(double averageFat) {
        this.averageFat = averageFat;
    }

    public static void calculateAveragesForMonthStatsList(List<MonthAvg> monthStatsList, int daysInMonth) {
        for (MonthAvg monthStats : monthStatsList) {
            monthStats.setAverageCalories(monthStats.getAverageCalories() / daysInMonth);
            monthStats.setAverageCarbohydrates(monthStats.getAverageCarbohydrates() / daysInMonth);
            monthStats.setAverageProtein(monthStats.getAverageProtein() / daysInMonth);
            monthStats.setAverageFat(monthStats.getAverageFat() / daysInMonth);
        }
    }
}
