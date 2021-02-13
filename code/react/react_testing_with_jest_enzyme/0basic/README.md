## 如何去掉production中的data-test属性

- 安装babel-plugin-react-remove-properties
- 使用`npm run eject`得到对react app的设置
- 在package.json中添加
```
 "env": {
      "production": {
        "plugins": [
          [
            "react-remove-properties",
            {
              "properties": [
                "data-test"
              ]
            }
          ]
        ]
      }
    }
```
- 使用`npm run build`得到production构建
- 使用`serve -s build`运行production
