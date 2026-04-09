/*********************************************** JAVA **************************************************/

// Optimal Solution - Use √ decomposition and modular difference arrays to efficiently process range-step multiplication queries.
/* “I split queries into large and small k. For small k, I use a difference array with modular inverse to batch updates efficiently.” */

class Solution {
    private static final int MOD = 1_000_000_007;
    // Fast exponentiation
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1)
                res = (res * base) % MOD;       
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
    // Modular inverse using Fermat's theorem
    private long modInv(long n) {
        return power(n, MOD - 2);
    }
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        // Slightly safer sqrt threshold
        int limit = (int) Math.sqrt(n) + 1;
        // Group queries by small k
        Map<Integer, List<int[]>> lightK = new HashMap<>();
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];   
            if (k >= limit) {
                // Large k → brute force
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) ((1L * nums[i] * v) % MOD);
                }
            } 
            else {
                lightK.computeIfAbsent(k, x -> new ArrayList<>()).add(q);
            }
        }
        // Process small k queries
        for (Map.Entry<Integer, List<int[]>> entry : lightK.entrySet()) {
            int k = entry.getKey();
            List<int[]> queryList = entry.getValue();
            long[] diff = new long[n];
            Arrays.fill(diff, 1L);
            for (int[] q : queryList) {
                int l = q[0], r = q[1], v = q[3];
                // Start multiplication
                diff[l] = (diff[l] * v) % MOD;
                // End range using modular inverse
                int steps = (r - l) / k;
                int next = l + (steps + 1) * k;
                if (next < n) {
                    diff[next] = (diff[next] * modInv(v)) % MOD;
                }
            }
            // Propagate with step k
            for (int i = 0; i < n; i++) {
                if (i >= k) {
                    diff[i] = (diff[i] * diff[i - k]) % MOD;
                }
                nums[i] = (int) ((1L * nums[i] * diff[i]) % MOD);
            }
        }
        // Final XOR
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}

// Time Complexity :- O(n \sqrt{n} + q \sqrt{n}).
// Space Complexity :- O(n).
