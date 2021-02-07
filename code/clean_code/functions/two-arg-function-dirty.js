// bad
function log(message, isError) {
  if (isError) {
    console.error(message);
  } else {
    console.log(message);
  }
}

log("Hi there!", false);

// good
function log(message) {
  console.log(message);
}

function logError(errorMessage) {
  console.error(errorMessage);
}
// 只使用一个参数, 更好理解
log("Hi there!");
logError("An error!");
