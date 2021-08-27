package greedy;

public class MaxWaterTrapped {
    //  An array of integers naturally defines a set of lines parallel to the Y-axis,starting from
    // x = 0 as illustrated in Figure 18.4(a). The goal of this problem is to find the pair of
    // lines that together with the X-axis "trap" the most water. See Figure 18.4(b) for an
    // example.
    //  Write a program which takes as input an integer array and returns the pair of entries
    // that trap the maximum amount of water.
    //
    // Given:
    //  arr -> [int], 0 < |arr| < sizeof(int)
    // Assumptions:
    //  there is no wall before 0 and after |arr|
    // Return: int
    // Ideas:
    // Example:     x
    //    x - - x   x
    //    x - - x   x
    //  x x - x x   x x
    //      3  2
    // result = 3
    //
    // let first height be a candidate
    // max_capacity = 0
    // capacity = 0
    // iterate over heights
    //   if curr < candidate
    //     capacity += candidate - curr
    //   if curr > candidate:
    //     candidate = curr
    //     capacity = 0
    //   max_capacity = max(max_capacity, capacity)
    // Completely wrong
    // I need to return square containing the max capacity
    //
    //

    public static int get(int[] heights) {
        int i = 0, j = heights.length - 1;
        int maxWater = 0;
        while (i < j) {
            int width = j - i;
            maxWater = Math.max(maxWater, width * Math.min(heights[i], heights[j]));
            if (heights[i] > heights[j]) {
                j--;
            } else if (heights[i] < heights[j]) {
                i++;
            } else {
                i++; j--;
            }
        }
        return maxWater;
    }


    public static void main(String[] args) {
        System.out.println(get(new int[] {1, 3, 0, 1, 3, 0, 4, 1}));
    }

}
