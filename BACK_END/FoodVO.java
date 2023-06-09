package com.pcwk.ehr;

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
	
	// 저번 주 탄/단/지/당/나/콜 (표)
    private double carbo_last_week;
    private double protein_last_week;
    private double fat_last_week;
    private double sugar_last_week;
    private double na_last_week;
    private double cole_last_week;
    
	// 이번 주 탄/단/지/당/나/콜 (표)
    private double carbo_this_week;
    private double protein_this_week;
    private double fat_this_week;
    private double sugar_this_week;
    private double na_this_week;
    private double cole_this_week;

	// 월 총합, 월 평균 칼로리/탄/단/지
	private String month;
	private double totalCalories;
	private double totalCarbohydrates;
	private double totalProtein;
	private double totalFat;
	private double avg_calories;
	private double avg_carbo;
	private double avg_protein;
	private double avg_fat;
	
	// 일일 섭취한 총 칼로리/탄/단/지
	private double sum_calories;
	private double sum_carbo;
	private double sum_protein;
	private double sum_fat;
	
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

	
	// 저번 주 탄/단/지/당/나/콜 (표)
	
	public double getCarbo_last_week() {
        return carbo_last_week;
    }

    public void setCarbo_last_week(double carbo_last_week) {
        this.carbo_last_week = carbo_last_week;
    }

    public double getProtein_last_week() {
        return protein_last_week;
    }

    public void setProtein_last_week(double protein_last_week) {
        this.protein_last_week = protein_last_week;
    }

    public double getFat_last_week() {
        return fat_last_week;
    }

    public void setFat_last_week(double fat_last_week) {
        this.fat_last_week = fat_last_week;
    }

    public double getSugar_last_week() {
        return sugar_last_week;
    }

    public void setSugar_last_week(double sugar_last_week) {
        this.sugar_last_week = sugar_last_week;
    }

    public double getNa_last_week() {
        return na_last_week;
    }

    public void setNa_last_week(double na_last_week) {
        this.na_last_week = na_last_week;
    }

    public double getCole_last_week() {
        return cole_last_week;
    }

    public void setCole_last_week(double cole_last_week) {
        this.cole_last_week = cole_last_week;
    }
    
    // 이번 주 탄/단/지/당/나/콜 (표)
    
    public double getCarbo_this_week() {
        return carbo_this_week;
    }

    public void setCarbo_this_week(double carbo_this_week) {
        this.carbo_this_week = carbo_this_week;
    }

    public double getProtein_this_week() {
        return protein_this_week;
    }

    public void setProtein_this_week(double protein_this_week) {
        this.protein_this_week = protein_this_week;
    }

    public double getFat_this_week() {
        return fat_this_week;
    }

    public void setFat_this_week(double fat_this_week) {
        this.fat_this_week = fat_this_week;
    }

    public double getSugar_this_week() {
        return sugar_this_week;
    }

    public void setSugar_this_week(double sugar_this_week) {
        this.sugar_this_week = sugar_this_week;
    }

    public double getNa_this_week() {
        return na_this_week;
    }

    public void setNa_this_week(double na_this_week) {
        this.na_this_week = na_this_week;
    }

    public double getCole_this_week() {
        return cole_this_week;
    }

    public void setCole_this_week(double cole_this_week) {
        this.cole_this_week = cole_this_week;
    }
    
    // 월 총합, 월 평균 칼로리/탄/단/지

 	public String getMonth() {
 		return month;
 	}

 	public void setMonth(String month) {
 		this.month = month;
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
 	
     public double getAvg_calories() {
 		return avg_calories;
 	}

 	public void setAvg_calories(double avg_calories) {
 		this.avg_calories = avg_calories;
 	}

 	public double getAvg_carbo() {
 		return avg_carbo;
 	}

 	public void setAvg_carbo(double avg_carbo) {
 		this.avg_carbo = avg_carbo;
 	}

 	public double getAvg_protein() {
 		return avg_protein;
 	}

 	public void setAvg_protein(double avg_protein) {
 		this.avg_protein = avg_protein;
 	}

 	public double getAvg_fat() {
 		return avg_fat;
 	}

 	public void setAvg_fat(double avg_fat) {
 		this.avg_fat = avg_fat;
 	}
 	
 	// 일일 섭취한 총 칼로리/탄/단/지
 	
	public double getSum_calories() {
		return sum_calories;
	}

	public void setSum_calories(double sum_calories) {
		this.sum_calories = sum_calories;
	}

	public double getSum_carbo() {
		return sum_carbo;
	}

	public void setSum_carbo(double sum_carbo) {
		this.sum_carbo = sum_carbo;
	}

	public double getSum_protein() {
		return sum_protein;
	}

	public void setSum_protein(double sum_protein) {
		this.sum_protein = sum_protein;
	}

	public double getSum_fat() {
		return sum_fat;
	}

	public void setSum_fat(double sum_fat) {
		this.sum_fat = sum_fat;
	}

	@Override
	public String toString() {
		return "FoodVO [date=" + date + ", f_code=" + f_code + ", f_name=" + f_name + ", f_size=" + f_size + ", f_cal="
				+ f_cal + ", f_carbo=" + f_carbo + ", f_protein=" + f_protein + ", f_fat=" + f_fat + ", f_sugar="
				+ f_sugar + ", f_na=" + f_na + ", f_cole=" + f_cole + ", carbo_last_week=" + carbo_last_week
				+ ", protein_last_week=" + protein_last_week + ", fat_last_week=" + fat_last_week + ", sugar_last_week="
				+ sugar_last_week + ", na_last_week=" + na_last_week + ", cole_last_week=" + cole_last_week
				+ ", carbo_this_week=" + carbo_this_week + ", protein_this_week=" + protein_this_week
				+ ", fat_this_week=" + fat_this_week + ", sugar_this_week=" + sugar_this_week + ", na_this_week="
				+ na_this_week + ", cole_this_week=" + cole_this_week + ", month=" + month + ", totalCalories="
				+ totalCalories + ", totalCarbohydrates=" + totalCarbohydrates + ", totalProtein=" + totalProtein
				+ ", totalFat=" + totalFat + ", avg_calories=" + avg_calories + ", avg_carbo=" + avg_carbo
				+ ", avg_protein=" + avg_protein + ", avg_fat=" + avg_fat + ", sum_calories=" + sum_calories
				+ ", sum_carbo=" + sum_carbo + ", sum_protein=" + sum_protein + ", sum_fat=" + sum_fat + ", toString()="
				+ super.toString() + "]";
	}

}