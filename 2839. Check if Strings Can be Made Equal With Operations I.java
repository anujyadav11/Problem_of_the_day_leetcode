/*********************************************** JAVA **************************************************/

// Optimal Solution - Compare frequency of characters separately at even and odd indices to determine if strings can be made equal.
/* “Since swaps are restricted to same parity indices, I compare frequency of characters separately for even and odd positions.” */

class Solution {
    public boolean canBeEqual(String s1, String s2) {
        // If lengths differ → cannot be equal
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] even = new int[26]; // Frequency for even indices
        int[] odd = new int[26]; // Frequency for odd indices
        int n = s1.length();
        // Count characters separately for even and odd indices
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                even[s1.charAt(i) - 'a']++;
                even[s2.charAt(i) - 'a']--;
            } else {
                odd[s1.charAt(i) - 'a']++;
                odd[s2.charAt(i) - 'a']--;
            }
        }
        // Check if all frequencies match
        for (int i = 0; i < 26; i++) {
            if (even[i] != 0 || odd[i] != 0) {
                return false;
            }
        }
        return true;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
