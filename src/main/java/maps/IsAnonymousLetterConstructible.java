package maps;

import java.util.HashMap;
import java.util.Map;

public class IsAnonymousLetterConstructible {
    // Write a program which takes text for an anonymous letter and text for a magazine
    // and determines if it is possible to write the anonymous letter using the magazine.
    // The anonymous letter can be written using the magazine if for each character in the
    // anonymous letter, the number of timesit appearsin the anonymousletter is no more
    // than the number of times it appearsin the magazine
    //
    // Example:
    // letter = money 150k
    // magazine = Abe tried to move to his parents, but it cost 100.5 dollars to do the cleaning.
    // result: false
    //
    // letter = money 250k
    // mmmmmoonnneeeyyy22255000kk
    // result: yes
    //
    // SOlution:
    //   build a map of occurrences for the letter.
    //   Do the linear scan in with premature exit if all occurrences are met.

    public static boolean test(String letter, String magazine) {
        Map<Character, Integer> occurrences = new HashMap<>();
        for (char ch : letter.toCharArray()) {
            if (Character.isWhitespace(ch)) continue;
            occurrences.put(ch, occurrences.getOrDefault(ch, 0) + 1);
        }
        for (char ch: magazine.toCharArray()) {
            Integer o = occurrences.get(ch);
            if (o != null) {
                o--;
                if (o == 0) occurrences.remove(ch);
                else        occurrences.put(ch, o);
            }
            if (occurrences.isEmpty()) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(test("money 150k", "mmmmmmoooooooonnnneeeeeyyyy111555000000k"));
        System.out.println(test("money 150k", "150k"));
        System.out.println(test("money 150k", "money"));
        System.out.println(test("money 150k", ""));
    }
}
