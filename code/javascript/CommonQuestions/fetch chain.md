```javascript
function getStarWarsPlanets(
  progress,
  url = "https://swapi.co/api/planets",
  planets = []
) {
  return new Promise((resolve, reject) =>
    fetch(url)
      .then((response) => {
        if (response.status !== 200) {
          throw `${response.status}: ${response.statusText}`;
        }
        response
          .json()
          .then((data) => {
            planets = planets.concat(data.results);
            // 如果还有
            // data.next是下一个url
            if (data.next) {
              progress && progress(planets);
              getStarWarsPlanets(progress, data.next, planets)
                .then(resolve)
                .catch(reject);
            } else {
              resolve(planets);
            }
          })
          .catch(reject);
      })
      .catch(reject)
  );
}

function progressCallback(planets) {
  // render progress
  console.log(`${planets.length} loaded`);
}

getStarWarsPlanets(progressCallback)
  .then((planets) => {
    // all planets have been loaded
    console.log(planets.map((p) => p.name));
  })
  .catch(console.error);
```
