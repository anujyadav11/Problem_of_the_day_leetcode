/*********************************************** JAVA **************************************************/

// Optimal Solution - Calculates maximum parenthesis nesting depth using a simple balance counter in O(n) time.
/* “I maintain a counter representing the current nesting depth. I increment it for an opening parenthesis and decrement it for a closing parenthesis. 
    After each character, I update the maximum depth seen so far.” */

class Solution {
    public int maxDepth(String s) {
        // Current number of open parentheses.
        int open = 0;
        // Maximum nesting depth seen so far.
        int res = 0;
        // Traverse every character in the string.
        for (char ch : s.toCharArray()) {
            // Entering a new level of nesting.
            if (ch == '(') {
                open++;
            // Leaving the current level of nesting.
            } else if (ch == ')') {
                open--;
            }
            // Keep track of the maximum depth reached.
            res = Math.max(res, open);
        }
        return res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
