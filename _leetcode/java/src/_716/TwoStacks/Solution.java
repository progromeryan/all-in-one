package _716.TwoStacks;

import java.util.Stack;

class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;

    public MaxStack() {
        stack = new Stack();
        maxStack = new Stack();
    }

    public void push(int x) {
        int max = Integer.MIN_VALUE;
        if (!maxStack.isEmpty()) {
            max = maxStack.peek();
        }

        if (max > x) {
            maxStack.push(max);
        } else {
            maxStack.push(x);
        }
        stack.push(x);
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = peekMax();
        Stack<Integer> temp = new Stack();
        while (top() != max) temp.push(pop());
        pop();
        while (!temp.isEmpty()) push(temp.pop());
        return max;
    }
}