/*********************************************** JAVA **************************************************/

// Optimal Solution - Used bitwise AND over misplaced permutation elements to calculate the result in O(n) time and O(1) auxiliary space.
/* “I traverse the permutation and identify elements that are not in their correct positions. I compute the bitwise AND of these misplaced values, 
    initialising the accumulator to -1 so the first AND preserves the value. If no elements are misplaced, I return zero.” */

class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k = -1;
        // Find elements that are not in their correct positions.
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                // Keep only the bits set in every misplaced element.
                k &= nums[i];
            }
        }
        // If no elements are misplaced, the permutation is already sorted.
        return k == -1 ? 0 : k;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
