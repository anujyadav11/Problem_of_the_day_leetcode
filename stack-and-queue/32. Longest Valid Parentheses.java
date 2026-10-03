/*********************************************** JAVA **************************************************/

// Optimal Solution - Find the longest valid parentheses substring using two-pass balance counting in O(n) time and O(1) space.
/* “I use two linear scans while maintaining counts of opening and closing parentheses. The left-to-right pass resets when closing parentheses exceed opening ones, 
    while the right-to-left pass resets when opening parentheses exceed closing ones. Together, the two passes handle both types of imbalance in O(n) time and O(1) space.” */

class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int open = 0;
        int close = 0;
        int res = 0;
        // Left-to-right scan:
        // Handles cases where there are extra closing parentheses.
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(')
                open++;
            else
                close++;
            // Equal number of '(' and ')' means we have
            // a valid parentheses substring.
            if (open == close) {
                res = Math.max(res, open + close);
            // More ')' than '(' makes the current substring invalid.
            // Start counting again from the next position.
            } else if (close > open) {
                open = 0;
                close = 0;
            }
        }
        // Reset counters for the right-to-left scan.
        open = 0;
        close = 0;
        // Right-to-left scan:
        // Handles cases where there are extra opening parentheses.
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '(')
                open++;
            else
                close++;
            // Equal number of '(' and ')' forms a valid substring.
            if (open == close) {
                res = Math.max(res, open + close);
            // More '(' than ')' makes the current substring invalid.
            } else if (open > close) {
                open = 0;
                close = 0;
            }
        }
        return res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
