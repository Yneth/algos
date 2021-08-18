package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateMatchedParens {
    //  Strings in which parens are matched are defined by the following three rules:
    // • The empty string, "", is a string in which parens are matched.
    // • The addition of a leading left parens and a trailing right parens to a string in
    // which parens are matched resultsin a string in which parens are matched. For
    // example,since "(())()" is a string with matched parens,so is "((())())".
    // • The concatenation of two stringsin which parens are matched isitself a string
    // in which parens are matched. For example, since "(())()" and "()" are strings
    // with matched parens,so is "(())()()".
    // For example, the set of strings containing two pairs of matched parens
    // is ((()),()()), and the set of strings with three pairs of matched parens is
    // «(())),(00),(0)0, 0(0),()()()}•
    //
    // Write a program that takes as input a number and returns all the strings with that
    // number of matched pairs of parens
    //
    // example:
    // n=3
    //  (
    //  >(
    //   >(
    //   >)
    //
    //  >)
    // at each step we have two options
    //  open a new one
    //  close current
    // if we decide to close, n stays the same
    // otherwise decrement n

    // curr = {str, n}
    // gen_parens(curr, n, k, res)
    //  if n == 0 && k == 0: res.add(")" * k) return
    //  curr = curr + "("
    //  gen_parens(curr, n - 1, k + 1, res)
    //  gen_parens(curr + ")", n - 1, k, res)
    //  return res

    // gen_parens(,2, 0)
    //   gen_parens("(",1,1)
    //     gen_parens("((",0,2)
    //     gen_parens("(()",0,2)
    //   gen_parens("()",1,0)
    //     gen_parens("()(",0,1)
    //     gen_parens("()()",0,0)

    private static List<String> gen(StringBuilder sb, int o, int c, int n, List<String> result) {
        if (o == n && c < n) {
            gen(sb.append(")"), o, c + 1, n, result);
            sb.deleteCharAt(sb.length() - 1);

        } else if (o == n && c == n) {
            result.add(sb.toString());
        } else if (o < n && c < n) {
            gen(sb.append("("), o + 1, c, n, result);
            sb.deleteCharAt(sb.length() - 1);

            if (c < o) {
                gen(sb.append(")"), o, c + 1, n, result);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return result;
    }

    public static List<String> gen(int n) {
        return gen(new StringBuilder(), 0, 0, n, new ArrayList<>());
    }

    public static void main(String[] args) {
        System.out.println(gen(3));
        System.out.println(gen(3).size());
    }

}
