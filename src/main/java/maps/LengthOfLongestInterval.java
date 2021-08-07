package maps;

import java.util.*;

public class LengthOfLongestInterval {
    // FIND THE LENGTH OF A LONGEST CONTAINED INTERVAL
    //     Write a program which takes asinput a set of integers represented by an array, and
    // returns the size of a largest subset of integersin the array having the property that if
    // two integers are in the subset, then so are all integers between them. For example, if
    // the input is(3,-2,7,9,8,1,2,0,-1,5,8), the largest such subset is{-2,-1,0,1,2,3),so
    // you should return 6.
    //     Hint: Do you really need a total ordering on the input?
    //
    // Examples:
    // - (3,-2,7,9,8,1,2,0,-1,5,8)
    //    1  1 1 1 1 1
    //    3          3 3 4
    //               4      6
    //       6           6    1 1
    // I have hypothesis that it will not work
    //
    // - (6, 8, 10, 1, 2, 0, -1, -2, 3)
    //    1  1   1  1  2
    //              2  2
    //              3     3
    //                    4   4
    //                        5   5   4
    //  But if I use mutable structure then it would be fine
    //   -2 -1 0 3 2 1
    //    3  3 3 3 3 3
    //
    // Solution:
    //  initialize a map of int to object with counter
    //  iterate over integers
    //    if int has no neighbours
    //       init it with 1
    //    if it has neighbours
    //       1 + left neighbour count + irght neibhour count
    //       update their counts
    // the problem here is to maintain shared counter
    // I can possible use shared atomic integer
    // Wow! looked up a solution, it is way simpler out there
    //
    // The idea is to initialize set out of input
    // iterate as long as this set is not empty
    //    process(remove all elements forward
    //            remove all elements backwar
    //    keep counter at the same time
    //
    // pseudocode:
    //   nums = set(input)
    //   while nums
    //     curr = get
    //     next = curr+1
    //     count = 1
    //     while nums[next++]
    //         count++
    //     prev = curr -1
    //     while nums[prev--]
    //         coutn++
    //     global_max = max(count, global_max)
    // return global_max
    //

    public static int find(List<Integer> nums) {
        Set<Integer> uniq = new HashSet<>(nums);
        int globalMax = -1;
        while (!uniq.isEmpty()) {
            int curr = uniq.iterator().next();
            uniq.remove(curr);

            int count = 1;

            int next = curr + 1;
            while (uniq.contains(next)) {
                uniq.remove(next);
                count++;
                next++;
            }

            int prev = curr - 1;
            while (uniq.contains(prev)) {
                uniq.remove(prev);
                count++;
                prev--;
            }
            globalMax = Math.max(count, globalMax);
        }
        return globalMax;
    }

    public static void main(String[] args) {
        System.out.println(find(Arrays.asList(new Integer[] {3,-2,7,9,8,1,2,0,-1,5,8})));
        System.out.println(find(Arrays.asList(new Integer[] {6, 8, 10, 1, 2, 0, -1, -2, 3})));
        System.out.println(find(Arrays.asList(new Integer[] {1, 2, 0, -3, -2, -4, -5})));
    }
}

