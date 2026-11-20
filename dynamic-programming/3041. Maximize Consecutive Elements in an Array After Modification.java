/*********************************************** JAVA **************************************************/

// Optimal Solution - Implemented top-down dynamic programming with two states per index, binary search for consecutive transitions, and sorting for efficient lookups. Achieves O(n log n) time and O(n) auxiliary space.
/* "I sort the array to make future element lookups efficient. For each index, I maintain two DP states depending on whether the current element is incremented or not. From the current effective value, 
    I use binary search to find the next consecutive value. I explore both possibilities: selecting an element that already has the required value or incrementing an element that is one less. 
    Memoization avoids recomputing states, and I return the maximum sequence length across all starting positions." */

class Solution {
    // Stores the length of the input array.
    int n;
    // Memoization table:
    // memo[i][0] = maximum sequence length starting from index i
    //              when nums[i] is not incremented.
    // memo[i][1] = maximum sequence length starting from index i
    //              when nums[i] is incremented by 1.
    int[][] memo;
    // Finds the first index >= start where nums[index] == target.
    // Returns -1 if the target does not exist.
    // Uses binary search on the sorted array.
    private int findMethod(int[] nums, int start, int target) {
        // Search in the range [start, n).
        int lo = start, hi = n;
        while (lo < hi) {
            // Calculate the middle index safely.
            int mid = lo + (hi - lo) / 2;
            // If the middle element is smaller than target,
            // search in the right half.
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                // Otherwise, continue searching in the left half
                // to find the first occurrence.
                hi = mid;
            }
        }
        // Check whether the target exists at the lower-bound index.
        if (lo < n && nums[lo] == target) {
            return lo;
        }
        // Target was not found.
        return -1;
    }
    // Returns the maximum consecutive sequence length
    // starting from index i.
    // inc = 0: current element is used without incrementing.
    // inc = 1: current element is increased by 1.
    private int solve(int i, int inc, int[] nums) {
        // Return the previously computed result if available.
        if (memo[i][inc] != -1) {
            return memo[i][inc];
        }
        // Calculate the current element after applying the increment.
        // Each element can either remain unchanged or increase by 1.
        int currVal = nums[i] + inc;
        // At minimum, the current element itself forms a sequence of length 1.
        int len = 1;
        // OPTION 1:
        // Find the next element whose original value is currVal + 1.
        // This element can be selected without incrementing.
        int j = findMethod(nums, i + 1, currVal + 1);
        if (j != -1) {
            // Extend the current sequence by selecting nums[j].
            // The next element is used without incrementing.
            len = Math.max(len, 1 + solve(j, 0, nums));
        }
        // OPTION 2:
        // Find the next element whose original value is currVal.
        // We can increment that element by 1 to get currVal + 1.
        j = findMethod(nums, i + 1, currVal);
        if (j != -1) {
            // Extend the current sequence by selecting nums[j] + 1.
            // Pass inc = 1 because the next element is incremented.
            len = Math.max(len, 1 + solve(j, 1, nums));
        }
        // Store the result for the current state and return it.
        return memo[i][inc] = len;
    }
    public int maxSelectedElements(int[] nums) {
        // Get the number of elements.
        n = nums.length;
        // Sort the array to enable binary search.
        Arrays.sort(nums);
        // Initialize the memoization table.
        // Two states exist for each index: increment or do not increment.
        memo = new int[n + 1][2];
        // Mark all states as uncomputed.
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // Store the maximum sequence length found so far.
        int result = 1;
        // Try every element as the starting element of a sequence.
        for (int i = 0; i < n; i++) {
            // Consider both possibilities:
            // 1. Use nums[i] as it is.
            // 2. Increment nums[i] by 1.
            result = Math.max(
                result,
                Math.max(
                    solve(i, 0, nums),
                    solve(i, 1, nums)
                )
            );
        }
        // Return the maximum achievable sequence length.
        return result;
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(n).
