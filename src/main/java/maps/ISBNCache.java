package maps;

import java.util.LinkedHashMap;
import java.util.Map;

public class ISBNCache {
    // The International Standard Book Number (ISBN) is a unique commercial book iden-
    // tifier. It is a string of length 10. The first 9 characters are digits; the last character
    // is a check character. The check character is the sum of the first 9 digits, modulo 11,
    // with 10 represented by 'X'. (Modern ISBNs use 13 digits, and the check digit is taken
    // modulo 10; this problem is concerned with 10-digit ISBNs.)
    //
    // Create a cache for looking up prices of books identified by their ISBN. You implement
    // lookup, insert, and remove methods. Use the Least Recently Used (LRU) policy for
    // cache eviction. If an ISBN is already present, insert should not change the price, but
    // it should update that entry to be the most recently used entry. Lookup should also
    // update that entry to be the most recently used entry.
    //
    // Solution:
    // - how should the solution look like?
    //    is it a class that implements an API for ISBNCache
    //    with put remove methods?
    //   yes
    //
    // - am I allowed to use LinkedHashMap instead of custom LRU?
    //   yes
    //

    private final LinkedHashMap<String, Integer> cache;

    public ISBNCache(int capacity) {
        this.cache = new LinkedHashMap<>( capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return this.size() > capacity;
            }
        };
    }

    public void insert(String isbn, int price) {
        Integer val = cache.get(isbn);
        if (val == null) {
            cache.put(isbn, price);
        }
    }

    public Integer get(String isbn) {
        return cache.get(isbn);
    }

    public void remove(String isbn) {
        cache.remove(isbn);
    }
}
