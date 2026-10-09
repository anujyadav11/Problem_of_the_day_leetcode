/*********************************************** JAVA **************************************************/

// Optimal Solution - Minimize insertions to balance parentheses where each opening bracket requires two consecutive closing brackets, using a greedy O(n)-time, O(1)-space approach.
/* “I use a greedy approach to track unmatched opening parentheses and the number of insertions required. Each opening parenthesis needs two closing parentheses. 
    I process closing parentheses in pairs, inserting a missing opening or closing parenthesis whenever necessary. Finally, each remaining opening parenthesis contributes two insertions.” */

class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int res = 0, count = 0, i = 0;
        while (i < n) {
            if (s.charAt(i) == '(') {
                // Track an unmatched '(' that needs two closing ')'.
                count++;
                i++;
            } else {
                // If an unmatched '(' exists, use it to match this ')'.
                if (count > 0) {
                    count--;
                } else {
                    // No '(' available, so insert one.
                    res++;
                }
                // Check whether the current ')' has a second ')' after it.
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    // Consume the pair of closing parentheses.
                    i += 2;
                } else {
                    // Insert a missing ')' to complete the pair.
                    res++;
                    i++;
                }
            }
        }
        // Each remaining '(' needs two closing parentheses.
        return res + count * 2;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
