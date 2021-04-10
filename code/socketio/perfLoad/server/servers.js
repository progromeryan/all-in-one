/**
 * Socket.io server that will service both node and react clients
 * entrypoint for our cluster which will make workers
 * and the workers will do the Socket.io handling
 */
const express = require('express');
/**
 *
 * A single instance of Node.js runs in a single thread
 * To take advantage of multi-core systems
 * the user will sometimes want to launch a cluster of Node.js processes to handle the load
 * cluster让多个thread同时运行Node.js, 但是每个thread仍然只运行一个Node.js, 所以依然是single thread
 */
const cluster = require('cluster');
const net = require('net');
const socketio = require('socket.io');
const socketMain = require('./socketMain');

const port = 8181;
const num_processes = require('os').cpus().length;

const io_redis = require('socket.io-redis');
const farmhash = require('farmhash');


if (cluster.isMaster) {
  // master 的运行
  let workers = [];

  // Helper function for spawning worker at index 'i'.
  let spawn = function (i) {
    workers[i] = cluster.fork();

    // Optional: Restart worker on exit
    workers[i].on('exit', function (code, signal) {
      spawn(i);
    });
  };

  // Spawn workers.
  for (let i = 0; i < num_processes; i++) {
    spawn(i);
  }

  // ip地址 -> hash
  const worker_index = function (ip, len) {
    return farmhash.fingerprint32(ip) % len; // Farmhash is the fastest and works with IPv6
  };

  const server = net.createServer(
    { pauseOnConnect: true },
    (connection) => {
      let worker = workers[worker_index(connection.remoteAddress, num_processes)];
      worker.send('sticky-session:connection', connection);
    });

  server.listen(port);
} else {
  // worker 的运行
  let app = express();

  const server = app.listen(0, 'localhost');
  const io = socketio(server);

  // 使用redis adapter
  io.adapter(io_redis({ host: 'localhost', port: 6379 }));

  io.on('connection', function (socket) {
    socketMain(io, socket);
  });

  process.on('message', function (message, connection) {
    // 如果消息不是来自于master则退出, 来自line 54
    if (message !== 'sticky-session:connection') {
      return;
    }
    
    server.emit('connection', connection);

    connection.resume();
  });
}

