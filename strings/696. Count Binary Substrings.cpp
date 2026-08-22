/*********************************************** JAVA **************************************************/

Optimal Solution - Counts binary substrings by tracking lengths of consecutive character groups.
                   Instead of generating substrings, count adjacent group sizes and add min(prev, curr).

class Solution {
    public int countBinarySubstrings(String s) {
        // Length of current consecutive group
        int curr = 1;
        // Length of previous consecutive group
        int prev = 0;
        // Final answer
        int res = 0;
        // Traverse string from second character
        for (int i = 1; i < s.length(); i++) {
            // If same as previous character → extend current group
            if (s.charAt(i - 1) == s.charAt(i)) {
                curr += 1;
            } else {
                // When group changes, valid substrings formed
                // = min(previous group size, current group size)
                res += Math.min(prev, curr);
                // Update previous group length
                prev = curr;
                // Reset current group length
                curr = 1;
            }
        }
        // Add contribution from last group transition
        return res + Math.min(prev, curr);
    }
}

Time Complexity :- O(N). size of String.
Space Complexity :- O(1).
