use employees;

/*
ROUND(number, decimals)
- 숫자를 지정한 소숫점 자리수로 반올림
- decimals이 0이면 정수로 반올림
 */

 -- 직원별 평균 급여 반올림해서 정수 출력
select
    emp_no,
    avg(salary) as '원본 평균 급여',
    round(avg(salary)) as '반올림 평균 급여', # 정수로 반올림
    round(avg(salary), 2) as '소수 둘째자리 반올림 평균 급여',
    FLOOR(avg(salary)) as '내림',
    CEIL(avg(salary)) as '올림'
from salaries
group by emp_no
limit 5;

/*
FORMAT(number, decimals)
- 숫자 천 단위 콤마 추가 및 소수점 자리수 지정 출력
- 문자열으로 반환됨
 */

select
    emp_no,
    salary,
    format(salary, 0) as 급여_포맷
from salaries
where to_date = '9999-01-01'
limit 5;