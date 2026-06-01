# DB 생성
drop database if exists scoula_db;
CREATE DATABASE scoula_db;

# 유저생성
drop user if exists 'scoula'@'%';
CREATE USER 'scoula'@'%' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON jdbc_ex.* TO 'scoula'@'%';
FLUSH PRIVILEGES;

# 유저확인
SELECT * FROM mysql.user;

# table 정의
USE scoula_db;
CREATE TABLE usertbl (
                         id VARCHAR(12) NOT NULL PRIMARY KEY,
                         password VARCHAR(12) NOT NULL,
                         name VARCHAR(30) NOT NULL,
                         role VARCHAR(6) NOT NULL
);

# 데이터 추가
INSERT INTO usertbl(id, password, name, role)
VALUES('guest', 'guest123', '방문자', 'USER');

INSERT INTO usertbl(id, password, name, role)
VALUES('admin', 'admin123', '관리자', 'ADMIN');

INSERT INTO usertbl(id, password, name, role)
VALUES ('member', 'member123', '일반회원', 'USER');

# 결과확인
SELECT * FROM usertbl;