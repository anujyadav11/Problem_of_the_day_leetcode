/*********************************************** JAVA **************************************************/

// Optimal Solution - Determine whether the array can be made uniform by checking the parity of its minimum element and the remaining values.
/* “I first find the minimum element. If it is odd, the condition is immediately satisfied. If the minimum is even, 
    I verify that every element is also even; encountering any odd element makes the result false.” */

class Solution {
    public boolean uniformArray(int[] nums1) {
        int minEl = Integer.MAX_VALUE;
        // Find the minimum element
        for (int num : nums1) {
            minEl = Math.min(minEl, num);
        }
        // Odd minimum means the array can be made uniform
        if (minEl % 2 != 0) {
            return true;
        }
        // If minimum is even, every element must be even
        for (int num : nums1) {
            if (num % 2 != 0) {
                return false;
            }
        }
        return true;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
