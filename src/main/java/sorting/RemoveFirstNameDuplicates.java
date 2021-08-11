package sorting;

import java.util.*;

public class RemoveFirstNameDuplicates {
    // REMOVE FIRST-NAME DUPLICATES
    //   Design an efficient algorithm for removing all first-name duplicates from an array. For
    // example, if the input is ((Ian,Botham),(David,Gower),(Ian,Bell),(Ian,Chappell)),
    // one result could be ((Ian,Bell),(David,Gower)); ((David,Gower),(Ian,Botham))
    // would also be acceptable.
    //
    // Solution:
    // - sort by name
    //   then remove duplicates
    //   Time: O(n log n)
    //   Space: O(n)  BUT we can get  O(1)
    //
    // - use a hash map for the first name
    //   Time: O(n)
    //   Space: O(n)

    static class Human {
        String firstName;
        String lastName;
        public Human(String fn, String ln) {
            this.firstName = fn;
            this.lastName = ln;
        }
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    private static final Comparator<Human> FIRST_NAME_CMP = (h0, h1) -> {
        return h0.firstName.compareTo(h1.firstName);
    };

    public static List<Human> compute(List<Human> names) {
        names.sort(FIRST_NAME_CMP);
        int writeIdx = 0;
        // a a a a b b c
        // ^ ^
        // ^ x x x j
        //         ^ x ^
        //             ^
        for (int i = 0; i < names.size() - 1; i++) {
            int j = i + 1;
            while (j < names.size()
                && names.get(i).firstName.equals(names.get(j).firstName)) {
                names.set(j++, null);
            }
            names.set(writeIdx, names.get(i));
            writeIdx++;
            i = j - 1;
        }
        return names.subList(0, writeIdx);
    }

    public static void main(String [] args) {
        System.out.println(compute(
            Arrays.asList(
                new Human("Ian", "Botham"),
                new Human("David","Gower"),
                new Human("Ian","Bell"),
                new Human("Ian","Chappell")
            )
        ));
    }
}
