/*********************************************** JAVA **************************************************/

// Optimal Solution - Predicts game winner using minimax DP where each state returns score difference achievable by current player, memoized over all subarray ranges.
/* "Always use for (int[] row : t) Arrays.fill(row, -1) for 2D array initialization — Arrays.fill(t, -1) on int[][] fills with -1 as an object reference which compiles but gives wrong results. 
    The minimax insight: subtracting the opponent's result captures their optimal play — positive final score means first player wins." */

class Solution {
    int[][] t;
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        t = new int[n][n];
        // fill each row individually for 2D array
        for (int[] row : t)
            Arrays.fill(row, -1);
        return solve(nums, 0, n - 1) >= 0;
    }
    public int solve(int[] nums, int i, int j) {
        // no elements left — score difference is 0
        if (i > j)
            return 0;
        // single element — current player takes it
        if (i == j)
            return nums[i];
        // return cached result
        if (t[i][j] != -1)
            return t[i][j];
        // take from left — opponent plays optimally on remaining
        int takeLeft = nums[i] - solve(nums, i + 1, j);
        // take from right — opponent plays optimally on remaining
        int takeRight = nums[j] - solve(nums, i, j - 1);
        // current player picks the better option
        return t[i][j] = Math.max(takeLeft, takeRight);
    }
}

// Time Complexity :- O(n^2).— at most n² unique states each computed once
// Space Complexity :- O(n^2).— memoization table of size n × n
