use employees;

/*
 VIEW
 - 실제 데이터를 저장하지 않고
 - 하나 이상의 테이블을 조회하는 SELECT문을 가상의 테이블로 사용하는 객체

 - 원본이 변경되면 반영이 되어 보인다.
 - INSERT, UPDATE, DELETE가 가능하지만 원본 자체가 왜곡될 가능성이 높음 (안하는 걸 권장)

 장점
 - 지주 사용하는 SELECT 쿼리를 재사용할 수 있게 해줌.
 - 보안, 편의성, 유지보수성 향상
 */

-- 참조하고 있는 테이블이 사라지만 뷰가 깨질 수 있으므로 뷰를 먼저 삭제할 것
create or replace view v_employees
as
select emp_no, concat(first_name, ' ', last_name)
from employees;

select * from v_employees where emp_no = '10002';
