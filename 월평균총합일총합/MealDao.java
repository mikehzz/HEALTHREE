package com.pcwk.ehr;

import java.sql.SQLException;
import java.util.List;

public interface MealDao<DayTotal> {

	// 회원ID, 날짜까지 선택 후 조회
	List<MealVO> getDivSeqFoodCode(MealVO meal) throws ClassNotFoundException, SQLException;

	// 회원ID, 날짜, 식사구분까지 선택 후 조회
	List<MealVO> getSeqFoodCode(MealVO meal) throws SQLException;

	// 회원ID, 날짜, 식사구분, SEQ까지 선택 후 입력한 음식코드 조회 (단 건 조회)
	MealVO getFoodCode(MealVO meal) throws ClassNotFoundException, SQLException;

	// 삭제
	int deleteOne(MealVO meal) throws SQLException;

	// 추가
	int add(MealVO meal) throws ClassNotFoundException, SQLException;
	
	// 월 일일 평균
	List<FoodVO> getMonthAvg(String memberId, String mealType) throws SQLException;
    // 일일 총합	
	List<DayTotal> getDayStats(String memberId, String mealType) throws SQLException;
	// 월 합
    List<FoodVO> getMonthStats(String memberId, String mealType) throws SQLException;


}