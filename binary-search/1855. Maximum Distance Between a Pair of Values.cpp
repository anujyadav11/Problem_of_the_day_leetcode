/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds maximum index distance (i,j) with nums1[i]≤nums2[j] using two pointers — advancing i on violation and j on valid pairs to maximize j−i.
/* "The greedy insight is never moving j backward — if nums1[i] <= nums2[j] holds, any smaller i would also hold (since nums1 is non-increasing), so we record and expand j. 
    When violated, incrementing i finds a smaller nums1 value that might satisfy the constraint at the current j. This gives O(m+n) vs O(mn) brute force." */

class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i = 0, j = 0;
        int res = 0;
        while (i < m && j < n) {
            if (nums1[i] > nums2[j]) {
                // constraint violated — move i forward to find smaller nums1 value
                i++;
            } else {
                // valid pair (i,j): nums1[i] <= nums2[j] — record distance
                res = Math.max(res, j - i);
                // expand j to try for a larger distance
                j++;
            }
        }
        // return maximum valid j - i distance found
        return res;
    }
}

// Time Complexity :- O(m + n) — each pointer advances at most m or n steps respectively.
// Space Complexity :- O(1).
