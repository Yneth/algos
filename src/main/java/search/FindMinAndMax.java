package search;

public class FindMinAndMax {
    // Given an array of comparable objects, you can find either the min or the max of the
    // elements in the array with n -1 comparisons, where n is the length of the array.
    //
    // Comparing elements may be expensive, e.g., a comparison may involve a number
    // of nested calls or the elements being compared may be long strings. Therefore, it is
    // natural to ask if both the min and the max can be computed with less than the 2(n-1)
    // comparisons required to compute the min and the max independently.
    // Design an algorithm to find the min and max elements in an array. For example, if
    // A = (3,2,5,1, 2,4), you should return 1for the min and 5 for the max.
    //
    // Solution:
    //   one way to solve is make two separate loops for fining min/max
    //       the other one
    //   one loop, two variables
    // BCT: O(n)
    //   we can improve one loop solution
    //   in a way that if we find new max,
    //   we do not need to make comparison for min
    //
    //   so in the worst case when array is out of order
    //   we would still have O(2 n-1) comparisons
    // wow!!!!
    //  the other solution would be to partition array into
    //  pair and evaluate MinMax for each one.
    //  comparing with global minmax at each step
    // this way we would have n/2 local comparisons
    // and n/2, global comparisons
    // resulting in O(n)

    static class MinMax {
        int min, max;

        public MinMax(int a, int b) {
            this.min = a;
            this.max = b;
        }

        public static MinMax of(int a, int b) {
            return Integer.compare(a, b) < 0
                ? new MinMax(a, b)
                : new MinMax(b, a);
        }
        @Override
        public String toString() {
            return "MinMax(" + min +"," + max + ")";
        }
    }

    public static MinMax find(int[] arr) {
        if (arr.length <= 0) return null;
        if (arr.length == 1) return new MinMax(arr[0], arr[0]);

        MinMax global = new MinMax(arr[0], arr[1]);
        for (int i = 2; i + 1 < arr.length; i += 2) {
            MinMax local = MinMax.of(arr[i], arr[i + 1]);
            global = new MinMax(
                Math.min(global.min, local.min),
                Math.max(global.max, local.max)
            );
        }
        if (arr.length % 2 == 1)
            global = new MinMax(
                Math.min(global.min, arr[arr.length - 1]),
                Math.max(global.max, arr[arr.length - 1])
            );

        return global;
    }

    public static void main(String[] args) {
        System.out.println(find(new int[] {0, 100, -100, 200, 300, -1000}));
        System.out.println(find(new int[] {0, 0, 0}));
        System.out.println(find(new int[] {0, 10, 20}));
        System.out.println(find(new int[] {0, 10, -20}));
    }

}
