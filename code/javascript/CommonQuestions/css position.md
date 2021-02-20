absolute 是绝对定位；而 relative 是相对定位

解释：
绝对定位就是相对于父元素的定位，不受父元素内其他子元素的影响；

而相对定位是相对于同级元素的定位，也就是上一个同级元素!

position:relative 相对定位。

relative 是相对定位，相对于本身的位置，元素的位置通过 "left", "top", "right" ，"bottom" 属性进行定位。left 是离原坐标 x 轴的距离，
top 是离原坐标 y 轴的距离，它本身的位置还在。

absolute 是绝对定位，相对于窗口左上角的位置，元素的位置通过 "left", "top", "right" 以，"bottom" 属性进行定位。left 是离窗口左上角 x 轴的距离，top 是离窗口左上角标 y 轴的距离，不占有空间。

如果你有两个包含关系的 div，父 div 的样式是 position：relative，子 div 的样式是 position：absolute，那么子 div 的位置是相对于父的 div 的来进行定位的
