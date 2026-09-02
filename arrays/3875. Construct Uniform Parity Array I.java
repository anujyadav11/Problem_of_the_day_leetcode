/*********************************************** JAVA **************************************************/

// Optimal Solution - Implemented uniformArray by checking the parity of array elements and returning the required boolean result.
/* “I iterate through the array and check whether each element is odd or even. Since both cases return true, any non-empty array produces true, while an empty array produces false.” */

class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        // Check the elements of the array
        for (int i = 0; i < n; i++) {
            // If the number is odd
            if (nums1[i] % 2 != 0) {
                return true;
            }
            // If the number is even
            else if (nums1[i] % 2 == 0) {
                return true;
            }
            // This condition is unreachable
            else {
                return true;
            }
        }
        return false;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).

class Solution {
    public boolean uniformArray(int[] nums1) {
        return nums1.length > 0;
    }
}

// Time Complexity :- O(1).
// Space Complexity :- O(1).
