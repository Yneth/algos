package recursion;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TowersOfHanoi {
    //  A peg contains rings in sorted order, with the largest ring being the lowest. You are
    // to transfer these rings to another peg, which is initially empty. This is illustrated in
    // Figure 16.1.
    //  Write aprogram which prints a sequence of operations that transfers n rings from one
    // peg to another. You have a third peg, which is initially empty. The only operation
    // you can perform is taking a single ring from the top of one peg and placing it on the
    // top of another peg. You must never place a larger ring above a smaller ring.

    // Example
    // 421 0 0
    // 42  1 0
    // 4 1 2
    // 4 0 12
    // 0 4 12
    // 2 14 0
    // 12 4 0
    // 12 0 4
    // 2 1 4
    // 0 1 24
    // 0 0 124
    //
    // Solution:
    // represent pegs as an array of stacks
    //
    // hanoi(n, pegs, from, to, use)
    //  hanoi(n - 1, pegs, from, use, to)
    //
    //  pegs[to].push(pegs[from].pop())
    //  println(from to)
    //
    //  hanoi(n - 1, pegs, use, to, from)

    private static void doHanoi(int rings, List<Deque<Integer>> pegs, int from, int to, int use) {
        if (rings > 0) {
            doHanoi(rings - 1, pegs, from, use, to);
            System.out.println("rings = " + rings + "    move(" + from + "," + use + "," + to + ")");
            pegs.get(to).push(pegs.get(from).pop());
            System.out.println(pegs);
            doHanoi(rings - 1, pegs, use, to, from);
        }
    }

    public static void hanoi(int rings) {
        List<Deque<Integer>> pegs = new ArrayList<>(rings);
        pegs.add(new LinkedList<>());
        pegs.add(new LinkedList<>());
        pegs.add(new LinkedList<>());

        for (int i = rings; i > 0; i--) {
            pegs.get(0).push(i);
        }
        doHanoi(rings, pegs, 0, 2, 1);
    }

    public static void main(String[] args) {
        hanoi(4);
    }

}
