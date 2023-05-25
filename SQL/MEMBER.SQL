DROP INDEX PK_MEMBER;

/* 회원 */
DROP TABLE MEMBER 
	CASCADE CONSTRAINTS;

/* 회원 */
CREATE TABLE MEMBER (
	m_id VARCHAR2(20) NOT NULL, /* 회원아이디 */
	m_pw VARCHAR2(20) NOT NULL, /* 비밀번호 */
	m_name VARCHAR2(20) NOT NULL, /* 이름 */
	m_email VARCHAR2(50) NOT NULL, /* 이메일 */
	m_gender CHAR(1) NOT NULL, /* 성별 */
	m_birthday DATE NOT NULL, /* 생년월일 */
	m_height NUMBER(4, 1) NOT NULL, /* 신장 */
	m_weight NUMBER(4, 1) NOT NULL, /* 현재 체중 */
	m_target_weight NUMBER(4, 1) NOT NULL, /* 목표 체중 */
	m_act_lv NUMBER(1) NOT NULL, /* 활동 레벨 */
	m_diet_goal NUMBER(1) NOT NULL /* 다이어트 목표 */
);

COMMENT ON TABLE MEMBER IS '회원';

COMMENT ON COLUMN MEMBER.m_id IS '회원아이디';

COMMENT ON COLUMN MEMBER.m_pw IS '비밀번호';

COMMENT ON COLUMN MEMBER.m_name IS '이름';

COMMENT ON COLUMN MEMBER.m_email IS '이메일';

COMMENT ON COLUMN MEMBER.m_gender IS '성별';

COMMENT ON COLUMN MEMBER.m_birthday IS '생년월일';

COMMENT ON COLUMN MEMBER.m_height IS '신장';

COMMENT ON COLUMN MEMBER.m_weight IS '현재 체중';

COMMENT ON COLUMN MEMBER.m_target_weight IS '목표 체중';

COMMENT ON COLUMN MEMBER.m_act_lv IS '활동 레벨';

COMMENT ON COLUMN MEMBER.m_diet_goal IS '다이어트 목표';

CREATE UNIQUE INDEX PK_MEMBER
	ON MEMBER (
		m_id ASC
	);

ALTER TABLE MEMBER
	ADD
		CONSTRAINT PK_MEMBER
		PRIMARY KEY (
			m_id
		);