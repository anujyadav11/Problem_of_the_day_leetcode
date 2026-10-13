/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximize the difference between two halves of squared array values using sorting and greedy partitioning.

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        // Square each element using long to avoid integer overflow.
        for (int i = 0; i < n; i++) {
            arr[i] = (long) nums[i] * nums[i];
        }
        // Sort the squared values.
        Arrays.sort(arr);
        // Sum the smaller half.
        long small = 0;
        for (int i = 0; i < n / 2; i++) {
            small += arr[i];
        }
        // Sum the larger half.
        long large = 0;
        for (int i = n / 2; i < n; i++) {
            large += arr[i];
        }
        // Maximize the difference between the two halves.
        return large - small;
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(n).
