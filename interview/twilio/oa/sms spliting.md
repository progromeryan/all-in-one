![img](../assets/sms%201.png)
![img](../assets/sms%202.png)

https://leetcode.com/discuss/interview-question/394697/Twili&#8205;&#8205;&#8205;&#8204;&#8204;&#8205;&#8204;&#8204;&#8205;&#8205;&#8205;&#8205;&#8204;&#8205;&#8204;&#8204;&#8204;&#8205;o-or-OA-2019


```python
class Solution:
    def split(self, input1):
        if len(ans) > 1:
            for i, s in enumerate(ans):
                ans[i] = s + '(' + str(i+1) + '/' + str(totalSplit) + ')'

        lastSpace = 0
        curStart = 0
        ans = []
        totalSplit = 0
        count = 0
        i = 0
        while i < len(input1):
            count += 1
            if input1[i] == ' ':
                lastSpace = i
            if count == 155:
                if input1[i] != ' ':
                    if (i < len(input1) - 1 and input1[i+1] == ' ') or i == len(input1) - 1:
                        lastSpace = i
                totalSplit += 1
                count = 0
                ans.append(input1[curStart:lastSpace+1])
                if i > lastSpace:
                    i = lastSpace
                curStart = i + 1
            i += 1
        if curStart < len(input1):
            if totalSplit == 0:
                return [input1]
            ans.append(input1[curStart:])
            totalSplit += 1
        
        for i, s in enumerate(ans):
            ans[i] = s + '(' + str(i+1) + '/' + str(totalSplit) + ')'
        return ans
```