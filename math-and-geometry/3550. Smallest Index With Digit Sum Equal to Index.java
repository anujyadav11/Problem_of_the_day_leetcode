/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds the smallest index where the digit sum of nums[i] equals the index using linear traversal and digit extraction.
/* “I traverse the array from left to right and calculate the digit sum of each element using modulo and division by 10. 
    If the digit sum equals the current index, I immediately return that index. Since I scan in increasing index order, the first match is guaranteed to be the smallest.” */

class Solution {
    public int smallestIndex(int[] nums) {
        // Traverse from left to right.
        // The first valid index will be the smallest one.
        for (int i = 0; i < nums.length; i++) {
            // Check if the digit sum of nums[i]
            // is equal to the current index.
            if (getDigitSum(nums[i]) == i) {
                return i;
            }
        }
        // No valid index found.
        return -1;
    }
    public int getDigitSum(int num) {
        int total = 0;
        // Extract each digit from right to left
        // and add it to the total.
        while (num != 0) {
            total += num % 10;
            num /= 10;
        }
        return total;
    }
}

// Time Complexity :- O(n * D) .
// Space Complexity :- O(1).
