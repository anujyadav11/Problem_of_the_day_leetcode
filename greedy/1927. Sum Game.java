/*********************************************** JAVA **************************************************/

// Optimal Solution - Determine the Sum Game winner by comparing digit sums and question-mark contributions across both halves.
/* “I divide the string into two halves and track the known digit sum and number of question marks in each half. If the total number of question marks is odd, Alice gets an extra turn and wins. 
    Otherwise, I compare the weighted contribution of both halves. If they are equal, Bob can balance the final sums; otherwise, Alice can force a win.” */

class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        // Store the sum of known digits in the left half
        int leftKnownSum = 0;
        // Count '?' characters in the left half
        int leftQnMarkCount = 0;
        // Store the sum of known digits in the right half
        int rightKnownSum = 0;
        // Count '?' characters in the right half
        int rightQnMarkCount = 0;
        // Process both halves of the string
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                // Count '?' based on which half they belong to
                if (i < n / 2) {
                    leftQnMarkCount++;
                } else {
                    rightQnMarkCount++;
                }
            } else {
                // Add known digit to the corresponding half
                if (i < n / 2) {
                    leftKnownSum += c - '0';
                } else {
                    rightKnownSum += c - '0';
                }
            }
        }
        // Total number of '?' across both halves
        int totalQnMarks = leftQnMarkCount + rightQnMarkCount;
        // If '?' count is odd, Alice gets an extra move and wins
        if (totalQnMarks % 2 == 1) {
            return true;
        }
        // Calculate the maximum possible weighted contribution
        // from each half
        int LEFT = 2 * leftKnownSum + 9 * leftQnMarkCount;
        int RIGHT = 2 * rightKnownSum + 9 * rightQnMarkCount;
        // Equal values mean Bob can force the sums to be equal
        if (LEFT == RIGHT) {
            return false;
        }
        // Otherwise, Alice can force a difference
        return true;
    }
}

// Time Complexity :- O(n). because we are iterating the array only one time 
// Space Complexity :- O(1).
