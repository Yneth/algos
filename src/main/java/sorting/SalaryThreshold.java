package sorting;

import java.util.*;

public class SalaryThreshold {
    //  You are working in the finance office for ABC corporation. ABC needs to cut payroll
    // expenses to a specified target. The chief executive officer wants to do this by putting
    // a cap on last year'ssalaries. Every employee who earned more than the cap last year
    // will be paid the cap this year; employees who earned no more than the cap will see
    // no change in their salary.
    //  For example, if there were five employees with salaries last year were
    // $90,$30,$100,$40, and $20, and the target payroll this year is $210, then 60 is a
    // suitable salary cap, since 60 + 30 + 60 + 40 + 20 = 210.
    //  Design an algorithm for computing the salary cap, given existing salaries and the
    // target payroll.
    // 20 30 40 90 100 = 280
    // average does not work
    //
    // what if we sort
    // then start from the end
    // and sum up until we go over cap
    // does not give us anything
    //
    // what if we init two indices at start/end
    //
    // 20 30 40 90 100
    // a  b  c  b  a
    // a = 120
    // b = 120
    // c = 40
    // cap = 210
    // what if we choose the next cap as half of the sum
    // like next cap = a / 2 = 60
    //
    // 20 30 40 60 60
    // a = 80
    // b = 90
    // c = 40

    // what if we apply binary search with l = arr[0], arr[-1]
    // mid = (l + r) >>> 1 = 60
    // if sum < cap
    //   l = mid
    // else if sum > cap
    //   r = mid
    // else return mid

    private static int sum(List<Integer> salaries, int cap) {
        int res = 0;
        for (int i = 0; i < salaries.size(); i++) {
            res += Math.min(cap, salaries.get(i));
        }
        return res;
    }

    public static int compute(List<Integer> salaries, int target) {
        salaries.sort(Integer::compare);

        int l = 0, r = salaries.get(salaries.size() - 1);
        while (l <= r) {
            int m = (l + r) >>> 1;
            int sum = sum(salaries, m);
            int cmp = Integer.compare(sum, target);
            if (cmp > 0) { r = m; }
            else if (cmp < 0) { l = m; }
            else return m;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(
            compute(Arrays.asList(
                20, 30, 40, 90, 100
            ), 210)
        );
    }
}
