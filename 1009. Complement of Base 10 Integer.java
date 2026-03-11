/*********************************************** JAVA **************************************************/

// Optimal Solution - Flip all bits of a number by XORing it with a mask of all 1s equal to its binary length.

class Solution {
    public int bitwiseComplement(int num) {
        // Convert number length to binary length
        int len = Integer.toBinaryString(num).length();
        // Create mask like 111 (same length as binary)
        int mask = (1 << len) - 1;
        // Flip bits using XOR
        return num ^ mask;
    }
}
// Time Complexity :- O(log n).
// Space Complexity :- O(1).
