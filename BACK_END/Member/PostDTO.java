package com.pcwk.ehr;

public class PostDTO {
	
	private int no; // 목록 번호
	private int pageSize; // 페이지 사이즈
	private int pageNo; // 페이지 번호
	private int searchDiv; // 검색 구분 : title(10), contents(20), id(30)
	private String searchWord; // 검색어
	private int totalCnt; //총 건수
	
	public PostDTO() {
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(int searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	@Override
	public String toString() {
		return "PostDTO [no=" + no + ", pageSize=" + pageSize + ", pageNo=" + pageNo + ", searchDiv=" + searchDiv
				+ ", searchWord=" + searchWord + ", totalCnt=" + totalCnt + "]";
	}
	
}
