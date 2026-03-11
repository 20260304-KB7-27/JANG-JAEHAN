// document 객체는 웹 페이지 최상위 DOM 요소
console.log(document);

// HTML Collection
// document에서 getElements~ 로 찾아온 요소들의 묶음
// 유사 배열 (배열 메소드 사용 불가)
let li = document.getElementsByTagName('li');
console.log(li);

// $ : 관례적으로 요소 노드를 선택할 때 사용
const $h1 = document.getElementById('header');

$h1.style.color = 'red';
