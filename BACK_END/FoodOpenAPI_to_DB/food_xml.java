package com.pcwk.ehr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pcwk.ehr.board.dao.FoodDAO;

public class food_xml {
	
	
	
	 // tag값의 정보를 가져오는 함수
	public static String getTagValue(String tag, Element eElement) {

           //결과를 저장할 result 변수 선언
           String result = "";

	    	NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    	if(null == nlList.item(0) || null == nlList.item(0).getTextContent()  ||  nlList.item(0).getTextContent().isEmpty()) {
	    		result = "null";
	    	}else {
		    	result = nlList.item(0).getTextContent();
	    	}
	    	
	    	return result;
	}
	
	public static float getTagValue_2(String tag, Element eElement) {

        //결과를 저장할 result 변수 선언
        String result = "";

	    	NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    	if(null == nlList.item(0) || null == nlList.item(0).getTextContent()  ||  nlList.item(0).getTextContent().isEmpty()) {
	    		result = "0";
	    	}else {
		    	result = nlList.item(0).getTextContent();
	    	}
	    	
	    	return Float.parseFloat(result);
	}
	
	public static void main(String[] args) {

		// 본인이 받은 api키를 추가
		String key = "c62870d4ae284c51aadb";
		
		try{
			
			String[] urls = new String[90];
			String str1;
			String str2;
			String str3;
			for(int i=0;i<90;i++) {
				String to = Integer.toString(i+1);
				String to1 = Integer.toString(i+2);
				str1 = "http://openapi.foodsafetykorea.go.kr/api/c62870d4ae284c51aadb/I2790/xml/";
				str2 = "001/";
				str3 = "000";
				urls[i] = str1+to+str2+to1+str3;	
			}
				
	        // i= 38 까지함.
			for(int i = 8; i < urls.length; i++) {
				
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(urls[i]);
				
				// 제일 첫번째 태그
				doc.getDocumentElement().normalize();
				
				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("row");
				
				for(int temp = 0; temp < nList.getLength(); temp++){
					Node nNode = nList.item(temp);
					
					Element eElement = (Element) nNode;
					StringBuilder sb = new StringBuilder(500);
					System.out.println("음식ID : " + getTagValue("FOOD_CD", eElement));
					System.out.println("이름 : " + getTagValue("DESC_KOR", eElement));
					System.out.println("음식사이즈 : " + getTagValue_2("SERVING_SIZE", eElement));
					System.out.println("칼로리 : " + getTagValue_2("NUTR_CONT1", eElement));
					System.out.println("탄수화물 : " + getTagValue_2("NUTR_CONT2", eElement));
					System.out.println("단백질 : " + getTagValue_2("NUTR_CONT3", eElement));
					System.out.println("지방 : " + getTagValue_2("NUTR_CONT4", eElement));
					System.out.println("총당류 : " + getTagValue_2("NUTR_CONT5", eElement));
					System.out.println("나트륨 : " + getTagValue_2("NUTR_CONT6", eElement));
					System.out.println("콜레스테롤 : " + getTagValue_2("NUTR_CONT7", eElement));
					
					FoodDTO outVO = new FoodDTO();
					
					outVO.setF_code(getTagValue("FOOD_CD", eElement));
					outVO.setF_name(getTagValue("DESC_KOR", eElement));
					outVO.setF_size(getTagValue_2("SERVING_SIZE", eElement));
					outVO.setF_cal(getTagValue_2("NUTR_CONT1", eElement));
					outVO.setF_carbo(getTagValue_2("NUTR_CONT2", eElement));
					outVO.setF_protein(getTagValue_2("NUTR_CONT3", eElement));
					outVO.setF_fat(getTagValue_2("NUTR_CONT4", eElement));
					outVO.setF_sugar(getTagValue_2("NUTR_CONT5", eElement));
					outVO.setF_na(getTagValue_2("NUTR_CONT6", eElement));
					outVO.setF_cole(getTagValue_2("NUTR_CONT7", eElement));
	
					FoodDAO f = new FoodDAO();
					f.doSave(outVO);
					
					}
				
			}
			
			}catch (Exception e){	
			e.printStackTrace();
				
			}	
		
		
	
	
	}


}
