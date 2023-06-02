package com.pcwk.ehr.post;

import com.pcwk.ehr.cmn.PostDTO;

public class PostVO extends PostDTO {
	private String seq; // 게시글 ID
	private String id; // 회원 ID
	private String title; // 제목
	private int readCnt; // 조회수
	private String atchFileId; // 첨부파일 ID
	private int div; // 게시글 구분 (10, 20, 30)
	private String contents; // 내용
	private String regDt; // 등록일
	private String modDt; // 수정일
	private int likeCnt; // 좋아요 수

	public PostVO() {
	}
	

	public PostVO(String seq, String id, String title, int div, String contents, String regDt) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.div = div;
		this.contents = contents;
	}
	
	

	public PostVO(String seq, String id, String title, int readCnt, String atchFileId, int div, String contents,
			String regDt, String modDt, int likeCnt) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.readCnt = readCnt;
		this.atchFileId = atchFileId;
		this.div = div;
		this.contents = contents;
		this.regDt = regDt;
		this.modDt = modDt;
		this.likeCnt = likeCnt;
	}


	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public int getDiv() {
		return div;
	}

	public void setDiv(int div) {
		this.div = div;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	@Override
	public String toString() {
		return "PostVO [seq=" + seq + ", id=" + id + ", title=" + title + ", readCnt=" + readCnt + ", atchFileId="
				+ atchFileId + ", div=" + div + ", contents=" + contents + ", regDt=" + regDt + ", modDt=" + modDt
				+ ", likeCnt=" + likeCnt + ", toString()=" + super.toString() + "]";
	}

}
