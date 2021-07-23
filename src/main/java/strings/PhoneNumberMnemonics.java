package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class PhoneNumberMnemonics {
    // Given a phone number, return all possible character sequences
    //   phoneNumber - string of digits
    //
    //   each digit correspond to a specific three or four chars
    //   as it is on old phones, except 1 and 0
    //
    //   2 - a b c
    //   3 - d e f
    //   4 - . . .
    //   .
    //   7 - . . . .
    //   8 - . . .
    //   9 - . . . .

    // Solution:
    // I think we can solve it by
    // reducing the task at each step
    //
    //   given the phone number 234
    //     we take 2 and generate three subtasks
    //     repeat the same for each subtask
    // test:
    // 25
    // ^
    // a
    // b
    // c
    //  ^
    // aj
    // ak
    // al
    // bj
    // bk
    // bl
    // ...

    public static final String[] MAPPING =
        new String[]{"ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    public List<String> solve(String number) {
        Stack<String> stack = new Stack<>();
        List<String> result = new ArrayList<>();

        stack.push(number);
        do {
            String num = stack.pop();
            int digit = num.charAt(0) - '2';

            if (result.isEmpty()) {
                result.addAll(
                    MAPPING[digit]
                        .chars()
                        .mapToObj(Character::toString)
                        .collect(Collectors.toList()));
            } else {
                List<String> tempRes = new ArrayList<>();
                for (String r : result) {
                    for (char c : MAPPING[digit].toCharArray()) {
                        tempRes.add(r + c);
                    }
                }
                result.addAll(tempRes);
            }


            stack.push(num.substring(1));
        } while (!stack.isEmpty() && stack.peek().length() >= 1);
        return result;
    }

    public static void main(String[] args) {
        PhoneNumberMnemonics pnm = new PhoneNumberMnemonics();
        for (String s : Arrays.asList(
            "24",
            "2276696")) {
            System.out.println(pnm.solve(s));
        }
    }

}
