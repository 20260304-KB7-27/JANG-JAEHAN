const path = require('path');
// import path from 'path'; //ESM 방식

// __filename -> 사용불가 (ESM 방식에서는)

/*
path 모듈
- 파일 경로나 디렉토리 경로를 다루는 모듈
- 운영체제간의 경로를 구분하는 구분자가 다른데 -> 통일 가능
*/

// import { fileURLToPath } from 'url';

// const dir = fileURLToPath(import.meta.url);
// console.log(dir);

// const fullPath = path.join('some', 'work', 'ex.txt');

// console.log(fullPath);

console.log('파일 절대 경로: ', __filename);

/* 
- 절대경로 : 루트 디렉터리 부터 시작하는 경로
- 상대경로 : 현재 작업 디렉터리 기준 경로
*/

const dir = path.dirname(__filename);
console.log('경로만: ', dir);

// 현재 작업 디렉토리
// code runner로 실행하면 프로세스가 실행되는 jang-jaehan 디렉토리까지의 경로
// 명령어로 node 02_path.js로 실행하면 현재 디렉토리가 나옴 ~~/01_path
console.log(process.cwd());
