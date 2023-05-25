DROP INDEX PK_POST;

/* 게시판 */
DROP TABLE POST 
	CASCADE CONSTRAINTS;

/* 게시판 */
CREATE TABLE POST (
	p_seq CHAR(40 BYTE) NOT NULL, /* 게시글ID */
	m_id VARCHAR2(20) NOT NULL, /* 게시글등록자 */
	p_title VARCHAR2(200 CHAR) NOT NULL, /* 게시글제목 */
	p_read_cnt NUMBER(6) DEFAULT 0, /* 게시글조회수 */
	p_atch_file_id VARCHAR2(30 BYTE), /* 첨부파일ID */
	p_div VARCHAR2(15 BYTE) NOT NULL, /* 게시글구분 */
	p_contents CLOB, /* 게시글내용 */
	p_reg_dt DATE DEFAULT SYSDATE NOT NULL, /* 게시글등록일 */
	p_mod_dt DATE DEFAULT SYSDATE, /* 게시글수정일 */
	p_like_cnt NUMBER(6) /* 게시글좋아요수 */
);

COMMENT ON TABLE POST IS '게시판';

COMMENT ON COLUMN POST.p_seq IS '게시글ID';

COMMENT ON COLUMN POST.m_id IS '게시글등록자';

COMMENT ON COLUMN POST.p_title IS '게시글제목';

COMMENT ON COLUMN POST.p_read_cnt IS '게시글조회수';

COMMENT ON COLUMN POST.p_atch_file_id IS '첨부파일ID';

COMMENT ON COLUMN POST.p_div IS '게시글구분';

COMMENT ON COLUMN POST.p_contents IS '게시글내용';

COMMENT ON COLUMN POST.p_reg_dt IS '게시글등록일';

COMMENT ON COLUMN POST.p_mod_dt IS '게시글수정일';

COMMENT ON COLUMN POST.p_like_cnt IS '게시글좋아요수';

CREATE UNIQUE INDEX PK_POST
	ON POST (
		p_seq ASC
	);

ALTER TABLE POST
	ADD
		CONSTRAINT PK_POST
		PRIMARY KEY (
			p_seq
		);

ALTER TABLE POST
	ADD
		CONSTRAINT FK_FILE_ATTR_TO_POST
		FOREIGN KEY (
			p_atch_file_id
		)
		REFERENCES FILE_ATTR (
			atch_file_id
		)
		ON DELETE CASCADE;

ALTER TABLE POST
	ADD
		CONSTRAINT FK_MEMBER_TO_POST
		FOREIGN KEY (
			m_id
		)
		REFERENCES MEMBER (
			m_id
		)ON DELETE CASCADE;