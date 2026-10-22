/*********************************************** JAVA **************************************************/

// Optimal Solution - Used circular-array traversal and modular arithmetic to validate consecutive increasing and decreasing sequences in O(n) time and O(1) space.
/* “I locate zero and use modular arithmetic to traverse the array circularly in both directions. I verify whether the values form consecutive increasing or decreasing sequences, 
    then calculate the minimum operations according to the position of zero.” */

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int pos = 0;
        // Find the position of zero.
        while (nums[pos] != 0) {
            pos++;
        }
        // Assume the circular sequence is increasing or decreasing.
        boolean inc = true;
        boolean dec = true;
        // Check consecutive values in both circular directions.
        for (int i = 0; i < n - 1; i++) {
            // Check increasing order: 0, 1, 2, 3, ...
            if (nums[(pos + i) % n] + 1
                    != nums[(pos + i + 1) % n]) {
                inc = false;
            }
            // Check decreasing order by traversing backward.
            if (nums[(pos - i + n) % n] + 1
                    != nums[(pos - i - 1 + n) % n]) {
                dec = false;
            }
        }
        // Calculate the minimum operations for increasing order.
        if (inc) {
            return Math.min(pos, n - pos + 2);
        }
        // Calculate the minimum operations for decreasing order.
        if (dec) {
            return Math.min(n - pos, pos + 2);
        }
        // Neither circular sequence is valid.
        return -1;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
