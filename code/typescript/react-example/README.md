# error fix

## error

> /node_modules/react-scripts/scripts/utils/verifyTypeScriptSetup.js:210

```javascript
appTsConfig.compilerOptions[option] = suggested;
```

## solution

change verifyTypeScriptSetup.js as follow:

Before:

```javascript
let result;
parsedTsConfig = immer(readTsConfig, (config) => {
  result = ts.parseJsonConfigFileContent(
    config,
    ts.sys,
    path.dirname(paths.appTsConfig)
  );
});
```

After:

```javascript
parsedTsConfig = { ...readTsConfig };

const result = ts.parseJsonConfigFileContent(
  parsedTsConfig,
  ts.sys,
  path.dirname(paths.appTsConfig)
);
```
