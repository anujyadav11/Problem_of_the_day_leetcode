/*********************************************** JAVA **************************************************/

// Optimal Solution - Perform a stable three-way partition around the pivot using precomputed segment boundaries.
/* “I first count how many numbers belong to each partition (< pivot, = pivot, > pivot). Those counts determine where each section starts, 
    allowing me to place elements in one pass while preserving order.” */

class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        // Count elements less than, equal to, and greater than pivot
        int lessCount = 0;
        int equalCount = 0;
        int greaterCount = 0;
        for (int num : nums) {
            if (num < pivot) {
                lessCount++;
            }
            else if (num > pivot) {
                greaterCount++;
            }
            else {
                equalCount++;
            }
        }
        /*
         * Starting positions:
         * [ less elements ][ equal elements ][ greater elements ]
         */
        int lessIndex = 0;
        int equalIndex = lessCount;
        int greaterIndex = lessCount + equalCount;
        int[] result = new int[nums.length];
        // Place elements while preserving relative order
        for (int num : nums) {
            if (num < pivot) {
                result[lessIndex] = num;
                lessIndex++;
            }
            else if (num > pivot) {
                result[greaterIndex] = num;
                greaterIndex++;
            }
            else {
                result[equalIndex] = num;
                equalIndex++;
            }
        }
        return result;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
