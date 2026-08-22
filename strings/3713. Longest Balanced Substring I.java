/*********************************************** JAVA **************************************************/

Optimal Solution - Brute-force solution that checks all substrings and validates equal character frequencies.
                   Generate all substrings and use a frequency array to verify if all present characters occur equally.

class Solution {
    public int longestBalanced(String s) {
        // Length of the string
        int n = s.length();
        // Variable to store maximum balanced substring length
        int maxLen = 0;
        // Try every possible starting index
        for (int i = 0; i < n; i++) {
            // Frequency array for 26 lowercase letters
            int[] freq = new int[26];
            // Extend substring from i to j
            for (int j = i; j < n; j++) {
                // Increase frequency of current character
                freq[s.charAt(j) - 'a']++;
                // Check if current substring is balanced
                if (checkBalanced(freq)) {
                    // Update maximum length if valid
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }
    // Helper function to check if all non-zero frequencies are equal
    public boolean checkBalanced(int[] freq) {
        int common = 0; // Stores expected frequency
        for (int i = 0; i < 26; i++) {
            // Skip characters not present
            if (freq[i] == 0) continue;
            // First non-zero frequency becomes reference
            if (common == 0) {
                common = freq[i];
            }
            // If any frequency differs, not balanced
            else if (freq[i] != common) {
                return false;
            }
        }
        // If all non-zero frequencies match, it's balanced
        return true;
    }
}

Time Complexity :- O(N^2).
Space Complexity :- O(1).
