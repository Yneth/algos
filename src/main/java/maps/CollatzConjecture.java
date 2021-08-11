package maps;

import java.util.*;

public class CollatzConjecture {
    //   The Collatz conjecture is the following: Take any natural number. If it is odd, triple
    // it and add one; if it is even, halve it. Repeat the processindefinitely. No matter what
    // number you begin with, you will eventually arrive at 1.
    // As an example, if we start with 11 we get the sequence 11,34,17,52,26,13,40,
    // 20,10,5,16,8,4,2,1. Despite intense efforts, the Collatz conjecture has not been
    // proved or disproved.
    // Suppose you were given the task of checking the Collatz conjecture for the first
    // billion integers. A direct approach would be to compute the convergence sequence
    // for each number in thisset.
    // Test the Collatz conjecture for the first n positive integers.
    // Example:
    //  5 -> 16 -> 8 -> 4 -> 2 -> 1
    //  6 -> 3 -> 10 -> 5
    //  7 -> 22 -> 11 -> 34 -> 12 -> 6
    //  ...
    //
    // Solution:
    //  maintain a set of converged ints as a cache
    //  for i in 3..n
    //    seq = #{}
    //    iter = i
    //    while iter >= i
    //      if (iter in seq) we are stuck in a loop, return false
    //      seq.add(iter)
    //      if (iter in verified) break;
    //      if odd(iter) iter = iter * 3 + 1
    //      else iter = iter / 2
    //  return true

    public static boolean test(long n) {
        Set<Long> verified = new HashSet<>();
        for (long i = 3; i <= n; i++) {
            Set<Long> sequence = new HashSet<>();
            long iter = i;
            while (iter >= i) {
                if (sequence.contains(iter)) return false;
                if (verified.contains(iter)) break;
                sequence.add(iter);
                if (iter % 2 == 0) iter = iter / 2;
                else iter = iter * 3 + 1;
            }
            verified.add(iter);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(test(10_000_000));
    }
}
