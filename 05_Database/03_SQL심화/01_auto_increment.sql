use test_db1;

/*
AUTO INCREMENT
- 행이 INSERT 될 때마다 자동으로 1씩 증가하는 고유번호 생성
- 적용하기 위해서는 PK, UNIQUE INDEX 이어야함.
 */

 create table board (
     board_no int not null auto_increment,
     title varchar(100) not null,
     content text,
     author varchar(50) not null,
     created_at datetime default now(),
     primary key (board_no)
 );

insert into board (title, content, author)
values ('첫번째 글', '안녕하세요 첫번째 게시글 입니다.', '홍길동');

insert into board (title, content, author)
values ('두번째 글', '안녕하세요 두번째 게시글 입니다.', '홍길동');

insert into board (title, content, author)
values ('세번째 글', '안녕하세요 세번째 게시글 입니다.', '홍길동');

-- null 또는 0을 넣어도 auto_increment가 동작한다.

insert into board
values (null, '네번째 글', '안녕하세요 네번째 게시글 입니다.','홍길동',now());

insert into board
values (0, '다섯번째 글', '안녕하세요 다섯번째 게시글 입니다.','홍길동',now());

insert into board
values (25, '여섯번째 글', '안녕하세요 여섯번째 게시글 입니다.','홍길동',now());

# insert into board
# values (1, '일곱번째 글', '안녕하세요 일곱번째 게시글 입니다.','홍길동',now());

-- auto_increment 시작값 변경
alter table board auto_increment = 100;

insert into board
values (null, '여덟번째 글', '안녕하세요 여덟번째 게시글 입니다.','홍길동',now());

-- last_insert_id()
-- 현재 세션에서 마지막으로 insert된 auto_increment 값을 반환
select last_insert_id();

select * from board;