/*********************************************** JAVA **************************************************/

// Optimal Solution - Calculate the sum of squares of special array elements using index divisibility in O(n) time and O(1) space.
/* “I iterate through the array using 1-based indices. Whenever the array length is divisible by the current index, 
  I add the square of that element to the sum. This takes O(n) time and O(1) extra space.” */

class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        // Check each element using its 1-based index.
        for (int i = 1; i <= n; i++) {
            // An element is special if its index divides n evenly.
            if (n % i == 0) {
                // Add the square of the special element.
                sum += nums[i - 1] * nums[i - 1];
            }
        }
        return sum;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
