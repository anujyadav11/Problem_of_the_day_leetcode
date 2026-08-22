/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximizes final array element by sorting, fixing first to 1, and capping each element to at most one more than its predecessor.
/* "Math.abs is the wrong operator here — it would also reduce elements that are equal to previous (difference 0, not > 1) and elements slightly less. 
    We only need to cap downward when arr[i] > arr[i-1] + 1. The last element is always the answer after adjustment — no need to track max separately since the array is sorted throughout." */

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        // sort to process elements in ascending order
        Arrays.sort(arr);
        // first element must always be 1
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            // each element can differ by at most 1 from previous
            if (arr[i] > arr[i - 1] + 1)
                arr[i] = arr[i - 1] + 1;
            // if arr[i] <= arr[i-1] + 1 already — keep as is
        }
        // maximum is always the last element after sorting and adjusting
        return arr[n - 1];
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(1).
