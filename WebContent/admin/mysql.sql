show databases;

-- 회원가입
CREATE TABLE MEMBER(
	id VARCHAR(30) PRIMARY KEY,
	password VARCHAR(100) NOT NULL,
	quiz VARCHAR(30) NOT NULL,
	name VARCHAR(10) NOT NULL,
	birthday VARCHAR(30),
	postcode VARCHAR(30),
	tel VARCHAR(20),
	phone VARCHAR(30),
	email VARCHAR(100),
	level INT(10) DEFAULT 0,
	address VARCHAR(100),
	regdate DATETIME  NOT NULL
) default char set utf8;

DESC MEMBER;
SELECT * FROM MEMBER;
DELETE FROM MEMBER;
DROP TABLE MEMBER;

-- 공지사항 게시판
CREATE TABLE NOTICE_BOARD(
	number INT NOT NULL PRIMARY KEY,
	id VARCHAR(30) NOT NULL,
	name VARCHAR(10) NOT NULL,
	category VARCHAR(30),
	subject VARCHAR(100),
	content TEXT,
	file VARCHAR(100),
	reply_reference INT,
	reply_depth INT DEFAULT 0,
	reply_sequence INT DEFAULT 0,
	address VARCHAR(30),
	count INT DEFAULT 0,
	regdate DATETIME NOT NULL
) default char set utf8;

DESC NOTICE_BOARD;
SELECT * FROM NOTICE_BOARD;
DELETE FROM NOTICE_BOARD;
DROP TABLE NOTICE_BOARD;

UPDATE NOTICE_BOARD SET COUNT = '0';

SELECT * FROM NOTICE_BOARD ORDER BY number DESC limit 20, 10

-- 일반 게시판
CREATE TABLE BASIC_BOARD(
	number INT NOT NULL PRIMARY KEY, -- number INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id VARCHAR(30) NOT NULL,
	name VARCHAR(10) NOT NULL,
	category VARCHAR(30),
	subject VARCHAR(100),
	content TEXT,
	file VARCHAR(100),
	reply_reference INT,
	reply_depth INT DEFAULT 0,
	reply_sequence INT DEFAULT 0,
	address VARCHAR(30),
	count INT DEFAULT 0,
	regdate DATETIME NOT NULL
) default char set utf8;

DESC BASIC_BOARD;
SELECT * FROM BASIC_BOARD;
DELETE FROM BASIC_BOARD;
DROP TABLE BASIC_BOARD;

ALTER TABLE BASIC_BOARD ADD uri VARCHAR(30); -- uri column 추가
ALTER TABLE BASIC_BOARD ADD uri VARCHAR(30) AFTER file; -- file column 뒤에 uri column 추가
ALTER TABLE BASIC_BOARD ADD thumbnail VARCHAR(100) AFTER uri; -- uri column 뒤에 thumbnail column 추가

-- 일반 게시판 특정 게시글 조회수 수정
-- UPDATE basic_board SET count = count + 1 WHERE number = 9999;

-- 일반 게시판 목록 게시글 노출 개수
-- SELECT * FROM basic_board ORDER BY number DESC limit 0, 10;

-- 댓글 게시판
CREATE TABLE COMMENT_BOARD(
	board INT NOT NULL,	
	number INT NOT NULL,
	id VARCHAR(30),
	name VARCHAR(10),
	content TEXT,
	reference INT, -- reparent 부모
	sequence INT, -- reorder 순서
	level INT, -- redepth 깊이
	together INT,
	regdate DATETIME NOT NULL
) default char set utf8;

DESC COMMENT_BOARD;
SELECT * FROM COMMENT_BOARD ORDER BY together ASC, sequence ASC;
DELETE FROM COMMENT_BOARD;
DROP TABLE COMMENT_BOARD;

-- 이미지 게시판
CREATE TABLE GALLERY_BOARD(
	number INT NOT NULL PRIMARY KEY,
	id VARCHAR(30) NOT NULL,
	name VARCHAR(10) NOT NULL,
	category VARCHAR(30),
	subject VARCHAR(100),
	content TEXT,
	address VARCHAR(30),
	count INT DEFAULT 0,
	regdate DATETIME NOT NULL,
	image VARCHAR(100) DEFAULT 'default.png'
) default char set utf8;

DESC GALLERY_BOARD;
SELECT * FROM GALLERY_BOARD;
DELETE FROM GALLERY_BOARD;
DROP TABLE GALLERY_BOARD;

-- 다운로드 게시판
CREATE TABLE DOWNLOAD_BOARD(
	number INT NOT NULL PRIMARY KEY,
	id VARCHAR(30) NOT NULL,
	name VARCHAR(10) NOT NULL,
	category VARCHAR(30),
	subject VARCHAR(100),
	content TEXT,
	file VARCHAR(100),
	reply_reference INT,
	reply_depth INT DEFAULT 0,
	reply_sequence INT DEFAULT 0,
	address VARCHAR(30),
	count INT DEFAULT 0,
	regdate DATETIME NOT NULL
) default char set utf8;

DESC DOWNLOAD_BOARD;
SELECT * FROM DOWNLOAD_BOARD;
DELETE FROM DOWNLOAD_BOARD;
DROP TABLE DOWNLOAD_BOARD;

-- SELECT * FROM basic_board WHERE subject LIKE '%테스트%';

-- 메인 이미지 슬라이드 게시판
CREATE TABLE IMAGE_SLIDE_BOARD(
	number INT DEFAULT 0,
	file VARCHAR(100),
	subject VARCHAR(100),
	content TEXT,
	regdate DATETIME NOT NULL
) default char set utf8;

DESC IMAGE_SLIDE_BOARD;
SELECT * FROM IMAGE_SLIDE_BOARD;
DELETE FROM IMAGE_SLIDE_BOARD;
DROP TABLE IMAGE_SLIDE_BOARD;

-- SELECT number FROM IMAGE_SLIDE_BOARD WHERE number = 1;
-- SELECT id FROM BASIC_BOARD WHERE number = 1;