/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts valid subsequence pairs with equal GCDs using bottom-up 2D rolling DP tracking running GCD states for both subsequences simultaneously.
/* "Rolling array converts 3D DP to 2D by noting each layer only depends on the next — save O(n × maxEl²) space down to O(maxEl²).
    GCD of 0 with any number = that number — so gcd(0, x) = x, making 0 a perfect 'empty subsequence' sentinel that naturally initializes when the first element is added." */

class Solution {
    int MOD = 1_000_000_007;

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        int maxEl = 0;
        for (int x : nums)
            maxEl = Math.max(maxEl, x);
        // rolling 2D DP — prev = layer i+1, curr = layer i
        int[][] prev = new int[maxEl + 1][maxEl + 1];
        // base case: i == n — both non-empty and gcds match
        for (int first = 0; first <= maxEl; first++)
            for (int second = 0; second <= maxEl; second++)
                prev[first][second] = (first != 0 && second != 0 && first == second) ? 1 : 0;
        // fill bottom-up from last element to first
        for (int i = n - 1; i >= 0; i--) {
            int[][] curr = new int[maxEl + 1][maxEl + 1];
            for (int first = 0; first <= maxEl; first++) {
                for (int second = 0; second <= maxEl; second++) {
                    // option 1: skip nums[i]
                    int skip = prev[first][second];
                    // option 2: add nums[i] to first subsequence
                    int take1 = prev[gcd(first, nums[i])][second];
                    // option 3: add nums[i] to second subsequence
                    int take2 = prev[first][gcd(second, nums[i])];
                    curr[first][second] = (int) ((0L + skip + take1 + take2) % MOD);
                }
            }
            prev = curr;
        }
        // start state: both gcds = 0 (empty subsequences)
        return prev[0][0];
    }
}

// Time Complexity :-O(n × maxEl² × log(maxEl)).
// Space Complexity :- O(maxEl²).
