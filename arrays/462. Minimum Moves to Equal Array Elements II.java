/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved Minimum Moves to Equal Array Elements II using sorting and median-based greedy optimization in O(n log n) time.
/* "I sort the array and choose its median because the median minimizes the sum of absolute differences. 
    I then calculate the total distance of every element from the median. This gives an O(n log n) solution dominated by sorting." */

class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;
        // Sort the array to find the median.
        Arrays.sort(nums);
        // Median minimizes the total absolute difference.
        int mid = nums[n / 2];
        long moves = 0;
        // Calculate total moves required to make all elements equal.
        for (int i = 0; i < n; i++) {
            moves += Math.abs((long) nums[i] - mid);
        }
        return (int) moves;
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(1).
