package maps;

import java.util.*;
import java.util.stream.Collectors;

public class KMostFrequentQueries {

    // You are given a log file containing search queries. Each query is a string, and queries
    // are separated by newlines. Diverse applications,such as autocompletion and trend
    // analysis, require computing the most frequent queries. In the abstract, you are to
    // solve the following problem.
    // You are given an array of strings. Compute the k strings that appear most frequently
    // in the array.
    //
    // Solution:
    // - build a map of frequencies, then sort, and pick last k elements
    //   Time: O(n log n)
    // - slightly better approach would be to use min heap instead of sorting
    //   Time: O(n log k)

    static class Entry implements Comparable<Entry> {
        String query;
        int count;
        public Entry(String q, int c) {
            this.query = q;
            this.count = c;
        }
        @Override
        public int compareTo(Entry that) {
            return Integer.compare(this.count, that.count);
        }
        public String getQuery() {
            return query;
        }
    }

    public static List<String> find(String[] queries, int k) {
        Map<String, Integer> frequencies = new HashMap<>();
        for (String q : queries) {
            frequencies.put(q, frequencies.getOrDefault(q, 0) + 1);
        }
        PriorityQueue<Entry> minHeap = new PriorityQueue<>(k + 1);
        for (Map.Entry<String, Integer> e : frequencies.entrySet()) {
            minHeap.add(new Entry(e.getKey(), e.getValue()));
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        return new ArrayList<>(minHeap).stream()
            .map(Entry::getQuery)
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(find(new String[] {"aaaa", "bbbb", "aa", "aaaa", "bbb", "bbb", "aa", "aa", "aa"}, 2));
        System.out.println(find(new String[] {"aaaa", "bbbb", "aa", "aaaa", "bbb", "bbb", "aa", "aa", "aa"}, 3));
    }

}
