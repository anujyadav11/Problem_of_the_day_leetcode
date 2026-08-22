/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds maximum valid partition value at each index using precomputed prefix max and suffix min arrays to check split validity in a right-to-left scan.
/* "Precomputing prefix max and suffix min reduces each split point check to O(1). Scanning right to left with result propagation handles invalid partitions elegantly — 
    if no valid split exists at i, the best answer is whatever was valid further right. This avoids nested loops, giving O(n) over O(n²) brute force." */

class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        // maxPrefix[i] = max value in nums[0..i]
        int[] maxPrefix = new int[n];
        // minSuffix[i] = min value in nums[i..n-1]
        int[] minSuffix = new int[n];
        maxPrefix[0] = nums[0];
        minSuffix[n - 1] = nums[n - 1];
        // build prefix max left to right
        for (int i = 1; i < n; i++)
            maxPrefix[i] = Math.max(nums[i], maxPrefix[i - 1]);
        // build suffix min right to left
        for (int i = n - 2; i >= 0; i--)
            minSuffix[i] = Math.min(nums[i], minSuffix[i + 1]);
        int[] res = new int[n];
        // last element — best value is max of entire prefix
        res[n - 1] = maxPrefix[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (maxPrefix[i] > minSuffix[i + 1])
                // partition invalid — carry forward next result
                res[i] = res[i + 1];
            else
                // valid partition — take prefix max as answer
                res[i] = maxPrefix[i];
        }
        return res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
