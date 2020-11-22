# 使用 JS library

- 例如 lodash，没有 TS

## 解决方法 1 (并不会彻底解决)

需要将 tsconfig.json 中的 noEmitOnError 变成 false

## 解决方法 2

```
npm install --save-dev @types/lodash
```

如果有些 library 没有@types 怎么办？

- 可以在文件前加 declare

```typescript
declare var GLOBAL: any;
```

# 使用 TS library

- class-transformer
- class-validator
