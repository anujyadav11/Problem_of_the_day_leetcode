/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved Minimum Moves to Equal Array Elements using mathematical transformation in O(n) time and O(1) auxiliary space.
/* "Instead of simulating increment operations on n-1 elements, I observe that each operation is equivalent to decrementing one element. 
    Therefore, I reduce every element to the minimum value and calculate the total difference." */

class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        // Calculate the sum and minimum element.
        for (int num : nums) {
            sum += num;
            min = Math.min(min, num);
        }
        // Minimum moves = sum - (n * minimum element).
        return sum - (n * min);
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
