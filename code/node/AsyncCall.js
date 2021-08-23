const https = require('https');

function getPromise(url) {
	return new Promise((resolve, reject) => {
		https.get(url, (response) => {
			let chunks = [];

			response.on('data', (chunk) => {
				chunks.push(chunk);
			});

			response.on('end', () => {
				const response = Buffer.concat(chunks);
				resolve(JSON.parse(response.toString()));
			});

			response.on('error', (error) => {
				reject(error);
			});
		});
	});
}

async function makeSynchronousRequest(url) {
    try {
		let response = await getPromise(url);
		console.log(response);
	} catch(error) {
		console.error(error);
	}
}

(async function () {
    const url = "https://jsonmock.hackerrank.com/api/movies/";
	await makeSynchronousRequest(url);
})();
