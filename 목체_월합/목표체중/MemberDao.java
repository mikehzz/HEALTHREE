package com.pcwk.ehr;

import java.sql.SQLException;

public interface MemberDao {
	
	// 권장 칼로리
	MemberVO myCalGoal(MemberVO member) throws ClassNotFoundException, SQLException;

	// 건수 조회
	int getCount(MemberVO member) throws SQLException;

	// 회원정보 조회
	MemberVO get(MemberVO member) throws ClassNotFoundException, SQLException;

	// 회원정보 수정
	int update(MemberVO member) throws SQLException;

	// 회원탈퇴
	int deleteOne(MemberVO member) throws SQLException;

	// 회원가입
	int add(MemberVO member) throws ClassNotFoundException, SQLException;
	
	// 목표체중
	double targetWeight(MemberVO member) throws SQLException;

}
