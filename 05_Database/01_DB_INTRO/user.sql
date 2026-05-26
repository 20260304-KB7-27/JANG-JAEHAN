-- 계정 만들기
create user 'jaehan'@'%' identified by "jaehan";

show tables;

# select user, user.host from user;

-- 권한 부여
-- employees 데이터베이스에 대한 모든 권한 부여
grant all privileges on employees.* to 'jaehan'@'%';

show grants for 'jaehan'@'%';
