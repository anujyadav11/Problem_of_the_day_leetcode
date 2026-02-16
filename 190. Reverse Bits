/************************************** java **********************************/

optimal solution :- Extract the i-th bit from n and place the extracted bit at the mirrored position

class Solution {
    public int reverseBits(int n) {
        // Variable to store the reversed bits result
        int res = 0;
        // Iterate over all 32 bits of the integer
        for (int i = 0; i < 32; i++) {
            // Extract the i-th bit from n
            int bit = (n >> i) & 1;
            // Place the extracted bit at the mirrored position
            res = res | (bit << (31 - i));
        }
        // Return the integer formed after reversing bits
        return res;
    }
}

Time Complexity :- O(n)
Space Complexity :- O(1)
