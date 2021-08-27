package greedy;

public class LargestRectUnderSkyline {
    //  You are given a sequence of adjacent buildings. Each has unit width and an integer
    // height. These buildings form the skyline of a city. An architect wants to know the
    // area of a largest rectangle contained in this skyline. See Figure 18.5 for an example.
    //  Let A be an array representing the heights of adjacent buildings of unit width. Design
    // an algorithm to compute the area of the largest rectangle contained in this skyline.
    // Example:
    //  x x
    //  xxxx
    // xxxxxx
    //
    //
    // Ideas:
    // lets init two pointers and global max
    //    for each pair
    //     calc square swap max if it is greater
    //     if h[i] < h[j] i++
    //     else j--
    //     else i++ j--
    //
    //  x x
    //  xxxx
    // xxxxxx
    // ^    ^   6*1
    //  ^  ^
    // No it will not work
    // search from current forward until we the height is lower
    // calculate max square for each index
    // and maintain max variable
    // Time: O(n^2)
    //
    // Better solution would be to maintain stack
    // of heights
    // before adding new height, remove all heights lower that it
    // also maintain max height in outer variable
    //
    // pseudo:
    //  stack = []
    //  max = 0
    //  min_h = 0
    //  for i in 1..len
    //    curr_h = h[i]
    //    while stack && stack.peek() > curr_h
    //
    //
}
