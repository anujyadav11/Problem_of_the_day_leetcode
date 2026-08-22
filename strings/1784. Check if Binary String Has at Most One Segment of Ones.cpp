/*********************************************** JAVA **************************************************/

// Optimal Solution - Check for the pattern "01" to detect if more than one segment of '1's exists.
                    // “If a new segment of '1's starts after a '0', we detect the pattern '01', meaning there are multiple segments.

class Solution {
    public boolean checkOnesSegment(String s) {
        int n = s.length();  // Length of the string
        // Traverse the string starting from index 1
        for (int i = 1; i < n; i++) {
            // If we see a pattern "01", it means a new segment of '1' starts
            if (s.charAt(i - 1) == '0' && s.charAt(i) == '1') {
                return false;  // More than one segment of '1's found
            }
        }
        return true;  // Only one continuous segment of '1's exists
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
