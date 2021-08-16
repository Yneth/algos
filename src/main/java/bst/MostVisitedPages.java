package bst;

import java.util.*;

public class MostVisitedPages {

    //  You are given a server log file containing billions of lines. Each line contains a
    // number of fields. For this problem, the relevant field is an id denoting the page that
    // was accessed.
    //  Write a function to read the next line from a log file, and a function to find the k
    // most visited pages, where k is an input to the function. Optimize performance for the
    // situation where calls to the two functions are interleaved. You can assume the set of
    // distinct pages is small enough to fit in RAM.
    //  As a concrete example, suppose the log file ids appear in the following order:
    // g,a,t,t,a,a,a,g,t,c,t,a,t, i.e., there are four pages with ids a,c,g,t. After the first 10
    // lines have been read, the most common page is a with a count of 4, and the next most
    // common page ist with a count of 3.
    //
    // Example:
    // g,a,b,c,d
    //
    // Solutions:
    // - maintain a map of counts for each page
    //   then by iterate over all pages and K most visited using a heap
    //   Time: O(|line_count|) + O(|page| log k)
    //
    // - maintain a tree set of counts for each page
    //   Time: O(|lines| log(|pages|))

    static class PageVisits implements Comparable<PageVisits> {
        String page;
        int visits;

        public PageVisits(String page, int count) {
            this.page = page;
            this.visits = count;
        }

        public PageVisits inc() {
            return new PageVisits(page, visits + 1);
        }

        public int compareTo(PageVisits o) {
            int visitsCmp = Integer.compare(this.visits, o.visits);
            return visitsCmp == 0
                ? this.page.compareTo(o.page)
                : visitsCmp;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PageVisits that = (PageVisits) o;
            return visits == that.visits && Objects.equals(page, that.page);
        }

        @Override
        public int hashCode() {
            return Objects.hash(page, visits);
        }

        public String toString() {
            return page + "(" + visits + ")";
        }
    }

    public static List<PageVisits> find(Iterator<String> lines, int k) {
        Map<String, PageVisits> idToVisits = new HashMap<>();
        NavigableSet<PageVisits> candidates = new TreeSet<>();
        while (lines.hasNext()) {
            String pageId = lines.next();
            PageVisits visits = idToVisits.get(pageId);
            if (visits != null) {
                idToVisits.remove(pageId);
                candidates.remove(visits);
            } else {
                visits = new PageVisits(pageId, 0);
            }

            PageVisits newVisits = visits.inc();
            idToVisits.put(pageId, newVisits);
            candidates.add(newVisits);
        }

        List<PageVisits> res = new ArrayList<>();
        Iterator<PageVisits> iter = candidates.descendingIterator();
        while (iter.hasNext() && res.size() < k) {
            res.add(iter.next());
        }
        return res;
    }

    public static void main(String[] args) {
        for (int k = 0; k < 10; k++) {
            System.out.println(find(
                Arrays.asList("a", "b", "a", "c", "d", "a", "f", "g", "k", "k", "d").iterator(),
                k
            ));
        }
    }
}
