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

	@Override
	public String toString() {
		return "FoodVO [f_code=" + f_code + ", f_name=" + f_name + ", f_size=" + f_size + ", f_cal=" + f_cal
				+ ", f_carbo=" + f_carbo + ", f_protein=" + f_protein + ", f_fat=" + f_fat + ", f_sugar=" + f_sugar
				+ ", f_na=" + f_na + ", f_cole=" + f_cole + ", toString()=" + super.toString() + "]";
	}

}
