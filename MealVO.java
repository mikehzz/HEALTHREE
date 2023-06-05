package com.pcwk.ehr;

public class MealVO extends MealDTO {
	private String id; // 회원 ID
	private String date; // 날짜: YYYY-MM-DD
	private String div; // 식사 구분: 아침(B)/점심(L)/저녁(D)/간식(S)
	private int seq; // 입력 순서: 1, 2, 3
	private String code; // 식품 코드
	
	public MealVO() {
	}

	
	public MealVO(String id, String date, String div, int seq, String code) {
		super();
		this.id = id;
		this.date = date;
		this.div = div;
		this.seq = seq;
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "MealVO [id=" + id + ", date=" + date + ", div=" + div + ", seq=" + seq + ", code=" + code
				+ ", toString()=" + super.toString() + "]";
	}
	
}
