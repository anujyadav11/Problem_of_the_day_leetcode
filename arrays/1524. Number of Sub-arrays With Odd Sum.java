/*********************************************** JAVA **************************************************/

// Optimal Solution - Used prefix-sum parity and frequency counting to count odd-sum subarrays in O(n) time and O(1) auxiliary space.
/* "I use prefix sums and parity counting. A subarray has an odd sum if its two corresponding prefix sums have different parity. 
    I maintain the frequency of even and odd prefix sums while traversing the array. Whenever the current prefix is odd, I add the number of previously seen even prefixes; 
    whenever it is even, I add the number of previously seen odd prefixes. I initialize the even count to one to account for the empty prefix." */

class Solution {
    private static final int MOD = 1_000_000_007;
    public int numOfSubarrays(int[] arr) {
        // Count prefix sums having odd and even parity.
        int oddCount = 0;
        int evenCount = 1; // Empty prefix has sum 0 (even).
        // Running prefix sum.
        int prefix = 0;
        // Total number of subarrays with odd sum.
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            // Update the running prefix sum.
            prefix += arr[i];
            if (prefix % 2 == 0) {
                // Even prefix - previous odd prefixes
                // produce an odd subarray sum.
                res = (res + oddCount) % MOD;
                evenCount++;
            } else {
                // Odd prefix - previous even prefixes
                // produce an odd subarray sum.
                res = (res + evenCount) % MOD;
                oddCount++;
            }
        }
        return res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
