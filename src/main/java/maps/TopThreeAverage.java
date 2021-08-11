package maps;

import java.util.*;

public class TopThreeAverage {
    // COMPUTE THE AVERAGE OF THE TOP THREE SCORES
    //     Student test scores are recorded in a file. Each line consists of a student ID, which is
    // an alphanumeric string, and an integer between 0 and 100, inclusive.
    //     Write a program which takes as input a file containing test scores and returns the
    // student who hasthe maximum score averaged across his or her top three tests. If the
    // student hasfewer than three test scores, ignore thatstudent.

    // Solution:
    //   maintain a map of student entry occurrences. (sum and count)
    //   plus use min heap to keep track of top K students.
    //
    // Test:
    //  ("a", 5), ("a", 6), ("b", 4), ("a",10),("c",100),("d",11)
    //  for the sake of the test decrease min number of tests to 1
    //  ^ a5,1
    //            ^a11,2    ^b4,1     ^a21,3   ^c100,1   ^d11,1
    //    [a]     [a]        [a,b]    [a,b]    [a,b,c]    [acd]
    //

    static class StudentRecord {
        int count;
        int scoreSum;
        String id;
        public StudentRecord(List<Object> pair) {
            this.count = 1;
            this.scoreSum = (Integer) pair.get(1);
            this.id = (String) pair.get(0);
        }
        public StudentRecord addScore(int score) {
            this.count++;
            this.scoreSum += score;
            return this;
        }
        public double average() {
            return (double) scoreSum / count;
        }
        public String toString() {
            return id + "(" + average() + ")";
        }
    }

    private static List<Object> student(String id, Integer score) {
        return Arrays.asList(id, score);
    }

    public static List<StudentRecord> find(Iterator<List<Object>> scores) {
        PriorityQueue<StudentRecord> minHeap = new PriorityQueue<>(4, (t, o) -> {
            return Double.compare(t.average(), o.average());
        });
        Map<String, StudentRecord> students = new HashMap<>();

        while (scores.hasNext()) {
            List<Object> idAndScore = scores.next();
            StudentRecord record = students.get((String) idAndScore.get(0));
            if (record != null) {
                record.addScore((Integer) idAndScore.get(1));
            } else {
                record = new StudentRecord(idAndScore);
            }
            students.put((String) idAndScore.get(0), record);

            if (record.count >= 3) {
                minHeap.add(record);
                if (minHeap.size() > 3) {
                    minHeap.remove();
                }
            }
        }
        return new ArrayList<>(minHeap);
    }

    public static void main(String[] args) {
        //  ("a", 5), ("a", 6), ("b", 4), ("a",10),("c",100),("d",11)
        System.out.println(
            find(Arrays.asList(
            student("a", 5),
            student("a", 6),
            student("b", 4),
                student("b", 4),
                student("b", 4),
            student("a",10),
            student("c",100),
                student("c",100),
                student("c",100),
            student("d",11),
                student("d",11),
                student("d",11)
        ).iterator()));
        System.out.println(
            find(
                Arrays.asList(
                    student("a", 0),
                    student("a", 0),
                    student("a", 0),
                    student("b", 0),
                    student("b", 0),
                    student("b", 1),
                    student("c", 1),
                    student("c", 1),
                    student("c", 1),
                    student("h", 1),
                    student("h", 10),
                    student("h", 11)
                ).iterator()
            )
        );
    }
}
