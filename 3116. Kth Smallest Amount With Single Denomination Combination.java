/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds k-th smallest coin multiple using binary search with inclusion-exclusion over all coin subsets to count multiples up to mid via LCM.
/* "Inclusion-exclusion counts numbers divisible by at least one coin — odd subsets add, even subsets subtract. LCM of a subset gives numbers divisible by ALL coins in that subset. 
    The division-before-multiplication lcm / gcd * coin prevents overflow — always divide first when computing LCM to keep intermediate values bounded." */

class Solution {
    // count numbers in [1, mid] divisible by at least one coin using inclusion-exclusion
    private long countSmaller(long mid, int[] coins) {
        long correctedCount = 0;
        int n = coins.length;
        // iterate over all non-empty subsets of coins
        for (int mask = 1; mask <= (1 << n) - 1; mask++) {
            long lcm = 0;
            long bits = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;
                    // update LCM with current coin
                    lcm = (lcm == 0) ? coins[i] : lcm / gcd(lcm, coins[i]) * coins[i];
                }
            }
            // inclusion-exclusion: odd subset size adds, even subtracts
            if (bits % 2 == 0)
                correctedCount -= mid / lcm;
            else
                correctedCount += mid / lcm;
        }
        return correctedCount;
    }
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    public long findKthSmallest(int[] coins, int k) {
        int maxCoin = 0;
        for (int c : coins) maxCoin = Math.max(maxCoin, c);
        long l = 1;
        long r = (long) maxCoin * k;
        long result = r;
        // binary search on answer
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (countSmaller(mid, coins) >= k) {
                // mid is a valid candidate — try smaller
                result = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return result;
    }
}

// Time Complexity :- O(log(maxCoin × k) × 2ⁿ × n × log(maxCoin)) — binary search × subset iteration × LCM computation.
// Space Complexity :- O(1) — only scalar variables beyond recursive GCD stack.
