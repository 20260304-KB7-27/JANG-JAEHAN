/*
 SELECT 컬럼, ...
 FROM 테이블A
 INNER JOIN 테이블B ON 테이블A.컬럼 = 테이블B.컬럼;

 INNER JOIN
 - ON 조건에 매칭되는 행기리 결합
 - 가장 일반적으로 사용되는 JOIN ( INNER JOIN = JOIN )
 */

use employees;

-- 직원 정보
select emp_no, first_name, last_name
from employees
limit 5;

select emp_no, dept_no, from_date, to_date
from dept_emp
limit 5;

select e.emp_no, e.first_name, e.last_name,
       d.dept_no, d.from_date, d.to_date
from employees e
inner join dept_emp d on e.emp_no = d.emp_no
limit 10;

-- dept_emp 와 departments JOIN
select
    de.emp_no,
    d.dept_no,
    d.dept_name
from dept_emp de JOIN departments d on de.dept_no = d.dept_no
limit 10;

select e.emp_no, e.first_name, e.last_name,
       de.dept_name, d.from_date, d.to_date
from employees e
         inner join dept_emp d on e.emp_no = d.emp_no
         join departments de on  de.dept_no = d.dept_no
where d.to_date = '9999-01-01'
limit 10;
