作用：可以从父元素监听全部子元素的事件

通过拿到 buttons 元素，监听其子元素的所有 button，这样点击任意一个 button，都会触发事件

好处是不用再给每个 button 绑定一个事件了

```html
<div id="buttons">
  <!-- Step 1 -->
  <button class="buttonClass">Click me</button>
  <button class="buttonClass">Click me</button>
  <button class="buttonClass">Click me</button>
</div>

<script>
  document.getElementById("buttons").addEventListener("click", (event) => {
    // Step 2
    if (event.target.className === "buttonClass") {
      // Step 3
      console.log("Click!");
    }
  });
</script>
```
