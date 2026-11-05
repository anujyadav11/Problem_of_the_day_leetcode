/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved Find the Distance Value Between Two Arrays using brute-force pairwise comparison with early termination in O(n*m) time and O(1) auxiliary space.
/* "I iterate through every element in the first array and check its absolute difference against every element in the second array. 
    If any difference is less than or equal to d, that element is invalid. Otherwise, I count it." */

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;
        // Check every element in arr1.
        for (int i = 0; i < arr1.length; i++) {
            boolean valid = true;
            // Compare the current element with every element in arr2.
            for (int j = 0; j < arr2.length; j++) {
                // If any element is within distance d,
                // the current arr1 element is invalid.
                if (Math.abs(arr1[i] - arr2[j]) <= d) {
                    valid = false;
                    break;
                }
            }
            // Count the element only if no close element was found.
            if (valid) {
                count++;
            }
        }
        return count;
    }
}

// Time Complexity :- O(m * n).
// Space Complexity :- O(1).
