/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts unique XOR triplets by computing the next power of 2 ≥ n using bit-filling, leveraging the mathematical property that all values 0 to 2^k-1 are achievable.
/* "The bit-filling trick m |= m >> k for k = 1,2,4,8,16 fills all bits below the highest set bit — giving 2^⌈log₂n⌉ - 1. Adding 1 gives the next power of 2. 
    The 3/(n+1) shift trick is clever but obscure — in interviews, prefer explicit if (n < 3) return n; return Integer.highestOneBit(n) << 1 for clarity." */

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n < 3) return n;
        // find next power of 2 >= n using bit filling trick
        int m = n;
        m |= m >> 1;
        m |= m >> 2;
        m |= m >> 4;
        m |= m >> 8;
        m |= m >> 16;
        // m+1 is next power of 2 >= n
        // when n >= 3: shift right by 0 to get m+1
        // trick: 3/(n+1) = 0 for n >= 2, so (m+1) >> 0 = m+1
        return (m + 1) >> (3 / (n + 1));
    }
}

// Time Complexity :- O(1).
// Space Complexity :- O(1).
