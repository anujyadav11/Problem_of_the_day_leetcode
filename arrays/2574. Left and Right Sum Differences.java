/*********************************************** JAVA **************************************************/

// Optimal Solution - Use running left and right sums to compute absolute differences for each index in linear time.
/* “I first compute the total array sum as the initial right sum. Then while traversing the array, 
    I update left and right sums on the fly and compute the absolute difference in O(1) per index.” */

class Solution {
    public int[] leftRightDifference(int[] nums) {
        int leftSum = 0; // Running sum of elements on the left
        int rightSum = 0; // Running sum of elements on the right
        int n = nums.length;
        // Compute total sum initially
        for (int num : nums) {
            rightSum += num;
        }
        // Process each index
        for (int i = 0; i < n; i++) {
            int currentValue = nums[i];
            // Remove current element from right side
            rightSum -= currentValue;
            // Store absolute difference between left and right sums
            nums[i] = Math.abs(leftSum - rightSum);
            // Add current element to left side for future indices
            leftSum += currentValue;
        }
        return nums;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
