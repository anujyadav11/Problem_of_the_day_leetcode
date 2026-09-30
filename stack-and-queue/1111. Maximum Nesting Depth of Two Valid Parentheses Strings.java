/*********************************************** JAVA **************************************************/

// Optimal Solution - Split parentheses between two groups using nesting-depth parity to minimize the maximum depth, in O(n) time and O(1) auxiliary space.
/* “I track the current nesting depth and assign each parenthesis to group 0 or 1 based on the parity of that depth. Alternating groups across nesting levels keeps the maximum depth of both groups balanced.” */

class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] res = new int[n];
        // Tracks the current nesting depth.
        int depth = 0;
        // Index for storing the group assignment.
        int idx = 0;
        for (char ch : seq.toCharArray()) {
            if (ch == '(') {
                // Entering a new level of nesting.
                depth++;
                // Alternate between group 0 and group 1
                // based on the current depth.
                res[idx++] = depth % 2;
            } else {
                // Closing parenthesis belongs to the same
                // depth level before we move back up.
                res[idx++] = depth % 2;
                // Leaving the current nesting level.
                depth--;
            }
        }
        return res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
