/*********************************************** JAVA **************************************************/

// Optimal Solution - Find the minimum deletions by comparing left-only, right-only, and two-sided removal strategies.
/* “I first find the indices of the minimum and maximum values. After ordering those indices as left and right, there are only three possible strategies: 
    remove both elements from the left, remove both from the right, or remove the earlier one from the left and the later one from the right. I calculate the cost of all three and return the minimum.” */

class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        // Find indices of minimum and maximum elements
        int minElIdx = 0;
        int maxElIdx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minElIdx])
                minElIdx = i;
            if (nums[i] > nums[maxElIdx])
                maxElIdx = i;
        }
        // left = earlier index, right = later index
        int left = Math.min(minElIdx, maxElIdx);
        int right = Math.max(minElIdx, maxElIdx);
        // Try:
        // 1. Delete both from the left and right
        // 2. Delete both from the left
        // 3. Delete both from the right
        return Math.min(
                left + 1 + n - right,
                Math.min(right + 1, n - left)
        );
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
