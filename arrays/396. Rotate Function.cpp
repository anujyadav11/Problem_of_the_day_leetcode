/*********************************************** JAVA **************************************************/

// Optimal Solution - Use a recurrence relation to compute rotation function values in linear time.
/* "Instead of recomputing each rotation, I use a recurrence relation that updates the value in O(1) using the total sum and the element that moves." */

class Solution {
    public int maxRotateFunction(int[] nums) {
        // If the array is empty, the maximum rotate function is 0
        if (nums.length == 0)  return 0;
        int sum = 0;   // Sum of F(0)
        int isum = 0;  // Sum of all elements in the array
        // Calculate the initial sum for F(0) and the total sum of the array
        for (int i = 0; i < nums.length; i++) {
            sum = sum + (nums[i] * i);  // F(0)
            isum = isum + nums[i];      // Sum of array elements
        }
        int max = sum;  // Initialize max to F(0)
        int j = nums.length - 1;  // Index to track the last element
        // Calculate F(k) for k = 1 to nums.length - 1
        for (int i = 0; i < nums.length; i++, j--) {
            // Calculate the new sum after rotating the array
            sum = sum - (nums.length - 1) * nums[j];
            sum = sum + isum - nums[j];
            // Update max if the new sum is greater
            max = Math.max(max, sum);
        }
        // Return the maximum value of the rotate function
        return max;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
