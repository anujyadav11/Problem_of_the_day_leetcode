/*********************************************** JAVA **************************************************/

// Optimal Solution - Computes the mirror distance of a number as the absolute difference between it and its digit-reversed value.
/* "Always apply Math.abs when computing differences where direction isn't guaranteed — n - reverse(n) can be negative when the reversed number has a larger value. 
    The reversal itself is the standard rem = n%10; rev = rev*10 + rem; n /= 10 pattern — worth memorizing as it appears in many digit manipulation problems." */

class Solution {
    public int mirrorDistance(int n) {
        return Math.abs(n - reverse(n));
    }
    public int reverse(int n) {
        int rev = 0;
        // extract digits right to left and build reversed number
        while (n > 0) {
            int rem = n % 10;
            rev = rev * 10 + rem;
            n /= 10;
        }
        return rev;
    }
}

// Time Complexity :- O(log10(n)).
// Space Complexity :- O(1).
