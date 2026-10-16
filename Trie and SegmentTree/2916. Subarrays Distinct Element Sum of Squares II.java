/*********************************************** JAVA **************************************************/

// Optimal Solution - Calculate the sum of squared distinct counts across all subarrays using last-occurrence tracking and a lazy-propagation segment tree in O(n log n) time.
/* “I process the array from left to right and use the last occurrence of each value to identify which subarrays gain a new distinct element. 
    A lazy-propagation segment tree maintains both the sum and sum of squares of the distinct counts, allowing each range to be incremented efficiently."*/

class Solution {
    static final long MOD = 1_000_000_007L;
    int n;
    long[] sqSumSegTree; // Sum of squares over the segment.
    long[] sumSegTree;   // Sum of values over the segment.
    long[] lazyTree;     // Pending range additions.
    void lazyAddition(int i, int l, int r, long val) {
        long len = r - l + 1;
        // Update sum of squares:
        // (x + val)^2 = x^2 + 2 * val * x + val^2.
        sqSumSegTree[i] += 2 * val * sumSegTree[i] + len * val * val;
        // Update the ordinary sum after adding val to every element.
        sumSegTree[i] += len * val;
    }
    void updateRange(int start, int end, int i, int l, int r, long val) {
        // Apply any pending lazy update before processing this segment.
        if (lazyTree[i] != 0) {
            lazyAddition(i, l, r, lazyTree[i]);
            // Propagate the pending update to the children.
            if (l != r) {
                lazyTree[2 * i + 1] += lazyTree[i];
                lazyTree[2 * i + 2] += lazyTree[i];
            }
            lazyTree[i] = 0;
        }
        // No overlap with the requested range.
        if (l > end || r < start || l > r) {
            return;
        }
        // The current segment lies completely inside the update range.
        if (l >= start && r <= end) {
            lazyAddition(i, l, r, val);
            if (l != r) {
                lazyTree[2 * i + 1] += val;
                lazyTree[2 * i + 2] += val;
            }
            return;
        }
        // Partially overlapping segment: update both children.
        int mid = l + (r - l) / 2;
        updateRange(start, end, 2 * i + 1, l, mid, val);
        updateRange(start, end, 2 * i + 2, mid + 1, r, val);
        // Recalculate the current segment from its children.
        sumSegTree[i] = sumSegTree[2 * i + 1] + sumSegTree[2 * i + 2];
        sqSumSegTree[i] = sqSumSegTree[2 * i + 1] + sqSumSegTree[2 * i + 2];
    }
    public int sumCounts(int[] nums) {
        n = nums.length;
        sqSumSegTree = new long[4 * n];
        sumSegTree = new long[4 * n];
        lazyTree = new long[4 * n];
        // Stores the most recent index of each value.
        int[] lastSeenIdx = new int[100001];
        java.util.Arrays.fill(lastSeenIdx, -1);
        long result = 0;
        for (int i = 0; i < n; i++) {
            // Only subarrays starting after the previous occurrence
            // gain one additional distinct element.
            int start = lastSeenIdx[nums[i]] + 1;
            int end = i;
            // Increment the distinct count for the affected subarrays.
            updateRange(start, end, 0, 0, n - 1, 1);
            // Add the squared distinct counts of all subarrays ending at i.
            result = (result + sqSumSegTree[0]) % MOD;
            lastSeenIdx[nums[i]] = i;
        }
        return (int) result;
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(n).
