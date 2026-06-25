/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts subarrays where target appears more than half the time using brute force with multiplication-based majority check to avoid integer division.
/* Always use count * 2 > length instead of count > length / 2 for majority element checks — integer division truncates and can give wrong results for odd lengths. 
   count * 2 > length is mathematically equivalent to count > length/2.0 but uses only integer arithmetic. This is a subtle but common bug in majority element problems." */

class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i; j < n; j++) {
                // count occurrences of target in current subarray
                if (nums[j] == target) count++;
                // target is majority if it appears more than half the time
                if (count * 2 > j - i + 1)
                    ans++;
            }
        }
        return ans;
    }
}

// Time Complexity :- O(n^2).
// Space Complexity :- O(1).
