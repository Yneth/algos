package stacks;

import java.util.*;

public class BuildingsSunsetView {
    // You are given with a series of buildingsthat have windowsfacing west. The buildings
    // are in a straight line, and any building which is to the east of a building of equal or
    // greater height cannot view the sunset.
    // Design an algorithm that processes buildings in east-to-west order and returns the
    // set of buildings which view the sunset. Each building isspecified by its height.
    // Example
    //
    // 50 50 49 51 52 50
    // ^        ^  ^
    //                 | start here
    //
    //                 ^ assume it can see the sunset
    //              ^    pop(50) push(52)
    //           ^       push(51)
    //        ^          push(49)
    //     ^             pop(49) push(50)
    //  ^                pop(50) push(50)
    //
    // 100 1 10 2 3 4 5
    //              ^   push(5)
    //            ^     push(4)
    //          ^       push(3)
    //        ^         pop(5 4 3) push(10)
    //     ^            push(1)
    // ^                pop(1 10) push(100)
    // looks like during processing we are always
    // keeping values in decreasing order with local max
    // as soon as max gets broken clear
    //
    // Solution:
    // init stack
    // init max
    // for b in buildings
    //   if max <= b
    //     stack.clear()
    //   stack.push(b)
    //   max = Math.max(max, b)

    public List<Integer> sunsetBuildings(List<Integer> heights) {
        Deque<Integer> stack = new LinkedList<>();
        for (int i = heights.size() - 1; i >= 0; i--) {
            int height = heights.get(i);
            while (!stack.isEmpty() && stack.peek() <= height) {
                stack.pop();
            }
            stack.push(height);
        }
        return (List<Integer>) stack;
    }

    public static void main(String[] args) {
        BuildingsSunsetView sut = new BuildingsSunsetView();
        System.out.println(sut.sunsetBuildings(Arrays.asList(100, 1, 10, 2, 3, 4, 5)));
        System.out.println(sut.sunsetBuildings(Arrays.asList(50,50,49,51,52,50)));
    }

}
