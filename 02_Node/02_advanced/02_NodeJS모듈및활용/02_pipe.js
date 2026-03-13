const fs = require('fs');
const path = require('path');

const filePath = path.join(__dirname, 'readMe.txt');
const savePath = path.join(__dirname, 'writeMe.txt');

const rs = fs.createReadStream(filePath, 'utf-8');
const ws = fs.createWriteStream(savePath, 'utf-8');

// 파이프(pipe)
// - 스트림을 연결하는 스트림
// - data 이벤트가 발생했을 때 데이터를 읽어오고 쓰는 것을 한 번에 처리 가능

rs.pipe(ws);
