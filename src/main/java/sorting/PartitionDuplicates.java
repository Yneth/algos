package sorting;

import java.util.*;

public class PartitionDuplicates {
    // PARTITIONING AND SORTING AN ARRAY WITH MANY REPEATED ENTRIES
    //  Suppose you need to reorder the elements of a very large array so that equal elements
    // appear together. For example, if the array is{b,a,c, b,d,a,b,d ) then (a,a,b,b,b,c,d,d)
    // is an acceptable reordering, asis(d, d,c,a,a,b,b,b).
    // If the entries are integers, this reordering can be achieved by sorting the array.
    // If the number of distinct integers is very small relative to the size of the array, an
    // efficient approach to sorting the array is to count the number of occurrences of each
    // distinct integer and write the appropriate number of each integer, in sorted order, to
    // the array. When array entries are objects, with multiple fields, only one of which isto
    // be used as a key, the problem is harder to solve.
    //  You aregiven an array ofstudent objects. Eachstudent hasan integer-valued age field
    // that is to be treated as a key. Rearrange the elements of the array so that students of
    // equal age appear together. The order in which different ages appear is not important.
    //  How would your solution change if ages have to appear in sorted order?
    //
    // Solution:
    // - sort
    // Time: O(n log n)
    // Space: O(1)
    //
    // - count elements, the result will not be sorted
    // Time: O(2n) ~= O(n)
    // Space: O(n)
    //
    // Example:
    // (Eric, 14), (Marie, 15), (Skyler, 20), (Lisa, 14), (Luis, 20), (Walter, 21)
    // (Eric, 14), (Lisa, 14), (Luis,14), (Marie, 15), (Skyler, 20), (Walter, 21)
    //
    // Suppose we know counts
    // 14 - 3
    // 15 - 1
    // 20 - 1
    // 21 - 1
    // then
    // we can just iterate over pairs and write to new array each group using offsets
    // 14 [0,2]
    // 15 [3,3]
    // 20 [4,4]
    // 21 [5,5]
    // also we can do it inplace

    static class Student {
        int age;
        String name;

        public Student(int a, String n) {
            this.age = a;
            this.name = n;
        }

        public String toString() {
            return name;
        }
    }

    public static List<Student> partition(List<Student> students) throws Exception {
        Map<Integer, Integer> ageToCount = new HashMap<>();
        for (Student s : students) {
            ageToCount.put(s.age, ageToCount.getOrDefault(s.age, 0) + 1);
        }
        System.out.println(ageToCount);
        int offsetIter = 0;
        Map<Integer, Integer> offsets = new HashMap<>();
        for (Map.Entry<Integer, Integer> e : ageToCount.entrySet()) {
            offsets.put(e.getKey(), offsetIter);
            offsetIter += e.getValue();
        }
        int i = 0;
        while (!ageToCount.isEmpty()) {
            int j = i, nextJ = -1;
            do {
                Student s = students.get(j);
                int offset = offsets.get(s.age);
                Integer k = ageToCount.get(s.age);
                if (k == null) break;

                nextJ = offset + k - 1;
                Collections.swap(students, j, nextJ);

                if (k - 1 == 0) ageToCount.remove(s.age);
                else ageToCount.put(s.age, k - 1);
            } while(j != nextJ);
            i++;
        }
        return students;
    }

    // (Eric, 14), (Lisa, 14), (Luis,14), (Marie, 15), (Skyler, 20), (Walter, 21)
    public static void main(String[] args) throws Exception {
        System.out.println(
            partition(
                Arrays.asList(
                    new Student(21, "Walter"),
                    new Student(15, "Marie"),
                    new Student(15, "Lara"),
                    new Student(14, "Luis"),
                    new Student(20, "Skyler"),
                    new Student(14, "Eric"),
                    new Student(14, "Lisa"),
                    new Student(15, "Hank")
                )
            )
        );
    }
}
