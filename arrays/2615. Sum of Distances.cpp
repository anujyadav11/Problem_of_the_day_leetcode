/*********************************************** JAVA **************************************************/

// Optimal Solution - Computes total index distances for same-valued elements using two-pass prefix/suffix index sum tracking, avoiding O(n²) pairwise comparison.
/* "The key formula is freq*i - sum for left distances — instead of summing (i-j) for each previous j, maintain running count and sum to compute it in O(1). 
    Same principle right-to-left for future occurrences. This converts an O(n²) brute force to O(n) using the classic prefix sum optimization pattern." */

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        // tracks count and sum of indices seen so far for each value
        Map<Integer, Long> indexSum = new HashMap<>();
        Map<Integer, Long> indexCount = new HashMap<>();
        // left to right pass — accumulate distances from all previous occurrences
        for (int i = 0; i < n; i++) {
            long freq = indexCount.getOrDefault(nums[i], 0L);
            long sum = indexSum.getOrDefault(nums[i], 0L);
            // freq*i - sum = sum of (i - j) for all j < i with same value
            arr[i] += freq * i - sum;
            indexCount.put(nums[i], freq + 1);
            indexSum.put(nums[i], sum + i);
        }
        indexSum.clear();
        indexCount.clear();
        // right to left pass — accumulate distances from all future occurrences
        for (int i = n - 1; i >= 0; i--) {
            long freq = indexCount.getOrDefault(nums[i], 0L);
            long sum = indexSum.getOrDefault(nums[i], 0L);
            // sum - freq*i = sum of (j - i) for all j > i with same value
            arr[i] += sum - freq * i;
            indexCount.put(nums[i], freq + 1);
            indexSum.put(nums[i], sum + i);
        }
        return arr;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
