package stacksnqueues;

import java.util.Deque;
import java.util.LinkedList;

public class CheckBrackets {
    // Write a program that tests if a string made up of the characters'(', ')', '[', and"}'
    // is well-formed.
    //
    // Example:
    // {{[]}} ok
    // {[}]   bad
    // {[]{}} ok
    // {      bad
    // {[]    bad
    //
    // Solution:
    // init stack
    // when hit opening bracket
    //  push closing one to the stack
    //  as soon as we hit any non opening bracket
    //  compare if the one in the stack is equal to current
    //  if not return false
    // return stack.isEmpty()
    //
    // Test:
    // {[}]
    // ^    }
    //  ^   ]}
    //   ^  fail } != ]
    // Test:
    // {
    // ^   }
    // fail, stack is not empty

    public boolean isValid(String str) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '[') stack.push(']');
            else if (ch == '{') stack.push('}');
            else if (ch == '(') stack.push(')');
            else if (ch != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        CheckBrackets sut = new CheckBrackets();
        System.out.println(sut.isValid("{"));
        System.out.println(sut.isValid("{[]}"));
        System.out.println(sut.isValid("{[}]"));
        System.out.println(sut.isValid("{[]()}"));
    }

}
