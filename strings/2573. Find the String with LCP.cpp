/*********************************************** JAVA **************************************************/

// Optimal Solution - Reconstructs a string from its LCP table by greedily assigning characters to matching positions, then validating via the LCP recurrence relation.
/* "This is a two-phase problem — first greedily construct a candidate string using the LCP hints, then verify it satisfies the full LCP recurrence lcp[i][j] = lcp[i+1][j+1] + 1. 
Building bottom-right to top-left in validation mirrors how LCP tables are computed — same direction as the recurrence dependency." */

class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        char current = 'a';
        // assign characters greedily — same lcp>0 positions share same character
        for (int i = 0; i < n; i++) {
            // position not yet assigned — give it next available character
            if (word[i] == 0) {
                // exhausted all 26 letters — impossible to construct
                if (current > 'z') return "";
                word[i] = current;
                // propagate same character to all positions sharing lcp with i
                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0)
                        word[j] = word[i];
                }
                current++;
            }
        }
        // validate constructed word against the given lcp table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word[i] != word[j]) {
                    // different characters must have lcp of 0
                    if (lcp[i][j] != 0) return "";
                } else {
                    // at last row or column — lcp must be exactly 1
                    if (i == n - 1 || j == n - 1) {
                        if (lcp[i][j] != 1) return "";
                    } else {
                        // lcp recurrence: lcp[i][j] = lcp[i+1][j+1] + 1
                        if (lcp[i][j] != lcp[i + 1][j + 1] + 1) return "";
                    }
                }
            }
        }
        // word satisfies all lcp conditions
        return new String(word);
    }
}

// Time Complexity :- O(n^2).
// Space Complexity :- O(n).
