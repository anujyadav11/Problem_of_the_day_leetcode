/*********************************************** JAVA **************************************************/

// Optimal Solution - Simulate special string operations using a StringBuilder for efficient append, delete, duplicate, and reverse processing.
/* “I simulate each operation directly using a StringBuilder. Regular characters are appended, while special symbols modify the current string according to the given rules.” */

class Solution {
    public String processStr(String s) {
        // Stores the current processed string
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // Duplicate the current string
            if (ch == '#') {
                result.append(result.toString());
            }
            // Reverse the current string
            else if (ch == '%') {
                result.reverse();
            }
            // Remove the last character if present
            else if (ch == '*') {
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            }
            // Append a normal character
            else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}

// Time Complexity :- O(l). length of generated string.
// Space Complexity :- O(l).
