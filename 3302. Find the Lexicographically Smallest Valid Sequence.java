/*********************************************** JAVA **************************************************/

// Optimal Solution - Use suffix matching and greedy subsequence selection to find a valid index sequence with at most one character mismatch.
/* “I first scan word1 from right to left to determine the latest valid positions for the suffix of word2. Then I scan word1 from left to right and greedily select indices for word2.
    Characters normally have to match, but I can use one mismatch if the remaining suffix can still be matched. The last array allows me to verify that condition in O(1).” */

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word2.length();
        // last[j] = latest index in word1 where word2[j] can start a valid suffix match
        int[] last = new int[n];
        int j = n - 1;
        // right-to-left pass — find latest valid position for each word2 character
        for (int i = word1.length() - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
        }
        // left-to-right pass — greedily match word2 with one allowed mismatch
        j = 0;
        int flag = 1; // 1 = mismatch still available, 0 = already used
        int[] ans = new int[n];
        for (int i = 0; i < word1.length() && j < n; i++) {
            boolean exactMatch = word1.charAt(i) == word2.charAt(j);
            // can use mismatch if: flag available AND remaining suffix still matchable
            boolean canMismatch = flag == 1 && (j == n - 1 || i + 1 <= last[j + 1]);
            if (exactMatch || canMismatch) {
                // consume mismatch token if characters don't match
                if (!exactMatch) flag = 0;
                ans[j] = i;
                j++;
            }
        }
        return j == n ? ans : new int[]{};
    }
}

// Time Complexity :- O(n + m) — two linear passes over word1 (length m) and word2 (length n).
// Space Complexity :- O(n) — last and ans arrays both size n.
