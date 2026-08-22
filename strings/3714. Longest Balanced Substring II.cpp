/*********************************************** JAVA **************************************************/

Optimal Solution - Prefix-difference hashing combined with multi-case handling to find longest balanced substring in linear time.
                   Equal counts can be transformed into equal prefix differences — repeating difference vectors indicate balanced substrings.

class Solution {
    // Pair used to store differences between counts
    static class Pair {
        int d1, d2;
        Pair(int d1, int d2) {
            this.d1 = d1;
            this.d2 = d2;
        }
        // Needed so HashMap can compare keys properly
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return d1 == p.d1 && d2 == p.d2;
        }
        // Hash function for hashmap storage
        @Override
        public int hashCode() {
            return 31 * d1 + d2;
        }
    }
    public int longestBalanced(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int res = 0;
        // ---------------- CASE 1 ----------------
        // Longest substring where all characters are identical
        int cur = 1;
        for (int i = 1; i < n; i++) {
            if (c[i] == c[i - 1]) {
                cur++;
            } else {
                res = Math.max(res, cur);
                cur = 1;
            }
        }
        res = Math.max(res, cur);
        // ---------------- CASE 2 ----------------
        // Longest substring containing exactly two characters with equal count
        res = Math.max(res, find2(c, 'a', 'b'));
        res = Math.max(res, find2(c, 'a', 'c'));
        res = Math.max(res, find2(c, 'b', 'c'));
        // ---------------- CASE 3 ----------------
        // Longest substring where counts of a, b, c are all equal
        int ca = 0, cb = 0, cc = 0;
        Map<Pair, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (c[i] == 'a') ca++;
            else if (c[i] == 'b') cb++;
            else cc++;
            // If equal counts directly
            if (ca == cb && ca == cc)
                res = Math.max(res, ca + cb + cc);
            // Store difference pattern
            Pair key = new Pair(ca - cb, ca - cc);
            Integer prev = mp.get(key);
            // If same difference seen before → balanced substring exists
            if (prev != null) {
                res = Math.max(res, i - prev);
            } else {
                mp.put(key, i);
            }
        }
        return res;
    }
    // Finds longest substring where two characters appear equal times
    private int find2(char[] c, char x, char y) {
        int n = c.length;
        int max_len = 0;
        // Difference array storing earliest index of each diff
        int[] first = new int[2 * n + 1];
        Arrays.fill(first, -2);
        int clear_idx = -1;
        int diff = n;
        // diff == 0 at index -1
        first[diff] = -1;
        for (int i = 0; i < n; i++) {
            // Reset if third character appears
            if (c[i] != x && c[i] != y) {
                clear_idx = i;
                diff = n;
                first[diff] = clear_idx;
            } else {
                if (c[i] == x) diff++;
                else diff--;
                // If first occurrence
                if (first[diff] < clear_idx) {
                    first[diff] = i;
                }
                // Balanced substring found
                else {
                    max_len = Math.max(max_len, i - first[diff]);
                }
            }
        }
        return max_len;
    }
}

Time Complexity :- O(n).
Space Complexity :- O(n).
