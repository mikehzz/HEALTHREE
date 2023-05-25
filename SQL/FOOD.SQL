DROP INDEX PK_FOOD;

/* 식품 */
DROP TABLE FOOD 
	CASCADE CONSTRAINTS;

/* 식품 */
CREATE TABLE FOOD (
	f_code CHAR(7) NOT NULL, /* 식품코드 */
	f_name VARCHAR2(50) NOT NULL, /* 식품명 */
	f_size NUMBER(4) NOT NULL, /* 총내용량 */
	f_cal NUMBER(6, 2) NOT NULL, /* 칼로리 */
	f_carbo NUMBER(6, 2) NOT NULL, /* 탄수화물 */
	f_protein NUMBER(6, 2) NOT NULL, /* 단백질 */
	f_fat NUMBER(6, 2) NOT NULL, /* 지방 */
	f_sugar NUMBER(6, 2) NOT NULL, /* 당류 */
	f_na NUMBER(6, 2) NOT NULL, /* 나트륨 */
	f_cole NUMBER(6, 2) NOT NULL /* 콜레스테롤 */
);

COMMENT ON TABLE FOOD IS '식품';

COMMENT ON COLUMN FOOD.f_code IS '식품코드';

COMMENT ON COLUMN FOOD.f_name IS '식품명';

COMMENT ON COLUMN FOOD.f_size IS '총내용량';

COMMENT ON COLUMN FOOD.f_cal IS '칼로리';

COMMENT ON COLUMN FOOD.f_carbo IS '탄수화물';

COMMENT ON COLUMN FOOD.f_protein IS '단백질';

COMMENT ON COLUMN FOOD.f_fat IS '지방';

COMMENT ON COLUMN FOOD.f_sugar IS '당류';

COMMENT ON COLUMN FOOD.f_na IS '나트륨';

COMMENT ON COLUMN FOOD.f_cole IS '콜레스테롤';

CREATE UNIQUE INDEX PK_FOOD
	ON FOOD (
		f_code ASC
	);

ALTER TABLE FOOD
	ADD
		CONSTRAINT PK_FOOD
		PRIMARY KEY (
			f_code
		);