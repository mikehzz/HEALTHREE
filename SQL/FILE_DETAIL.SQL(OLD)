DROP INDEX PK_FILE_DETAIL;

/* 파일상세 */
DROP TABLE FILE_DETAIL 
	CASCADE CONSTRAINTS;

/* 파일상세 */
CREATE TABLE FILE_DETAIL (
	atch_file_id VARCHAR2(30 BYTE) NOT NULL, /* 첨부파일ID */
	file_sn NUMBER(5) NOT NULL, /* 파일순번 */
	file_stre_path VARCHAR2(500 BYTE) NOT NULL, /* 파일저장경로 */
	org_file_nm VARCHAR2(500 CHAR) NOT NULL, /* 원본파일명 */
	stre_file_nm VARCHAR2(40 BYTE) NOT NULL, /* 저장파일명 */
	file_ext VARCHAR2(20 BYTE), /* 파일확장자 */
	file_size NUMBER(8) /* 파일크기 */
);

COMMENT ON TABLE FILE_DETAIL IS '파일상세';

COMMENT ON COLUMN FILE_DETAIL.atch_file_id IS '첨부파일ID';

COMMENT ON COLUMN FILE_DETAIL.file_sn IS '파일순번';

COMMENT ON COLUMN FILE_DETAIL.file_stre_path IS '파일저장경로';

COMMENT ON COLUMN FILE_DETAIL.org_file_nm IS '원본파일명';

COMMENT ON COLUMN FILE_DETAIL.stre_file_nm IS '저장파일명';

COMMENT ON COLUMN FILE_DETAIL.file_ext IS '파일확장자';

COMMENT ON COLUMN FILE_DETAIL.file_size IS '파일크기';

CREATE UNIQUE INDEX PK_FILE_DETAIL
	ON FILE_DETAIL (
		atch_file_id ASC,
		file_sn ASC
	);

ALTER TABLE FILE_DETAIL
	ADD
		CONSTRAINT PK_FILE_DETAIL
		PRIMARY KEY (
			atch_file_id,
			file_sn
		);

ALTER TABLE FILE_DETAIL
	ADD
		CONSTRAINT FK_FILE_ATTR_TO_FILE_DETAIL
		FOREIGN KEY (
			atch_file_id
		)
		REFERENCES FILE_ATTR (
			atch_file_id
		)
		ON DELETE CASCADE
		;