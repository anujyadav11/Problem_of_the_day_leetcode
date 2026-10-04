/*********************************************** JAVA **************************************************/

// Optimal Solution - Validate a parenthesis string with wildcards using greedy range tracking in O(n) time and O(1) space.
/* “I maintain the minimum and maximum possible number of unmatched opening parentheses. A * can act as an opening bracket, closing bracket, or empty character, 
    so it expands this range. If the maximum becomes negative, the string is impossible. At the end, a minimum of zero means a valid interpretation exists.” */

class Solution {
    public boolean checkValidString(String s) {
        // minOpen = minimum possible number of unmatched '('
        // maxOpen = maximum possible number of unmatched '('
        int minOpen = 0, maxOpen = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                // '(' must increase the number of open brackets.
                minOpen++;
                maxOpen++;
            } else if (ch == ')') {
                // ')' must close an open bracket.
                minOpen--;
                maxOpen--;
            } else {
                // '*' can be '(', ')' or empty.
                // Therefore, it can decrease or increase the range.
                minOpen--;
                maxOpen++;
            }
            // If even the maximum possible number of open brackets
            // becomes negative, there is no way to make the string valid.
            if (maxOpen < 0)
                return false;
            // We cannot have fewer than zero unmatched '('.
            minOpen = Math.max(minOpen, 0);
        }
        // If zero unmatched '(' is possible, the string is valid.
        return minOpen == 0;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
