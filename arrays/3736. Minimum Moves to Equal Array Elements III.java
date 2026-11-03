/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved Minimum Moves to Equal Array Elements using maximum-element greedy calculation in O(n) time and O(1) auxiliary space.
/* "Instead of simulating each operation, I find the maximum element and calculate how many increments each element needs to reach it. 
    Since each operation increments exactly n-1 elements, the total required increments can be calculated directly." */

class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int maxEl = Integer.MIN_VALUE;
        int moves = 0;
        // Find the maximum element.
        for (int num : nums) {
            maxEl = Math.max(maxEl, num);
        }
        // Calculate increments required to make every element
        // equal to the maximum element.
        for (int num : nums) {
            moves += maxEl - num;
        }
        return moves;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
