const mongoose = require('mongoose');
mongoose.connect('mongodb://127.0.0.1/perfData', { useNewUrlParser: true });
const Machine = require('./models/Machine');

function socketMain(io, socket) {
  let macA;
  socket.on('clientAuth', (key) => {
    if (key === '5t78yuhgirekjaht32i3') {
      socket.join('clients');
    } else if (key === 'uihjt3refvdsadf') {
      // react ui client
      socket.join('ui');
      Machine.find({}, (err, docs) => {
        docs.forEach((aMachine) => {
          // initially, all machines are offline
          aMachine.isActive = false;
          io.to('ui').emit('data', aMachine);
        });
      });
    } else {
      // an invalid client
      socket.disconnect(true);
    }
  });

  socket.on('disconnect', () => {
    Machine.find({ macA: macA }, (err, docs) => {
      if (docs.length > 0) {
        // send one last emit to React
        docs[0].isActive = false;
        io.to('ui').emit('data', docs[0]);
      }
    });
  });

  // a machine has connected, check to see if it's new.
  // if it is, add it!
  socket.on('initPerfData', async (data) => {
    macA = data.macA;
    await checkAndAdd(data);
  });

  socket.on('perfData', (data) => {
    io.to('ui').emit('data', data);
  });
}

function checkAndAdd(data) {
  return new Promise((resolve, reject) => {
    Machine.findOne(
      { macA: data.macA },
      (err, doc) => {
        if (err) {
          throw err;
          reject(err);
        } else if (doc === null) {
          let newMachine = new Machine(data);
          newMachine.save();
          resolve('added');
        } else {
          resolve('found');
        }
      }
    );
  });
}

module.exports = socketMain;
