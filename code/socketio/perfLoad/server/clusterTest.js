/**
 * 官方档案的例子
 */
const cluster = require('cluster');
const http = require('http');
const numCPUs = require('os').cpus().length;

// 初次运行是true
if (cluster.isMaster) {
  console.log(`Master ${process.pid} is running`);

  // Fork workers.
  for (let i = 0; i < numCPUs; i++) {
    cluster.fork(); // 会把同样的程序放到其他的thread再运行一遍
  }

  cluster.on('exit', (worker, code, signal) => {
    console.log(`worker ${worker.process.pid} died`);
  });
} else {
  // Workers can share any TCP connection
  // In this case it is an HTTP server
  http.createServer((req, res) => {
    res.writeHead(200);
    res.end('hello world\n');
    console.log(`Worker ${process.pid} has been called!`);
  }).listen(8000);

  console.log(`Worker ${process.pid} started`);
}