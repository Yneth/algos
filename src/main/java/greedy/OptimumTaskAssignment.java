package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptimumTaskAssignment {

    //  We consider theproblem of assigning tasksto workers. Each worker mustbeassigned
    // exactly two tasks. Each task takes a fixed amount of time. Tasks are independent, i.e.,
    // there are no constraints of the form "Task 4 cannot start before Task 3 is completed."
    // Any task can be assigned to any worker.
    //  We want to assign tasks to workers so as to minimize how long it takes before all
    // tasks are completed. For example, if there are 6 taskswhosedurations are 5, 2, 1, 6,4,4
    // hours, then an optimum assignment is to give the first two tasks (i.e., the tasks with
    // duration 5 and 2) to one worker, the next two (1 and 6) to another worker, and the
    // last two tasks (4 and 4) to the last worker. For this assignment, all tasks will finish
    // after max(5 + 2, 1 + 6,4 + 4) = 8 hours.
    //  Design an algorithm that takes asinput a set of tasks and returns an optimum assign¬
    // ment.
    //
    // Example:
    // 5 2 1 6 4 4
    // 1 2 4 4 5 6
    //
    // 1 6
    // 2 5
    // 4 4
    // we can actually sort the array
    // and pick tasks from both ends
    //
    // counter example
    // 1 100 200 1000
    // 1000 1
    // 100 200
    //
    // Assumptions:
    //  array of size 2n

    static class Pair {
        int task0, task1;
        public Pair(int i0, int i1) {
            this.task0 = i0;
            this.task1 = i1;
        }
        public String toString() {
            return String.format("(%s,%s)", task0, task1);
        }

    }

    public static List<Pair> find(int[] arr) {
        Arrays.sort(arr);
        List<Pair> result = new ArrayList<>();
        for (int i = 0; i < arr.length / 2; i++) {
            result.add(new Pair(arr[i], arr[arr.length - 1 - i]));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(find(new int[] {5,2,1,6,4,4}));
    }

}
