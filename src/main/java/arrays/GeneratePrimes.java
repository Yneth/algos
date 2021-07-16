package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneratePrimes {
    // the idea here is to pick a number
    // if it is prime, remove all its multiples
    // otherwise go the next number

    public List<Long> generatePrimes(int n) {
        List<Long> primes = new ArrayList<>();
        List<Boolean> isPrime = new ArrayList<>(
            Collections.nCopies(n + 1, true)
        );
        for (int i = 2; i <= n; i++) {
            if (isPrime.get(i)) {
                primes.add((long) i);
                for (int j = i + i; j <= n; j += i) {
                    isPrime.set(j, false);
                }
            }
        }
        return primes;
    }

    // actually we can do a little bit better
    // by storing only odd numbers
    // I will achieve that in the following way
    // here is our sequence:
    // 1     2     3   4     5   6    7  8 ...
    // subtract 3
    // -2   -1     0   1     2   3    4  5 ...
    // multiply by 1/2
    // -1   -0.5   0   0.5   1   1.5  2  2.5
    // Math.floor
    // -1   -1     0   0     1   1    2  2

//    x = 0.5 * (y - 3)
//    y - 3 = 2 * x
//    y = 2 * x + 3
    // (2i + 3)^2 = 4 i i + 12 i + 9
    // 4 i^2 + 12i + 9  | - 3
    // 4 i^2 + 12i + 6   | * 0.5
    // 2 i^2 + 6i + 3

    public List<Long> generatePrimesPlus(int n) {
        int size = (int) Math.floor(0.5 * (n - 3));
        List<Long> primes = new ArrayList<>();
        primes.add(2L);

        List<Boolean> isPrime = new ArrayList<>(
            Collections.nCopies(size, true)
        );
        for (int i = 0; i < size; i++) {
            if (isPrime.get(i)) {
                long p = 2L * i + 3;
                primes.add(p);

                for (long j = 2L * i * i + 6L * i + 3; j < size; j += p) {
                    isPrime.set((int) j, false);
                }
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        GeneratePrimes gp = new GeneratePrimes();
        System.out.println(gp.generatePrimesPlus(100));
    }
}
