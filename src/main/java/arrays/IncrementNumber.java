package arrays;

import java.util.Arrays;

public class IncrementNumber {

    // solutions:
    // - iterate from the end
    //   increment and set boolean hasRemainder
    //   if hasRemainder increment
    // tests:
    // [1, 2, 9]
    // -> [1, 2, 3] hasRemainder = true
    // -> [1, 3, 0] complete
    // [9,9,9]
    // -> [9,9,0] hasRemainder = true
    // -> [9,0,0] hasReminder = true
    // -> [0,0,0] hasReminder = true
    // -> [1,0,0,0]
    public int[] increment(int[] number) {
        boolean hasRemainder = true;
        for (int i = number.length - 1; i >= 0 && hasRemainder; i--) {
            number[i] = (number[i] + 1) % 10;
            hasRemainder = number[i] % 10 == 0;
        }
        int[] result = number;
        if (hasRemainder) {
            int[] tmp = new int[result.length + 1];
            System.arraycopy(result, 0, tmp, 1, result.length);
            tmp[0] = 1;
            result = tmp;
        }
        return result;
    }

    public static void main(String[] args) {
        IncrementNumber in = new IncrementNumber();
        System.out.println(Arrays.toString(in.increment(new int[] {1, 2, 3})));
        System.out.println(Arrays.toString(in.increment(new int[] {1, 2, 9})));
        System.out.println(Arrays.toString(in.increment(new int[] {9, 9, 9})));
    }
}
