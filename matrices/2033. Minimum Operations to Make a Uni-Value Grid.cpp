/*********************************************** JAVA **************************************************/

// Optimal Solution - Minimizes grid equalization operations by flattening to sorted array, using median as optimal target, and summing step distances modulo x.
/* "Median is the mathematical optimum for minimizing sum of absolute differences — provable by calculus or geometric argument. 
    The remainder check num % x != target % x is the impossibility gate — if any element can't reach the target in steps of x, return -1 immediately." */

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int L = m * n;
        // flatten grid directly into array — avoids ArrayList boxing overhead
        int[] nums = new int[L];
        int idx = 0;
        for (int[] row : grid)
            for (int val : row)
                nums[idx++] = val;
        Arrays.sort(nums);
        // median minimizes sum of absolute differences
        int target = nums[L / 2];
        int res = 0;
        int rem = target % x;
        for (int num : nums) {
            // all elements must have same remainder mod x as target
            if (num % x != rem)
                return -1;
            res += Math.abs(target - num) / x;
        }
        return res;
    }
}

// Time Complexity :- O(mn log mn).
// Space Complexity :- O(mn).
