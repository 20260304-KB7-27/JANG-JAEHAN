use tabledb;

insert into
    usertbl
values
    ('LSG','이승기',1987,'서울','011','11111111',182,'2008-02-21');

-- default 제약조건 확인
insert into
    usertbl
values
    ('KKH','김경호',1987,'서울','011','11111111',182,default);

-- CHECK 제약조건 확인 (height < 500)
insert into
    usertbl
values
    ('NBJ','나비족',1987,'서울','011','11111111',600,default);



-- buytbl
insert into buytbl
values (NULL, 'LSG', '운동화', NULL, 30, 2);

-- 참조무결성 : 외래키가 참조하는 데이터는 반드시 실제로 존재해야함
insert into
    buytbl
values
    (null,'NBJ','운동화', NULL, 30, 2);

delete
from buytbl
where prodName = '운동화';

delete from usertbl where userID = 'LSG';

DROP TABLE IF EXISTS buytbl;
DROP TABLE IF EXISTS usertbl;





SELECT * FROM buytbl;
SELECT * FROM usertbl;









