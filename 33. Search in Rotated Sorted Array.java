/*********************************************** JAVA **************************************************/

// Optimal Solution - Use modified binary search by identifying the sorted half in a rotated sorted array.
/* “Even though the array is rotated, one half is always sorted. I identify the sorted half and check whether the target belongs there, allowing binary search in O(log n).” */

class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        // Binary search on rotated sorted array
        while (low <= high) {
            // Middle index
            int mid = (low + high) / 2;
            // Target found
            if (nums[mid] == target)
                return mid;
            /* Check which half is sorted */
            // Left half is sorted
            if (nums[low] <= nums[mid]) {
                /* Check if target lies inside the sorted left half */
                if (target >= nums[low] && target <= nums[mid]) {
                    // Search left half
                    high = mid - 1;
                } else {
                    // Search right half
                    low = mid + 1;
                }
            }
            // Right half is sorted
            else {
                /* Check if target lies inside the sorted right half */
                if (target >= nums[mid] && target <= nums[high]) {
                    // Search right half
                    low = mid + 1;
                } else {
                    // Search left half
                    high = mid - 1;
                }
            }
        }
        // Target not found
        return -1;
    }
}

// Time Complexity :- O(log n).
// Space Complexity :- O(1).
