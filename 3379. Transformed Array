/*********************************************** JAVA **************************************************/

Optimal Solution - Constructs a transformed array using circular index shifts derived from each element’s value.
                   Use modulo arithmetic to simulate circular movement and carefully handle negative indices to avoid overflow.

class Solution {
    public int[] constructTransformedArray(int[] nums) {
        // Length of the array
        int n = nums.length;
        // Result array to store transformed values
        int[] res = new int[n];
        // Traverse each index of the array
        for (int i = 0; i < n; i++) {
            // Compute shift using modulo to stay within bounds
            int shift = nums[i] % n;
            // Calculate new index after applying shift
            int newIdx = (i + shift) % n;
            // Handle negative indices by wrapping around
            if (newIdx < 0) {
                newIdx += n;
            }
            // Assign value from the shifted index
            res[i] = nums[newIdx];
        }
        // Return the transformed array
        return res;
    }
}

Time Complexity :- O(n).
Space Complexity :- O(1).
