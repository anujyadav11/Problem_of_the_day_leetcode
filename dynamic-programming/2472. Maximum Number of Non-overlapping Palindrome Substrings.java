/*********************************************** JAVA **************************************************/

// Optimal Solution - Uses O(N²) palindrome DP combined with prefix DP to maximise the number of non-overlapping palindromic substrings of length at least k.
/* “I solve this in two DP phases. First, I precompute whether every substring is a palindrome using the recurrence based on its two endpoints and the inner substring. 
    Then I use prefix DP, where t[len] represents the maximum number of valid non-overlapping palindromes in the first len characters. For each ending position, 
    I either skip it or select any palindrome ending there and combine it with t[i], the optimal result before that palindrome.” */

class Solution {
    // isPalindrome[i][j] = true if s[i...j] is a palindrome
    boolean[][] isPalindrome;
    // t[i] = maximum number of valid non-overlapping
    // palindromes using the first i characters
    int[] t;
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        // STEP 1: Precompute whether every substring is
        // a palindrome.
        isPalindrome = new boolean[n][n];
        // L = length of current substring
        for (int L = 1; L <= n; L++) {
            // i = starting index
            for (int i = 0; i + L <= n; i++) {
                // j = ending index
                int j = i + L - 1;
                // Length 1 → always a palindrome
                if (i == j) {
                    isPalindrome[i][i] = true;
                // Length 2 → both characters must match
                } else if (i + 1 == j) {
                    isPalindrome[i][j] =
                        (s.charAt(i) == s.charAt(j));
                // Length >= 3:
                // outer characters must match AND
                // inner substring must be a palindrome
                } else {
                    isPalindrome[i][j] =
                        (s.charAt(i) == s.charAt(j))
                        && isPalindrome[i + 1][j - 1];
                }
            }
        }
        // STEP 2: Prefix DP
        // t[len] = maximum number of valid palindromes
        // that can be selected from s[0...len-1]
        t = new int[n + 1];
        // For strings shorter than k, no valid palindrome
        // can be selected.
        for (int len = 0; len < k; len++) {
            t[len] = 0;
        }
        // Consider every prefix of length >= k
        for (int len = k; len <= n; len++) {
            // Option 1:
            // Don't select a palindrome ending at len - 1.
            int result = t[len - 1];
            // Current ending index
            int j = len - 1;
            // Try every possible starting index
            // for a palindrome ending at j.
            for (int i = 0; j - i + 1 >= k; i++) {
                // If s[i...j] is a palindrome,
                // we can select it.
                if (isPalindrome[i][j]) {
                    // t[i] represents the best answer
                    // before this palindrome.
                    //
                    // +1 = select current palindrome.
                    result = Math.max(
                        result,
                        1 + t[i]
                    );
                }
            }
            // Store best answer for this prefix
            t[len] = result;
        }
        return t[n];
    }
}

// Time Complexity :- O(n^2).
// Space Complexity :- O(n^2).
