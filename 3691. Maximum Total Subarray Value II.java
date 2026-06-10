/*********************************************** JAVA - I did not solve this question, I took help of leetcode editorial. **************************************************/

// Optimal Solution - Maximizes k range max-min values using sparse table O(1) queries and a max heap to greedily select and shrink highest-value subranges.
/* "Sparse table gives O(1) range max/min after O(n log n) build — ideal for repeated queries. The heap-based greedy works because shrinking a range can only decrease its max-min value 
    — so the current maximum is always the best choice. Extracting repeated query logic into a helper prevents subtle index formula bugs." */

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        int logn = 32 - Integer.numberOfLeadingZeros(n);
        // sparse tables for range max and range min queries
        int[][] stMax = new int[n][logn];
        int[][] stMin = new int[n][logn];
        for (int i = 0; i < n; i++)
            stMax[i][0] = stMin[i][0] = nums[i];
        // build sparse table — O(n log n)
        for (int j = 1; j < logn; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                stMax[i][j] = Math.max(stMax[i][j-1], stMax[i + (1 << (j-1))][j-1]);
                stMin[i][j] = Math.min(stMin[i][j-1], stMin[i + (1 << (j-1))][j-1]);
            }
        }
        // max heap ordered by range (max - min) value
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        // seed with full range starting at each index going to n-1
        for (int l = 0; l < n; l++) {
            int[] res = rangeQuery(stMax, stMin, l, n - 1);
            pq.offer(new int[]{res[0] - res[1], l, n - 1});
        }
        long ans = 0;
        while (k-- > 0) {
            int[] top = pq.poll();
            ans += top[0];
            int l = top[1];
            int r = top[2];
            // shrink window from right — push subrange [l, r-1]
            if (r > l) {
                int[] res = rangeQuery(stMax, stMin, l, r - 1);
                pq.offer(new int[]{res[0] - res[1], l, r - 1});
            }
        }
        return ans;
    }

    // returns {rangeMax, rangeMin} for range [l, r] using sparse table
    private int[] rangeQuery(int[][] stMax, int[][] stMin, int l, int r) {
        int j = 31 - Integer.numberOfLeadingZeros(r - l + 1);
        int mx = Math.max(stMax[l][j], stMax[r - (1 << j) + 1][j]);
        int mn = Math.min(stMin[l][j], stMin[r - (1 << j) + 1][j]);
        return new int[]{mx, mn};
    }
}

// Time Complexity :- O(n log n + k log n) — sparse table build plus k heap operations.
// Space Complexity :- O(n log n) — two sparse tables.
