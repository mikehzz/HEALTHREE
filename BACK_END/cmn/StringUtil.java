package com.pcwk.ehr.cmn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class StringUtil {

	/**
	 * PK를 날짜(8)+UUID(32)
	 * @return
	 */
	public static String getPK() {
		return getDate("yyyyMMdd")+getUUID();
	}
	
	/**
	 * 날짜를 형식문자로 생성
	 * default : yyyyMMdd
	 * @param format(yyyy-MM-dd HH:mm:ss)
	 * @return 
	 */
	public static String getDate(String format) {
		String date = "";
		if(null == format || format.equals("")) {
			format="yyyyMMdd";
		}
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		date = currentDateTime.format(formatter);
		return date;
	}
	
	/**
	 * 32byte uuid생성
	 * @return 32bit uuid
	 */
	public static String getUUID() {
		String pk = "";
		
		UUID uuid = UUID.randomUUID();
		pk = uuid.toString();
		pk = pk.replace("-", "");
		
		return pk;
	}
	
	
}
