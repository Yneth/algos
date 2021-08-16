package bst;

import java.util.*;

public class ABSqrt2Seq {
    //  Numbers of the form a + b where a and b are nonnegative integers, and q is an
    // integer which is not the square of another integer, have special properties, e.g., they
    // are closed under addition and multiplication. Some of the first few numbers of this
    // form are given in Figure 15.4.
    //  Design an algorithm for efficiently computing the k smallest numbers of the form
    // a + bV2 for nonnegative integers a and b.
    //
    // Example:
    // ordered seq: (0,0), (0,1), (1,0) (2,0) (1,1)...
    // the solution for this would be to create a TreeSet
    // init with 0,0
    // take min, add to result
    // at each step iterate by i+1,j and i,j+1
    //

    static class ABSqrt2 implements Comparable<ABSqrt2> {
        int a, b;
        double value;

        public ABSqrt2(int a, int b) {
            this.a = a;
            this.b = b;
            this.value = a + b * Math.sqrt(2);
        }

        public int compareTo(ABSqrt2 o) {
            int cmp = Double.compare(this.value, o.value);
            if (cmp != 0) return cmp;
            int bCmp = Integer.compare(this.b, o.b);
            if (bCmp != 0) return bCmp;
            return Integer.compare(this.a, o.a);
        }

        public String toString() {
            return "(" + a + "," + b + ") = " + value;
        }
    }

    public static List<Double> generate(int k) {
        List<Double> result = new ArrayList<>();
        SortedSet<ABSqrt2> candidates = new TreeSet<>();
        candidates.add(new ABSqrt2(0, 0));
        while (result.size() < k) {
            ABSqrt2 x = candidates.first();
            result.add(x.value);

            candidates.add(new ABSqrt2(x.a, x.b + 1));
            candidates.add(new ABSqrt2(x.a + 1, x.b));
            candidates.remove(x);
        }
        return result;
    }

    public static void main(String[] args) {
        for (int k = 0; k < 15; k++) {
            System.out.println(generate(k));
        }
    }
}
