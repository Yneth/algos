package heaps;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ComputeKClosestStars {
    // Consider a coordinate system for the Milky Way in which Earth is at (0,0,0). Model
    // stars as points, and assume distances are in light years. The Milky Way consists of
    // approximately 1012 stars, and their coordinates are stored in a file.
    //
    // How would you compute the k stars which are closest to Earth?

    // Solution:
    //  given: iterator of points and number K
    //
    //  init max pq of integers
    //  for each star
    //    calculate square distance to 0
    //    add to pq
    //    remove from pq
    //
    // Test:
    // 900 199 90 180 300 500     k=2
    //  pq900
    //  pq900,199
    //  pq199,90
    //  pq199,90
    //  pq
    // ...

    static class Star {
        List<Integer> point;
        int distance;

        public Star(List<Integer> point) {
            this.point = point;
            for (int i = 0; i < point.size(); i++)
                this.distance += point.get(i) * point.get(i);
        }

        public List<Integer> getPoint() {
            return point;
        }
    }

    public static List<List<Integer>> kClosest(Iterator<List<Integer>> stars, int k) {
        PriorityQueue<Star> maxPq = new PriorityQueue<>((s0, s1) -> {
            return Integer.compare(s1.distance, s0.distance);
        });
        while (stars.hasNext()) {
            maxPq.add(new Star(stars.next()));
            if (maxPq.size() > k) maxPq.remove();
        }
        return maxPq.stream().map(Star::getPoint)
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(
            kClosest(
                Arrays.asList(
                    Arrays.asList(30, 0, 0),
                    Arrays.asList(3, 0, 0),
                    Arrays.asList(10, 0, 0),
                    Arrays.asList(50, 0, 0),
                    Arrays.asList(9, 0, 0),
                    Arrays.asList(40, 0, 0)
                ).iterator(),
                4
            )
        );
    }
}
