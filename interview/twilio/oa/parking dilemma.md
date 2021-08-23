https://leetcode.com/discuss/interview-question/379066/twitter-oa-2019-parking-dilemma

There are many cars parked in the parking lot. The parking is a straight very long line and a parking slot for every single meter. There are cars parked currently and you want to cover them from the rain by building a roof. The requirement is that at least k cars are covered by the roof.What's the minium length of the roof that would cover k cars?

The function has the following parameters:
cars: integer array of length denoting the parking slots where cars are parked
k: integer denoting the number of cars that have to be covered by the roof


```python
class Solution:
    """
    @param cars:  integer array of length denoting the parking slots where cars are parked
    @param k: integer denoting the number of cars that have to be covered by the roof
    @return: return the minium length of the roof that would cover k cars
    """
    def ParkingDilemma(self, cars, k):
        # write your code here
        cars.sort()
        n = len(cars)
        res = float('inf')
        for i in range(n - k + 1):
            res = min(res, cars[i + k - 1] - cars[i])
        return res + 1
```
