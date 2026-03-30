/*********************************************** JAVA **************************************************/

// Optimal Solution - Checks if s1 can be transformed into s2 by verifying equal character frequencies at even and odd positions separately using two difference arrays.
/* "The key constraint is that swaps are position-parity restricted — even indices can only rearrange among themselves, odd among themselves. 
    This reduces the problem to two independent anagram checks: one for even positions, one for odd. A difference array per parity confirms both in a single O(n) pass." */

class Solution {
    public boolean checkStrings(String s1, String s2) {
        // strings of different lengths can never match
        if (s1.length() != s2.length())
            return false;
        // frequency difference arrays for even and odd positions separately
        int[] even = new int[26];
        int[] odd = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            if (i % 2 == 0) {
                // increment for s1 char, decrement for s2 char at even index
                even[s1.charAt(i) - 'a']++;
                even[s2.charAt(i) - 'a']--;
            } else {
                // increment for s1 char, decrement for s2 char at odd index
                odd[s1.charAt(i) - 'a']++;
                odd[s2.charAt(i) - 'a']--;
            }
        }
        // if all differences are zero, even/odd positions have same char frequencies
        for (int i = 0; i < 26; i++)
            if (even[i] != 0 || odd[i] != 0)
                return false;
        // s1 can be rearranged at even/odd positions to match s2
        return true;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
