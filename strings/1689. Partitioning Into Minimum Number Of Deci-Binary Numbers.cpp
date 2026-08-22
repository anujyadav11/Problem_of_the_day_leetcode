/*********************************************** JAVA **************************************************/

// Optimal Solution - The minimum number of deci-binary numbers required equals the maximum digit in the string.
                      // “Each deci-binary number contributes at most 1 to any digit position, so the answer is simply the maximum digit present in the number.”

class Solution {
    public int minPartitions(String n) {
        int res = 0;  // Stores maximum digit
        // Traverse all characters
        for (int i = 0; i < n.length(); i++) {
            // Convert character to digit
            int digit = n.charAt(i) - '0';
            // Update maximum digit
            res = Math.max(res, digit);
        }
        return res;  // Return maximum digit
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
