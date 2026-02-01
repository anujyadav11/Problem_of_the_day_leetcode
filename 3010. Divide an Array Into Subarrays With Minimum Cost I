/*********************************************** JAVA **************************************************/

Optimal Solution - Greedy solution that computes minimum cost by adding the first element with the two smallest remaining values.
                   Fix the mandatory first cost, then minimize the total by choosing the two smallest values from the rest in one pass.

class Solution {
    public int minimumCost(int[] nums) {
        // Length of the array
        int n = nums.length;
        // Result starts with the first element's cost
        int res = nums[0];
        // Variables to store the smallest and second smallest values (excluding nums[0])
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        // Find the two minimum elements from index 1 to n-1
        for (int i = 1; i < n; i++) {
            // Update first and second minimums
            if (nums[i] < firstMin) {
                secondMin = firstMin;
                firstMin = nums[i];
            } 
            else if (nums[i] < secondMin) {
                secondMin = nums[i];
            }
        }
        // Minimum cost is sum of first element and two smallest remaining elements
        return res + firstMin + secondMin;
    }
}

Time Complexity :- O(N).
Space Complexity :- O(1).
