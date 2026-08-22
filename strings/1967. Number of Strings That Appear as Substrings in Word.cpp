/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts patterns that appear as substrings in word using Java's built-in contains check.
/* "String.contains(s) internally calls indexOf(s) != -1 — same complexity, cleaner syntax. 
    For large inputs, KMP or Aho-Corasick can reduce to O(n + sum of pattern lengths) by preprocessing. 
    For this problem size, the built-in O(n×m) check is sufficient and most readable." */

class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        for (String s : patterns) {
            // check if pattern s is a substring of word
            if (word.contains(s))
                count++;
        }
        return count;
    }
}

// Time Complexity :- O(p * n * m).— p patterns, n = word length, m = pattern length for each contains check
// Space Complexity :- O(1).
