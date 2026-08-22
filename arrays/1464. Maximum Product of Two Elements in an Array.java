/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds maximum product of two largest decremented values by tracking top two elements in a single O(n) pass.
/* "Tracking top-2 in one pass is a classic pattern — when a new max is found, the old max becomes second. Otherwise update second directly. 
    This avoids sorting O(n log n) for just two values. Always initialize both to 0 here since the constraint guarantees values ≥ 1, making 0 a safe sentinel below any valid value." */

class Solution {
    public int maxProduct(int[] nums) {
        int largest = 0;
        int secondLargest = 0;
        for (int num : nums) {
            if (num > largest) {
                // new largest found — demote current largest to second
                secondLargest = largest;
                largest = num;
            } else {
                // update second largest if current beats it
                secondLargest = Math.max(secondLargest, num);
            }
        }
        return (largest - 1) * (secondLargest - 1);
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
