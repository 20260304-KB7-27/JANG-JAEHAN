use tabledb;

create table userTBL (
    userID char(8) not null,
    name varchar(10) not null,
    birthYear int not null,

    -- CONSTRAINT : 제약조건을 명시적으로 이름을 붙여 정의
    CONSTRAINT PRIMARY KEY PK_usertbl(userID)
);

create table prodTBL (
                         prodCode char(8) not null,
                         prodID varchar(10) not null,
                         prodDate int not null,
                         prodCur int not null,

    -- 복합키 : 여러 컬럼을 묶어서 하나의 키로 사용하는 것
    -- 복합키는 컬럼 단위에서 정의할 수 없음.
                         CONSTRAINT PRIMARY KEY PK_prodCode_prodID(prodCode, prodID)
);