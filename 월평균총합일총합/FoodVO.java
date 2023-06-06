package com.pcwk.ehr;

import java.util.List;

public class FoodVO extends FoodDTO {
    private String date; // 날짜: YYYYMMDD
    private String f_code; // 음식 코드
    private String f_name; // 이름
    private double f_size; // 사이즈
    private double f_cal; // 칼로리
    private double f_carbo; // 탄수화물
    private double f_protein; // 단백질
    private double f_fat; // 지방
    private double f_sugar; // 당류
    private double f_na; // 나트륨
    private double f_cole; // 콜레스테롤


    public FoodVO() {
    }

    public FoodVO(String f_code, String f_name, double f_size, double f_cal, double f_carbo, double f_protein,
            double f_fat, double f_sugar, double f_na, double f_cole) {
        super();
        this.f_code = f_code;
        this.f_name = f_name;
        this.f_size = f_size;
        this.f_cal = f_cal;
        this.f_carbo = f_carbo;
        this.f_protein = f_protein;
        this.f_fat = f_fat;
        this.f_sugar = f_sugar;
        this.f_na = f_na;
        this.f_cole = f_cole;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getF_code() {
        return f_code;
    }

    public void setF_code(String f_code) {
        this.f_code = f_code;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public double getF_size() {
        return f_size;
    }

    public void setF_size(double f_size) {
        this.f_size = f_size;
    }

    public double getF_cal() {
        return f_cal;
    }

    public void setF_cal(double f_cal) {
        this.f_cal = f_cal;
    }

    public double getF_carbo() {
        return f_carbo;
    }

    public void setF_carbo(double f_carbo) {
        this.f_carbo = f_carbo;
    }

    public double getF_protein() {
        return f_protein;
    }

    public void setF_protein(double f_protein) {
        this.f_protein = f_protein;
    }

    public double getF_fat() {
        return f_fat;
    }

    public void setF_fat(double f_fat) {
        this.f_fat = f_fat;
    }

    public double getF_sugar() {
        return f_sugar;
    }

    public void setF_sugar(double f_sugar) {
        this.f_sugar = f_sugar;
    }

    public double getF_na() {
        return f_na;
    }

    public void setF_na(double f_na) {
        this.f_na = f_na;
    }

    public double getF_cole() {
        return f_cole;
    }

    public void setF_cole(double f_cole) {
        this.f_cole = f_cole;
    }

    public static void calculateAveragesForDayTotalList(List<FoodVO> foodList) {
        double totalCalories = 0.0;
        double totalCarbohydrates = 0.0;
        double totalProtein = 0.0;
        double totalFat = 0.0;

        for (FoodVO food : foodList) {
            totalCalories += food.getF_cal();
            totalCarbohydrates += food.getF_carbo();
            totalProtein += food.getF_protein();
            totalFat += food.getF_fat();
        }

        int numDays = foodList.size();
        double averageCalories = totalCalories / numDays;
        double averageCarbohydrates = totalCarbohydrates / numDays;
        double averageProtein = totalProtein / numDays;
        double averageFat = totalFat / numDays;

        for (FoodVO food : foodList) {
            food.setF_cal(averageCalories);
            food.setF_carbo(averageCarbohydrates);
            food.setF_protein(averageProtein);
            food.setF_fat(averageFat);
        }
    }

    @Override
    public String toString() {
        return "FoodVO [f_code=" + f_code + ", f_name=" + f_name + ", f_size=" + f_size + ", f_cal=" + f_cal
                + ", f_carbo=" + f_carbo + ", f_protein=" + f_protein + ", f_fat=" + f_fat + ", f_sugar=" + f_sugar
                + ", f_na=" + f_na + ", f_cole=" + f_cole + ", toString()=" + super.toString() + "]";
    }

    public double getAverageCalories() {
        return f_cal;
    }

    public double getAverageCarbohydrates() {
        return f_carbo;
    }

    public double getAverageProtein() {
        return f_protein;
    }

    public double getAverageFat() {
        return f_fat;
    }
    

	public void setTotalCalories(double double1) {
		// TODO Auto-generated method stub
		
	}

	public void setTotalCarbohydrates(double double1) {
		// TODO Auto-generated method stub
		
	}

	public void setTotalProtein(double double1) {
		// TODO Auto-generated method stub
		
	}

	public void setTotalFat(double double1) {
		// TODO Auto-generated method stub
		
	}

	public double getTotalCalories() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getTotalCarbohydrates() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getTotalProtein() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getTotalFat() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setMonth(String string) {
		// TODO Auto-generated method stub
		
	}
}
