/*********************************************** JAVA **************************************************/

// Optimal Solution - Used greedy pairwise comparison to count mismatched adjacent bits, achieving O(n) time and O(1) auxiliary space.
/* "I use a greedy approach by processing the binary string in consecutive pairs. Since every valid partition must consist of even-length substrings containing identical characters, 
    each adjacent pair must contain matching bits. If the two characters differ, exactly one change is sufficient. I count these mismatched pairs and return the total." */

class Solution {
    public int minChanges(String s) {
        int n = s.length();
        // Count the number of changes required.
        int changes = 0;
        // Process the string in consecutive pairs.
        for (int i = 0; i < n; i += 2) {
            // If both characters are different,
            // one character must be changed.
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }
        return changes;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
