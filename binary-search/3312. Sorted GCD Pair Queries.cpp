/*********************************************** JAVA **************************************************/

// Optimal Solution - Answers k-th GCD pair queries using harmonic-sum pair counting, inclusion-exclusion for exact GCD distribution, and binary search on prefix sums.
/* "The downward inclusion-exclusion sweep is the key — processing from m down to 1 ensures gcdPairs[j] is already exact when subtracting from gcdPairs[i]. 
    This Mobius-style sieve converts 'divisible by i' counts to 'exactly i' counts. The prefix sum then enables O(log m) rank queries — elegant combination of number theory and binary search." */

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int m = 0;
        for (int num : nums)
            m = Math.max(m, num);
        // frequency of each value in nums
        long[] count = new long[m + 1];
        for (int num : nums)
            count[num]++;
        // gcdPairs[i] = number of pairs whose gcd is divisible by i
        long[] gcdPairs = new long[m + 1];
        for (int i = 1; i <= m; i++) {
            // sum counts of all multiples of i — harmonic series O(m log m)
            for (int j = i; j <= m; j += i)
                gcdPairs[i] += count[j];
            // C(c, 2) = pairs from c elements divisible by i
            gcdPairs[i] = (gcdPairs[i] * (gcdPairs[i] - 1)) / 2;
        }
        // inclusion-exclusion: subtract pairs with gcd = multiple of i
        // leaving only pairs with gcd exactly i
        for (int i = m; i >= 1; i--)
            for (int j = 2 * i; j <= m; j += i)
                gcdPairs[i] -= gcdPairs[j];
        // prefix sum — presum[i] = total pairs with gcd <= i
        long[] presum = new long[m + 1];
        for (int i = 1; i <= m; i++)
            presum[i] = presum[i - 1] + gcdPairs[i];
        // answer each query — find smallest gcd value whose cumulative count >= k
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long k = queries[i] + 1; // convert 0-indexed to 1-indexed rank
            int left = 1, right = m;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (presum[mid] >= k)
                    right = mid;
                else
                    left = mid + 1;
            }
            ans[i] = left;
        }
        return ans;
    }
}

// Time Complexity :- O(m log m + q log m).
// Space Complexity :- O(m).
