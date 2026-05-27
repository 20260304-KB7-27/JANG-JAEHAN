use employees;

select *
from employees
where emp_no in (10001,10002,10003,10004,10005,10006);

-- 현재 d005 부서에 재직중인 직원들의 상세정보
-- IN 안에 서브쿼리 작성
select
    e.emp_no,
    e.first_name,
    e.last_name,
    e.gender
from employees e
where emp_no in (
    select emp_no
    from dept_emp
    where dept_no = 'd005'
    and to_date = '9999-01-01'
    )
limit 5;


-- NOT IN - 서브 쿼리로 없는 것 찾기

-- 관리자였던 적이 없는 직원의 수

select count(*)
from employees e
where emp_no not in (
    select emp_no
    from dept_manager # 관리자를 했던 직원들의 emp_no
    );

-- NOT IN의 NULL 함정
-- != AND 비교로 동작하는데 NULL이랑 비교하게 되면 TRUE/FALSE가 아닌 UNKNOWN이 나옴.
-- ㄴ> 모든 결과가 전부 제외되는 문제가 발생할 수 있음

use sqldb;

-- 우리가 기대하는 거
-- 010, 016, 011이 아닌 나머지 user 데이터
select *
from usertbl
where mobile1 not in (
    select mobile1
    from usertbl
    where addr = '서울' and mobile1 is not null -- null 제외
    );

