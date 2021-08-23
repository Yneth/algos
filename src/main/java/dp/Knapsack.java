package dp;

import java.util.*;

public class Knapsack {
    //  A thief breaksinto a clock store. Each clock has a weight and a value, which are known
    // to the thief. His knapsack cannot hold more than a specified combined weight. His
    // intention is to take clocks whose total value is maximum subject to the knapsack's
    // weight constraint.
    //  His problem is illustrated in Figure 17.8 on the next page. If the knapsack can
    // hold at most 130 ounces, he cannot take all the clocks. If he greedily chooses clocks,
    // in decreasing order of value-to-weight ratio, he will choose P, H,O, B,I, and L in that
    // order for a total value of $669. However, {H,/, O) is the optimum selection, yielding
    // a total value of $695.
    //  Write a program for the knapsack problem that selects a subset of items that has
    // maximum value and satisfies the weight constraint. All items have integer weights
    // and values. Return the value of the subset.
    //
    // Problem:
    // Given:
    //   1 < maxWeight < 2^31-1
    //   weights[i] int
    //   prices[i] int
    // Return optimal solution
    //
    // dp(w, c, item[i..n]) = | if w < 0:                 -inf
    //                        | if w == 0 || j >= |item|: c
    //                        | else:
    //                              max(
    //                                for k in i..n
    //                                  swap(item, max(i - 1, 0), k)
    //                                  dp(w - item[k], c + item[k], item[k..n])
    //                                  swap(item, max(i - 1, 0), k)
    //                              )
    // Test:
    // [[100, 3], [100,2], [100,2], [101,2], [205,3]], maxWeight = 6
    // Solution: [305]
    //
    // dp(6,0,0)
    //   dp(3,100,1)
    //     dp(1,200,2)
    //     dp(1,200,2)
    //     dp(1,201,2)
    //     dp(0,305,2)
    //   dp(2,100,1)
    //   dp(2,100,1)
    //   dp(2,101,1)
    //   dp(3,205,1)
    // lets analyze a graph of dependecies
    // almost the same approach as with score combinations
    //
    // init a matrix of plays (items) as rows
    // and weight as columns
    //
    //  [100, 3], [100,2], [100,2], [101,2], [205,3]
    //
    //          w    0   1     2    3    4    5    6
    // item  0   |   0   0     0  100  100  100  100  [100,3]
    // items 0-1 |   0   0   100  100  100  200  200  +[100,2]
    // items 0-2 |   0   0   100  100  200  200  200  +[100,2]
    // items 0-3 |   0   0   101  101  201  201  201  +[101,2]
    // items 0-4 |   0   0     0  205  205  305  305  +[205,3]
    //
    // we only need 2capcity of the data

    static class Item {
        int price;
        int weight;

        public Item(int p, int w) {
            this.price = p;
            this.weight = w;
        }

        public String toString() {
            return String.format("(%s,%s)", price, weight);
        }
    }

    private static void swap(Item[] items, int i, int j) {
        Item tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    private static int knapsack(Item[] items, int i, int weight, int cost) {
        if (weight < 0) return Integer.MIN_VALUE;
        if (i >= items.length || weight == 0) return cost;

        int result = cost;
        for (int j = i; j < items.length; j++) {
            Item item = items[j];
            swap(items, i, j);
            result = Math.max(
                result,
                knapsack(items, i + 1, weight - item.weight, cost + item.price)
            );
            swap(items, i, j);
        }
        return result;
    }

    public static int knapsack(Item[] items, int maxWeight) {
        return knapsack(items, 0, maxWeight, 0);
    }

    public static int knapsackIter(Item[] items, int maxWeight) {
        int[] dp = new int[maxWeight];
        for (int i = 0; i < items.length; i++) {
            int[] next = new int[maxWeight];

            Item item = items[i];
            for (int w = 1; w <= maxWeight; w++) {
                int withoutCurrent = i > 0 ? dp[w - 1] : 0;
                int withCurrent = 0;

                if (item.weight <= w) {
                    int remainingWeight = w - item.weight - 1;
                    withCurrent = item.price + (remainingWeight > 0 ? dp[remainingWeight] : 0);
                }

                next[w - 1] = Math.max(withoutCurrent, withCurrent);
            }
            System.out.println(Arrays.toString(next));

            dp = next;
        }
        return dp[maxWeight - 1];
    }

    public static void main(String[] args) {
        System.out.println(
            knapsackIter(new Item[]{
                new Item(100000, 6),
                new Item(300, 1),
                new Item(400, 1),
                new Item(205, 3)
            }, 6)
        );
    }
}
