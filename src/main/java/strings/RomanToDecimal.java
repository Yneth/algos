package strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RomanToDecimal {
    // The Roman numeral representation of positive integers uses the symbols
    // I,V,X,L,C,D,M. Each symbol represents a value, with I being 1, V being 5, X being
    // 10, L being 50, C being 100, D being 500, and M being 1000.
    // In this problem we give simplified rulesfor representing numbersin thissystem.
    // Specifically, define a string over the Roman number symbols to be a valid Roman
    // numberstringifsymbolsappearin nonincreasingorder,with thefollowingexceptions
    // allowed:
    //     • I can immediately precede V and X.
    //     • X can immediately precede L and C.
    //     • C can immediately precede D and M.
    //     Back-to-back exceptions are not allowed, e.g., IXC isinvalid, asis CDM.
    // A valid complex Roman number string represents the integer which is the sum
    // of the symbols that do not correspond to exceptions; for the exceptions, add the
    // difference of the larger symbol and the smallersymbol.
    // For example, the strings "XXXXXfflllllH", "LVIIII" and "LIX" are valid Roman
    // number strings representing 59. The shortest valid complex Roman number string
    // corresponding to the integer 59 is "LIX".
    // Write a program which takes asinput a valid Roman numberstring s and returnsthe
    // integer it corresponds to.

    // Solution:
    // As far as I can see
    // lower value numbers are always at the end
    // this way we can tell that number is sorted
    // from higher to lower
    //
    // I will iterate from the beginning
    //   if current is lower than next
    //      fail if is not possible
    //      add value of the (curr,next) X-1
    //   else add current to the total
    //
    // Test:
    // XXIXII
    // ^       10
    //  ^      20
    //   ^     29
    //      ^  30

    public static final Map<Character, Integer> mapping = new HashMap<>();

    static {
        mapping.put('I', 1);
        mapping.put('V', 5);
        mapping.put('X', 10);
        mapping.put('L', 50);
        mapping.put('C', 100);
        mapping.put('D', 500);
        mapping.put('M', 1000);
    }

    public static final Map<Character, String> predMapping = new HashMap<>();

    static {
        predMapping.put('I', "VX");
        predMapping.put('X', "LC");
        predMapping.put('C', "DM");
    }

    public int roman2int(String roman) {
        int result = 0;
        for (int i = 0; i < roman.length(); i++) {
            char c = roman.charAt(i);
            int curr = mapping.get(c);
            if (i < roman.length() - 1) {
                char n = roman.charAt(i + 1);
                int next = mapping.get(n);
                if (curr < next) {
                    if (!predMapping.get(c).contains(n + "")) {
                        throw new RuntimeException("fail");
                    }
                    result += next - curr;
                    i += 1;
                } else {
                    result += curr;
                }
            } else {
                result += curr;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        RomanToDecimal r2d = new RomanToDecimal();
        for (String s : Arrays.asList(
            "XXII",
            "XXXXXIIIIIIIII",
            "XXXXXVIIII",
            "LVIIII",
            "LIX"
        )) {
            System.out.println(r2d.roman2int(s));
        }
    }

}
