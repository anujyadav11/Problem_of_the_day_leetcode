/*********************************************** JAVA **************************************************/

// Brute Force Solution - Find the shortest substring containing exactly k ones and return the lexicographically smallest valid substring.
/* “I try substring lengths from smallest to largest. For each length, I examine every substring and count its number of ones. If it contains exactly k ones, 
    I keep the lexicographically smallest candidate. As soon as I find a valid substring for a particular length, I return it because we’re processing lengths in increasing order.” */

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String res = "";
        // Try every possible substring length
        for (int len = 1; len <= n; len++) {
            // Check all substrings of this length
            for (int start = 0; start <= n - len; start++) {
                String temp = s.substring(start, start + len);
                // Count number of 1s in the substring
                int count1 = 0;

                for (char ch : temp.toCharArray()) {
                    if (ch == '1') {
                        count1++;
                    }
                }
                // Found a beautiful substring
                if (count1 == k) {
                    // Keep lexicographically smaller substring
                    if (res.length() == 0 || temp.compareTo(res) < 0) {
                        res = temp;
                    }
                }
            }
            // First valid length is the shortest length
            if (!res.isEmpty()) {
                return res;
            }
        }
        // No substring contains exactly k ones
        return "";
    }
}

// Time Complexity :- O(n^2).
// Space Complexity :- O(n).
