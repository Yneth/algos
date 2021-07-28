package queues;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class CircularQueue {
    // A queue can be implemented using an array and two additional fields, the beginning
    // and the end indices. Thisstructure issometimes referred to as a circular queue. Both
    // enqueue and dequeue have 0(1) time complexity. If the array is fixed, there is a
    // maximum number of entries that can be stored. If the array is dynamically resized,
    // the total time for m combined enqueue and dequeue operationsis0(m).
    // Implement a queue API using an array forstoring elements. Your APIshould include
    // a constructor functionj which takes as argument the initial capacity of the queue,
    // enqueue and dequeuefunctions,and a function which returnsthe numberof elements
    // stored. Implement dynamic resizing to support storing an arbitrarily large number
    // of elements.
    // Example:
    // n = 5
    // enq 1
    // 1
    // enq 2
    // 1 2
    // dequeue
    // 2
    // dequeue
    // 2
    // Solution:
    // naive
    // keep index of the head and tail
    // enqueue(val):
    //   if (tail - head >= size) resize();
    //   arr[tail % size] = val
    //   tail++
    //
    // dequeue(val):
    //   int ret = arr[head];
    //   arr[head % size] = null
    //   head++
    //   return ret;
    //
    // resize()
    //   new_arr = new int[size << 1];
    //   System.arraycopy(arr, head, new_arr, 0, size - head)
    //   if (tail < head)
    //      System.arraycopy(arr, 0, new_arr, size - head, tail)

    private int size;
    private int[] arr;

    private int head;
    private int tail;

    public CircularQueue(int size) {
        this.arr = new int[size];
        this.size = 0;
        this.head = -1;
        this.tail = -1;
    }

    public void enqueue(int val) {
        if (size >= arr.length - 1) resize();
        size++;
        arr[++tail % arr.length] = val;
        if (head == -1) head = 0;
    }

    public Integer dequeue() {
        if (size == 0) return null;
        size--;
        int val = arr[head % arr.length];
        head++;
        if (size == 0) tail = head = -1;
        return val;
    }

    private void resize() {
        int[] newArr = new int[arr.length << 1];
        if (size == 0) {
            arr = newArr;
        } else {
            System.arraycopy(arr, head, newArr, 0, size - head);
            if (head > tail)
                System.arraycopy(arr, 0, newArr, size - head, tail);
            arr = newArr;

            int newHead = 0;
            int newTail = size - 1;
            head = newHead;
            tail = newTail;
        }
    }

    public int size() {
        return size;
    }

    public Integer first() {
        if (head < 0) return null;
        return arr[head % arr.length];
    }

    public Integer last() {
        if (tail < 0) return null;
        return arr[tail % arr.length];
    }

    public void debug() {
        System.out.println("first = " + first() + ", tail = " + last());
    }

    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(1);
        for (List<? extends Serializable> op : Arrays.asList(
            Arrays.asList("d", null),
            Arrays.asList("e", 1),
            Arrays.asList("e", 2),
            Arrays.asList("e", 3),
            Arrays.asList("e", 4),
            Arrays.asList("d", null),
            Arrays.asList("d", null),
            Arrays.asList("e", 5),
            Arrays.asList("d", null),
            Arrays.asList("d", null),
            Arrays.asList("d", null)
        )) {
            if ("e".equals(op.get(0))) {
                q.enqueue((int) op.get(1));
            } else {
                q.dequeue();
            }
            q.debug();
        }
    }
}
