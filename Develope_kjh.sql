-- SYS 관리자 계쩡
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- 사용자 계정 생성
CREATE USER Develope_kjh	IDENTIFIED BY kjh1234;

-- 생성한 사용자 계정에 권한 부여
GRANT CONNECT, RESOURCE, CREATE VIEW TO Develope_kjh;

-- 테이블 스페이스 할당
ALTER USER Develope_kjh DEFAULT
TABLESPACE USERS QUOTA UNLIMITED ON USERS;
COMMIT;
-------------------------------------------------------
-- 회원 테이블
CREATE TABLE "MEMBER"(
	MEMBER_NO NUMBER PRIMARY KEY,
	MEMBER_ID VARCHAR2(30) NOT NULL,
	MEMBER_PW VARCHAR2(30) NOT NULL,
	MEMBER_NAME VARCHAR2(30) NOT NULL,
	MEMBER_GENDER CHAR(1) CHECK(MEMBER_GENDER IN('M','F')),
	PHONE VARCHAR2(30) NOT NULL,
	EMAIL VARCHAR2 (30) NOT NULL,
	ENROLL_DATE DATE DEFAULT SYSDATE,
	SECESSION_FL CHAR(1) DEFAULT 'N' CHECK(SECESSION_FL IN('Y','N'))
);
SELECT * FROM "MEMBER";
DROP TABLE "MEMBER";
COMMIT;
DELETE FROM "MEMBER" WHERE MEMBER_ID = 'jhyeon310';
COMMIT;
-- 회원 번호 시퀀스 생성
CREATE SEQUENCE SEQ_MEMBER_NO
START WITH 1 
INCREMENT BY 1
NOCYCLE
NOCACHE;

DROP TABLE "MEMBER";
DROP SEQUENCE SEQ_MEMBER_NO;

-- 기본 계정 생성
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user01', 'pass01', '유저일'
		,'M','01012345678','a@naver.com', DEFAULT, DEFAULT);
	
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user02', 'pass02', '유저2'
		,'F','01011112222','b@naver.com', DEFAULT, DEFAULT);
	
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user03', 'pass03', '유저3'
		,'M','01022223333','c@naver.com', DEFAULT, DEFAULT);
		
COMMIT;

-- 로그인
SELECT MEMBER_NO, MEMBER_ID, MEMBER_NAME, MEMBER_GENDER, 
	 TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월"  DD"일" HH24:MI:SS') ENROLL_DATE
FROM "MEMBER"
WHERE MEMBER_ID = 'user01'
AND MEMBER_PW = 'pass01'
AND SECESSION_FL ='N';

-- 아이디 찾기(Eamil)
SELECT MEMBER_ID
FROM "MEMBER"
WHERE EMAIL = 'a@naver.com' AND MAMBER_NAME = '유저일';
-- 아이디 찾기(Phone)
SELECT MEMBER_ID
FROM "MEMBER"
WHERE PHONE = ?

-- 비밀번호 찾기(Email)
SELECT MEMBER_PW 
FROM "MEMBER" 
WHERE MEMBER_ID ='user01' 
AND MEMBER_NAME ='유저일' 
AND EMAIL ='a@naver.com';
-------------------------------------
SELECT * FROM MEMBER;

DROP TABLE "MEMBER" ;

COMMIT;

-- 멤버 - 비밀번호 변경
UPDATE "MEMBER" SET
MEMBER_PW =?
WHERE MEMBER_NO =?
AND MEMBER_PW =?
-- 멤버 - 회원탈퇴 - secession_FL -> Y로 변경
UPDATE "MEMBER" SET
SECESSION_FL = 'Y'
WHERE MEMBER_NO =?
AND MEMBER_PW = ?


-- 관리자 테이블
CREATE TABLE "MANAGER" (
	MANAGER_NO NUMBER PRIMARY KEY,
	MANAGER_ID VARCHAR2(30) NOT NULL,
	MANAGER_PW VARCHAR2(30) NOT NULL,
	MANAGER_NAME VARCHAR2(30) NOT NULL,
	MANAGER_GENDER CHAR(1) CHECK(MANAGER_GENDER IN('M','F')),
	ENROLL_DATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE SEQ_MANAGER_NO
START WITH 1 
INCREMENT BY 1
NOCYCLE
NOCACHE;

INSERT INTO "MANAGER"
	VALUES(SEQ_MANAGER_NO.NEXTVAL,'admin','admin12','김정현','M',
	DEFAULT);
	
SELECT * FROM MANAGER;

COMMIT;

SELECT *
FROM "MANAGER"
WHERE MANAGER_ID = 'admin'
AND MANAGER_PW = 'admin12';

CREATE TABLE "PRODUCT" (
	PRODUCT_NO NUMBER PRIMARY KEY,
	CATEGORY VARCHAR2(30) NOT NULL,
	PRODUCT_NAME VARCHAR2(30) NOT NULL,
	STOCK NUMBER,
	PRICE NUMBER 
);

CREATE SEQUENCE PRODUCT_NO
START WITH 1 
INCREMENT BY 1
NOCYCLE
NOCACHE;

DROP TABLE "PRODUCT";
ROLLBACK;

INSERT INTO "PRODUCT"
VALUES(PRODUCT_NO.NEXTVAL, '주방', '도마', 10, 10000);
INSERT INTO "PRODUCT"
VALUES(PRODUCT_NO.NEXTVAL, '주방', '칼', 10, 5000);

SELECT * FROM "PRODUCT";

COMMIT;
-- 상품 번호 + 제품 + 가격 테이블
CREATE TABLE PRODUCT_PRICE (
	PRODUCT_NO NUMBER PRIMARY KEY,
	PRODUCT_NAME VARCHAR2(30) NOT NULL,
	PRICE NUMBER NOT NULL
);
INSERT INTO PRODUCT_PRICE VALUES(1,'도마',15000);

SELECT * FROM "MEMBER" ;

-- 전체 제품 조회
SELECT PRODUCT_NO, CATEGORY, PRODUCT_NAME, STOCK, PRICE
FROM "PRODUCT";

-- 관리자@@@@@@@@@@
-- 제품 수정(재고 수, 가격)
UPDATE "PRODUCT" SET
STOCK = 10, PRICE = 10000
WHERE PRODUCT_NO = 1;

-- 제품 추가( 카테고리, 상품명, 재고 수, 가격)
INSERT INTO "PRODUCT"
VALUES(PRODUCT_NO.NEXTVAL, ?, ? ,?,?);


-- 사용자- > 구매리스트(테이블)
-- 구매리스트 테이블 생성
CREATE TABLE "BUYPRODUCT"(
	BUY_NO NUMBER PRIMARY KEY, -- 구매번호
	MEMBER_NO NUMBER,  -- 고객별 구별
	CATEGORY VARCHAR2(30) ,
	PRODUCT_NAME VARCHAR2(30),
	STOCK NUMBER,
	PRICE NUMBER,
	BUYDATE DATE DEFAULT SYSDATE,
	DELETE_NY CHAR(1) DEFAULT 'N' CHECK (DELETE_NY IN('Y','N'))
);
SELECT M.MEMBER_NO,BUY_NO, PRODUCT_NAME, STOCK, BUYDATE, DELETE_NY
FROM B."BUYPRODUCT"
JOIN M."MEMBER" USING(MEMBER_NO)
WHERE = M.MEMBER_NO;

SELECT * FROM "BUYPRODUCT";
SELECT BUY_

CREATE SEQUENCE SEQ_BUYPRODUCT_NO
START WITH 1 
INCREMENT BY 1
NOCYCLE
NOCACHE;

SELECT * FROM BUYPRODUCT 

-- 고객별 구매내역 조회
SELECT  BUY_NO, PRODUCT_NAME, STOCK, BUYDATE
FROM "BUYPRODUCT"
WHERE MEMBER_NO= 3 ;

-- 제품 구매하기 -> SELECT로??

INSERT INTO "BUYPRODUCT"  (BUY_NO, MEMBER_NO, PRODUCT_NAME, STOCK, BUYDATE,DELETE_NY)
	VALUES(SEQ_BUYPRODUCT_NO.NEXTVAL,2, 'a' , 1 ,DEFAULT, DEFAULT);

-- 구매한 제품 조회 (멤버번호 추가) - 고객별 주문내역 확인xxxx
SELECT PRODUCT_NO, PRODUCT_NAME, STOCK	
FROM "BUYPRODUCT" 
JOIN "MEMBER" USING(MEMBER_NO)
WHERE MEMBER_NO = ?;


-- 입력한 제품명과 일치하는 제품의 카테고리와 가격 출력 XXX
--SELECT CATEGORY, PRICE
--FROM "BUYPRODUCT"
--WHERE PRODUCT_NAME =  ?

-- 입력한 제품명과 개수를 BUYPRODUCT테이블에 입력  XXX
-- + 카테고리와 가격은 기본값으로


SELECT PRICE FROM PRODUCT  WHERE PRODUCT_NAME = '도마'
SELECT CATEGORY FROM PRODUCT WHERE PRODUCT_NAME = '도마'

-- 상품 구매시 product 테이블의 재고수 조정
UPDATE "PRODUCT" SET
STOCK = STOCK - ?
WHERE PRODUCT_NAME = ?;

SELECT * FROM PRODUCT ;
COMMIT;

SELECT * FROM BUYPRODUCT;
SELECT * FROM "MEMBER" ;
SELECT * FROM PRODUCT ;
DELETE FROM BUYPRODUCT ;
COMMIT;