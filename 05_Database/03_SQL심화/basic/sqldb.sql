# sqldb 데이터베이스에서 다음 조건을 처리하세요
-- 1. 사용자별로 구매 이력을 출력함
-- 2. 모든 컬럼을 출력함
-- 3. 구매이력이 없는 정보는 출력하지 않음

-- Answer Query
use sqldb;
select *
from usertbl u
inner join buytbl b on u.userID = b.userID;

# 앞의 결과에서 userID가 'JYP'인 데이터만 출력하세요.

-- Answer Query
select *
from usertbl u
inner join buytbl b on u.userID = b.userID
where u.userID = 'JYP';

# sqldb 데이터베이스에서 다음 조건을 처리하세요.
-- 1. 각 사용자별로 구매 이력을 출력하세요.
-- 2. 연결 컬럼은 userID로 함
-- 3. 결과를 userID를 기준으로 오름차순으로 정렬함
-- 4. 구매이력이 없는 사용자도 출력하세요.
-- 5. userID, name, prodName, addr, 연락처를 다음과 같이 출력함

-- Answer Query
select u.userID, u.name, b.prodName, u.addr, concat(u.mobile1, u.mobile2) as 연락처
from usertbl u left outer join buytbl b
on u.userID = b.userID
ORDER BY u.userID;


# sqldb의 사용자를 모두 조회하되 전화가 없는 사람은 제외하고 출력하세요.

-- Answer Query
select
    name,
    concat(mobile1, mobile2) as '전화번호'
from usertbl
where name not in (select name from usertbl where mobile1 IS NULL);


# sqldb의 사용자를 모두 조회하되 전화가 없는 사람만 출력하세요.

-- Answer Query
select
    name,
    concat(mobile1, mobile2) as '전화번호'
from usertbl
where name in (select name from usertbl where mobile1 IS NULL);