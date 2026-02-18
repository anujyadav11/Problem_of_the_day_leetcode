/*********************************************** JAVA **************************************************/

Optimal Solution - Checks whether binary representation of a number has alternating bits.
                   After converting to binary, simply ensure no two adjacent bits are equal.

class Solution {
    public boolean hasAlternatingBits(int n) {
        // Convert integer to binary string representation
        String b = Integer.toBinaryString(n);
        // Traverse binary string and check adjacent bits
        for (int i = 0; i < b.length() - 1; i++) {
            // If two consecutive bits are same → not alternating
            if (b.charAt(i) == b.charAt(i + 1)) {
                return false;
            }
        }
        // If no equal adjacent bits found → alternating
        return true;
    }
}

Time Complexity :- O(log n).
Space Complexity :- O(log n).
