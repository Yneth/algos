package stacks;

import java.util.Deque;
import java.util.LinkedList;

public class EvalReversePolishNotation {

    // Write a program that takes an arithmetical expression in RPN and returns the number
    // that the expression evaluates to.
    //
    // Example
    // 10 200 + 80 *
    // 300 80 *
    //
    // Solution:
    // init stack of values
    // as soon as we reach operator
    // pop two values
    // and apply
    // then push result back
    //
    // Test:
    // 10 10 + 2 * 20 +
    //                 stack
    // 10
    // 10 10
    // 20
    // 2 20
    // 40
    // 20 40
    // 60
    //
    // 10 10 + 10 10 + *
    // 10
    // 10 10
    // 20
    // 10 20
    // 10 10 20
    // 20 20
    // 400


    public int eval(String expr) {
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < expr.length(); i++) {
            if (Character.isDigit(expr.charAt(i))) {
                int v = 0;
                while (Character.isDigit(expr.charAt(i))) {
                    v = v * 10 + Character.getNumericValue(expr.charAt(i++));
                }
                stack.push(v);
            } else if (expr.charAt(i) == '+') {
                stack.push(stack.pop() + stack.pop());
            } else if (expr.charAt(i) == '-') {
                int rval = stack.pop();
                int lval = stack.pop();
                stack.push(lval - rval);
            } else if (expr.charAt(i) == '*') {
                int rval = stack.pop();
                int lval = stack.pop();
                stack.push(lval * rval);
            } else if (expr.charAt(i) == '/') {
                int rval = stack.pop();
                int lval = stack.pop();
                stack.push(lval / rval);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        EvalReversePolishNotation sut = new EvalReversePolishNotation();
        System.out.println(sut.eval("10 10 + 10 10 + *")); // 400
        System.out.println(sut.eval("10 10 + 2 * 20 +")); // 60
    }
}
