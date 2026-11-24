/*********************************************** JAVA **************************************************/

// Optimal Solution - Implemented subarray enumeration with incremental GCD, LCM, and product calculations, using mathematical pruning to find the maximum valid subarray length.
/* "I enumerate all possible subarrays using two nested loops. For each starting index, I incrementally maintain the product, GCD, and LCM as I extend the subarray. 
    Whenever the product equals the LCM multiplied by the GCD, I update the maximum length. Since all elements are positive, 
    once the product exceeds LCM multiplied by GCD, I can safely stop expanding that subarray." */

class Solution {
    // Calculates the Greatest Common Divisor (GCD)
    // using the Euclidean algorithm.
    int gcd(int a, int b) {
        // When b becomes 0, a is the GCD.
        // Otherwise, recursively calculate gcd(b, a % b).
        return b == 0 ? a : gcd(b, a % b);
    }
    // Calculates the Least Common Multiple (LCM)
    // using the relationship between GCD and LCM.
    int lcm(int a, int b) {
        // LCM(a, b) = (a * b) / GCD(a, b).
        return (a * b) / gcd(a, b);
    }
    public int maxLength(int[] nums) {
        // Get the length of the input array.
        int n = nums.length;
        // Stores the maximum valid subarray length.
        int ans = 0;
        // Try every possible starting index of a subarray.
        for (int i = 0; i < n; i++) {
            // Initialize LCM for the current subarray.
            int l = 1;
            // Initialize GCD for the current subarray.
            int g = 0;
            // Initialize product of the current subarray.
            int prod = 1;
            // Expand the subarray from index i to the right.
            for (int j = i; j < n; j++) {
                // Include the current element in the product.
                prod *= nums[j];
                // Update the LCM using the current element.
                l = lcm(l, nums[j]);
                // Update the GCD using the current element.
                g = gcd(g, nums[j]);
                // Check whether the product equals LCM * GCD.
                if (prod == l * g) {
                    // Update the maximum valid subarray length.
                    ans = Math.max(ans, j - i + 1);
                }
                // If product exceeds LCM * GCD,
                // stop expanding this subarray.
                if (prod > l * g) {
                    break;
                }
            }
        }
        // Return the longest valid subarray length.
        return ans;
    }
}

// Time Complexity :- O(n^2 log m).
// Space Complexity :- O(log m).
