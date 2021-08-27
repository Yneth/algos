package greedy;

import java.util.*;

public class Gasup {
    //  In the gasup problem, a number of cities are arranged on a circular road. You need
    // to visit all the cities and come back to the starting city. A certain amount of gas is
    // available at each city. The amount of gas summed up over all cities is equal to the
    // amount of gas required to go around the road once. Your gas tank has unlimited
    // capacity. Call a city ample if you can begin at that city with an empty tank, refill at it,
    // then travel through all the remaining cities, refilling at each, and return to the ample
    // city, without running out of gas at any point. See Figure 18.2 for an example.
    //  Given an instanceof the gasup problem, how would you efficiently computeanample
    // city? You can assume that there exists an ample city.
    //
    // Assumption:
    //  ample city exists
    //  20 miles per 1 gallon
    //  500 miles == 50/2 = 25 gallons
    //  100miles == 5 G
    //
    // how much gas do we need to travel 100 miles?
    //
    //
    // Example:
    //
    //   2G --->  0G ----> 3G ----> 20G ---->
    //      100M     200M    100M        100M
    //
    // Lets say we have
    // 20,100  2,100, 0,200, 3,100
    // just pick the city with a max gallons?
    // that have enough capacity to move to the next city
    //
    // pick any city, as soon as we can move forward, it is our candidate
    // if at any point we get negative gas, pick new candidate
    // repeat
    //
    // let 2G to be the candidate
    // we canot move to the next one
    // 0G
    // 3G
    // 20G
    // can use set to filter off candidate cities
    //
    // well that's not the best solution
    //
    //

    private final static int MPG = 20;

    public static int findAmpleCity(List<Integer> gallons,
                                    List<Integer> miles) {
        int candidate = 0;
        int currentGas = gallons.get(0);
        for (int i = 1; i < gallons.size(); i++) {
            currentGas = currentGas - (miles.get(i - 1) / MPG);
            if (currentGas < 0) {
                candidate = i;
                currentGas = gallons.get(i);
            } else {
                currentGas += gallons.get(i);
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        System.out.println(
            findAmpleCity(
                Arrays.asList(2,  0,   3,    20),
                Arrays.asList(100, 200, 100, 100)
            )
        );

        System.out.println(
            findAmpleCity(
                Arrays.asList(2,  0,    20,    3),
                Arrays.asList(100, 200, 100, 100)
            )
        );

        System.out.println(
            findAmpleCity(
                Arrays.asList(2,  0,    20,    3, 10),
                Arrays.asList(100, 200, 100, 400, 100)
            )
        );

        System.out.println(
            findAmpleCity(
                Arrays.asList(10,  5,    5,   20, 0),
                Arrays.asList(100, 100, 200, 100, 500)
            )
        );
    }
}
