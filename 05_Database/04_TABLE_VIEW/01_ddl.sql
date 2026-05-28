-- DDL (Data Definition Language)
-- 데이터베이스 구조를 정의하는 언어

DROP DATABASE IF EXISTS tabledb; -- 삭제
CREATE DATABASE tabledb; -- 생성

use tabledb;

drop table usertbl;
create table usertbl
(
    userID    char(8)     not null
        primary key,
    name      varchar(10) not null,
    birthYear int         not null,
    addr      char(2)     not null,
    mobile1   char(3)     null,
    mobile2   char(8)     null,
    height    smallint    null check ( height < 500 ),
    mDate     date        default (now())
);

# DROP TABLE IF EXISTS usertbl;

create table buytbl
(
    num       int auto_increment primary key,
    userID    char(8)  not null,
    prodName  char(6)  not null,
    groupName char(4)  null,
    price     int      not null,
    amount    smallint not null,

-- FOREIGN KEY (현재 테이블컬럼명)
    -- REFERENCES 참조할 테이블 명 ( 참조할 컬럼명 )
#     constraint buytbl_ibfk_1
#         foreign key (userID) references usertbl (userID)
);

-- 테이블 구조 변경
ALTER TABLE buytbl
    ADD CONSTRAINT fK_usertbl_buytbl
        foreign key(userID) -- 외래키로 설정할 컬럼
        references usertbl(userID); -- 참조할 부모테이블(컬럼)