/*********************************************** JAVA **************************************************/

Optimal Solution - Greedy linear scan to minimise deletions by balancing 'a's before 'b's.
                   At each position, compute deletions as (#b on left + #a on right) and take the minimum.

class Solution {
    public int minimumDeletions(String s) {
        // Count total number of 'a's in the string
        int aCount = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') {
                aCount++;
            }
        }
        // Count of 'b's seen so far (to the left)
        int bCount = 0;
        //Initialise result with maximum possible deletions
        int res = s.length();
        // Traverse the string from left to right
        for (char c : s.toCharArray()) {
            // If current character is 'a',
            // it will move from right side to left side
            if (c == 'a') {
                aCount--;
            }
            // Deletions needed:
            // delete all 'b's on the left + all 'a's on the right
            res = Math.min(res, bCount + aCount);
            // If current character is 'b',
            // it contributes to the left-side 'b' count
            if (c == 'b') {
                bCount++;
            }
        }
        // Minimum deletions needed
        return res;
    }
}

Time Complexity :- O(N).
Space Complexity :- O(1).
