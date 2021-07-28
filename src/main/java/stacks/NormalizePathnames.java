package stacks;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class NormalizePathnames {
    // Write a program which takes a pathname, and returns the shortest equivalent path¬
    // name. Assume individual directories and files have names that use only alphanu¬
    // meric characters. Subdirectory names may be combined using forward slashes (/),
    // the current directory (.), and parent directory (..).
    //
    // Example:
    // /usr/bin/scripts/test.sh  => scripts/test.sh
    // scripts/test.sh           => scripts/test.sh
    // scripts/../scripts/./test.sh => scripts/test.sh
    //
    // Solution
    //
    // scripts/../scripts/./test.sh
    // ^                             scripts
    //         ^                     []
    //            ^                  scripts
    //                    ^          scripts
    //                      ^        test.sh scripts
    // init stack
    // iterate over path parts
    //    if .. pop
    //    if .  skip
    //    else push
    // sb = ""
    // for (String val stack.reverseIterator())
    //
    // return sb


    public String normalize(String path) {
        boolean absolute = path.startsWith("/");
        String[] parts = path.split("/");

        Deque<String> stack = new LinkedList<>();
        for (String part : parts) {
            if ("..".equals(part)) {
                if (!stack.isEmpty()) stack.pop();
            } else if (".".equals(part) || "".equals(part)) {
                // skip
            } else {
                stack.push(part);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (absolute) sb.append("/");
        for (Iterator<String> it = stack.descendingIterator(); it.hasNext(); ) {
            String part = it.next();
            sb.append(part);
            if (it.hasNext()) sb.append("/");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        NormalizePathnames sut = new NormalizePathnames();
        System.out.println(sut.normalize("test/../scripts/./test.sh"));
        System.out.println(sut.normalize("/scripts/../scripts/./test.sh"));
        System.out.println(sut.normalize("/../scripts/../scripts/./test.sh"));
    }
}
