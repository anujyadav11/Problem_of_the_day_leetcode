/*********************************************** JAVA **************************************************/

Optimal Solution - For each number, compute the minimum valid value using bit manipulation, returning -1 for even numbers.

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] res = new int[n];
        // Process each number independently
        for (int i = 0; i < n; i++) {
            int a = nums.get(i);
            // If the number is even, no valid result exists
            if (a % 2 == 0) {
                res[i] = -1;
            } else {
                /*
                 * For odd numbers:
                 * - (a + 1) & (-(a + 1)) gives the lowest set bit of (a + 1)
                 * - Dividing by 2 adjusts the bit contribution
                 * - Subtracting it from 'a' yields the minimum valid value
                 */
                res[i] = a - (((a + 1) & (-(a + 1))) / 2);
            }
        }
        return res;
    }
}

Time Complexity :- O(n).
Space Complexity :- O(n).
