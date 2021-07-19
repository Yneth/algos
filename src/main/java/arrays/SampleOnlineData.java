package arrays;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SampleOnlineData {


    // write a packet sniffer
    // Given a stream of integers
    // Return sample of its' values picked uniformly
    //
    // Solution:
    // Read k elements
    // Read next stream elements
    // until consumed
    //   at each step generate random value [0, i]
    //   if it is in range of K
    //   set value in the sample

    public List<Integer> solve(Iterator<Integer> vals, int k) {
        Random rand = new Random();
        List<Integer> sample = new ArrayList<>(k);
        for (int i = 0; i < k && vals.hasNext(); i++) {
            sample.add(i, vals.next());
        }

        int seenSoFar = sample.size();
        while (vals.hasNext()) {
            Integer x = vals.next();
            seenSoFar++;
            int next = rand.nextInt(seenSoFar);
            if (next < k) {
                sample.set(next, x);
            }
        }
        return sample;
    }

    public static void main(String[] args) {
        SampleOnlineData sod = new SampleOnlineData();
        for (int i = 0; i < 10; i++) {
            System.out.println(
                sod.solve(IntStream.range(0, 100).boxed().iterator(), 30)
            );
        }
    }

}
