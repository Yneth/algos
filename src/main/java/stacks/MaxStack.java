package stacks;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MaxStack {
    // Design a stack that includes a max operation, in addition to push and pop. The max
    // method should return the maximum value stored in the stack.
    //
    // Example:
    // head <- 3 <- 5 <- 4
    // max 5
    // pop 3, max 5
    // pop 5, max 4
    // pop 4, max nil
    //
    // Solution:
    // create wrapper class for Stack items
    // class Item { int val; int max; }
    // during push init class with current_val, Math.max(peek.max, current_val)
    // for top() use peek and return current max;

    static class Item {
        int val;
        int max;
        public Item(int val, int max) {
            this.val = val;
            this.max = max;
        }
    }

    private final java.util.Deque<Item> stack = new LinkedList<>();

    public void push(int value) {
        if (!stack.isEmpty()) {
            Item prev = stack.peek();
            stack.push(new Item(value, Math.max(prev.max, value)));
        } else {
            stack.push(new Item(value, value));
        }
    }

    private Item doPeek() {
        Item head = stack.peek();
        if (head == null)
            throw new NoSuchElementException("stack is empty");
        return head;
    }

    public int peek() {
        return doPeek().val;
    }

    public int pop() {
        return stack.pop().val;
    }

    public int top() {
        return doPeek().max;
    }

    public static void main(String[] args) {
        MaxStack sut = new MaxStack();
        sut.push(3);
        System.out.println(sut.top());
        sut.push(100);
        System.out.println(sut.top());
        sut.push(4);
        System.out.println(sut.top());
        sut.push(4);
        System.out.println(sut.top());
        sut.pop(); // 4
        sut.pop(); // 4
        sut.pop(); // 100
        System.out.println(sut.top());

        sut.push(4); // 4
        System.out.println(sut.top());
    }

}
