/*********************************************** JAVA **************************************************/

// Optimal Solution - Find the longest substring in which each character appears at most twice using a sliding window and a frequency array.
/* “I maintain a sliding window and track character frequencies using an array. When the current character appears more than twice, 
    I move the left pointer until the window becomes valid. I then update the maximum window length.” */

class Solution {
    public int maximumLengthSubstring(String s) {
        // Frequency of each character in the window
        int[] freq = new int[26];
        int left = 0;
        int maxLen = 0;
        for (int right = 0; right < s.length(); right++) {
            // Add current character
            freq[s.charAt(right) - 'a']++;
            // Shrink until every character appears at most twice
            while (freq[s.charAt(right) - 'a'] > 2) {
                freq[s.charAt(left) - 'a']--;
                left++;
            }
            // Update longest valid window
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}

// Time Complexity :- O(n) — each character enters and leaves the window at most once.
// Space Complexity :- O(26) ~ O(1) — only 26 character frequencies..
