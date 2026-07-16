/*********************************************** JAVA **************************************************/

// Optimal Solution - Computes GCD of odd and even indexed sums using closed-form formulas n² and n(n+1) with Euclidean algorithm.
/* "Always look for closed-form mathematical patterns before iterating — summing grid elements often reduces to triangular number formulas.
    gcd(n², n(n+1)) = gcd(n², n) * gcd(n, n+1) = n * 1 = n by GCD properties — so the answer simplifies to just n. Worth verifying this algebraically: 
    gcd(n², n(n+1)) = n * gcd(n, n+1) = n * 1 = n since consecutive integers are always coprime." */

class Solution {
    public int gcdOfOddEvenSums(int n) {
        // sum of odd-indexed: n*n, sum of even-indexed: n*(n+1)
        return gcd(n * n, n * (n + 1));
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

// Time Complexity :- O(log n).
// Space Complexity :- O(log n).
