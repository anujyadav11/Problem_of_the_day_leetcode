/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds the smallest common element in two sorted arrays using two-pointer technique, returning immediately on first match.
/* "Since both arrays are sorted, the first common element found by two pointers is always the smallest — no need to scan further. 
    This is O(n+m) vs O(n×m) brute force or O((n+m) log(n+m)) with a HashSet approach. Two pointers exploit sorted order — always mention this optimization when both input arrays are sorted." */

class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (nums1[i] == nums2[j])
                // found common element — return immediately (smallest since arrays are sorted)
                return nums1[i];
            else if (nums1[i] < nums2[j])
                // nums1 element is smaller — advance i to find potential match
                i++;
            else
                // nums2 element is smaller — advance j to find potential match
                j++;
        }
        // no common element found
        return -1;
    }
}

// Time Complexity :- O(n + m).
// Space Complexity :- O(1).
