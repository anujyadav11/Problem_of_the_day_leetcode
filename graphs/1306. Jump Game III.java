/*********************************************** JAVA **************************************************/

// Optimal Solution - Checks if zero is reachable from start using DFS with in-place negation visited marking, saving jump value before marking to avoid wrong index calculation.
/* "In-place negation is an elegant O(1) space visited marker for integer arrays — but always save the original value before modifying it when you still need it for computation. 
    This is a subtle but critical ordering bug — arr[i] *= -1 then arr[i] reads the wrong value. Alternatively restore values after recursion if the array must be unchanged." */

class Solution {
    public boolean canReach(int[] arr, int start) {
        return solve(arr, start);
    }

    public boolean solve(int[] arr, int i) {
        // out of bounds or already visited
        if (i < 0 || i >= arr.length || arr[i] < 0)
            return false;
        // reached a zero — valid end point
        if (arr[i] == 0) return true;
        // save jump value before negating
        int jump = arr[i];
        // mark as visited by negating
        arr[i] *= -1;
        // use saved jump value not arr[i] which is now negative
        boolean a = solve(arr, i + jump);
        boolean b = solve(arr, i - jump);
        return a || b;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
