package queues;

import java.util.Deque;
import java.util.LinkedList;

public class QueueAsStacks {

    // Queue insertion and deletion followsfirst-in, first-out semantics;stack insertion and
    // deletion islast-in, first-out.
    // How would you implement a queue given a library implementing stacks?
    //
    // Example:
    // 1 2 3
    // push 1 to the stack
    // push 2 to the stack
    // push 3 to the stack
    // to retrieve 1
    // we need to pop 3 and 2
    // we cannot just pop them
    // we can move them to the other stack
    // this way we would have one stack for writing
    // and one for reading
    //
    // enqueue(val)
    //   if writeStack.isEmpty() move(readStack, writeStack)
    //   writeStack.push(val)
    //
    // dequeue()
    //   if readStack.isEmpty() move(writeStack, readStack)
    //   return readStack.pop()
    //
    // Test:
    // enq 1, 2, 3             write stack [3, 2 ,1]      queue order 1 2 3
    //                         sink to read
    //                         1 2 3
    //                         pop 1

    private Deque<Integer> readStack = new LinkedList<>();
    private Deque<Integer> writeStack = new LinkedList<>();

    public void enqueue(int val) {
        if (writeStack.isEmpty()) move(readStack, writeStack);
        writeStack.push(val);
    }

    public int dequeue() {
        if (readStack.isEmpty()) move(writeStack, readStack);
        return readStack.pop();
    }

    public Integer peek() {
        if (readStack.isEmpty()) move(writeStack, readStack);
        return readStack.peek();
    }

    private void move(Deque<Integer> from, Deque<Integer> to) {
        while (!from.isEmpty()) {
            to.push(from.pop());
        }
    }

    public static void main(String[] args) {
        QueueAsStacks sut = new QueueAsStacks();
        sut.enqueue(10);
        System.out.println(sut.peek());
        sut.enqueue(11);
        System.out.println(sut.peek());
        sut.enqueue(12);
        sut.dequeue();
        System.out.println(sut.peek());
        sut.dequeue();
        System.out.println(sut.peek());
        sut.dequeue();
        System.out.println(sut.peek());
    }
}
