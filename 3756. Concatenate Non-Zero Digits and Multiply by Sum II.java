/*********************************************** JAVA **************************************************/

// Optimal Solution - Answers range non-zero digit concatenation-times-sum queries in O(1) each using prefix digit sum, prefix concatenation, and precomputed powers of 10.
/* "The concatenation subtraction formula B[r] - B[l] * 10^gap mirrors prefix sum subtraction — multiplying B[l] by 10^gap shifts it left to align with B[r]'s magnitude. 
    Always add MOD before taking modulo when subtracting to prevent negative results. Precomputing powers of 10 avoids repeated modular exponentiation per query." */

class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int MAX = 100_001;
    // precomputed powers of 10 for digit concatenation
    private static final int[] pow = new int[MAX];
    static {
        pow[0] = 1;
        for (int i = 1; i < MAX; i++)
            pow[i] = (int) ((pow[i - 1] * 10L) % MOD);
    }
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        // A[i] = sum of non-zero digits in s[0..i-1]
        int[] A = new int[n + 1];
        // B[i] = number formed by concatenating non-zero digits in s[0..i-1] mod MOD
        int[] B = new int[n + 1];
        // len[i] = count of non-zero digits in s[0..i-1]
        int[] len = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            // prefix sum of digit values
            A[i + 1] = A[i] + d;
            if (d > 0) {
                // append a non-zero digit to the concatenated number
                B[i + 1] = (int) ((B[i] * 10L + d) % MOD);
                len[i + 1] = len[i] + 1;
            } else {
                // zero digit — skip in B and len
                B[i + 1] = B[i];
                len[i + 1] = len[i];
            }
        }
        int[] res = new int[queries.length];
        int idx = 0;
        for (int[] q : queries) {
            int l = q[0], r = q[1] + 1;
            // extract substring non-zero concatenation: B[r] - B[l] * 10^(len[r]-len[l])
            long sub = ((long) B[l] * pow[len[r] - len[l]]) % MOD;
            long x = (B[r] - sub + MOD) % MOD;
            // digit sum of substring
            long digitSum = A[r] - A[l];
            res[idx++] = (int) ((x * digitSum) % MOD);
        }
        return res;
    }
}

// Time Complexity :- O(n + q) — O(n) prefix build, O(1) per query.
// Space Complexity :- O(n + MAX) — prefix arrays plus power table.
