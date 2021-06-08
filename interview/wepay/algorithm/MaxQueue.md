> 设计并实现一个 MaxQueue 的 poll(), push(int a), pollMax()三个操作
> poll() 移除最先进入队列的元素并返回它的值
> pollMax() 移除队列中值最大的元素并返回它的值
> push(int a) 将 a 放入队尾
> 每个操作的 average time complexity O(logn)

```java
import java.util.*;

public class MaxQueue {

    LinkedList<Integer> queue;
    TreeMap<Integer, List<Integer>> map;
    int headIndex;

    public MaxQueue() {
        queue = new LinkedList<>();
        map = new TreeMap<>((a, b) -> (b - a));
        headIndex = -1;
    }

    public void offer(int num) {
        if (headIndex == -1) {
            headIndex = 0;
        }
        int size = queue.size();
        queue.add(num);
        if (!map.containsKey(num)) {
            map.put(num, new ArrayList<>());
        }

        map.get(num).add(size);
    }

    public int poll() {
        int num = queue.get(headIndex);
        queue.set(headIndex, null);
        map.get(num).remove(0);
        if (map.get(num).size() == 0) map.remove(num);
        updateHeader();
        return num;
    }

    public int pollMax() {
        int max = map.firstKey();
        int index = map.get(max).get(0);
        queue.set(index, null);
        updateHeader();
        map.get(max).remove(0);
        if (map.get(max).size() == 0) map.remove(max);
        return max;
    }

    public void updateHeader() {
        for (int i = headIndex; i < queue.size(); i++) {
            if (queue.get(i) != null) {
                headIndex = i;
                break;
            }
        }
    }

    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.offer(1);
        maxQueue.offer(3);
        maxQueue.offer(4);
        maxQueue.offer(0);
        maxQueue.offer(3);

        System.out.println(maxQueue.poll());
        System.out.println(maxQueue.pollMax());
        System.out.println(maxQueue.pollMax());
        System.out.println(maxQueue.poll());
        maxQueue.offer(5);
        maxQueue.offer(5);
        maxQueue.offer(5);
        System.out.println(maxQueue.pollMax());
        System.out.println(maxQueue.poll());
        System.out.println(maxQueue.poll());
    }
}
```
