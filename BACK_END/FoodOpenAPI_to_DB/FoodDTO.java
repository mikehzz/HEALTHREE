package com.pcwk.ehr.board.dao;

import com.pcwk.ehr.cmn.DTO;

public class FoodDTO extends DTO {
	//java 변수, 메서드 : 카멜케이스

	private String f_code;   	//음식 코드
	private String f_name;   	//이름
	private float f_size;   	//사이즈
	private float f_cal;	 	//칼로리
	private float f_carbo;	 	//탄수화물
	private float f_protein;	//단백질
	private float f_fat; 		//지방
	private float f_sugar;		//당류
	private float f_na;		//나트륨
	private float f_cole;		//콜레스테롤
	
	public FoodDTO() {}

	public FoodDTO(String f_code, String f_name, float f_size, float f_cal, float f_carbo, float f_protein, float f_fat,
			float f_sugar, float f_na, float f_cole) {
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

	public float getF_size() {
		return f_size;
	}

	public void setF_size(float f_size) {
		this.f_size = f_size;
	}

	public float getF_cal() {
		return f_cal;
	}

	public void setF_cal(float f_cal) {
		this.f_cal = f_cal;
	}

	public float getF_carbo() {
		return f_carbo;
	}

	public void setF_carbo(float f_carbo) {
		this.f_carbo = f_carbo;
	}

	public float getF_protein() {
		return f_protein;
	}

	public void setF_protein(float f_protein) {
		this.f_protein = f_protein;
	}

	public float getF_fat() {
		return f_fat;
	}

	public void setF_fat(float f_fat) {
		this.f_fat = f_fat;
	}

	public float getF_sugar() {
		return f_sugar;
	}

	public void setF_sugar(float f_sugar) {
		this.f_sugar = f_sugar;
	}

	public float getF_na() {
		return f_na;
	}

	public void setF_na(float f_na) {
		this.f_na = f_na;
	}

	public float getF_cole() {
		return f_cole;
	}

	public void setF_cole(float f_cole) {
		this.f_cole = f_cole;
	}

	@Override
	public String toString() {
		return "FoodDTO [f_code=" + f_code + ", f_name=" + f_name + ", f_size=" + f_size + ", f_cal=" + f_cal
				+ ", f_carbo=" + f_carbo + ", f_protein=" + f_protein + ", f_fat=" + f_fat + ", f_sugar=" + f_sugar
				+ ", f_na=" + f_na + ", f_cole=" + f_cole + "]";
	}


	




	

}
