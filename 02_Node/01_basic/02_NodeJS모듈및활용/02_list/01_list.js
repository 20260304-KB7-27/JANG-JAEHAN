const fs = require('fs'); // fs 모듈 가져오기

// 동기 방식

// "./" : 현재 위치 기반
// code runner로 실행하면 프로세스가 실행되는 (working directory) jang-jaehan 디렉토리까지의 경로
// 명령어로 node 02_path.js로 실행하면 현재 디렉토리가 나옴 ~~/02_list

// let files = fs.readdirSync('./');
// console.log(files);

// 콜백 방식
// fs.readdir('./', (err, files) => {
//   if (err) {
//     console.error(err);
//     return;
//   }
//   console.log(files);
// });

// Promise 방식
fs.promises
  .readdir('./')
  .then((files) => {
    console.log(files);
  })
  .catch((err) => {
    console.error(err);
  });
