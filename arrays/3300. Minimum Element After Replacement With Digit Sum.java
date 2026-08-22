/*********************************************** JAVA **************************************************/

// Optimal Solution - Compute the digit sum of each number and return the minimum digit sum.
/* “I iterate through each number, calculate its digit sum using modulo and division operations, and keep track of the smallest digit sum encountered.” */

class Solution {
    public int minElement(int[] nums) {
        // Stores the minimum digit sum found so far
        int minimumDigitSum = Integer.MAX_VALUE;
        // Process each number in the array
        for (int number : nums) {
            int digitSum = 0;
            // Calculate sum of digits
            while (number > 0) {
                digitSum += number % 10; // Extract last digit
                number /= 10; // Remove last digit
            }
            // Update minimum digit sum
            minimumDigitSum = Math.min(minimumDigitSum, digitSum);
        }
        return minimumDigitSum;
    }
}

// Time Complexity :- O(n log m).
// Space Complexity :- O(1).
