package search;

public class ComputeSquareRoot {
    // Square root computations can be implemented using sophisticated numerical tech¬
    // niques involving iterative methods and logarithms. However, if you were asked to
    // implement a square root function, you would not be expected to know these tech¬
    // niques.
    // Implement a function which takes as input a floating point value and returns its
    // square root.
    //
    // Example:
    //   9
    //   [1 9] mid = 5  5*5 > 9
    //   [1 5] mid = 3 3*3 == 9
    //
    //   3
    //   [1 3] mid = 2 2*2 > 3
    //   [1 2] mid = 1.5  1.5*1.5=2,25 < 3
    //   [1.5 2] mid = 3.5/2 = 1,75 = 3,06 > 3
    //   0.5
    //   [0.5 1] mid =
    //   ...
    // Solution:
    //  binary search
    //     in range of [1, x] for x > 1
    private static int compare(double x, double y) {
        double diff = (x - y)/ y;
        double eps = 0.000001;
        return diff < -eps
            ? -1
            : diff > eps ? 1 : 0;
    }

    public static double find(double x) {
        double l, h;
        if (x > 1.0) {
            l = 1.0;
            h = x;
        } else {
            l = x;
            h = 1.0;
        }
        // low + high / 2
        // low + high / 2 + low - low
        //

        // -1 if l < r
        // 0  if l == r
        // 1  if l > r
        while (compare(l, h) <= 0) {
            double mid = l + (h - l) / 2;
            int cmp = compare(mid * mid, x);
            if (cmp > 0) h = mid;
            else if (cmp < 0) l = mid;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(find(300));
        System.out.println(find(3));
        System.out.println(find(9));
        System.out.println(find(16));
        System.out.println(find(25));
    }
}
