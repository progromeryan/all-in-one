// 运行在需要检测的机器上
// 得到机器的数据并传送到socketio server

const os = require('os');
const io = require('socket.io-client');
let socket = io('http://127.0.0.1:8181');

// 链接socketio
socket.on('connect', () => {
  // we need a way to identify this machine to whomever concerned
  const nI = os.networkInterfaces();
  let macA;
  // loop through all the nI for this machine and find a non-internal one
  for (let key in nI) {
    macA = Math.floor(Math.random() * 3) + 1;
    break;
    if (!nI[key][0].internal) {
      if (nI[key][0].mac === '00:00:00:00:00:00') {
        macA = Math.random().toString(36).substr(2, 15);
      } else {
        macA = nI[key][0].mac;
      }
      break;
    }
  }

  // client auth with single key value
  socket.emit('clientAuth', '5t78yuhgirekjaht32i3');

  performanceData().then((allPerformanceData) => {
    allPerformanceData.macA = macA;
    socket.emit('initPerfData', allPerformanceData);
  });

  // start sending over data on interval
  let perfDataInterval = setInterval(() => {
    performanceData().then((allPerformanceData) => {
      allPerformanceData.macA = macA;
      socket.emit('perfData', allPerformanceData);
    });
  }, 1000);

  socket.on('disconnect', () => {
    clearInterval(perfDataInterval);
  });
});

function performanceData() {
  return new Promise(async (resolve, reject) => {
    const cpus = os.cpus();
    const freeMem = os.freemem();
    const totalMem = os.totalmem();
    const usedMem = totalMem - freeMem;
    const memUsage = Math.floor(usedMem / totalMem * 100) / 100;
    const osType = os.type() === 'Darwin' ? 'Mac' : os.type();
    const upTime = os.uptime();
    const cpuModel = cpus[0].model;
    const numCores = cpus.length;
    const cpuSpeed = cpus[0].speed;
    const cpuLoad = await getCpuLoad();
    const isActive = true;
    resolve({
      freeMem,
      totalMem,
      usedMem,
      memUsage,
      osType,
      upTime,
      cpuModel,
      numCores,
      cpuSpeed,
      cpuLoad,
      isActive,
    });
  });
}

function cpuAverage() {
  const cpus = os.cpus();
  let idleMs = 0;
  let totalMs = 0;

  cpus.forEach((aCore) => {
    for (let type in aCore.times) {
      totalMs += aCore.times[type];
    }
    idleMs += aCore.times.idle;
  });
  return {
    idle: idleMs / cpus.length,
    total: totalMs / cpus.length
  };
}

function getCpuLoad() {
  return new Promise((resolve, reject) => {
    const start = cpuAverage();
    setTimeout(() => {
      const end = cpuAverage();
      const idleDifference = end.idle - start.idle;
      const totalDifference = end.total - start.total;
      const percentageCpu = 100 - Math.floor(100 * idleDifference / totalDifference);
      resolve(percentageCpu);
    }, 100);
  });
}

