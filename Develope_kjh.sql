-- SYS 관리자 계쩡
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- 사용자 계정 생성
CREATE USER Develope_kjh	IDENTIFIED BY kjh1234;

-- 생성한 사용자 계정에 권한 부여
GRANT CONNECT, RESOURCE, CREATE VIEW TO Develope_kjh;

-- 테이블 스페이스 할당
ALTER USER Develope_kjh DEFAULT
TABLESPACE SYSTEM QUOTA UNLIMITED ON SYSTEM;

-------------------------------------------------------
-- 회원 테이블
CREATE TABLE "MEMBER"(
	MEMBER_NO NUMBER PRIMARY KEY,
	MEMBER_ID VARCHAR2(30) NOT NULL,
	MEMBER_PW VARCHAR2(30) NOT NULL,
	MEMBER_NAME VARCHAR2(30) NOT NULL,
	MEMBER_GENDER CHAR(1) CHECK(MEMBER_GENDER IN('M','F')),
	ENROLL_DATE DATE DEFAULT SYSDATE,
	SECESSION_FL CHAR(1) DEFAULT 'N' CHECK(SECESSION_FL IN('Y','N'))
);


-- 회원 번호 시퀀스 생성
CREATE SEQUENCE SEQ_MEMBER_NO
START WITH 1 
INCREMENT BY 1
NOCYCLE
NOCACHE;

-- 기본 계정 생성
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user01', 'pass01', '유저일'
		,'M', DEFAULT, DEFAULT);
	
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user02', 'pass02', '유저이'
		,'F', DEFAULT, DEFAULT);
	
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user03', 'pass03', '유저삼'
		,'F', DEFAULT, DEFAULT);
		
COMMIT;

-- 로그인
SELECT MEMBER_NO, MEMBER_ID, MEMBER_NAME, MEMBER_GENDER, 
	 TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월"  DD"일" HH24:MI:SS') ENROLL_DATE
FROM "MEMBER"
WHERE MEMBER_ID = 'user01'
AND MEMBER_PW = 'pass01'
AND SECESSION_FL ='N';

SELECT * FROM MEMBER;

DROP TABLE "MEMBER" ;

COMMIT;


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
