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

const client = new greetPackageDefinition.GreetService("localhost:50051", grpc.credentials.createInsecure());

function callGreetings() {
  var request = {
    greeting: {
      first_name: 'jacob',
      last_name: 'huang'
    }
  }

  client.greet(request, (error, response) => {
    if (!error) {
      console.log("response", response.result)
    }
  })
}

function main() {
  callGreetings();
};

main();