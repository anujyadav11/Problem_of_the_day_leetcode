/*********************************************** JAVA **************************************************/

// Optimal Solution - Use XOR properties to determine whether the entire array or one fewer element gives the longest subsequence with non-zero XOR.

/*“I first check whether all elements are zero. If they are, no subsequence can have a non-zero XOR. Otherwise, 
I calculate the XOR of the entire array. If it is non-zero, the whole array is the answer. If it is zero, removing any non-zero element changes the XOR to that element’s value, making it non-zero, so the answer is n - 1.”*/

class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xor = 0;
        boolean allZero = true;
        // XOR of all elements
        for (int num : nums) {
            xor ^= num;
            if (num != 0) {
                allZero = false;
            }
        }
        // All zeros cannot form a non-zero XOR subsequence
        if (allZero) {
            return 0;
        }
        // If total XOR is non-zero, use the entire array.
        // Otherwise, remove one non-zero element.
        return xor != 0 ? n : n - 1;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
