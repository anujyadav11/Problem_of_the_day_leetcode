/*********************************************** JAVA **************************************************/

// Optimal Solution - Use divide-and-conquer recursion based on mirror and invert properties of the constructed binary string.
                    // “Instead of building the string, I use the recursive structure of Sn to determine whether k lies in the left half, middle, or mirrored right half, reducing exponential construction to O(n).”

class Solution {
    public char findKthBit(int n, int k) {
        // Base case: S1 = "0"
        if (n == 1)
            return '0';
        // Length of Sn = 2^n - 1
        int length = (1 << n) - 1;
        // Middle position
        int mid = (length + 1) / 2;
        // If k is in the left half → same as previous string
        if (k < mid) {
            return findKthBit(n - 1, k);
        }
        // If k is exactly middle → always '1'
        else if (k == mid) {
            return '1';
        }
        // If k is in the right half
        else {
            // Mirror position in left half
            char ch = findKthBit(n - 1, length - k + 1);
            // Invert the mirrored bit
            return (ch == '0') ? '1' : '0';
        }
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n). recursion stack space.
