package arrays;


// solution:
//  we need to track minPrice and maxProfit
//  as soon as new minPrice is found, replace the old one
//  or update maxProfit

public class BuyAndSellStock {

    public int maxProfit(int[] arr) {
        int maxProfit = Integer.MIN_VALUE;
        int minPrice = arr[0];
        for (int i = 1; i < arr.length; i++) {
            minPrice = Math.min(minPrice, arr[i]);
            maxProfit = Math.max(maxProfit, arr[i] - minPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        BuyAndSellStock bass = new BuyAndSellStock();
        System.out.println(bass.maxProfit(new int[]{1, 2, 3, 4}));
        System.out.println(bass.maxProfit(new int[]{7,3,2,7,0,10}));
        System.out.println(bass.maxProfit(new int[]{100, 0, 0, 4}));
    }

}
