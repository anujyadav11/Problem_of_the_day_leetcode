/*********************************************** JAVA **************************************************/

// Recursive Solution - Solve Stone Game V using interval DP with prefix sums to evaluate every possible split in O(1).
/* “I use interval DP where dp[left][right] represents the maximum score obtainable from that subarray. For every possible split, 
  I calculate the left and right sums using prefix sums. Based on which side has the smaller sum, I can only continue with that side; if they are equal, I try both. 
  Memoisation avoids recomputing the same intervals.” */

class Solution {
    int[][] t;
    public int solve(int left, int right, int[] cumSum) {
        // One element cannot be split
        if (left >= right) {
            return 0;
        }
        if (t[left][right] != -1) {
            return t[left][right];
        }
        int score = 0;
        // Try every possible split
        for (int mid = left; mid < right; mid++) {
            // Calculate left and right partition sums
            int leftSum = cumSum[mid] - (left > 0 ? cumSum[left - 1] : 0);
            int rightSum = cumSum[right] - cumSum[mid];
            if (leftSum < rightSum) {
                // Can only keep the left part
                score = Math.max(score,leftSum + solve(left, mid, cumSum));
            } else if (leftSum > rightSum) {
                // Can only keep the right part
                score = Math.max(score,rightSum + solve(mid + 1, right, cumSum));
            } else {
                // Equal sums: choose the better side
                score = Math.max(score,Math.max(leftSum + solve(left, mid, cumSum),rightSum + solve(mid + 1, right, cumSum)));
            }
        }
        // Store result after checking all splits
        return t[left][right] = score;
    }
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        // DP: t[left][right] = maximum score for this range
        t = new int[n][n];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        // Build prefix sum
        int[] cumSum = new int[n];
        cumSum[0] = stoneValue[0];
        for (int i = 1; i < n; i++) {
            cumSum[i] = cumSum[i - 1] + stoneValue[i];
        }
        return solve(0, n - 1, cumSum);
    }
}

// Time Complexity :- O(n ^ 3).
// Space Complexity :- O(n ^ 2).

/*********************************************** JAVA **************************************************/

// Bottom-Up Solution - Solve Stone Game V using bottom-up interval DP and prefix sums to evaluate every possible split efficiently.
/* “I use interval DP where dp[l][r] represents the best score for that range. I try every split between l and r, compare the two partition sums, 
    and recursively use the allowed side’s previously computed DP value. Since the DP is bottom-up, both smaller intervals are already available. Prefix sums make each partition-sum calculation O(1).” */

class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        // Prefix sum for O(1) range-sum queries
        int[] cumSum = new int[n];
        cumSum[0] = stoneValue[0];
        for (int i = 1; i < n; i++) {
            cumSum[i] = cumSum[i - 1] + stoneValue[i];
        }
        // dp[l][r] = maximum score for subarray [l, r]
        int[][] dp = new int[n][n];
        // Process smaller ranges before larger ranges
        for (int l = n - 1; l >= 0; l--) {
            for (int r = l + 1; r < n; r++) {
                int score = 0;
                // Try every possible split
                for (int mid = l; mid < r; mid++) {
                    int leftSum = cumSum[mid]
                            - (l > 0 ? cumSum[l - 1] : 0);
                    int rightSum = cumSum[r] - cumSum[mid];
                    if (leftSum < rightSum) {
                        // Keep left part
                        score = Math.max(score,leftSum + dp[l][mid]);
                    } else if (leftSum > rightSum) {
                        // Keep right part
                        score = Math.max(score,rightSum + dp[mid + 1][r]);
                    } else {
                        // Equal: try both sides
                        score = Math.max(score,Math.max(leftSum + dp[l][mid],rightSum + dp[mid + 1][r]));
                    }
                }
                dp[l][r] = score;
            }
        }
        return dp[0][n - 1];
    }
}

// Time Complexity :- O(n ^ 3).
// Space Complexity :- O(n ^ 2).
