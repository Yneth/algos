package strings;

import java.util.ArrayList;
import java.util.List;

public class ComputeValidIps {
    // A decimal string is a string consisting of digits between 0 and 9. Internet Protocol
    //     (IP) addresses can be written as four decimal strings separated by periods, e.g.,
    //     192.168.1.201. A careless programmer mangles a string representing an IP address
    // in such a way that all the periods vanish.
    // Write a program that determines where to add periods to a decimal string so that the
    // resulting string is a valid IP address. There may be more than one valid IP address
    // corresponding to a string, in which case you should print all possibilities.
    // For example, if the mangled string is "19216811" then two corresponding IP ad¬
    // dresses are 192.168.1.1 and 19.216.81.1. (There are seven other possible IP addresses
    // for thisstring.)

    // Test:
    // 19216811
    // 1.92.168.11
    // 19.2.168.11
    // 19.21.68.11
    // 19.216.81.1
    // 192.1.68.11
    // 192.16.81.1
    // 192.168.1.1

    // Solution:
    // brute force approach
    // try all possible permutations of dot placements
    // filtering all impossible cases when x0.x1.x2.x3 where 0 <= xn <= 255
    //
    // dp problem
    // dp(i) = curr_ip ++ [dp(i) for i in 0..len]
    //
    // pseudocode:
    // n = 4
    // if n == 0
    //    return
    // for (int i = 0; i < len(word); i++)
    //    digit = subs(word, 0, i)
    //    if digit > 255 || digit < 0
    //       return
    //    dp(subs(word,i), n - 1)

    private List<String> runComputeIps(String word, String currIp, int n, List<String> results) {
        if (n == 0 && word.length() == 0) {
            results.add(currIp);
            return results;
        }
        for (int i = 1; i <= word.length(); i++) {
            int digit = Integer.parseInt(word.substring(0, i));
            if (digit > 255) break;

            String nextIp = "".equals(currIp)
                ? Integer.toString(digit)
                : currIp + "." + digit;

            runComputeIps(word.substring(i), nextIp, n - 1, results);
        }
        return results;
    }

    public List<String> computeIps(String ip) {
        return runComputeIps(ip, "", 4, new ArrayList<>());
    }

    public static void main(String[] args) {
        ComputeValidIps cvi = new ComputeValidIps();
        System.out.println(cvi.computeIps("19216811"));
        System.out.println(cvi.computeIps("255255255255"));
        System.out.println(cvi.computeIps("0000"));
        System.out.println(cvi.computeIps("123123123123"));
        System.out.println(cvi.computeIps("12312311"));
        System.out.println(cvi.computeIps("212111211"));
    }

}
