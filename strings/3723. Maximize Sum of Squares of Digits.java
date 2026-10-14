/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximize the sum of squared digits using greedy digit construction in O(num) time and space.
/* “To maximize the sum of squared digits, I greedily allocate the digit sum to as many 9s as possible, then append the remainder and pad with zeros to reach the required length.” */

class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // A num-digit string can have a maximum digit sum of 9 * num.
        if (sum > num * 9)
            return "";
        // Use as many 9s as possible to maximize the sum of squares.
        int nines = sum / 9;
        int remain = sum % 9;
        StringBuilder sb = new StringBuilder();
        // Append all possible 9s.
        for (int i = 0; i < nines; i++) {
            sb.append('9');
        }
        // Append the remaining digit, if any.
        if (remain > 0) {
            sb.append((char) (remain + '0'));
        }
        // Pad with zeros to reach the required length.
        while (sb.length() < num) {
            sb.append('0');
        }
        return sb.toString();
    }
}

// Time Complexity :- O(num).
// Space Complexity :- O(num).
