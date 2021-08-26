package greedy;

import java.util.*;

public class MajoritySearch {
    //  Several applications require identification of elements in a sequence which occur
    // more than a specified fraction of the total number of elements in the sequence. For
    // example, we may want to identify the users using excessive network bandwidth or
    // IP addresses originating the most Hypertext Transfer Protocol (HTTP) requests. Here
    // we consider a simplified version of this problem.
    //  You are reading a sequence of strings. You know a priori that more than half the
    // strings are repetitions of a single string (the "majority element") but the positions
    // where the majority element occurs are unknown. Write a program that makes a
    // single pass over the sequence and identifies the majority element. For example, if the
    // input is(b,a,c,a,a,b,a,a,c,a), then a is the majority element (it appearsin 6 out of the
    // 10 places).
    //
    // Given:
    //  iterator of int non-null non sorted values
    //
    // Solution:
    // iterate over all chars, maintaining a counter for candidates.
    // each time counter is less than 0 we pick new candidate
    // coutner is updated when candidate = or != to current char
    //
    // init rep counter
    // for each element
    //   if current == candidate
    //      rep_counter++
    //   else rep_counter--
    //
    //   if rep counter < 0
    //     candidate = current
    //
    // return candidate

    public static String findMajority(Iterator<String> iter) {
        int repCount = 0;
        String candidate = "";
        while (iter.hasNext()) {
            String curr = iter.next();
            if (candidate.equals(curr)) {
                repCount++;
            } else {
                repCount--;
            }
            if (repCount < 0) {
                repCount = 0;
                candidate = curr;
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        System.out.println(
            findMajority(
                Arrays.asList("aaabb".split("")).iterator()
            )
        );

        System.out.println(
            findMajority(
                Arrays.asList("aaabbbbc".split("")).iterator()
            )
        );
    }
}
