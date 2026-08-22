/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts substrings containing all three characters using a sliding window, batch-counting valid extensions with n−right when all three are present.
/* "The key insight is count += (n - right) — once the window is valid, fixing the left boundary gives n - right valid substrings by extending right to any end position. 
    Shrinking left while valid collects all minimal valid windows efficiently, making it O(n) instead of O(n²) brute force." */

class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        // frequency array for 'a', 'b', 'c' only
        int[] freq = new int[3];
        int count = 0;
        // tracks how many distinct chars among a,b,c are present in the window
        int curr = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);
            freq[ch - 'a']++;
            // new distinct character entered the window
            if (freq[ch - 'a'] == 1) curr++;
            // window contains all 3 — every extension to the right is also valid
            while (curr == 3) {
                // all substrings from the current window to the end of the string are valid
                count += (n - right);
                // shrink window from left
                char c = s.charAt(left);
                freq[c - 'a']--;
                // lost a distinct character from the window
                if (freq[c - 'a'] == 0) curr--;
                left++;
            }
        }
        return count;
    }
}

// Time Complexity :- O(2n). - worst case complexity.
// Space Complexity :- O(1).
