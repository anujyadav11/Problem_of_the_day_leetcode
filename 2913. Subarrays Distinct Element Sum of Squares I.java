/*********************************************** JAVA **************************************************/

// Optimal Solution - Calculate the sum of squared distinct-element counts across all subarrays using incremental HashSet tracking in O(n²) time.
/* “I enumerate every subarray by fixing its starting index. For each starting position, I maintain a HashSet and extend the subarray one element at a time.
    After each extension, I add the square of the set’s size to the result. " */

class Solution {
    public int sumCounts(List<Integer> nums) {
        int res = 0;
        int n = nums.size();
        // Consider every index as the starting point of a subarray.
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            // Extend the subarray and track its distinct elements.
            for (int j = i; j < n; j++) {
                set.add(nums.get(j));
                // Add the square of the number of distinct elements.
                res += set.size() * set.size();
            }
        }
        return res;
    }
}

// Time Complexity :- O(N^2).
// Space Complexity :- O(n).
