/**
 * 动态使用proto文件
 */

const path = require('path');
const protoLoader = require('@grpc/proto-loader');
const grpc = require('grpc');

const greetProtoPath = path.join(__dirname, "..", "proto", "greet.proto");

const greetProtoDefinition = protoLoader.loadSync(greetProtoPath, {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true,
});

const greetPackageDefinition = grpc.loadPackageDefinition(greetProtoDefinition).greet;

function greet(call, callback) {
  var firstName = call.request.greeting.first_name;
  var lastName = call.request.greeting.last_name;

  callback(null, {result: "hello" + firstName + lastName});
}

function main() {
  const server = new grpc.Server();

  server.addService(greetPackageDefinition.GreetService.service, {
    greet: greet,
  });

  service.bind("localhost:50051", grpc.ServerCredentials.createInsecure());
  server.start();
}

main();
