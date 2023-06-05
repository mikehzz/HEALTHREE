package com.pcwk.ehr;

public class MemberVO extends MemberDTO {
	private String id; // id
	private String pw; // 비번
	private String name; // 이름
	private String email; // 이메일
	private String gender; // 성별 ('M', 'F')
	private String birthday; // 생년월일
	private double height; // 신장
	private double weight; // 현재 체중
	private double targetWeight; // 목표 체중
	private int actLevel; // 활동 레벨 (1 낮음, 2 보통, 3 높음, 4 매우 높음)
	private int dietGoal; // 다이어트 목표 (1 감량, 2 유지, 3 증량)
	private double myCalory; // 권장 칼로리;

	public MemberVO() {
	}

	public MemberVO(String id, String pw, String name, String email, String gender, String birthday, double height,
			double weight, double targetWeight, int actLevel, int dietGoal) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.birthday = birthday;
		this.height = height;
		this.weight = weight;
		this.targetWeight = targetWeight;
		this.actLevel = actLevel;
		this.dietGoal = dietGoal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getTargetWeight() {
		return targetWeight;
	}

	public void setTargetWeight(double targetWeight) {
		this.targetWeight = targetWeight;
	}

	public int getActLevel() {
		return actLevel;
	}

	public void setActLevel(int actLevel) {
		this.actLevel = actLevel;
	}

	public int getDietGoal() {
		return dietGoal;
	}

	public void setDietGoal(int dietGoal) {
		this.dietGoal = dietGoal;
	}
	

	public double getMyCalory() {
		return myCalory;
	}

	public void setMyCalory(double myCalory) {
		this.myCalory = myCalory;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email + ", gender=" + gender
				+ ", birthday=" + birthday + ", height=" + height + ", weight=" + weight + ", targetWeight="
				+ targetWeight + ", actLevel=" + actLevel + ", dietGoal=" + dietGoal + ", myCalory=" + myCalory + "]";
	}	

}
