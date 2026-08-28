/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds lexicographically smallest palindromic permutation greater than target using backtracking on the left half with a greater-flag for comparison pruning.
/* "The greater flag is the key optimisation — once a character exceeds the target's character at that position, all subsequent positions are unconstrained so we greedily pick the smallest available character. 
    Building only the left half and mirroring halves the search space. The palindrome validity check (at most one odd-frequency character) is a necessary prerequisite before any backtracking." */

class Solution {
    String result = "";
    char midChar = '$';
    int half = 0;
    boolean solve(StringBuilder curr, int[] count, String target, int i, boolean greater) {
        if (i == half) {
            // build full palindrome from left half
            String leftHalf = curr.toString();
            String rightHalf = new StringBuilder(leftHalf).reverse().toString();
            String candidate = leftHalf + (midChar != '$' ? midChar : "") + rightHalf;
            // only accept if strictly greater than target
            if (candidate.compareTo(target) > 0) {
                result = candidate;
                return true;
            }
            return false;
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (count[ch - 'a'] == 0) continue;
            // if not already greater, skip chars smaller than target's char at i
            if (!greater && ch < target.charAt(i)) continue;
            curr.append(ch);
            count[ch - 'a']--;
            // once we pick a char greater than target's, all further chars are free
            boolean isGreater = greater || ch > target.charAt(i);
            if (solve(curr, count, target, i + 1, isGreater))
                return true;
            // backtrack
            curr.deleteCharAt(curr.length() - 1);
            count[ch - 'a']++;
        }
        return false;
    }

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char ch : s.toCharArray())
            count[ch - 'a']++;
        // find odd-frequency character — at most one allowed for palindrome
        int oddCount = 0;
        for (int c = 0; c < 26; c++) {
            if (count[c] % 2 == 1) {
                oddCount++;
                midChar = (char) (c + 'a');
            }
        }
        // more than one odd-frequency character — palindrome impossible
        if (oddCount > 1) return "";
        // use half-counts for left half construction
        int[] halfCount = new int[26];
        for (int c = 0; c < 26; c++)
            halfCount[c] = count[c] / 2;
        half = n / 2;
        solve(new StringBuilder(), halfCount, target, 0, false);
        return result;
    }
}

// Time Complexity :- O(26^(n/2)) worst case — backtracking over half length; greater flag prunes significantly.
// Space Complexity :- O(n) — StringBuilder and recursion stack both scale with n/2.
