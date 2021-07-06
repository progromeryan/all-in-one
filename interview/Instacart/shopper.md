
楼主电面碰到了新题
情景是shoppers从某location pickup order，然后deliver到另一个location，要求计算从某个地点到另个地点的average travel time。让你implement这些函数。
public void pickup(int timestamp, String pickUpLocation, int shopperId)
public void deliver(int timestamp, String, DeliverLocation, int shopperId)
public double calculateAverage(String locationB, String locationB)

比如 shopper1 在时间 0 从 地点 A pickup 了order，在时间5 地点B 放下了order
shopper2 在时间0 从地点A pickup了order， 在时间6 地点B 放下了order
那calculateAverage（A, B) 就返回 1.0*(5 + 6)/2 = 5.5


我之前也遇到了这道题。 几个clarification:
1. 只需要考虑直接deliver的情况，不需要考虑类似已知 A-B, B-C, 求A-C。
2. input都是顺序的，不会出现（shoper1, pickup1, time0), (shoper1, pickup2, time2), (shoper1, deliver1, time1), (shoper1, deliver2, time3) 这样乱序的pickup & deliver。
3. 需要自己设计test case，并且编译跑通

非常简单...