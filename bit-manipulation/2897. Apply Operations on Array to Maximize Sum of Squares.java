/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximize the sum of squares using greedy bit reconstruction and bit-frequency counting in O(30(n + k)) time and O(1) auxiliary space.
/* “I count the occurrences of each set bit across all input numbers. Then, for each of the k numbers, I greedily use every bit position that still has an available occurrence. 
    This maximizes each constructed number while preserving the total available bit counts. I accumulate the squared values modulo 10⁹ + 7.” */

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final int MOD = 1_000_000_007;
        final int BITS = 30; // nums[i] <= 10^9, so 30 bits are sufficient.
        // count[b] stores the number of elements having bit b set to 1.
        int[] count = new int[BITS];
        for (int num : nums) {
            for (int b = 0; b < BITS; b++) {
                // Count how many numbers have the current bit set.
                if ((num & (1 << b)) != 0) {
                    count[b]++;
                }
            }
        }
        long result = 0;
        // Construct k numbers that maximize the sum of their squares.
        while (k-- > 0) {
            long curr = 0;
            for (int b = 0; b < BITS; b++) {
                // Greedily include every available bit in the current number.
                if (count[b] > 0) {
                    count[b]--;
                    // Use 1L to perform the shift with a long value.
                    curr |= (1L << b);
                }
            }
            // Add the square of the constructed number, applying modulo.
            result = (result + curr * curr) % MOD;
        }
        return (int) result;
    }
}

// Time Complexity :- O(n + k).
// Space Complexity :- O(1).
