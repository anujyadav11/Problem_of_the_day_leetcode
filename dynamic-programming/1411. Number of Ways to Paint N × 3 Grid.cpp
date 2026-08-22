/************************************************************* JAVA *********************************************************/

Optimal Solution - We use dynamic programming to count the number of ways to colour each row by tracking two valid colouring patterns and building the result row by 
                    row using transition relations from the previous row.

class Solution {
    // Modulo constant as required by the problem
    static final int MOD = 1000000007;
    public int numOfWays(int n) {
        // a[i]: number of ways where the i-th row has 3 different colours (ABC pattern)
        long[] a = new long[n];
        // b[i]: number of ways where the i-th row has 2 different colours (ABA pattern)
        long[] b = new long[n];
        // Base case: for the first row
        // There are 6 ways for both patterns
        a[0] = 6;
        b[0] = 6;
        // Build the solution row by row using DP
        for (int i = 1; i < n; i++) {
            // If the current row has 3 different colours,
            // it can be formed from both previous patterns
            a[i] = (2 * a[i - 1] + 2 * b[i - 1]) % MOD;
            // If the current row has 2 different colours,
            // it has more combinations from the previous row
            b[i] = (2 * a[i - 1] + 3 * b[i - 1]) % MOD;
        }
        // Total ways = sum of both patterns for the last row
        return (int) ((a[n - 1] + b[n - 1]) % MOD);
    }
}
Time Complexity :- O(N).
Space Complexity :- O(N).
