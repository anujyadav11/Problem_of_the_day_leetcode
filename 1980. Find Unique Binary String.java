/*********************************************** JAVA **************************************************/

// Optimal Solution - Generate a unique binary string using Cantor's diagonalisation by flipping the i-th bit of the i-th binary string.

class Solution {
    public String findDifferentBinaryString(String[] nums) {
        // Number of binary strings
        int n = nums.length;
        // StringBuilder to construct the resulting binary string
        StringBuilder result = new StringBuilder();
        // Iterate through each string index
        for (int i = 0; i < n; i++) {
            // Get the i-th character from the i-th string
            char ch = nums[i].charAt(i);
            // Flip the bit:
            // If '0' → append '1'
            // If '1' → append '0'
            result.append(ch == '0' ? '1' : '0');
        }
        // Convert StringBuilder to String and return
        return result.toString();
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
