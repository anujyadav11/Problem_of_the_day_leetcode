/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved Minimum Difference Between Largest and Smallest Value in Three Moves using sorting and boundary-based range evaluation.
/* "I sort the array so that the smallest and largest values are easy to access. Since I can modify at most three elements, I evaluate the possible combinations of modifying elements from both ends. 
    I return the minimum difference among those configurations." */

class Solution {
    public int minimizeSum(int[] nums) {
        // Store the length of the array.
        int n = nums.length;
        // Sort the array in ascending order.
        Arrays.sort(nums);
        // Calculate the minimum possible difference by considering
        // different combinations of removing/changing three elements.
        return Math.min(
            Math.min(nums[n - 3] - nums[0],nums[n - 1] - nums[2]),
            Math.min(nums[n - 1] - nums[2],nums[n - 2] - nums[1])
        );
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(1).
