DROP INDEX PK_MEAL;

/* 식사 */
DROP TABLE MEAL 
	CASCADE CONSTRAINTS;

/* 식사 */
CREATE TABLE MEAL (
	m_id VARCHAR2(20) NOT NULL, /* 회원아이디 */
	m_div CHAR(1) NOT NULL, /* 식사구분 */
	m_date CHAR(8 BYTE) NOT NULL, /* 날짜 */
	m_seq NUMBER(2) NOT NULL, /* 입력순서 */
	f_code CHAR(7) NOT NULL /* 식품코드 */
);

COMMENT ON TABLE MEAL IS '식사';

COMMENT ON COLUMN MEAL.m_id IS '회원아이디';

COMMENT ON COLUMN MEAL.m_div IS '식사구분';

COMMENT ON COLUMN MEAL.m_date IS '날짜';

COMMENT ON COLUMN MEAL.m_seq IS '입력순서';

COMMENT ON COLUMN MEAL.f_code IS '식품코드';

CREATE UNIQUE INDEX PK_MEAL
	ON MEAL (
		m_id ASC,
		m_div ASC,
		m_date ASC,
		m_seq ASC
	);

ALTER TABLE MEAL
	ADD
		CONSTRAINT PK_MEAL
		PRIMARY KEY (
			m_id,
			m_div,
			m_date,
			m_seq
		);

ALTER TABLE MEAL
	ADD
		CONSTRAINT FK_FOOD_TO_MEAL
		FOREIGN KEY (
			f_code
		)
		REFERENCES FOOD (
			f_code
		);

ALTER TABLE MEAL
	ADD
		CONSTRAINT FK_MEAL_DIARY_TO_MEAL
		FOREIGN KEY (
			m_date,
			m_id
		)
		REFERENCES MEAL_DIARY (
			m_date,
			m_id
		);