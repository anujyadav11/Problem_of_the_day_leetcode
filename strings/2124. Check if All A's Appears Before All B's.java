/*********************************************** JAVA **************************************************/

// Optimal Solution - Used a single-pass string traversal to detect invalid adjacent character ordering in O(n) time and O(1) auxiliary space.
/* "I traverse the string and check every adjacent pair. If I encounter a 'b' followed by an 'a', the ordering condition is violated, so I return false. 
    If the traversal completes without finding this pattern, I return true." */

class Solution {
    public boolean checkString(String s) {
        // Get the length of the string.
        int n = s.length();
        // Traverse the string and check adjacent characters.
        for (int i = 0; i < n - 1; i++) {
            // If 'b' is immediately followed by 'a',
            // then an 'a' appears after a 'b'.
            if (s.charAt(i) == 'b' && s.charAt(i + 1) == 'a') {
                return false;
            }
        }
        // No invalid "ba" pattern was found.
        // Therefore, all 'a' characters appear before all 'b' characters.
        return true;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
