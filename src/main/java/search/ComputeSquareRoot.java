package search;

public class ComputeSquareRoot {
    // Write a program which takes a nonnegative integer and returns the largest integer
    // whose square is less than or equal to the given integer. For example, if the input is
    // 16, return 4; if the input is 300, return 17,since 172 = 289 < 300 and 182 = 324 > 300
    //
    // Solution:
    //  take an int
    //  take its' square root
    //  run Math.floor
    //  return

    public static int find(int val) {
        return (int) Math.floor(Math.sqrt(val));
    }

    public static int findIter(int val) {
        int l = 0, h = val;
        int result = -1;
        while (l <= h) {
            int mid = l + h >>> 1;
            int cmp = mid * mid;
            if (cmp > val) {
                h = mid - 1;
            } else if (cmp < val) {
                l = mid + 1;
                result = mid;
            } else if (cmp == val) {
                return mid;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(find(300));
        System.out.println(find(16));

        System.out.println(findIter(300));
        System.out.println(findIter(16));
    }
}
