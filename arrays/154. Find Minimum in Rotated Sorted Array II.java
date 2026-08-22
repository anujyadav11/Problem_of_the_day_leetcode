/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds minimum in rotated array with duplicates by skipping equal boundary elements and tracking minimum index explicitly during binary search.
/* "Duplicates are the hard part — when nums[mid] == nums[r] we can't tell which half contains the minimum. The duplicate-skipping loops handle this by shrinking boundaries until they differ. 
    This is the same approach as LC 81 (Search in Rotated Array II) — worst case degrades to O(n) for all-duplicate arrays like [2,2,2,2,1,2]." */

class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        // track index of minimum found so far
        int resultIdx = 0;
        while (l <= r) {
            // skip duplicate elements from left
            while (l < r && nums[l] == nums[l + 1]) l++;
            // skip duplicate elements from right
            while (l < r && nums[r] == nums[r - 1]) r--;
            int mid = l + (r - l) / 2;
            // update minimum index if mid is smaller
            if (nums[mid] < nums[resultIdx])
                resultIdx = mid;
            if (nums[mid] > nums[r])
                // left half is sorted — minimum in right half
                l = mid + 1;
            else
                // minimum in left half including mid
                r = mid - 1;
        }
        return nums[resultIdx];
    }
}

// Time Complexity :- O(log n). worst case due to duplicate skipping O(n).
// Space Complexity :- O(1).
