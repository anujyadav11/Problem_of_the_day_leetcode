/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved Minimum Operations to Sort a String using greedy boundary analysis and character frequencies in O(n) time and O(1) auxiliary space.
/* “I first check whether the string is already sorted and handle strings of length two separately. For longer strings, 
    I find the minimum and maximum characters and count their frequencies. The positions of these extreme characters determine whether sorting requires one, two, or three operations.” */

class Solution {
    public int minOperations(String s) {
        int n = s.length();
        // A single character is already sorted.
        if (n == 1) {
            return 0;
        }
        // An unsorted string of length 2 cannot be sorted
        // because the only substring containing both characters
        // would be the entire string.
        if (n == 2) {
            return s.charAt(0) <= s.charAt(1) ? 0 : -1;
        }
        int[] count = new int[26];
        char minChar = 'z';
        char maxChar = 'a';
        boolean sorted = true;
        // Count character frequencies and find minimum and maximum.
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            count[ch - 'a']++;
            minChar = (char) Math.min(minChar, ch);
            maxChar = (char) Math.max(maxChar, ch);
            // Check whether the string is already sorted.
            if (i > 0 && s.charAt(i - 1) > ch) {
                sorted = false;
            }
        }
        // No operations are needed if the string is already sorted.
        if (sorted) {
            return 0;
        }
        char first = s.charAt(0);
        char last = s.charAt(n - 1);
        // One operation is sufficient if the first character
        // is the minimum or the last character is the maximum.
        if (first == minChar || last == maxChar) {
            return 1;
        }
        // Two operations are sufficient unless the first character
        // is the unique maximum and the last is the unique minimum.
        if (first != maxChar || last != minChar) {
            return 2;
        }
        // In the remaining case, duplicates of the minimum or
        // maximum allow sorting in two operations.
        if (count[minChar - 'a'] > 1 ||
            count[maxChar - 'a'] > 1) {
            return 2;
        }
        // Unique maximum at the beginning and unique minimum
        // at the end require three operations.
        return 3;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
