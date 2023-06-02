package com.pcwk.ehr.cmn;

import java.util.ArrayList;


public interface WorkDiv<T> extends PcwkLog {

	/**
	 * 목록 조회
	 * @param param
	 * @return ArrayList<T>
	 */
	ArrayList<T>  doRetrieve(PostDTO search);
	
	/**
	 * 저장
	 * @param param
	 * @return int
	 */
	int doSave(T param);
	
	/**
	 * 수정
	 * @param param
	 * @return int
	 */
	int doUpdate(T param);
	
	/**
	 * 삭제
	 * @param param
	 * @return int
	 */
	int doDelete(T param);
	
	/**
	 * 단건조회
	 * @param param
	 * @return T
	 */
	T doSelectOne(T param);
}
