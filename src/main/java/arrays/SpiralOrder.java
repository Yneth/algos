package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SpiralOrder {
    // COMPUTE THE SPIRAL ORDERING OF A 2D ARRAY
    // Given matrix a with size n*n
    // Return its' spiral order
    //
    // Example:
    // 1 2 3
    // 4 5 6
    // 7 8 9
    // Result:
    // 1 2 3 6 9 8 7 4 5
    // Solution:
    // Imagine if we are operating on lists
    //   algorithm would be the following:
    //     while matrix.isNotEmpty()
    //       pop first list
    //       transpose the rest
    //       reverse
    //
    // Test:
    //    using example
    //    #1 iteration
    //        res = [1 2 3]   matrix=[4 7]
    //                               [5 8]
    //                               [6 9]
    //    #2 iteration
    //        res = [1 2 3 6 9] matrix=[4 5]
    //                                 [7 8]
    //    #3 iteration
    //        res = [1 2 3 6 9 7 8] matrix=[4 5]
    //
    // 30 minutes

    private static List<List<Integer>> transpose(List<List<Integer>> matrix) {
        if (matrix.isEmpty()) return Collections.emptyList();
        int m = matrix.size();
        int n = matrix.get(0).size();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (result.size() == j) result.add(new ArrayList<>());
                result.get(j).add(matrix.get(i).get(j));
            }
        }
        return result;
    }

    public List<Integer> solve(List<List<Integer>> m) {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> tempMatrix = new ArrayList<>(m);
        System.out.println(tempMatrix);
        while (!tempMatrix.isEmpty()) {
            result.addAll(tempMatrix.get(0));
            tempMatrix.remove(0);

            tempMatrix = transpose(tempMatrix);
            Collections.reverse(tempMatrix);
        }
        return result;
    }

    public static void main(String[] args) {
        SpiralOrder so = new SpiralOrder();
        System.out.println(
            so.solve(Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
            ))
        );
    }


}
