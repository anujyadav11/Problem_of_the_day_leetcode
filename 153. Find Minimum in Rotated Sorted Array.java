/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds minimum in rotated sorted array using binary search by comparing mid to right boundary to determine which half contains the rotation point.
/* Always compare nums[mid] against nums[right] not nums[left] — comparing with left can be ambiguous when the array is unrotated. If nums[mid] > nums[right], the left half is sorted and minimum is right 
  — if nums[mid] <= nums[right], the right half is sorted and minimum is left including mid. This handles both rotated and unrotated arrays correctly." */

class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right])
                // minimum is in the right half
                left = mid + 1;
            else
                // minimum is in the left half including mid
                right = mid;
        }
        // left == right — minimum element found
        return nums[left];
    }
}

// Time Complexity :- O(log n).
// Space Complexity :- O(1).
