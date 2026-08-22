/*********************************************** JAVA **************************************************/

Optimal Solution - For each number, return the minimum value whose OR equals the original number using bit manipulation, or -1 if impossible.

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = findMinOr(nums.get(i));
        }
        return ans;
    }
    private int findMinOr(int num) {
        // If number is even, no valid answer exists
        if ((num & 1) == 0)
            return -1;
        int i = 0;
        // Find the first 0-bit starting from LSB
        while ((num & (1 << i)) != 0) {
            i++;
        }
        // Flip the bit just before the first zero bit
        return num ^ (1 << (i - 1));
    }
}

Time Complexity :- O(n · log M).
Space Complexity :- O(n).
