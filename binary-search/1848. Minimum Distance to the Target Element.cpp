/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds minimum distance from start to any target occurrence using a linear scan with absolute index difference tracking .
/*  "Always use && not & for boolean conditions — & is bitwise AND which evaluates both sides regardless, eliminating short-circuit safety. 
      The faulty early termination was trying to optimize by stopping when current index distance exceeds best — but this incorrectly assumes targets only appear close to start, which is not guaranteed." */

class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] == target)
                // update minimum distance to target from start
                res = Math.min(res, Math.abs(i - start));
        }
        // return minimum distance found
        return res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
