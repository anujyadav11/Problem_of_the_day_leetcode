/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds minimum end-removals using prefix-sum HashMap by maximizing the remaining subarray with sum totalSum - x.
/* “Instead of directly finding the elements to remove from the two ends, I calculate the total sum and convert the problem into finding the longest subarray whose sum is totalSum - x. 
    I use a prefix-sum HashMap to find this subarray in linear time. For every current prefix sum, I look for currentPrefix - restSum. 
    The longest valid subarray represents the maximum number of elements we can keep, so the answer is n - longest.” */

class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = 0;
        // Store prefix sum -> index
        HashMap<Integer, Integer> mp = new HashMap<>();
        // Prefix sum 0 exists before the array starts
        mp.put(0, -1);
        // Calculate total sum and store prefix sums
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            mp.put(sum, i);
        }
        // If total sum is smaller than x,
        // it is impossible to remove elements summing to x
        if (sum < x)
            return -1;
        // Instead of removing elements with sum x,
        // find the longest subarray whose sum is sum - x
        int restSum = sum - x;
        // Store the maximum length of such a subarray
        int longest = Integer.MIN_VALUE;
        sum = 0;
        // Find the longest subarray with sum = restSum
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            // If:
            // currentPrefix - previousPrefix = restSum
            // then previousPrefix = currentPrefix - restSum
            if (mp.containsKey(sum - restSum)) {
                longest = Math.max(
                    longest,
                    i - mp.get(sum - restSum)
                );
            }
        }
        // If no valid subarray exists, return -1.
        // Otherwise, remove everything outside
        // the longest remaining subarray.
        return longest == Integer.MIN_VALUE ? -1 : n - longest;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
