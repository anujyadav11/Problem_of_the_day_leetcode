/*********************************************** JAVA **************************************************/

// Optimal Solution - Count numbers whose digits remain valid after rotation and produce a different number.
/* "I iterate through each number and validate its digits. A number is good if it has no invalid digits and at least one digit changes after rotation." */

class Solution {
    public int rotatedDigits(int n) {
        int count = 0; // Count of valid "good" numbers
        // Check every number from 1 to n
        for (int i = 1; i <= n; i++) {
            // If number becomes valid and different after rotation
            if (isGood(i)) {
                count++;
            }
        }
        return count;
    }
    private boolean isGood(int num) {
        boolean isDifferent = false; // Tracks if number changes after rotation
        // Process each digit
        while (num > 0) {
            int digit = num % 10; // Extract last digit
            num /= 10;
            // Invalid digits (cannot be rotated)
            if (digit == 3 || digit == 4 || digit == 7) {
                return false;
            }
            // Valid digits that change after rotation
            if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                isDifferent = true;
            }
        }
        // Return true only if number is valid AND changes
        return isDifferent;
    }
}

// Time Complexity :- O(n * d).
// Space Complexity :- O(1).
