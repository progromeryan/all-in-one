package _716.TreeMap;

import java.util.*;

class MaxStack {
    TreeMap<Integer, Integer> map;
    Stack<Integer> stack;

    public MaxStack() {
        map = new TreeMap<>();
        stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        map.put(x, map.getOrDefault(x, 0) + 1);
    }

    public int pop() {
        int val = stack.pop();
        map.put(val, map.get(val) - 1);
        if (map.get(val) == 0) {
            map.remove(val);
        }

        return val;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = map.lastKey();
        map.put(max, map.get(max) - 1);
        if (map.get(max) == 0) {
            map.remove(max);
        }

        Stack<Integer> temp = new Stack<>();

        while (!stack.isEmpty()) {
            int val = stack.pop();
            if (val == max) break;

            temp.push(val);
        }

        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        return max;
    }
}

