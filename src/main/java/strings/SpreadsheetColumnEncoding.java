package strings;

public class SpreadsheetColumnEncoding {
    // Spreadsheets often use an alphabetical encoding of the successive columns. Specif¬
    // ically, columns are identified by "A", "B", "C", ..., "X", "Y", "Z", "AA", "AB", ...,
    // "ZZ", "AAA", "AAB", ....
    // Implement a function that converts a spreadsheet column id to the corresponding
    // integer, with "A" corresponding to 1. For example, you should return 4 for "D", 27
    // for "AA", 702 for "ZZ", etc. How would you test your code?
    //
    // Solution:
    //  iterate string
    //    map character to an integer by subtracting 'A'
    //     charI -> charI - 'A' * i * 26
    //    sum up all mapped values
    // Test:
    // AA
    // 1 + 26*1
    // ZZ
    // 26 + 26*26

    public int columnToId(String column) {
        int res = 0;
        for (int i = 0; i < column.length(); i++) {
            res = res * 26 + (column.charAt(i) - 'A' + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        SpreadsheetColumnEncoding sce = new SpreadsheetColumnEncoding();
        System.out.println(sce.columnToId("AA"));
        System.out.println(sce.columnToId("ZZ"));
        System.out.println(sce.columnToId("ZZZ"));
    }

}
