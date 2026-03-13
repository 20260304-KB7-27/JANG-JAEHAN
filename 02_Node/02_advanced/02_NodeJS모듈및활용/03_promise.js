const fs = require('fs').promises;
// const fs = require('fs/promises');

// Promise 방식
fs.readdir('./')
  .then((files) => {
    console.log(files);
  })
  .catch((err) => {
    console.error(err);
  });
