/*********************************************** JAVA **************************************************/

// Optimal Solution - Checks if goal is a rotation of s by verifying goal appears as a substring in the self-concatenation s+s with equal length guard.  
/* "The s+s trick works because rotating s by any k positions produces a substring of s+s starting at index k. The length check is essential — without it, 
    shorter strings could falsely match as substrings. For O(n) time, mention KMP or Z-algorithm as follow-up optimizations over Java's built-in O(n²) contains." */

class Solution {
    public boolean rotateString(String s, String goal) {
        // concatenating s with itself contains all possible rotations of s
        // check equal length first to avoid false positives
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}

// Time Complexity :- O(n²) — contains uses naive search on string of length 2n; O(n) with KMP.
// Space Complexity :- O(n) — concatenated string of length 2n.
