// use tutorial_v2;

// 기존 컬렉션 초기화
db.members.drop();

/*
* shop DB - member 컬렉션 데이터 세팅
*
* 필드 구성
*   name   : 이름
*   age    : 나이
*   office : 근무지
*   phone  : 전화번호
* */
db.members.insertMany([
    { name: "Baby",    age: 1,  office: "busan",   phone: "010-0001-0001" },
    { name: "Grace",   age: 5,  office: "seoul",   phone: "010-1111-1111" },
    { name: "George",  age: 8,  office: "busan",   phone: "010-2222-2222" },
    { name: "Alice",   age: 12, office: "daejeon", phone: "010-3333-3333" },
    { name: "Bob",     age: 18, office: "seoul",   phone: "010-4444-4444" },
    { name: "Charlie", age: 22, office: "busan",   phone: "010-5555-5555" },
    { name: "Diana",   age: 28, office: "seoul",   phone: "010-6666-6666" },
    { name: "Eve",     age: 30, office: "daejeon", phone: "010-7777-7777" },
    { name: "Frank",   age: 38, office: "busan",   phone: "010-8888-8888" },
    { name: "Gloria",  age: 45, office: "seoul",   phone: "010-9999-9999" },
    { name: "Henry",   age: 55, office: "busan",   phone: "010-1010-1010" },
    { name: "Irene",   age: 62, office: "seoul",   phone: "010-1212-1212" },
]);