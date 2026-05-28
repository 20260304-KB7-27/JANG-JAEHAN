/*
트랜잭션(Transaction)
- 데이터베이스 작업을 하나의 논리적인 작업 단위로 묶은 것
 */

select @@autocommit; -- 현재 세션의 자동 커밋 상태
set @@autocommit = false; -- autocommit 비활성화

-- 트랜잭션 시작
start transaction;

-- 동작하는 query들은 하나의 작업 단위로 묶임
use sqldb;

delete from buytbl where num = 1;
delete from buytbl where num = 2;

select * from buytbl;
-- 현재 트랜잭션 세션 안에서는 삭제된 것으로 보이나 실제 DB에는 반영되지 않음

rollback; --  transaction 시작 이전 가장 최근의 commit 시점으로 되돌림

commit; -- commit을 했을 때 DB에 반영됨

-- autocommit 모드 원래대로 돌려놓기
set @@autocommit = TRUE;