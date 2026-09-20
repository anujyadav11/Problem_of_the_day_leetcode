/*********************************************** JAVA **************************************************/

// Optimal Solution - Calculates reverse degree by mapping each character to its reverse alphabet value and multiplying by its 1-based position.
/* “I map each lowercase character to its reverse alphabet value, where a has value 26 and z has value 1. Then I iterate through the string and multiply each character’s reverse value by its 1-based position. 
    I use character - 'a' to map each character to the corresponding index in a 26-element array.” */

class Solution {
    public int reverseDegree(String s) {
        int[] count = new int[26];
        // Assign reverse alphabet values:
        // a = 26, b = 25, ..., z = 1
        for (char ch = 'a'; ch <= 'z'; ch++) {
            count[ch - 'a'] = 'z' - ch + 1;
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = count[s.charAt(i) - 'a'];
            // Position is 1-based
            res += value * (i + 1);
        }
        return res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
